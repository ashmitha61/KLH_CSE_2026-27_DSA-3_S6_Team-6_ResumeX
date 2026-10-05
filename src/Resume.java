public class Resume {

    private String candidateId;
    private String name;
    private String email;
    private String phone;
    private String skills;
    private String education;
    private String experience;
    private String projects;

    public Resume(String candidateId, String name, String email,
                  String phone, String skills, String education,
                  String experience, String projects) {

        this.candidateId = candidateId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.skills = skills;
        this.education = education;
        this.experience = experience;
        this.projects = projects;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getSkills() {
        return skills;
    }

    public String getEducation() {
        return education;
    }

    public String getExperience() {
        return experience;
    }

    public String getProjects() {
        return projects;
    }

    public String getFullText() {
        return name + " " +
               email + " " +
               phone + " " +
               skills + " " +
               education + " " +
               experience + " " +
               projects;
    }
}