public class LCPArray {

    private LCPArray() {
    }

    public static int[] build(
            String text,
            int[] suffixArray) {

        int n = text.length();

        int[] rank = new int[n];
        int[] lcp = new int[n];

        for (int i = 0; i < n; i++) {
            rank[suffixArray[i]] = i;
        }

        int currentLCP = 0;

        for (int i = 0; i < n; i++) {

            int position = rank[i];

            if (position == n - 1) {

                currentLCP = 0;
                continue;
            }

            int nextSuffix =
                    suffixArray[position + 1];

            while (
                    i + currentLCP < n
                    && nextSuffix + currentLCP < n
                    && text.charAt(
                            i + currentLCP
                    )
                    ==
                    text.charAt(
                            nextSuffix + currentLCP
                    )
            ) {

                currentLCP++;
            }

            lcp[position] = currentLCP;

            if (currentLCP > 0) {
                currentLCP--;
            }
        }

        return lcp;
    }
}