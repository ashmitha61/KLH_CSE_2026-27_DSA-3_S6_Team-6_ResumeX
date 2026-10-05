import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVResumeLoader {

    public static Resume[] loadResumes(String filePath) {

        Resume[] temporary = new Resume[1200];
        int count = 0;

        try {

            BufferedReader reader = new BufferedReader(
                    new FileReader(filePath)
            );

            // Skip header
            reader.readLine();

            String record;

            while ((record = readCSVRecord(reader)) != null) {

                if (record.trim().length() == 0) {
                    continue;
                }

                String[] data = parseCSVRecord(record);

                String name = getColumn(data, 0);
                String email = getColumn(data, 1);
                String phone = getColumn(data, 2);
                String university = getColumn(data, 3);
                String graduationYear = getColumn(data, 4);
                String yearsExperience = getColumn(data, 5);
                String jobRole = getColumn(data, 6);
                String skills = getColumn(data, 7);
                String resumeText = getColumn(data, 8);

                String education =
                        university + " | Graduation Year: "
                        + graduationYear;

                String experience =
                        yearsExperience + " years | Role: "
                        + jobRole;

                Resume resume = new Resume(
                        "CSV-" + (count + 1),
                        name,
                        email,
                        phone,
                        skills,
                        education,
                        experience,
                        resumeText
                );

                temporary[count] = resume;
                count++;
            }

            reader.close();

        } catch (IOException e) {

            System.out.println("Error reading CSV file.");
            System.out.println(e.getMessage());
        }

        Resume[] results = new Resume[count];

        for (int i = 0; i < count; i++) {
            results[i] = temporary[i];
        }

        return results;
    }


    private static String getColumn(
            String[] data,
            int index) {

        if (index >= 0 && index < data.length) {
            return clean(data[index]);
        }

        return "";
    }


    private static String readCSVRecord(
            BufferedReader reader)
            throws IOException {

        String line = reader.readLine();

        if (line == null) {
            return null;
        }

        String record = line;

        while (!quotesAreBalanced(record)) {

            String nextLine = reader.readLine();

            if (nextLine == null) {
                break;
            }

            record = record + "\n" + nextLine;
        }

        return record;
    }


    private static boolean quotesAreBalanced(
            String text) {

        boolean insideQuotes = false;

        for (int i = 0; i < text.length(); i++) {

            if (text.charAt(i) == '"') {

                if (i + 1 < text.length()
                        && text.charAt(i + 1) == '"') {

                    i++;
                } else {

                    insideQuotes = !insideQuotes;
                }
            }
        }

        return !insideQuotes;
    }


    private static String[] parseCSVRecord(
            String record) {

        String[] temporary = new String[20];

        int count = 0;
        String current = "";
        boolean insideQuotes = false;

        for (int i = 0; i < record.length(); i++) {

            char ch = record.charAt(i);

            if (ch == '"') {

                if (i + 1 < record.length()
                        && record.charAt(i + 1) == '"') {

                    current = current + '"';
                    i++;

                } else {

                    insideQuotes = !insideQuotes;
                }

            } else if (ch == ',' && !insideQuotes) {

                temporary[count] = current;
                count++;

                current = "";

            } else {

                current = current + ch;
            }
        }

        temporary[count] = current;
        count++;

        String[] result = new String[count];

        for (int i = 0; i < count; i++) {
            result[i] = temporary[i];
        }

        return result;
    }


    private static String clean(String text) {

        if (text == null) {
            return "";
        }

        return text.trim();
    }
}