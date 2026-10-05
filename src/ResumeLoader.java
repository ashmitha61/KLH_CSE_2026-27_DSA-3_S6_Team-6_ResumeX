import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ResumeLoader {

    public static Resume[] loadResumes(String folderPath) {

        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files == null) {
            System.out.println("Resume folder not found.");
            return new Resume[0];
        }

        Resume[] resumes = new Resume[files.length];
        int count = 0;

        for (int i = 0; i < files.length; i++) {

            if (files[i].isFile() && files[i].getName().endsWith(".txt")) {

                Resume resume = readResume(files[i]);

                if (resume != null) {
                    resumes[count] = resume;
                    count++;
                }
            }
        }

        Resume[] result = new Resume[count];

        for (int i = 0; i < count; i++) {
            result[i] = resumes[i];
        }

        return result;
    }

    private static Resume readResume(File file) {

        String name = "";
        String email = "";
        String phone = "";
        String skills = "";
        String education = "";
        String experience = "";
        String projects = "";

        try {

            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.startsWith("Name:")) {
                    name = line.substring(5).trim();
                }
                else if (line.startsWith("Email:")) {
                    email = line.substring(6).trim();
                }
                else if (line.startsWith("Phone:")) {
                    phone = line.substring(6).trim();
                }
                else if (line.startsWith("Skills:")) {
                    skills = line.substring(7).trim();
                }
                else if (line.startsWith("Education:")) {
                    education = line.substring(10).trim();
                }
                else if (line.startsWith("Experience:")) {
                    experience = line.substring(11).trim();
                }
                else if (line.startsWith("Projects:")) {
                    projects = line.substring(9).trim();
                }
            }

            reader.close();

            String fileName = file.getName();

            String candidateId =
                    fileName.substring(0, fileName.lastIndexOf("."));

            return new Resume(
                    candidateId,
                    name,
                    email,
                    phone,
                    skills,
                    education,
                    experience,
                    projects
            );

        }
        catch (Exception e) {

            System.out.println(
                    "Error reading " + file.getName()
            );

            return null;
        }
    }
}