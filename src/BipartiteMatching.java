public class BipartiteMatching {

    private BipartiteMatching() {
    }

    public static int maximumMatching(
            String[] requiredSkills,
            String[][] candidateSkills) {

        if (requiredSkills == null
                || candidateSkills == null) {

            return 0;
        }

        int leftSize = requiredSkills.length;
        int rightSize = candidateSkills.length;

        int[] matchedCandidate =
                new int[rightSize];

        for (int i = 0; i < rightSize; i++) {
            matchedCandidate[i] = -1;
        }

        int matching = 0;

        for (int i = 0; i < leftSize; i++) {

            boolean[] visited =
                    new boolean[rightSize];

            if (findMatch(
                    i,
                    requiredSkills,
                    candidateSkills,
                    matchedCandidate,
                    visited)) {

                matching++;
            }
        }

        return matching;
    }

    private static boolean findMatch(
            int requiredIndex,
            String[] requiredSkills,
            String[][] candidateSkills,
            int[] matchedCandidate,
            boolean[] visited) {

        String required =
                requiredSkills[requiredIndex]
                .toLowerCase()
                .trim();

        for (int candidate = 0;
             candidate < candidateSkills.length;
             candidate++) {

            if (visited[candidate]) {
                continue;
            }

            visited[candidate] = true;

            String[] skills =
                    candidateSkills[candidate];

            for (int j = 0; j < skills.length; j++) {

                if (skills[j] == null) {
                    continue;
                }

                String skill =
                        skills[j]
                        .toLowerCase()
                        .trim();

                if (required.equals(skill)) {

                    if (matchedCandidate[candidate]
                            == -1
                            ||
                        findMatch(
                                matchedCandidate[candidate],
                                requiredSkills,
                                candidateSkills,
                                matchedCandidate,
                                visited
                        )) {

                        matchedCandidate[candidate] =
                                requiredIndex;

                        return true;
                    }
                }
            }
        }

        return false;
    }
}
