
public class WeightedEditDistance {

    private WeightedEditDistance() {
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
            dp[i][0] = i * 2;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j * 2;
        }

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                int insertion =
                        dp[i][j - 1] + 2;

                int deletion =
                        dp[i - 1][j] + 2;

                int replacementCost;

                if (first.charAt(i - 1)
                        == second.charAt(j - 1)) {

                    replacementCost = 0;

                } else {

                    replacementCost = 3;
                }

                int replacement =
                        dp[i - 1][j - 1]
                        + replacementCost;

                dp[i][j] =
                        Math.min(
                                insertion,
                                Math.min(
                                        deletion,
                                        replacement
                                )
                        );
            }
        }

        return dp[m][n];
    }
}