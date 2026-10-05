public class ApproximateSelection {

    private ApproximateSelection() {
    }

    public static int[] selectTopCandidates(
            double[] scores,
            int numberToSelect) {

        if (scores == null) {
            return new int[0];
        }

        if (numberToSelect <= 0) {
            return new int[0];
        }

        if (numberToSelect > scores.length) {
            numberToSelect = scores.length;
        }

        double[] working =
                new double[scores.length];

        boolean[] selected =
                new boolean[scores.length];

        for (int i = 0; i < scores.length; i++) {
            working[i] = scores[i];
        }

        int[] result =
                new int[numberToSelect];

        for (int i = 0;
             i < numberToSelect;
             i++) {

            int bestIndex = -1;
            double bestScore =
                    Double.NEGATIVE_INFINITY;

            for (int j = 0;
                 j < working.length;
                 j++) {

                if (!selected[j]
                        && working[j] > bestScore) {

                    bestScore = working[j];
                    bestIndex = j;
                }
            }

            if (bestIndex == -1) {
                break;
            }

            selected[bestIndex] = true;
            result[i] = bestIndex;
        }

        return result;
    }
}