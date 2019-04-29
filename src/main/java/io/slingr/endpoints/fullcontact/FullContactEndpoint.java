package io.slingr.endpoints.fullcontact;

import io.slingr.endpoints.HttpEndpoint;
import io.slingr.endpoints.framework.annotations.*;
import io.slingr.endpoints.services.AppLogs;
import io.slingr.endpoints.services.rest.RestMethod;
import io.slingr.endpoints.utils.Json;
import io.slingr.endpoints.ws.exchange.FunctionRequest;
import io.slingr.endpoints.ws.exchange.WebServiceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FullContact endpoint
 *
 * Created by dgaviola on 11/06/17.
 */
@SlingrEndpoint(name = "fullcontact", functionPrefix = "_")
public class FullContactEndpoint extends HttpEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(FullContactEndpoint.class);

    private static final String FULLCONTACT_API_URL = "https://api.fullcontact.com/v2";

    @ApplicationLogger
    private AppLogs appLogger;

    @EndpointProperty
    private String apiKey;

    @Override
    public String getApiUri() {
        return FULLCONTACT_API_URL;
    }

    @EndpointFunction(name = "_get")
    public Json get(FunctionRequest request) {
        setRequestHeadersAndParams(request);
        return defaultGetRequest(request);
    }

    @EndpointFunction(name = "_post")
    public Json post(FunctionRequest request) {
        setRequestHeadersAndParams(request);
        return defaultPostRequest(request);
    }

    @EndpointWebService
    public String webhookProcessor(WebServiceRequest request){
        if(request.getMethod() == RestMethod.POST) {
            Json body = request.getJsonBody();
            String webhookId = body.string("webhookId");
            Json result = body.json("result");
            events().send("webhook", result, webhookId);
        }
        return "ok";
    }

    private void setRequestHeadersAndParams(FunctionRequest request) {
        Json body = request.getJsonParams();
        Json headers = body.json("headers");
        if (headers == null) {
            headers = Json.map();
        }
        headers.set("X-FullContact-APIKey", apiKey);
        headers.set("Accept", "application/json");
        body.set("headers", headers);
        Json params = body.json("params");
        if (params == null) {
            params = Json.map();
        }
        if (params.contains("webhook")) {
            params.set("webhookId", request.getFunctionId());
            params.set("webhookUrl", properties().getWebServicesUri());
            params.set("webhookBody", "json");
            params.remove("webhook");
        }
        body.set("params", params);
    }
}
