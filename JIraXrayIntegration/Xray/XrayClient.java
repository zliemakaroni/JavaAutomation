package JIraXrayIntegration.Xray;

import JIraXrayIntegration.Xray.Models.AddTestExecutionResultReq;
import JIraXrayIntegration.Xray.Models.AuthenticationReq;
import JIraXrayIntegration.Xray.Models.Test;

import java.io.IOException;
import java.util.List;

public class XrayClient {

    private final Logger logger = Logger.getInstance();
    private final XrayApi xrayApi;

    private static final String LOG_DELIMETER = "===================================================";

    public XrayClient(XrayConfiguration xrayConfiguration) {
        this.xrayApi = new XrayApi(xrayConfiguration);
    }

    public void addTestsToTestExecution(String executionId, List<Test> testList) {
        logger.info("========= Xray: Add Test To Test Execution ========");
        logger.info(String.format("Test Execution Key: %s", executionId));
        logger.info(String.format(
                        "Tests to ADD: %s",
                        (!testList.isEmpty()
                                ? String.join(",", testList.get(0).getTestKey())
                                : "No tests specified to ADD in request object"
                        )
                )
        );

        AddTestExecutionResultReq addTestExecutionResultReq = new AddTestExecutionResultReq();
        addTestExecutionResultReq.setTests(testList);
        addTestExecutionResultReq.setTestExecutionKey(executionId);
        try {
            xrayApi.addTestToTestExecution(addTestExecutionResultReq);
        } catch (IOException e) {
            IllegalArgumentException exception = new IllegalArgumentException(
                    String.format("Cannot add tests %1$s to execution %2$s .%nException: %3$s",
                            String.join(",", testList.get(0).getTestKey()), executionId, e.getMessage()));
            logger.fatal(e.getMessage(), exception);
            throw exception;
        }
        logger.info(LOG_DELIMETER);
    }

    public String getXrayToken(String clientId, String clientSecret) {
        AuthenticationReq authenticationReq = new AuthenticationReq();
        authenticationReq.setClientId(clientId);
        authenticationReq.setClientSecret(clientSecret);
        String token;
        try {
            token = xrayApi.getAuthorisationTokenFromAPI(authenticationReq).getBody().replaceAll("\"", "");
        } catch (IOException e) {
            IllegalArgumentException exception = new IllegalArgumentException("Cannot get authorisation token");
            logger.fatal(e.getMessage(), exception);
            throw exception;
        }
        logger.info(LOG_DELIMETER);
        return token;
    }
}
