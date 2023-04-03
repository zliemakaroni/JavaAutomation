package JIraXrayIntegration.Jira;

import java.util.HashMap;
import java.util.Map;

public class JiraExecutionCreator {
    private final XrayConfiguration xrayConfiguration;
    private final Logger logger = Logger.getInstance();
    private static final String ISSUE_TYPE = "Test Execution";

    public JiraExecutionCreator(){
        this(new XrayConfiguration());
    }

    public JiraExecutionCreator(XrayConfiguration xrayConfiguration){
        this.xrayConfiguration = xrayConfiguration;
    }

    public String createExecution(){
        if (xrayConfiguration.isEnabled()) {
            String issueTypeId = "0";
            // Find the issue type
            for (IssueTypeDetails issueTypeDetails : JiraClient.getInstance().getCurrentProject().getIssueTypes()) {
                if (issueTypeDetails.getName().equalsIgnoreCase(ISSUE_TYPE)) {
                    issueTypeId = issueTypeDetails.getId();
                }
            }

            Map<String, Object> issueCreateParams = new HashMap<>();
            Environment environment = new Environment(Configuration.getXrayConfiguration().getEnvironmentName());
            Description description = new Description(xrayConfiguration.getExecutionDescription());
            IssueType issueType = new IssueType(issueTypeId);
            Project project = new Project(JiraClient.getInstance().getCurrentProject().getId());
            Assignee assignee = new Assignee(JiraClient.getInstance().getCurrentUser().getAccountId());
            Fields fields = new Fields(xrayConfiguration.getExecutionSummary(), environment, description, issueType, project, assignee);
            issueCreateParams.put("fields", fields.getMap());

            logger.info("========= Create JIRA issue ========");
            logger.info(String.format("Project Key: %s", JiraClient.getInstance().getCurrentProject().getName()));
            logger.info(String.format("Summary: %s", xrayConfiguration.getExecutionSummary()));
            logger.info(String.format("Description: %s", xrayConfiguration.getExecutionDescription()));
            logger.info(String.format("Type: %s", ISSUE_TYPE));
            logger.info(String.format("Assignee: %s", JiraClient.getInstance().getCurrentUser().getDisplayName()));
            logger.info("====================================");
            CreatedIssue createdIssue = JiraClient.getInstance().createIssue(issueCreateParams);
            return createdIssue.getKey();
        } else {
            return null;
        }
    }
}