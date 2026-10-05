public class Similarity {

    private Similarity() {
    }

    public static double skillSimilarity(
            String query,
            String skill) {

        if (query == null || skill == null) {
            return 0.0;
        }

        query = query.trim().toLowerCase();
        skill = skill.trim().toLowerCase();

        if (query.length() == 0
                || skill.length() == 0) {
            return 0.0;
        }

        if (query.equals(skill)) {
            return 100.0;
        }

        int distance =
                EditDistance.calculate(
                        query,
                        skill
                );

        int maxLength =
                Math.max(
                        query.length(),
                        skill.length()
                );

        double similarity =
                (1.0
                - ((double) distance / maxLength))
                * 100.0;

        if (similarity < 0) {
            similarity = 0;
        }

        return similarity;
    }

    public static double bestSkillSimilarity(
            String query,
            String skillsText) {

        if (skillsText == null
                || skillsText.trim().length() == 0) {

            return 0.0;
        }

        String[] skills =
                skillsText.split(",");

        double best = 0.0;

        for (int i = 0; i < skills.length; i++) {

            double current =
                    skillSimilarity(
                            query,
                            skills[i]
                    );

            if (current > best) {
                best = current;
            }
        }

        return best;
    }
}
