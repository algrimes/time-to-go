package com.testupstream.timetogo.proxy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testupstream.timetogo.model.Arrival;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ArrivalsParserTest {

    @Spy
    @SuppressWarnings("unused")
    private ObjectMapper objectMapper;

    @Test
    public void parse_shouldRemoveCarriageReturnsAndNewlines() throws Exception {
        ArrivalsParser parser = new ArrivalsParser(objectMapper);

        parser.parse("[1,2,3,4]\n\r[5,6,7,8]\n");

        verify(objectMapper).readValue("[[1,2,3,4],[5,6,7,8]]", JsonNode.class);
    }

    @Test
    public void parse_shouldDecorateResponseWithSquareBrackets() throws Exception {
        ArrivalsParser parser = new ArrivalsParser(objectMapper);

        parser.parse("[1,2,3,4]\r[5,6,7,8]");

        verify(objectMapper).readValue("[[1,2,3,4],[5,6,7,8]]", JsonNode.class);
    }

    @Test
    public void parse_shouldSkipFirstRedundantRow() throws Exception {
        ArrivalsParser parser = new ArrivalsParser(objectMapper);

        List<Arrival> arrivalList = parser.parse("[1,2,3,4,5]\r[6,7,8,9,10]\r[11,12,13,14,15]");

        assertThat(arrivalList.size(), is(2));
        assertThat(arrivalList.get(0).getRoute(), is("8"));
        assertThat(arrivalList.get(1).getRoute(), is("13"));
    }

    @Test
    public void parse_shouldReturnEmptyListWhenFewerThanTwoRowsExist() throws Exception {
        ArrivalsParser parser = new ArrivalsParser(objectMapper);

        List<Arrival> arrivalList = parser.parse("[1,2,3,4,5]");

        assertThat(arrivalList.isEmpty(), is(true));
    }

}
