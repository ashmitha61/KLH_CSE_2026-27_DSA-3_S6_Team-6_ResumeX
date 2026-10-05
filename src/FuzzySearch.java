public class FuzzySearch {

    // Checks whether two words are close enough
    public static boolean isSimilar(
            String first,
            String second,
            int maximumDistance) {

        int distance =
                EditDistance.calculate(first, second);

        return distance <= maximumDistance;
    }


    // Finds the best matching skill in a resume
    public static int findBestMatch(
            String query,
            String[] skills) {

        int bestDistance = Integer.MAX_VALUE;

        for (int i = 0; i < skills.length; i++) {

            String skill =
                    skills[i].toLowerCase().trim();

            int distance =
                    EditDistance.calculate(query, skill);

            if (distance < bestDistance) {
                bestDistance = distance;
            }
        }

        return bestDistance;
    }
}