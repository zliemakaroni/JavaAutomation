package JIraXrayIntegration.Xray.Models;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "client_id",
        "client_secret"
})
public class AuthenticationReq {

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("client_secret")
    private String clientSecret;

    @JsonProperty("client_id")
    public String getClientId() {
        return clientId;
    }

    @JsonProperty("client_id")
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @JsonProperty("client_secret")
    public String getClientSecret() {
        return clientSecret;
    }

    @JsonProperty("client_secret")
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
