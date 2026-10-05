public class EditDistance {

    private EditDistance() {
    }

    public static int calculate(
            String first,
            String second) {

        if (first == null || second == null) {
            return Integer.MAX_VALUE;
        }

        int m = first.length();
        int n = second.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (first.charAt(i - 1)
                        == second.charAt(j - 1)) {

                    dp[i][j] =
                            dp[i - 1][j - 1];

                } else {

                    int insertion =
                            dp[i][j - 1];

                    int deletion =
                            dp[i - 1][j];

                    int replacement =
                            dp[i - 1][j - 1];

                    int minimum =
                            Math.min(
                                    insertion,
                                    Math.min(
                                            deletion,
                                            replacement
                                    )
                            );

                    dp[i][j] = minimum + 1;
                }
            }
        }

        return dp[m][n];
    }
}