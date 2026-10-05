public class Candidate {

    private Resume resume;

    private int exactMatches;
    private int fuzzyMatches;

    private double similarityScore;
    private double finalScore;

    public Candidate(Resume resume) {
        this.resume = resume;
        this.exactMatches = 0;
        this.fuzzyMatches = 0;
        this.similarityScore = 0;
        this.finalScore = 0;
    }

    public Resume getResume() {
        return resume;
    }

    public int getExactMatches() {
        return exactMatches;
    }

    public int getFuzzyMatches() {
        return fuzzyMatches;
    }

    public double getSimilarityScore() {
        return similarityScore;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setExactMatches(int exactMatches) {
        this.exactMatches = exactMatches;
    }

    public void setFuzzyMatches(int fuzzyMatches) {
        this.fuzzyMatches = fuzzyMatches;
    }

    public void setSimilarityScore(double similarityScore) {
        this.similarityScore = similarityScore;
    }

    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }
}