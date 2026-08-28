import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class SimilarityCalculator {

    /**
     * TF-IDF stands for Term Frequency - Inverse Document Frequency.
     * TF tells how often a word appears in a document, while IDF reduces the importance
     * of common words across many documents.
     *
     * Cosine Similarity is the formula:
     * (A · B) / (||A|| × ||B||)
     * It compares the angle between two vectors and gives a value between 0 and 1.
     */
    public void calculateSimilarity(Resume resume, String requiredSkillsText) {
        String requiredText = normalizeText(requiredSkillsText);
        String resumeText = normalizeText(resume.getResumeText());

        List<String> requiredTokens = tokenize(requiredText);
        List<String> resumeTokens = tokenize(resumeText);

        List<List<String>> allDocuments = new ArrayList<>();
        allDocuments.add(requiredTokens);
        allDocuments.add(resumeTokens);

        Set<String> vocabulary = new HashSet<>();
        for (List<String> doc : allDocuments) {
            vocabulary.addAll(doc);
        }

        Map<String, Double> tfidfQuery = computeTfIdf(requiredTokens, vocabulary, allDocuments.size());
        Map<String, Double> tfidfResume = computeTfIdf(resumeTokens, vocabulary, allDocuments.size());

        double dotProduct = 0.0;
        double normQuery = 0.0;
        double normResume = 0.0;

        for (String word : vocabulary) {
            double q = tfidfQuery.getOrDefault(word, 0.0);
            double r = tfidfResume.getOrDefault(word, 0.0);
            dotProduct += q * r;
            normQuery += q * q;
            normResume += r * r;
        }

        double cosineSimilarity = 0.0;
        if (normQuery > 0.0 && normResume > 0.0) {
            cosineSimilarity = dotProduct / (Math.sqrt(normQuery) * Math.sqrt(normResume));
        }

        double percentage = Math.max(0.0, Math.min(1.0, cosineSimilarity)) * 100.0;
        resume.setSimilarityScore(percentage);
    }

    private String normalizeText(String text) {
        if (text == null) {
            return "";
        }

        return text.toLowerCase(Locale.ROOT)
                .replaceAll("[^a-z0-9\\s]", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }

    private List<String> tokenize(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new ArrayList<>();
        }

        List<String> tokens = new ArrayList<>();
        String[] parts = text.split("\\s+");
        for (String part : parts) {
            if (!part.trim().isEmpty()) {
                tokens.add(part.trim());
            }
        }
        return tokens;
    }

    private Map<String, Double> computeTfIdf(List<String> documentTokens, Set<String> vocabulary, int totalDocuments) {
        Map<String, Integer> termFrequency = new LinkedHashMap<>();
        for (String token : documentTokens) {
            termFrequency.put(token, termFrequency.getOrDefault(token, 0) + 1);
        }

        Map<String, Double> tfidf = new LinkedHashMap<>();
        for (String word : vocabulary) {
            int tf = termFrequency.getOrDefault(word, 0);
            double idf = Math.log((double) totalDocuments / (1 + documentFrequency(word, documentTokens, totalDocuments))) + 1.0;
            tfidf.put(word, (tf * idf));
        }

        return tfidf;
    }

    private double documentFrequency(String word, List<String> documentTokens, int totalDocuments) {
        // For this simplified implementation, we treat the current document as one document
        // and approximate the global frequency using the presence of the word in the document.
        return documentTokens.contains(word) ? 1.0 : 0.0;
    }
}
