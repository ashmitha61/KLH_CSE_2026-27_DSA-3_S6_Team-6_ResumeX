public class RabinKarp {

    private static final int BASE = 256;
    private static final int PRIME = 101;

    private RabinKarp() {
    }

    public static int search(String text, String pattern) {

        if (text == null || pattern == null) {
            return 0;
        }

        int n = text.length();
        int m = pattern.length();

        if (m == 0 || m > n) {
            return 0;
        }

        int patternHash = 0;
        int textHash = 0;
        int highestPower = 1;

        for (int i = 0; i < m - 1; i++) {
            highestPower =
                    (highestPower * BASE) % PRIME;
        }

        for (int i = 0; i < m; i++) {

            patternHash =
                    (BASE * patternHash
                    + pattern.charAt(i)) % PRIME;

            textHash =
                    (BASE * textHash
                    + text.charAt(i)) % PRIME;
        }

        int count = 0;

        for (int i = 0; i <= n - m; i++) {

            if (patternHash == textHash) {

                boolean matched = true;

                for (int j = 0; j < m; j++) {

                    if (text.charAt(i + j)
                            != pattern.charAt(j)) {

                        matched = false;
                        break;
                    }
                }

                if (matched) {
                    count++;
                }
            }

            if (i < n - m) {

                textHash =
                        (BASE * (textHash
                        - text.charAt(i)
                        * highestPower)
                        + text.charAt(i + m))
                        % PRIME;

                if (textHash < 0) {
                    textHash += PRIME;
                }
            }
        }

        return count;
    }
}