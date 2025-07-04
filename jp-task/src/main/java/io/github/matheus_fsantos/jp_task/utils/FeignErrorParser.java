package io.github.matheus_fsantos.jp_task.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeignErrorParser {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(FeignErrorParser.class);

    public static String extractMessage(FeignException exception) {
        try {
            String json = exception.contentUTF8();
            JsonNode node = objectMapper.readTree(json);

            if (node.has("message")) {
                return node.get("message").asText();
            }

        } catch (Exception e) { FeignErrorParser.logger.warn("Error parsing error message: {}", e.getMessage(), e); }

        return "Unexpected error in remote service";
    }
}
