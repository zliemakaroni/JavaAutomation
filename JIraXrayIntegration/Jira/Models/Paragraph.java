package JIraXrayIntegration.Jira.Models;

import java.util.HashMap;
import java.util.Map;

public class Paragraph {

    private final Map<String, Object> paragraph;

    public Paragraph(Content content) {
        paragraph = new HashMap<>();
        paragraph.put("type", "paragraph");
        paragraph.put("content", content.getArray());
    }

    public Map<String, Object> getMap() {
        return paragraph;
    }
}
