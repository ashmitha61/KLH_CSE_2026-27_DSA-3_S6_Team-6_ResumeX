public class SequenceAlignment {

    private SequenceAlignment() {
    }

    public static int calculateScore(
            String first,
            String second) {

        if (first == null || second == null) {
            return 0;
        }

        int match = 2;
        int mismatch = -1;
        int gap = -2;

        int m = first.length();
        int n = second.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            dp[i][0] = i * gap;
        }

        for (int j = 1; j <= n; j++) {
            dp[0][j] = j * gap;
        }

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                int diagonal;

                if (first.charAt(i - 1)
                        == second.charAt(j - 1)) {

                    diagonal =
                            dp[i - 1][j - 1]
                            + match;

                } else {

                    diagonal =
                            dp[i - 1][j - 1]
                            + mismatch;
                }

                int delete =
                        dp[i - 1][j] + gap;

                int insert =
                        dp[i][j - 1] + gap;

                dp[i][j] =
                        Math.max(
                                diagonal,
                                Math.max(delete, insert)
                        );
            }
        }

        return dp[m][n];
    }
}
