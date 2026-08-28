import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class FuzzySearch {

    
    public int levenshteinDistance(String first, String second) {
        String a = first == null ? "" : first.trim().toLowerCase(Locale.ROOT);
        String b = second == null ? "" : second.trim().toLowerCase(Locale.ROOT);

        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= b.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }
            }
        }

        return dp[a.length()][b.length()];
    }

    /**
     * We convert the edit distance into a percentage using the longest string length.
     * similarity = (1 - distance / maxLength) * 100
     */
    public double calculateSimilarityPercentage(String first, String second) {
        if (first == null || second == null) {
            return 0.0;
        }

        String a = first.trim();
        String b = second.trim();
        if (a.isEmpty() && b.isEmpty()) {
            return 100.0;
        }

        int maxLength = Math.max(a.length(), b.length());
        if (maxLength == 0) {
            return 100.0;
        }

        int distance = levenshteinDistance(a, b);
        double similarity = (1.0 - (distance / (double) maxLength)) * 100.0;
        return Math.max(0.0, Math.min(100.0, similarity));
    }

    /**
     * This helps identify approximate skill matches such as "Machine Learnng" and "Python" vs "Pythn".
     */
    public boolean hasApproximateSkillMatch(String searchTerm, String resumeText, double threshold) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return false;
        }

        List<String> candidateTerms = extractCandidateTerms(resumeText);
        for (String term : candidateTerms) {
            if (calculateSimilarityPercentage(searchTerm, term) >= threshold) {
                return true;
            }
        }

        return false;
    }

    public List<String> extractCandidateTerms(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String normalizedText = text.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9\\s]", " ");
        String[] words = normalizedText.split("\\s+");
        List<String> terms = new ArrayList<>();

        for (String word : words) {
            if (!word.trim().isEmpty()) {
                terms.add(word.trim());
            }
        }

        // Also add phrases of 2 words for multi-word skills like "machine learning"
        for (int i = 0; i < words.length - 1; i++) {
            String phrase = words[i] + " " + words[i + 1];
            if (!phrase.trim().isEmpty()) {
                terms.add(phrase);
            }
        }

        return terms;
    }

    public double getAverageFuzzyScore(String searchTerm, List<String> skillList) {
        if (searchTerm == null || searchTerm.trim().isEmpty() || skillList == null || skillList.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;
        int count = 0;

        for (String skill : skillList) {
            total += calculateSimilarityPercentage(searchTerm, skill);
            count++;
        }

        return count == 0 ? 0.0 : total / count;
    }
}
