package com.eintrusty.rest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.awt.*;

public class WSDataRest {


    public static DataRest getObjectFromWebService(String url, DataRest sendObject, String httpMethod){
        DataRest response = new DataRest();
        HttpMethod method =  getHttpMethod(httpMethod);
        HttpMethod method2 = HttpMethod.resolve(httpMethod.toUpperCase());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<DataRest> requestEntity = null;
        if(sendObject !=null){
            requestEntity = new HttpEntity<DataRest>(sendObject,headers);
        }else {
            requestEntity = new HttpEntity<DataRest>(headers);
        }
        ResponseEntity<DataRest> responseEntity = restTemplate.exchange(url, method, requestEntity, DataRest.class);
        response = responseEntity.getBody();
        return response;
    }

    private static HttpMethod getHttpMethod(String method){
        HttpMethod HttpMethod = null;
        String methodUp = method.toUpperCase();
        switch (methodUp){
            case "GET" : HttpMethod = HttpMethod.GET;
                break;
            case "POST" : HttpMethod = HttpMethod.POST;
                break;
            case "DELETE" : HttpMethod = HttpMethod.DELETE;
                break;
            case "HEAD" : HttpMethod = HttpMethod.HEAD;
                break;
            case "PUT" : HttpMethod = HttpMethod.PUT;
                break;
            case "PATCH" : HttpMethod = HttpMethod.PATCH;
                break;
            case "OPTIONS" : HttpMethod = HttpMethod.OPTIONS;
                break;
            default: HttpMethod = null;
                break;

        }
        return  HttpMethod;
    }
}
