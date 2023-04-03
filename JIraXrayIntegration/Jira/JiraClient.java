package JIraXrayIntegration.Jira;

import com.atlassian.jira.rest.client.ApiClient;
import com.atlassian.jira.rest.client.ApiException;
import com.atlassian.jira.rest.client.api.IssuesApi;
import com.atlassian.jira.rest.client.api.MyselfApi;
import com.atlassian.jira.rest.client.api.ProjectsApi;
import com.atlassian.jira.rest.client.model.*;

import java.util.List;
import java.util.Map;

public class JiraClient {

    private static final JiraClient instance = new JiraClient();

    private final Logger logger = Logger.getInstance();
    private final JiraConfiguration configuration;

    private final ApiClient apiClient;
    private final MyselfApi myselfApi;
    private final IssuesApi issuesApi;
    private final User currentUser;
    private final Project currentProject;


    private JiraClient(){

        this.configuration = Configuration.getJiraConfiguration();
        this.apiClient = new ApiClient();
        apiClient.setUsername(configuration.getMail());
        apiClient.setPassword(configuration.getApiToken());
        apiClient.setBasePath(configuration.getUrl().toString());
        apiClient.setConnectTimeout(configuration.getConnectTimeout());
        this.myselfApi = new MyselfApi(apiClient);
        this.issuesApi = new IssuesApi(apiClient);
        try {
            this.currentUser = myselfApi.getCurrentUser(null);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

        ProjectsApi projectsApi = new ProjectsApi(apiClient);
        PageBeanProject projects = null;
        try {
            projects = projectsApi.searchProjects(
                    null, // startAt
                    null, // maxResults
                    null, // orderBy
                    null, // id
                    null, // keys
                    configuration.getProject(), // query
                    null,  // typeKey
                    null,  // categoryId
                    null, // action
                    "issueTypes", // expand issue types
                    null,  // status
                    null, // properties
                    null // propertyQuery
            );
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
        this.currentProject = projects.getValues().get(0);

    }

    public static JiraClient getInstance() {
        return instance;
    }

    public JiraConfiguration getConfiguration() {
        return configuration;
    }

    public CreatedIssue createIssue(Map<String, Object> issueCreateParams){

        CreatedIssue createdIssue = null;
        try {
            createdIssue = issuesApi.createIssue(issueCreateParams, true);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

        logger.info(String.format("Created Issue Key: %s", createdIssue.getKey()));
        logger.info("====================================");

        return createdIssue;
    }

    public void doTransitionForIssue(String issueKey, Map<String, Object> fields) {
        if(configuration.isEnabled()) {
            try {
                issuesApi.doTransition(fields, issueKey);
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } else {
            logger.warn(String.format(
                    "Issue '%s' wasn't transited as Jira configuration is not enabled. You need to enable '%s' to be able to use Jira Api.",
                    issueKey,
                    JiraConfiguration.JiraProperties.ENABLED.getName()
            ));
        }
    }

    public void updateIssueFields(String issueKey, Map<String, Object> fieldsMap) {
        try {
            issuesApi.editIssue(fieldsMap, issueKey, true, false, false);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }
    public IssueBean getIssue(String issueKey, List<String> fields) {
        try {
            return issuesApi.getIssue(issueKey, fields, null, null, null, null);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public Project getCurrentProject() {
        return currentProject;
    }
}
