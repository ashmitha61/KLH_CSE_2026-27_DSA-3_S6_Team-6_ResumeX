import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResumeReader {

    public List<Resume> readResumesFromFolder(String folderPath) {
        List<Resume> resumes = new ArrayList<>();
        Path resumeFolder = Paths.get(folderPath);

        if (!Files.exists(resumeFolder)) {
            System.out.println("Error: Resumes folder not found at '" + folderPath + "'.");
            return resumes;
        }

        if (!Files.isDirectory(resumeFolder)) {
            System.out.println("Error: The path '" + folderPath + "' is not a directory.");
            return resumes;
        }

        try {
            List<Path> files = Files.list(resumeFolder)
                    .filter(path -> path.getFileName().toString().toLowerCase().endsWith(".txt"))
                    .sorted()
                    .toList();

            if (files.isEmpty()) {
                System.out.println("No resume files were found in the resumes folder.");
                return resumes;
            }

            for (Path file : files) {
                try {
                    String content = Files.readString(file, StandardCharsets.UTF_8);
                    Resume resume = parseResume(file.getFileName().toString(), content);
                    if (resume != null) {
                        resumes.add(resume);
                    }
                } catch (IOException ioException) {
                    System.out.println("Warning: Could not read file '" + file.getFileName() + "'. " + ioException.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading resumes folder: " + e.getMessage());
        }

        return resumes;
    }

    private Resume parseResume(String fileName, String content) {
        if (content == null || content.trim().isEmpty()) {
            System.out.println("Warning: Skipping empty resume file: " + fileName);
            return null;
        }

        String name = extractName(content);
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Warning: Could not find a 'Name:' line in file: " + fileName);
            return null;
        }

        return new Resume(name.trim(), content);
    }

    private String extractName(String content) {
        Pattern pattern = Pattern.compile("(?im)^Name\\s*:\\s*(.+)$");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }
}
