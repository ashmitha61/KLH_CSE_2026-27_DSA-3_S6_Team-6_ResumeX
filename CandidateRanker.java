import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class CandidateRanker {
    public static final double PATTERN_WEIGHT = 0.40;
    public static final double FUZZY_WEIGHT = 0.20;
    public static final double SIMILARITY_WEIGHT = 0.40;

    /**
     * Final Score = 40% Pattern Matching + 20% Fuzzy Matching + 40% Similarity Score
     */
    public void calculateFinalScores(List<Resume> resumes, List<String> requiredSkills) {
        FuzzySearch fuzzySearch = new FuzzySearch();

        for (Resume resume : resumes) {
            double fuzzyScore = calculateFuzzyScore(resume, requiredSkills, fuzzySearch);
            resume.setFuzzyMatchScore(fuzzyScore);

            double finalScore = (PATTERN_WEIGHT * resume.getPatternMatchScore())
                    + (FUZZY_WEIGHT * fuzzyScore)
                    + (SIMILARITY_WEIGHT * resume.getSimilarityScore());

            resume.setFinalScore(finalScore);
        }
    }

    public List<Resume> sortCandidatesByFinalScore(List<Resume> resumes) {
        List<Resume> ranked = new ArrayList<>(resumes);
        ranked.sort(Comparator.comparingDouble(Resume::getFinalScore).reversed());
        return ranked;
    }

    private double calculateFuzzyScore(Resume resume, List<String> requiredSkills, FuzzySearch fuzzySearch) {
        if (requiredSkills == null || requiredSkills.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;
        for (String skill : requiredSkills) {
            String normalizedSkill = skill.trim();
            if (normalizedSkill.isEmpty()) {
                continue;
            }

            double highestMatch = 0.0;
            List<String> candidateTerms = fuzzySearch.extractCandidateTerms(resume.getResumeText());
            if (candidateTerms.isEmpty()) {
                continue;
            }

            for (String term : candidateTerms) {
                double score = fuzzySearch.calculateSimilarityPercentage(normalizedSkill, term);
                if (score > highestMatch) {
                    highestMatch = score;
                }
            }

            total += highestMatch;
        }

        return requiredSkills.size() == 0 ? 0.0 : total / requiredSkills.size();
    }
}
