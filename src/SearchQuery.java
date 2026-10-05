public class SearchQuery {

    private String requiredSkill;

    public SearchQuery(String requiredSkill) {

        if (requiredSkill == null) {
            this.requiredSkill = "";
        } else {
            this.requiredSkill =
                    requiredSkill
                    .trim()
                    .toLowerCase();
        }
    }

    public String getRequiredSkill() {
        return requiredSkill;
    }

    public boolean isEmpty() {
        return requiredSkill.length() == 0;
    }
}