package com.testupstream.timetogo.proxy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.testupstream.timetogo.model.Arrival;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ArrivalsParser {

    private final ObjectMapper objectMapper;
    private static final int ETA_POSITIONAL_INDEX = 4;
    private static final int ROUTE_POSITIONAL_INDEX = 2;
    private static final int STOP_NAME_POSITIONAL_INDEX = 1;
    private static final int DESTINATION_POSITIONAL_INDEX = 3;

    @Inject
    public ArrivalsParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Arrival> parse(String responseBody) {
        try {
            List<JsonNode> rows = Lists.newArrayList(objectMapper.readValue(cleanBody(responseBody), JsonNode.class));

            return tail(rows).stream().map(row -> new Arrival(
                    row.path(ETA_POSITIONAL_INDEX).asLong(),
                    row.path(ROUTE_POSITIONAL_INDEX).asText(),
                    row.path(STOP_NAME_POSITIONAL_INDEX).asText(),
                    row.path(DESTINATION_POSITIONAL_INDEX).asText()
            )).collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException("Unable to parse response", e);
        }
    }

    private List<JsonNode> tail(List<JsonNode> jsonNodes) {
        return jsonNodes.subList(1, jsonNodes.size());
    }

    private String cleanBody(String response) {
        return "[" + response.replaceAll("\r", ",").replaceAll("\n", "") + "]";
    }
}
