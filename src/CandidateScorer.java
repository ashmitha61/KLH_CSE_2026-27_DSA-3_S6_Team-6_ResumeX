public class CandidateScorer {

    private CandidateScorer() {
    }

    public static void scoreCandidate(
            Candidate candidate) {

        if (candidate == null) {
            return;
        }

        int exact =
                candidate.getExactMatches();

        int fuzzy =
                candidate.getFuzzyMatches();

        double similarity =
                candidate.getSimilarityScore();

        double exactPart =
                Math.min(exact, 1) * 60.0;

        double fuzzyPart =
                Math.min(fuzzy, 1) * 20.0;

        double similarityPart =
                similarity * 0.20;

        double finalScore =
                exactPart
                + fuzzyPart
                + similarityPart;

        if (finalScore > 100.0) {
            finalScore = 100.0;
        }

        if (finalScore < 0.0) {
            finalScore = 0.0;
        }

        candidate.setFinalScore(
                Math.round(finalScore * 100.0)
                / 100.0
        );
    }
}