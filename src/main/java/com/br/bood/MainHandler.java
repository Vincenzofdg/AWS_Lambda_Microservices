package com.br.bood;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.br.bood.actions.GetAction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.br.bood.dto.PayloadDTO;

public class MainHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        String httpMethod = event.getHttpMethod();
        String body = event.getBody();
        PayloadDTO payload = null;

        // Check if body has values
        if (body != null && !body.trim().isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                payload = objectMapper.readValue(body, PayloadDTO.class);
            } catch (JsonProcessingException e) {
                response.setStatusCode(400);
                response.setBody("Erro ao processar o corpo da requisição: " + e.getMessage());
                return response;
            }
        }

        String jsonString = "";

        switch (httpMethod) {
            case "GET":
//                try {
//                    ObjectMapper objectMapper = new ObjectMapper();
//                    jsonString = objectMapper.writeValueAsString(payload);
//                } catch (JsonProcessingException e) {
//                    throw new RuntimeException(e);
//                }

                GetAction test = new GetAction();
                test.getAll();

                response.setStatusCode(200);
                response.setBody(jsonString);  // Retorna a string JSON como corpo da resposta
                break;
            case "PUT":
                response.setStatusCode(200);
                response.setBody("Método HTTP PUT");
                break;
            case "POST":
                response.setStatusCode(200);
                response.setBody("Método HTTP POST");
                break;
            case "DELETE":
                response.setStatusCode(200);
                response.setBody("Método HTTP DELETE");
                break;
            default:
                response.setStatusCode(400);
                response.setBody("Método HTTP não suportado");
        }

        return response;
    }
}
