public class SuffixArray {

    private SuffixArray() {
    }

    public static int[] build(String text) {

        int n = text.length();

        IntegerWrapper[] suffixes =
                new IntegerWrapper[n];

        for (int i = 0; i < n; i++) {
            suffixes[i] =
                    new IntegerWrapper(i);
        }

        for (int i = 0; i < n - 1; i++) {

            for (int j = i + 1; j < n; j++) {

                if (compareSuffix(
                        text,
                        suffixes[i].value,
                        suffixes[j].value
                ) > 0) {

                    IntegerWrapper temp =
                            suffixes[i];

                    suffixes[i] = suffixes[j];
                    suffixes[j] = temp;
                }
            }
        }

        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[i] = suffixes[i].value;
        }

        return result;
    }

    private static int compareSuffix(
            String text,
            int first,
            int second) {

        int i = first;
        int j = second;

        while (i < text.length()
                && j < text.length()) {

            char a = text.charAt(i);
            char b = text.charAt(j);

            if (a < b) {
                return -1;
            }

            if (a > b) {
                return 1;
            }

            i++;
            j++;
        }

        if (i == text.length()
                && j == text.length()) {
            return 0;
        }

        if (i == text.length()) {
            return -1;
        }

        return 1;
    }

    private static class IntegerWrapper {

        int value;

        IntegerWrapper(int value) {
            this.value = value;
        }
    }
}