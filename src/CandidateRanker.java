public class CandidateRanker {

    private CandidateRanker() {
    }

    public static void rank(
            Candidate[] candidates) {

        if (candidates == null) {
            return;
        }

        for (int i = 0;
             i < candidates.length - 1;
             i++) {

            int bestIndex = i;

            for (int j = i + 1;
                 j < candidates.length;
                 j++) {

                if (candidates[j].getFinalScore()
                        > candidates[bestIndex]
                        .getFinalScore()) {

                    bestIndex = j;
                }
            }

            if (bestIndex != i) {

                Candidate temp =
                        candidates[i];

                candidates[i] =
                        candidates[bestIndex];

                candidates[bestIndex] =
                        temp;
            }
        }
    }
}