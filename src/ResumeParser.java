public class ResumeParser {

    public static String[] extractSkills(Resume resume) {

        String skillText = resume.getSkills();

        if (skillText.length() == 0) {
            return new String[0];
        }

        int count = 1;

        for (int i = 0; i < skillText.length(); i++) {

            if (skillText.charAt(i) == ',') {
                count++;
            }
        }

        String[] skills = new String[count];

        int skillIndex = 0;
        int start = 0;

        for (int i = 0; i <= skillText.length(); i++) {

            if (i == skillText.length()
                    || skillText.charAt(i) == ',') {

                String skill =
                        skillText.substring(start, i).trim();

                skills[skillIndex] = skill;

                skillIndex++;
                start = i + 1;
            }
        }

        return skills;
    }
}
