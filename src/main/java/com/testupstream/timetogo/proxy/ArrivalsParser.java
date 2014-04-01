package com.testupstream.timetogo.proxy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.testupstream.timetogo.model.Arrival;

import java.io.IOException;
import java.util.List;

import static com.google.inject.internal.util.$Lists.newArrayList;

public class ArrivalsParser {

    private final ObjectMapper objectMapper;
    private static final int ETA_POSITIONAL_INDEX = 4;
    private static final int ROUTE_POSITIONAL_INDEX = 2;
    private static final int STOP_NAME_POSITIONAL_INDEX = 1;

    @Inject
    public ArrivalsParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Arrival> parse(String responseBody) {
        List<Arrival> arrivals = newArrayList();
        String cleanBody = "[" + responseBody.replaceAll("\r", ",").replaceAll("\n", "") + "]";
        try {
            JsonNode jsonNode = objectMapper.readValue(cleanBody, JsonNode.class);
            if (jsonNode.size() < 2) {
                return arrivals;
            }
            for(int i = 1;i < jsonNode.size(); i++) {
                arrivals.add(new Arrival(
                    //Indicies using arcane knowledge of search query for now
                    jsonNode.get(i).path(ETA_POSITIONAL_INDEX).asLong(),
                    jsonNode.get(i).path(ROUTE_POSITIONAL_INDEX).asText(),
                    jsonNode.get(i).path(STOP_NAME_POSITIONAL_INDEX).asText()
                ));
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to parse response", e);
        }
        return arrivals;
    }
}
