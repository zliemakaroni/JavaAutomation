package JIraXrayIntegration.Jira.Models;

import java.util.HashMap;
import java.util.Map;

public class Fields {

    private static final String ENV_FIELD_ID = "customfield_10114";

    private Map<String, Object> fields;

    public Fields(@Nullable String summary, @Nullable Environment environment, @Nullable Description description, @Nullable IssueType issueType, @Nullable Project project, @Nullable Assignee assignee) {
        fields = new HashMap<>();
        if(summary != null) {
            fields.put("summary", summary);
        }
        if(environment != null) {
            fields.put(ENV_FIELD_ID, environment.getArray());
        }
        if(description != null) {
            fields.put("description", description.getMap());
        }
        if(issueType != null) {
            fields.put("issuetype", issueType.getMap());
        }
        if(project != null) {
            fields.put("project", project.getMap());
        }
        if(assignee != null) {
            fields.put("assignee", assignee.getMap());
        }
    }

    public Fields(Transition transition) {
        fields = new HashMap<>();
        fields.put("transition", transition.getMap());
    }

    public Map<String, Object> getMap() {
        return fields;
    }

}