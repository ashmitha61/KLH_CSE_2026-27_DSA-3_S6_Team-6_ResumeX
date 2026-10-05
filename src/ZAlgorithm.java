public class ZAlgorithm {

    private ZAlgorithm() {
    }

    private static int[] buildZ(String text) {

        int n = text.length();

        int[] z = new int[n];

        int left = 0;
        int right = 0;

        for (int i = 1; i < n; i++) {

            if (i <= right) {
                z[i] = Math.min(
                        right - i + 1,
                        z[i - left]
                );
            }

            while (i + z[i] < n
                    && text.charAt(z[i])
                    == text.charAt(i + z[i])) {

                z[i]++;
            }

            if (i + z[i] - 1 > right) {

                left = i;
                right = i + z[i] - 1;
            }
        }

        return z;
    }

    public static int search(
            String text,
            String pattern) {

        if (text == null || pattern == null) {
            return 0;
        }

        if (pattern.length() == 0) {
            return 0;
        }

        String combined =
                pattern + "$" + text;

        int[] z = buildZ(combined);

        int count = 0;

        for (int i = pattern.length() + 1;
             i < z.length;
             i++) {

            if (z[i] >= pattern.length()) {
                count++;
            }
        }

        return count;
    }

    public static int[] getZArray(String text) {
        return buildZ(text);
    }
}
