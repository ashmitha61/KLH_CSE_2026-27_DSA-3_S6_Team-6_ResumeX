import java.util.ArrayList;
import java.util.List;

public class Resume {
    private String name;
    private String resumeText;
    private List<String> matchedSkills;
    private List<String> missingSkills;
    private double patternMatchScore;
    private double fuzzyMatchScore;
    private double similarityScore;
    private double finalScore;

    public Resume() {
        this.matchedSkills = new ArrayList<>();
        this.missingSkills = new ArrayList<>();
    }

    public Resume(String name, String resumeText) {
        this();
        this.name = name;
        this.resumeText = resumeText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResumeText() {
        return resumeText;
    }

    public void setResumeText(String resumeText) {
        this.resumeText = resumeText;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(List<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }

    public double getPatternMatchScore() {
        return patternMatchScore;
    }

    public void setPatternMatchScore(double patternMatchScore) {
        this.patternMatchScore = patternMatchScore;
    }

    public double getFuzzyMatchScore() {
        return fuzzyMatchScore;
    }

    public void setFuzzyMatchScore(double fuzzyMatchScore) {
        this.fuzzyMatchScore = fuzzyMatchScore;
    }

    public double getSimilarityScore() {
        return similarityScore;
    }

    public void setSimilarityScore(double similarityScore) {
        this.similarityScore = similarityScore;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }
}
