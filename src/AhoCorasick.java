public class AhoCorasick {

    private static final int ALPHABET_SIZE = 128;

    private int[][] next;
    private int[] failure;
    private boolean[] output;

    private int nodeCount;

    public AhoCorasick() {

        next = new int[1000][ALPHABET_SIZE];
        failure = new int[1000];
        output = new boolean[1000];

        nodeCount = 1;

        for (int i = 0; i < next.length; i++) {

            for (int j = 0; j < ALPHABET_SIZE; j++) {
                next[i][j] = -1;
            }
        }
    }

    public void build(String[] patterns) {

        for (int i = 0; i < patterns.length; i++) {

            String pattern = patterns[i];

            if (pattern == null) {
                continue;
            }

            int current = 0;

            for (int j = 0; j < pattern.length(); j++) {

                char ch = pattern.charAt(j);

                if (ch >= ALPHABET_SIZE) {
                    continue;
                }

                if (next[current][ch] == -1) {

                    next[current][ch] = nodeCount;

                    for (int k = 0;
                         k < ALPHABET_SIZE;
                         k++) {

                        next[nodeCount][k] = -1;
                    }

                    nodeCount++;
                }

                current = next[current][ch];
            }

            output[current] = true;
        }

        buildFailureLinks();
    }

    private void buildFailureLinks() {

        int[] queue = new int[nodeCount];

        int front = 0;
        int rear = 0;

        for (int ch = 0; ch < ALPHABET_SIZE; ch++) {

            int child = next[0][ch];

            if (child != -1) {

                failure[child] = 0;
                queue[rear++] = child;

            } else {

                next[0][ch] = 0;
            }
        }

        while (front < rear) {

            int current = queue[front++];

            for (int ch = 0; ch < ALPHABET_SIZE; ch++) {

                int child = next[current][ch];

                if (child != -1) {

                    failure[child] =
                            next[failure[current]][ch];

                    if (output[failure[child]]) {
                        output[child] = true;
                    }

                    queue[rear++] = child;

                } else {

                    next[current][ch] =
                            next[failure[current]][ch];
                }
            }
        }
    }

    public int search(String text) {

        if (text == null) {
            return 0;
        }

        int current = 0;
        int matches = 0;

        for (int i = 0; i < text.length(); i++) {

            char ch = text.charAt(i);

            if (ch >= ALPHABET_SIZE) {
                current = 0;
                continue;
            }

            current = next[current][ch];

            if (output[current]) {
                matches++;
            }
        }

        return matches;
    }
}