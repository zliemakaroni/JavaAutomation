package JIraXrayIntegration.Jira.Models;

import java.util.HashMap;
import java.util.Map;

public class Description {

    private final Map<String, Object> description;

    public Description(Content content) {
        description = new HashMap<>();
        description.put("type", "doc");
        description.put("version", 1);
        description.put("content", content.getArray());
    }

    public Description(String text) {

        description = new HashMap<>();
        description.put("type", "doc");
        description.put("version", 1);
        Content paragraphContent = new Content(text);
        Paragraph paragraph = new Paragraph(paragraphContent);
        Content descriptionContent = new Content(paragraph);
        description.put("content",descriptionContent.getArray());
    }

    public Map<String, Object> getMap() {
        return description;
    }

}