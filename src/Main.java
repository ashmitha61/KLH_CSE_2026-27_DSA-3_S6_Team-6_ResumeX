import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println();
        System.out.println("============================================================");
        System.out.println("           RESUME SEARCH & CANDIDATE SCREENING");
        System.out.println("============================================================");
        System.out.println();

        /*
         * Load the large resume dataset internally.
         * The recruiter does not need to know
         * where the resumes are stored.
         */
        Resume[] resumes =
                CSVResumeLoader.loadResumes(
                        "dataset/resume_dataset_2.csv"
                );

        if (resumes.length == 0) {

            System.out.println(
                    "Unable to load resume data."
            );

            return;
        }

        Scanner scanner =
                new Scanner(System.in);

        System.out.print(
                "Enter Required Skill: "
        );

        String query =
                scanner.nextLine()
                .trim()
                .toLowerCase();

        if (query.length() == 0) {

            System.out.println();
            System.out.println(
                    "Skill cannot be empty."
            );

            scanner.close();
            return;
        }

        System.out.println();
        System.out.println(
                "Searching resumes..."
        );

        System.out.println(
                "Please wait..."
        );

        SearchEngine searchEngine =
                new SearchEngine(resumes);

        /*
         * First perform exact search.
         */
        Candidate[] results =
                searchEngine.searchExact(query);

        boolean fuzzySearchUsed = false;

        /*
         * If no exact candidate is found,
         * automatically perform fuzzy search.
         */
        if (results.length == 0) {

            results =
                    searchEngine.searchFuzzy(query);

            fuzzySearchUsed = true;
        }

        displayResults(
                results,
                query,
                fuzzySearchUsed
        );

        scanner.close();
    }


    /*
     * Display recruiter-friendly search results.
     */
    public static void displayResults(
            Candidate[] results,
            String query,
            boolean fuzzySearchUsed) {

        System.out.println();
        System.out.println(
                "============================================================"
        );

        System.out.println(
                "                  SEARCH RESULTS"
        );

        System.out.println(
                "============================================================"
        );

        System.out.println();

        System.out.println(
                "Required Skill : " + query
        );

        System.out.println(
                "Candidates Found : " + results.length
        );

        if (results.length == 0) {

            System.out.println();
            System.out.println(
                    "No matching candidates found."
            );

            System.out.println();
            System.out.println(
                    "Try another skill."
            );

            System.out.println(
                    "============================================================"
            );

            return;
        }


        /*
         * Display each candidate.
         */
        for (int i = 0;
             i < results.length;
             i++) {

            Candidate candidate =
                    results[i];

            Resume resume =
                    candidate.getResume();

            System.out.println();

            System.out.println(
                    "------------------------------------------------------------"
            );

            System.out.println(
                    "Candidate " + (i + 1)
            );

            System.out.println(
                    "------------------------------------------------------------"
            );

            System.out.println();

            System.out.println(
                    "Name        : "
                    + resume.getName()
            );

            System.out.println(
                    "Candidate ID: "
                    + resume.getCandidateId()
            );

            System.out.println(
                    "Email       : "
                    + resume.getEmail()
            );

            System.out.println(
                    "Phone       : "
                    + cleanPhone(resume.getPhone())
            );

            System.out.println();

            System.out.println(
                    "Education   : "
                    + resume.getEducation()
            );

            System.out.println(
                    "Experience  : "
                    + resume.getExperience()
            );

            System.out.println();

            System.out.println(
                    "Skills Matched:"
            );

            displayMatchedSkills(
                    resume.getSkills(),
                    query
            );

            System.out.println();

            if (fuzzySearchUsed) {

                System.out.println(
                        "Match Type  : Fuzzy Match"
                );

            } else {

                System.out.println(
                        "Match Type  : Exact Match"
                );
            }

            System.out.println(
                    "Similarity  : "
                    + candidate.getSimilarityScore()
                    + "%"
            );

            System.out.println(
                    "Match Score : "
                    + candidate.getFinalScore()
            );
        }


        System.out.println();

        System.out.println(
                "============================================================"
        );

        System.out.println(
                "              SEARCH COMPLETED SUCCESSFULLY"
        );

        System.out.println(
                "============================================================"
        );
    }


    /*
     * Display only the skills related to the
     * recruiter's search.
     */
    public static void displayMatchedSkills(
            String skillsText,
            String query) {

        if (skillsText == null
                || skillsText.trim().length() == 0) {

            System.out.println(
                    "- No skill information available"
            );

            return;
        }

        String[] skills =
                skillsText.split(",");

        boolean found = false;

        for (int i = 0;
             i < skills.length;
             i++) {

            String skill =
                    skills[i].trim();

            if (skill.length() == 0) {
                continue;
            }

            String lowercaseSkill =
                    skill.toLowerCase();

            int distance =
                    EditDistance.calculate(
                            query,
                            lowercaseSkill
                    );

            /*
             * Exact or fuzzy skill match.
             */
            if (lowercaseSkill.contains(query)
                    || query.contains(lowercaseSkill)
                    || distance <= 2) {

                System.out.println(
                        "- " + skill
                );

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "- Similar skill found"
            );
        }
    }


    /*
     * Removes .0 from phone numbers such as:
     *
     * 8371518054.0
     *
     * and displays:
     *
     * 8371518054
     */
    public static String cleanPhone(
            String phone) {

        if (phone == null) {
            return "";
        }

        phone = phone.trim();

        if (phone.endsWith(".0")) {

            phone =
                    phone.substring(
                            0,
                            phone.length() - 2
                    );
        }

        return phone;
    }
}