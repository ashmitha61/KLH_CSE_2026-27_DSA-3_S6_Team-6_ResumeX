import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==================================================");
        System.out.println("       RESUME SEARCH AND CANDIDATE SCREENING");
        System.out.println("==================================================");

        String jobTitle = readNonEmptyInput(scanner, "Enter Job Title:");
        System.out.println();
        String skillsInput = readNonEmptyInput(scanner, "Enter Required Skills:");

        System.out.println();
        System.out.println("==================================================");
        System.out.println("PROCESSING RESUMES...");
        System.out.println("==================================================");

        ResumeReader resumeReader = new ResumeReader();
        String resumeFolder = "resumes";
        List<Resume> resumes = resumeReader.readResumesFromFolder(resumeFolder);

        if (resumes.isEmpty()) {
            System.out.println("No resumes were loaded. Please check the 'resumes' folder.");
            return;
        }

        String[] skillArray = skillsInput.split(",");
        List<String> requiredSkills = new ArrayList<>();
        for (String skill : skillArray) {
            String trimmed = skill.trim();
            if (!trimmed.isEmpty()) {
                requiredSkills.add(trimmed);
            }
        }

        PatternMatcher patternMatcher = new PatternMatcher();
        SimilarityCalculator similarityCalculator = new SimilarityCalculator();
        CandidateRanker candidateRanker = new CandidateRanker();

        for (Resume resume : resumes) {
            patternMatcher.evaluateRequiredSkills(resume, requiredSkills);
            similarityCalculator.calculateSimilarity(resume, skillsInput);
        }

        candidateRanker.calculateFinalScores(resumes, requiredSkills);
        List<Resume> rankedResumes = candidateRanker.sortCandidatesByFinalScore(resumes);

        for (Resume resume : rankedResumes) {
            displayCandidateSummary(resume, requiredSkills);
        }

        System.out.println();
        System.out.println("==================================================");
        System.out.println("             CANDIDATE RANKING");
        System.out.println("==================================================");
        System.out.println("Rank  Candidate        Final Score");
        System.out.println("--------------------------------------------------");

        for (int i = 0; i < rankedResumes.size(); i++) {
            Resume resume = rankedResumes.get(i);
            System.out.printf("%-4d %-17s %.2f%%%n", (i + 1), resume.getName(), resume.getFinalScore());
        }

        System.out.println("==================================================");
        if (!rankedResumes.isEmpty()) {
            System.out.println("Most relevant candidate: " + rankedResumes.get(0).getName());
        }

        System.out.println();
        if (scanner.hasNextLine()) {
            System.out.print("Enter search keyword: ");
            String searchKeyword = scanner.nextLine().trim();
            if (!searchKeyword.isEmpty()) {
                searchCandidatesByKeyword(rankedResumes, searchKeyword);
            }
        } else {
            System.out.println("No search keyword provided. Skipping keyword search.");
        }

        scanner.close();
    }

    private static String readNonEmptyInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            String value = scanner.nextLine();
            if (value != null && !value.trim().isEmpty()) {
                return value.trim();
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    private static void displayCandidateSummary(Resume resume, List<String> requiredSkills) {
        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("Candidate: " + resume.getName());
        System.out.println("----------------------------------------------");
        System.out.println("Matched Skills:");
        if (resume.getMatchedSkills().isEmpty()) {
            System.out.println("None");
        } else {
            for (String skill : resume.getMatchedSkills()) {
                System.out.println(skill);
            }
        }

        System.out.println("Missing Skills:");
        if (resume.getMissingSkills().isEmpty()) {
            System.out.println("None");
        } else {
            for (String skill : resume.getMissingSkills()) {
                System.out.println(skill);
            }
        }

        System.out.printf("Pattern Matching Score: %.2f%%%n", resume.getPatternMatchScore());
        System.out.printf("Fuzzy Matching Score: %.2f%%%n", resume.getFuzzyMatchScore());
        System.out.printf("Cosine Similarity Score: %.2f%%%n", resume.getSimilarityScore());
        System.out.printf("Final Score: %.2f%%%n", resume.getFinalScore());
    }

    private static void searchCandidatesByKeyword(List<Resume> resumes, String keyword) {
        FuzzySearch fuzzySearch = new FuzzySearch();
        List<String> matches = new ArrayList<>();

        for (Resume resume : resumes) {
            String resumeText = resume.getResumeText().toLowerCase(Locale.ROOT);
            String lowerKeyword = keyword.toLowerCase(Locale.ROOT);

            if (resumeText.contains(lowerKeyword) || fuzzySearch.hasApproximateSkillMatch(lowerKeyword, resume.getResumeText(), 70.0)) {
                matches.add(resume.getName());
            }
        }

        if (matches.isEmpty()) {
            System.out.println("No candidates found for keyword: " + keyword);
        } else {
            System.out.println("Candidates containing '" + keyword + "':");
            for (int i = 0; i < matches.size(); i++) {
                System.out.println((i + 1) + ". " + matches.get(i));
            }
        }
    }
}
