package com.br.bood.actions;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class GetAction {
    private static final String TABLE_NAME = "emporiums";
    private DynamoDbClient dynamoDbClient = DynamoDbClient.create();

//    public Map<String, Object> getAll(String lastId)
    public void getAll() {
        Map<String, Object> response = new HashMap<>();

        ScanRequest.Builder scanRequestBuilder = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .limit(15)
                .filterExpression("#status = :accepted");
//                .expressionAttributeNames(Map.of("#status", "status"))
//                .expressionAttributeValues(Map.of(":accepted", AttributeValue.builder().n("2").build()));

        // Verifique se há um `lastId` para suportar paginação
//        if (lastId != null && !lastId.isEmpty()) {
//            Map<String, AttributeValue> exclusiveStartKey = new HashMap<>();
//            exclusiveStartKey.put("id", AttributeValue.builder().s(lastId).build());
//            scanRequestBuilder.exclusiveStartKey(exclusiveStartKey);
//        }

        try {
            // Envia a consulta (Scan)
            System.out.println('AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAZ');
            ScanRequest scanRequest = scanRequestBuilder.build();
            ScanResponse result = dynamoDbClient.scan(scanRequest);

//            // Formata os dados recebidos (como em scanFormater)
//            List<Map<String, AttributeValue>> formattedData = formatScanResult(result.items());

            // Retorna os dados formatados junto com a chave da última avaliação para paginação
//            response.put("items", formattedData);
//            response.put("lastId", result.lastEvaluatedKey() != null ? result.lastEvaluatedKey().get("id").s() : null);
//            response.put("status", 200); // OK

            System.out.println(result);

        } catch (DynamoDbException e) {
            response.put("error", "Erro ao acessar o DynamoDB: " + e.getMessage());
            response.put("status", 500); // Internal Server Error
        }

//        return response;
        return;
    }
}
