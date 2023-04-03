package JIraXrayIntegration.Xray.Models;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "testExecutionKey",
        "info",
        "tests"
})
public class AddTestExecutionResultReq {
    @JsonProperty("testExecutionKey")
    private String testExecutionKey;
    @JsonProperty("info")
    private Info info;
    @JsonProperty("tests")
    private List<Test> tests = null;

    @JsonProperty("testExecutionKey")
    public String getTestExecutionKey() {
        return testExecutionKey;
    }

    @JsonProperty("testExecutionKey")
    public void setTestExecutionKey(String testExecutionKey) {
        this.testExecutionKey = testExecutionKey;
    }

    @JsonProperty("info")
    public Info getInfo() {
        return info;
    }

    @JsonProperty("info")
    public void setInfo(Info info) {
        this.info = info;
    }

    @JsonProperty("tests")
    public List<Test> getTests() {
        return tests;
    }

    @JsonProperty("tests")
    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

}

