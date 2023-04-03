package JIraXrayIntegration.Xray.Models;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "testKey",
        "start",
        "finish",
        "comment",
        "status",
        "defects",
        "evidences"
})
public class Test {
    @JsonProperty("testKey")
    private String testKey;
    @JsonProperty("start")
    private String start;
    @JsonProperty("finish")
    private String finish;
    @JsonProperty("comment")
    private String comment;
    @JsonProperty("defects")
    private List<String> defects = null;
    @JsonProperty("status")
    private String status;
    @JsonProperty("evidences")
    private List<Evidence> evidences = null;

    @JsonProperty("testKey")
    public String getTestKey() {
        return testKey;
    }

    @JsonProperty("testKey")
    public void setTestKey(String testKey) {
        this.testKey = testKey;
    }

    @JsonProperty("start")
    public String getStart() {
        return start;
    }

    @JsonProperty("start")
    public void setStart(String start) {
        this.start = start;
    }

    @JsonProperty("finish")
    public String getFinish() {
        return finish;
    }

    @JsonProperty("finish")
    public void setFinish(String finish) {
        this.finish = finish;
    }

    @JsonProperty("comment")
    public String getComment() {
        return comment;
    }

    @JsonProperty("comment")
    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonProperty("defects")
    public List<String> getDefects() {
        return defects;
    }

    @JsonProperty("defects")
    public void setDefects(List<String> defects) {
        this.defects = defects;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("evidences")
    public List<Evidence> getEvidences() {
        return evidences;
    }

    @JsonProperty("evidences")
    public void setEvidences(List<Evidence> evidences) {
        this.evidences = evidences;
    }
}

