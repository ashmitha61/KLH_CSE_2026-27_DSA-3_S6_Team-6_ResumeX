
public class SearchEngine {

    private Resume[] resumes;

    public SearchEngine(Resume[] resumes) {
        this.resumes = resumes;
    }

    public Candidate[] search(
            String query) {

        Candidate[] exact =
                searchExact(query);

        if (exact.length > 0) {
            return exact;
        }

        return searchFuzzy(query);
    }

    public Candidate[] searchExact(
            String query) {

        if (query == null) {
            return new Candidate[0];
        }

        query =
                query.trim().toLowerCase();

        if (query.length() == 0) {
            return new Candidate[0];
        }

        Candidate[] temporary =
                new Candidate[resumes.length];

        int resultCount = 0;

        for (int i = 0;
             i < resumes.length;
             i++) {

            Resume resume =
                    resumes[i];

            String skills =
                    resume.getSkills();

            if (skills == null) {
                continue;
            }

            skills =
                    skills.toLowerCase();

            int kmpMatches =
                    KMP.search(
                            skills,
                            query
                    );

            if (kmpMatches > 0) {

                Candidate candidate =
                        new Candidate(resume);

                int rabinMatches =
                        RabinKarp.search(
                                skills,
                                query
                        );

                int zMatches =
                        ZAlgorithm.search(
                                skills,
                                query
                        );

                int exactMatches =
                        Math.max(
                                kmpMatches,
                                Math.max(
                                        rabinMatches,
                                        zMatches
                                )
                        );

                candidate.setExactMatches(
                        exactMatches
                );

                candidate.setFuzzyMatches(0);

                double similarity =
                        Similarity.bestSkillSimilarity(
                                query,
                                resume.getSkills()
                        );

                candidate.setSimilarityScore(
                        Math.round(
                                similarity * 100.0
                        ) / 100.0
                );

                CandidateScorer.scoreCandidate(
                        candidate
                );

                temporary[resultCount++] =
                        candidate;
            }
        }

        return buildAndRank(
                temporary,
                resultCount
        );
    }

    public Candidate[] searchFuzzy(
            String query) {

        if (query == null) {
            return new Candidate[0];
        }

        query =
                query.trim().toLowerCase();

        if (query.length() == 0) {
            return new Candidate[0];
        }

        Candidate[] temporary =
                new Candidate[resumes.length];

        int resultCount = 0;

        for (int i = 0;
             i < resumes.length;
             i++) {

            Resume resume =
                    resumes[i];

            String skills =
                    resume.getSkills();

            if (skills == null) {
                continue;
            }

            String[] skillList =
                    skills.split(",");

            int fuzzyMatches = 0;
            double bestSimilarity = 0.0;

            for (int j = 0;
                 j < skillList.length;
                 j++) {

                String skill =
                        skillList[j]
                        .trim()
                        .toLowerCase();

                if (skill.length() == 0) {
                    continue;
                }

                int distance =
                        EditDistance.calculate(
                                query,
                                skill
                        );

                double similarity =
                        Similarity.skillSimilarity(
                                query,
                                skill
                        );

                if (similarity > bestSimilarity) {
                    bestSimilarity =
                            similarity;
                }

                if (distance <= 2
                        || similarity >= 60.0) {

                    fuzzyMatches++;
                }
            }

            if (fuzzyMatches > 0) {

                Candidate candidate =
                        new Candidate(resume);

                candidate.setExactMatches(0);

                candidate.setFuzzyMatches(
                        fuzzyMatches
                );

                candidate.setSimilarityScore(
                        Math.round(
                                bestSimilarity * 100.0
                        ) / 100.0
                );

                CandidateScorer.scoreCandidate(
                        candidate
                );

                temporary[resultCount++] =
                        candidate;
            }
        }

        return buildAndRank(
                temporary,
                resultCount
        );
    }

    private Candidate[] buildAndRank(
            Candidate[] temporary,
            int resultCount) {

        Candidate[] results =
                new Candidate[resultCount];

        for (int i = 0;
             i < resultCount;
             i++) {

            results[i] =
                    temporary[i];
        }

        CandidateRanker.rank(results);

        return results;
    }
}