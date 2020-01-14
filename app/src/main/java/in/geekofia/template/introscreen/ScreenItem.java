package in.geekofia.template.introscreen;

public class ScreenItem {

    private String title, description;
    private int screenTag;

    public ScreenItem(String title, String description, int screenTag) {
        this.title = title;
        this.description = description;
        this.screenTag = screenTag;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getScreenTag() {
        return screenTag;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setScreenTag(int screenTag) {
        this.screenTag = screenTag;
    }
}
