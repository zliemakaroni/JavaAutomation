package JIraXrayIntegration.Xray;

import JIraXrayIntegration.Xray.Models.AddTestExecutionResultReq;
import JIraXrayIntegration.Xray.Models.AuthenticationReq;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * https://docs.getxray.app/display/XRAYCLOUD/REST+API
 */
public class XrayApi{
    private static final Logger LOGGER = Logger.getInstance();
    private final XrayHttpClient httpClient;
    private final XrayConfiguration xrayConfiguration;

    public XrayApi(XrayConfiguration xrayConfiguration){
        httpClient = new XrayHttpClient();
        this.xrayConfiguration = xrayConfiguration;
    }

    /** add test to test execution
     * @param addTestExecutionResultReq object with execution update request body
     * @return response of POST request
     * @throws IOException IOException
     */
    public RestClientResponse addTestToTestExecution(AddTestExecutionResultReq addTestExecutionResultReq) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(addTestExecutionResultReq);
        StringEntity postingString = new StringEntity(jsonInString, ContentType.APPLICATION_JSON);
        String url = xrayConfiguration.getBaseUrl() + "/api/v1/import/execution";
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Authorization", "Bearer " + xrayConfiguration.getToken());
        httpPost.setEntity(postingString);
        return executePost(httpPost);
    }

    /**
     * allows to import/update results of test(s) in the test execution
     * necessary fields in AddTestExecutionResultReq object: testExecutionKey, testKey, testStatus
     * all other fields are optional
     * @param addTestExecutionResultReq object with import/update details
     * @return rest client response
     * @throws IOException IOException
     */
    public RestClientResponse importTestExecutionResult(AddTestExecutionResultReq addTestExecutionResultReq) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(addTestExecutionResultReq);
        StringEntity postingString = new StringEntity(jsonInString);
        String url = xrayConfiguration.getBaseUrl() + "/api/v1/import/execution";
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Authorization", "Bearer " + xrayConfiguration.getToken());
        httpPost.setEntity(postingString);
        return executePost(httpPost);
    }

    /**
     * getting API token from Xray cloud
     * @param authenticationReq object with credentials
     * @return rest client response
     * @throws IOException IOException
     */
    public RestClientResponse getAuthorisationTokenFromAPI(AuthenticationReq authenticationReq) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(authenticationReq);
        StringEntity postingString = new StringEntity(jsonInString);
        String url = xrayConfiguration.getBaseUrl() + "/api/v1/authenticate";
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(postingString);
        return executePost(httpPost);
    }

    /** executes POST request
     * @param httpPost post request
     * @return response of POST request
     * @throws IOException IOException
     */
    protected RestClientResponse executePost(HttpPost httpPost) throws IOException {
        if(xrayConfiguration.isEnabled()){
            httpPost.setHeader("Content-Type", "application/json");
            RestClientResponse restClientResponse = httpClient.sendRequest(httpPost);
            LOGGER.info(String.format("POST request %s was executed with HTTP_CODE: %s", httpPost.getURI(), restClientResponse.getStatusCode()));
            LOGGER.debug("JSON POST DATA: " + IOUtils.toString(httpPost.getEntity().getContent(), StandardCharsets.UTF_8.name()));
            LOGGER.debug("RESPONSE BODY: " + restClientResponse.getBody());
            return restClientResponse;
        }
        throw new XrayConfigurationNotEnabledException("Xray configuration must be enabled for getTestExecutionTests() method. Check your configuration.");
    }
}
