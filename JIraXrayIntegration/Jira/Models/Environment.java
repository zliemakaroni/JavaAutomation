package JIraXrayIntegration.Jira.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Environment {

    private final ArrayList<Object> environment;

    public Environment(String ... environmentNames) {
        environment = new ArrayList<>();
        for (Object env : environmentNames) {
            Map<String, Object> envField = new HashMap<>();
            envField.put("value", env);
            environment.add(envField);
        }
    }

    public ArrayList<Object> getArray() {
        return environment;
    }
}
