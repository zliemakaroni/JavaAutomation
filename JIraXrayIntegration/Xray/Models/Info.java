package JIraXrayIntegration.Xray.Models;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "summary",
        "description",
        "version",
        "user",
        "revision",
        "startDate",
        "finishDate",
        "testPlanKey",
        "testEnvironments"
})
public class Info {
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("description")
    private String description;
    @JsonProperty("version")
    private String version;
    @JsonProperty("user")
    private String user;
    @JsonProperty("revision")
    private String revision;
    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("finishDate")
    private String finishDate;
    @JsonProperty("testPlanKey")
    private String testPlanKey;
    @JsonProperty("testEnvironments")
    private List<String> testEnvironments = null;

    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("user")
    public String getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(String user) {
        this.user = user;
    }

    @JsonProperty("revision")
    public String getRevision() {
        return revision;
    }

    @JsonProperty("revision")
    public void setRevision(String revision) {
        this.revision = revision;
    }

    @JsonProperty("startDate")
    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("finishDate")
    public String getFinishDate() {
        return finishDate;
    }

    @JsonProperty("finishDate")
    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    @JsonProperty("testPlanKey")
    public String getTestPlanKey() {
        return testPlanKey;
    }

    @JsonProperty("testPlanKey")
    public void setTestPlanKey(String testPlanKey) {
        this.testPlanKey = testPlanKey;
    }

    @JsonProperty("testEnvironments")
    public List<String> getTestEnvironments() {
        return testEnvironments;
    }

    @JsonProperty("testEnvironments")
    public void setTestEnvironments(List<String> testEnvironments) {
        this.testEnvironments = testEnvironments;
    }
}
