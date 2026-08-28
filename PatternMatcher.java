import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class PatternMatcher {

    /**
     * Pattern matching is checking whether each required skill appears in the resume.
     * This version uses case-insensitive regular expressions and supports multi-word skills
     * such as "Machine Learning".
     */
    public void evaluateRequiredSkills(Resume resume, List<String> requiredSkills) {
        List<String> matchedSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();

        for (String skill : requiredSkills) {
            String cleanSkill = skill.trim();
            if (cleanSkill.isEmpty()) {
                continue;
            }

            if (containsSkill(resume.getResumeText(), cleanSkill)) {
                matchedSkills.add(cleanSkill);
            } else {
                missingSkills.add(cleanSkill);
            }
        }

        resume.setMatchedSkills(matchedSkills);
        resume.setMissingSkills(missingSkills);

        if (requiredSkills.isEmpty()) {
            resume.setPatternMatchScore(0.0);
            return;
        }

        double score = (matchedSkills.size() * 100.0) / requiredSkills.size();
        resume.setPatternMatchScore(score);
    }

    /**
     * Regular expressions are used to match a skill while ignoring case.
     * For example, "Machine Learning" becomes a pattern like:
     * (?i)\bMachine\s+Learning\b
     * which allows spaces between words.
     */
    private boolean containsSkill(String resumeText, String skill) {
        String cleanSkill = skill.trim();
        if (cleanSkill.isEmpty()) {
            return false;
        }

        String[] segments = cleanSkill.split("\\s+");
        StringBuilder patternBuilder = new StringBuilder();
        for (int i = 0; i < segments.length; i++) {
            if (i > 0) {
                patternBuilder.append("\\s+");
            }
            patternBuilder.append(Pattern.quote(segments[i]));
        }

        String regex = "(?i)\\b" + patternBuilder + "\\b";
        return Pattern.compile(regex).matcher(resumeText).find();
    }
}
