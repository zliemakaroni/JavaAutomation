package JIraXrayIntegration.Jira.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Content {
    private final ArrayList<Object> content;

    public Content(Map<String, Object>... values) {
        content = new ArrayList<>();
        Collections.addAll(content, values);
    }

    public Content(String text) {
        content = new ArrayList<>();
        Map<String, Object> contentField = new HashMap<>();
        contentField.put("type", "text");
        contentField.put("text", text);
        content.add(contentField);
    }

    public Content(Paragraph paragraph) {
        content = new ArrayList<>();
        content.add(paragraph.getMap());
    }

    public ArrayList<Object> getArray() {
        return content;
    }
}