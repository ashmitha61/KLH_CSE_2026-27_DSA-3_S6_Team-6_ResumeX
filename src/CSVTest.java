public class CSVTest {

    public static void main(String[] args) {

        System.out.println("Testing Large Resume Dataset...");
        System.out.println();

        Resume[] resumes = CSVResumeLoader.loadResumes(
                "dataset/resume_dataset_2.csv"
        );

        System.out.println("Large Dataset Loaded Successfully!");
        System.out.println(
                "Total Resumes Loaded: " + resumes.length
        );

        if (resumes.length > 0) {

            Resume first = resumes[0];

            System.out.println();
            System.out.println("First Resume:");
            System.out.println("------------------------------");

            System.out.println("Name        : " + first.getName());
            System.out.println("Candidate ID: " + first.getCandidateId());
            System.out.println("Email       : " + first.getEmail());
            System.out.println("Phone       : " + first.getPhone());
            System.out.println("Skills      : " + first.getSkills());
            System.out.println("Education   : " + first.getEducation());
            System.out.println("Experience  : " + first.getExperience());
            System.out.println("Resume Text : " + first.getProjects());
        }


        if (resumes.length > 1) {

            Resume last = resumes[resumes.length - 1];

            System.out.println();
            System.out.println("Last Resume:");
            System.out.println("------------------------------");

            System.out.println("Name        : " + last.getName());
            System.out.println("Candidate ID: " + last.getCandidateId());
            System.out.println("Email       : " + last.getEmail());
            System.out.println("Phone       : " + last.getPhone());
            System.out.println("Skills      : " + last.getSkills());
            System.out.println("Education   : " + last.getEducation());
            System.out.println("Experience  : " + last.getExperience());
            System.out.println("Resume Text : " + last.getProjects());
        }
    }
}