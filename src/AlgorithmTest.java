public class AlgorithmTest {

    public static void main(String[] args) {

        System.out.println();
        System.out.println(
                "============================================================"
        );
        System.out.println(
                "             ADVANCED ALGORITHM TEST"
        );
        System.out.println(
                "============================================================"
        );

        testKMP();
        testRabinKarp();
        testZAlgorithm();
        testAhoCorasick();
        testEditDistance();
        testWeightedEditDistance();
        testSequenceAlignment();
        testSuffixArray();
        testLCP();
        testBipartiteMatching();
        testApproximateSelection();

        System.out.println();
        System.out.println(
                "============================================================"
        );
        System.out.println(
                "              ALL ALGORITHMS TESTED"
        );
        System.out.println(
                "============================================================"
        );
    }

    private static void testKMP() {

        String text =
                "java python java sql";

        String pattern =
                "java";

        int result =
                KMP.search(text, pattern);

        System.out.println();
        System.out.println("[1] KMP");
        System.out.println(
                "Occurrences of java: "
                + result
        );
    }

    private static void testRabinKarp() {

        String text =
                "java python java sql";

        String pattern =
                "java";

        int result =
                RabinKarp.search(text, pattern);

        System.out.println();
        System.out.println("[2] Rabin-Karp");
        System.out.println(
                "Occurrences of java: "
                + result
        );
    }

    private static void testZAlgorithm() {

        String text =
                "java python java sql";

        String pattern =
                "java";

        int result =
                ZAlgorithm.search(text, pattern);

        System.out.println();
        System.out.println("[3] Z Algorithm");
        System.out.println(
                "Occurrences of java: "
                + result
        );
    }

    private static void testAhoCorasick() {

        String[] patterns = {
                "java",
                "python",
                "sql"
        };

        AhoCorasick aho =
                new AhoCorasick();

        aho.build(patterns);

        String text =
                "java python sql java";

        int result =
                aho.search(text);

        System.out.println();
        System.out.println("[4] Aho-Corasick");
        System.out.println(
                "Pattern matches: "
                + result
        );
    }

    private static void testEditDistance() {

        int result =
                EditDistance.calculate(
                        "python",
                        "pythn"
                );

        System.out.println();
        System.out.println("[5] Edit Distance");
        System.out.println(
                "python vs pythn: "
                + result
        );
    }

    private static void testWeightedEditDistance() {

        int result =
                WeightedEditDistance.calculate(
                        "python",
                        "pythn"
                );

        System.out.println();
        System.out.println(
                "[6] Weighted Edit Distance"
        );

        System.out.println(
                "python vs pythn: "
                + result
        );
    }

    private static void testSequenceAlignment() {

        int result =
                SequenceAlignment.calculateScore(
                        "java",
                        "jvaa"
                );

        System.out.println();
        System.out.println(
                "[7] Sequence Alignment"
        );

        System.out.println(
                "java vs jvaa score: "
                + result
        );
    }

    private static void testSuffixArray() {

        String text =
                "banana";

        int[] suffixArray =
                SuffixArray.build(text);

        System.out.println();
        System.out.println(
                "[8] Suffix Array"
        );

        System.out.print("Suffix Array: ");

        for (int i = 0;
             i < suffixArray.length;
             i++) {

            System.out.print(
                    suffixArray[i]
            );

            if (i < suffixArray.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println();
    }

    private static void testLCP() {

        String text =
                "banana";

        int[] suffixArray =
                SuffixArray.build(text);

        int[] lcp =
                LCPArray.build(
                        text,
                        suffixArray
                );

        System.out.println();
        System.out.println(
                "[9] LCP / Kasai"
        );

        System.out.print("LCP Array: ");

        for (int i = 0;
             i < lcp.length;
             i++) {

            System.out.print(lcp[i]);

            if (i < lcp.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println();
    }

    private static void testBipartiteMatching() {

        String[] requiredSkills = {
                "java",
                "python",
                "sql"
        };

        String[][] candidateSkills = {
                {"java", "sql"},
                {"python"},
                {"java", "python", "sql"}
        };

        int result =
                BipartiteMatching.maximumMatching(
                        requiredSkills,
                        candidateSkills
                );

        System.out.println();
        System.out.println(
                "[10] Bipartite Matching"
        );

        System.out.println(
                "Maximum Matching: "
                + result
        );
    }

    private static void testApproximateSelection() {

        double[] scores = {
                72.5,
                91.0,
                85.5,
                66.0,
                95.0
        };

        int[] selected =
                ApproximateSelection
                .selectTopCandidates(
                        scores,
                        3
                );

        System.out.println();
        System.out.println(
                "[11] Approximate Selection"
        );

        System.out.print(
                "Selected Candidate Indexes: "
        );

        for (int i = 0;
             i < selected.length;
             i++) {

            System.out.print(
                    selected[i]
            );

            if (i < selected.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println();
    }
}