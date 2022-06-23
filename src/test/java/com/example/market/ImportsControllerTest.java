package com.example.market;

import com.example.market.model.ImportsJson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ImportsControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Test
    public void testValidRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/imports")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "        \"items\": [\n" +
                        "            {\n" +
                        "                \"type\": \"CATEGORY\",\n" +
                        "                \"name\": \"Товары\",\n" +
                        "                \"id\": \"069cb8d7-bbdd-47d3-ad8f-82ef4c269df1\",\n" +
                        "                \"parentId\": null,\n" +
                        "                \"price\": null\n" +
                        "            }\n" +
                        "        ],\n" +
                        "        \"updateDate\": \"2022-02-01T12:00:00.000Z\"\n" +
                        "    }")
                )
                .andExpect(status().is(200));
    }
}
