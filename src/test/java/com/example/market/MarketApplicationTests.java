package com.example.market;

import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureMockMvc
@SpringBootTest
class MarketApplicationTests {
    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void cleanTree() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/delete/069cb8d7-bbdd-47d3-ad8f-82ef4c269df1"));
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/delete/1cc0129a-2bfe-474c-9ee6-d435bf5fc8f2"));
    }

    @Nested
    class ImportsTest {

        @Test
        @Transactional
        public void testImportsValidRequest() throws Exception {
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

        @Test
        @Transactional
        public void testChangeType() throws Exception {
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
            mockMvc.perform(MockMvcRequestBuilders.post("/imports")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "        \"items\": [\n" +
                                    "            {\n" +
                                    "                \"type\": \"OFFER\",\n" +
                                    "                \"name\": \"Товар\",\n" +
                                    "                \"id\": \"069cb8d7-bbdd-47d3-ad8f-82ef4c269df1\",\n" +
                                    "                \"parentId\": null,\n" +
                                    "                \"price\": 200\n" +
                                    "            }\n" +
                                    "        ],\n" +
                                    "        \"updateDate\": \"2022-02-02T12:00:00.000Z\"\n" +
                                    "    }")
                    )
                    .andExpect(status().is(400));
            mockMvc.perform(MockMvcRequestBuilders.post("/imports")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "        \"items\": [\n" +
                                    "            {\n" +
                                    "                \"type\": \"OFFER\",\n" +
                                    "                \"name\": \"Товар\",\n" +
                                    "                \"id\": \"73bc3b36-02d1-4245-ab35-3106c9ee1c65\",\n" +
                                    "                \"parentId\": \"069cb8d7-bbdd-47d3-ad8f-82ef4c269df1\",\n" +
                                    "                \"price\": 69999\n" +
                                    "            }\n" +
                                    "        ],\n" +
                                    "        \"updateDate\": \"2022-02-03T15:00:00.000Z\"\n" +
                                    "    }")
                    )
                    .andExpect(status().is(200));
            mockMvc.perform(MockMvcRequestBuilders.post("/imports")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "        \"items\": [\n" +
                                    "            {\n" +
                                    "                \"type\": \"CATEGORY\",\n" +
                                    "                \"name\": \"Товар\",\n" +
                                    "                \"id\": \"73bc3b36-02d1-4245-ab35-3106c9ee1c65\",\n" +
                                    "                \"parentId\": \"069cb8d7-bbdd-47d3-ad8f-82ef4c269df1\",\n" +
                                    "                \"price\": null\n" +
                                    "            }\n" +
                                    "        ],\n" +
                                    "        \"updateDate\": \"2022-02-03T15:00:00.000Z\"\n" +
                                    "    }")
                    )
                    .andExpect(status().is(400));
        }

        @Test
        @Transactional
        public void testImportsNonUniqueIds() throws Exception {
            var result = mockMvc.perform(MockMvcRequestBuilders.post("/imports")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "        \"items\": [\n" +
                                    "            {\n" +
                                    "                \"type\": \"CATEGORY\",\n" +
                                    "                \"name\": \"Смартфоны\",\n" +
                                    "                \"id\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                                    "                \"parentId\": \"null\"\n" +
                                    "            },\n" +
                                    "            {\n" +
                                    "                \"type\": \"OFFER\",\n" +
                                    "                \"name\": \"jPhone 13\",\n" +
                                    "                \"id\": \"863e1a7a-1304-42ae-943b-179184c077e3\",\n" +
                                    "                \"parentId\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                                    "                \"price\": 79999\n" +
                                    "            },\n" +
                                    "            {\n" +
                                    "                \"type\": \"OFFER\",\n" +
                                    "                \"name\": \"Xomiа Readme 10\",\n" +
                                    "                \"id\": \"863e1a7a-1304-42ae-943b-179184c077e3\",\n" +
                                    "                \"parentId\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                                    "                \"price\": 59999\n" +
                                    "            }\n" +
                                    "        ],\n" +
                                    "        \"updateDate\": \"2022-02-02T12:00:00.000Z\"\n" +
                                    "    }")
                    )
                    .andExpect(status().is(400));
        }

        @Test
        @Transactional
        public void testImportsOfferIsParent() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/imports")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "        \"items\": [\n" +
                                    "            {\n" +
                                    "                \"type\": \"CATEGORY\",\n" +
                                    "                \"name\": \"Смартфоны\",\n" +
                                    "                \"id\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                                    "                \"parentId\": \"null\"\n" +
                                    "            },\n" +
                                    "            {\n" +
                                    "                \"type\": \"OFFER\",\n" +
                                    "                \"name\": \"jPhone 13\",\n" +
                                    "                \"id\": \"863e1a7a-1304-42ae-943b-179184c077e3\",\n" +
                                    "                \"parentId\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                                    "                \"price\": 79999\n" +
                                    "            },\n" +
                                    "            {\n" +
                                    "                \"type\": \"OFFER\",\n" +
                                    "                \"name\": \"Xomiа Readme 10\",\n" +
                                    "                \"id\": \"b1d8fd7d-2ae3-47d5-b2f9-0f094af800d4\",\n" +
                                    "                \"parentId\": \"863e1a7a-1304-42ae-943b-179184c077e3\",\n" +
                                    "                \"price\": 59999\n" +
                                    "            }\n" +
                                    "        ],\n" +
                                    "        \"updateDate\": \"2022-02-03T12:00:00.000Z\"\n" +
                                    "    }")
                    )
                    .andExpect(status().is(400));
        }

        @Test
        @Transactional
        public void testImportsNameIsNull() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/imports")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "        \"items\": [\n" +
                                    "            {\n" +
                                    "                \"type\": \"CATEGORY\",\n" +
                                    "                \"name\": null,\n" +
                                    "                \"id\": \"069cb8d7-bbdd-47d3-ad8f-82ef4c269df1\",\n" +
                                    "                \"parentId\": null,\n" +
                                    "                \"price\": null\n" +
                                    "            }\n" +
                                    "        ],\n" +
                                    "        \"updateDate\": \"2022-02-04T12:00:00.000Z\"\n" +
                                    "    }")
                    )
                    .andExpect(status().is(400));
        }

        @Test
        @Transactional
        public void testImportsCategoryPriceIsNotNull() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/imports")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "        \"items\": [\n" +
                                    "            {\n" +
                                    "                \"type\": \"CATEGORY\",\n" +
                                    "                \"name\": \"Товары\",\n" +
                                    "                \"id\": \"1\",\n" +
                                    "                \"parentId\": null,\n" +
                                    "                \"price\": 200\n" +
                                    "            }\n" +
                                    "        ],\n" +
                                    "        \"updateDate\": \"2022-02-05T12:00:00.000Z\"\n" +
                                    "    }")
                    )
                    .andExpect(status().is(400));
        }

        @Test
        @Transactional
        public void testImportsOfferPriceIsNull() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/imports")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "        \"items\": [\n" +
                                    "            {\n" +
                                    "                \"type\": \"OFFER\",\n" +
                                    "                \"name\": \"IPhone\",\n" +
                                    "                \"id\": \"1\",\n" +
                                    "                \"parentId\": null,\n" +
                                    "                \"price\": null\n" +
                                    "            }\n" +
                                    "        ],\n" +
                                    "        \"updateDate\": \"2022-02-06T12:00:00.000Z\"\n" +
                                    "    }")
                    )
                    .andExpect(status().is(400));
        }

        @Test
        @Transactional
        public void testImportsOfferPriceLessThanZero() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/imports")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "        \"items\": [\n" +
                                    "            {\n" +
                                    "                \"type\": \"OFFER\",\n" +
                                    "                \"name\": \"IPhone\",\n" +
                                    "                \"id\": \"1\",\n" +
                                    "                \"parentId\": null,\n" +
                                    "                \"price\": -1\n" +
                                    "            }\n" +
                                    "        ],\n" +
                                    "        \"updateDate\": \"2022-02-07T12:00:00.000Z\"\n" +
                                    "    }")
                    )
                    .andExpect(status().is(400));
        }

        @Test
        @Transactional
        public void testImportsInvalidDateFormat() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/imports")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "        \"items\": [\n" +
                                    "            {\n" +
                                    "                \"type\": \"OFFER\",\n" +
                                    "                \"name\": \"IPhone\",\n" +
                                    "                \"id\": \"1\",\n" +
                                    "                \"parentId\": null,\n" +
                                    "                \"price\": 0\n" +
                                    "            }\n" +
                                    "        ],\n" +
                                    "        \"updateDate\": \"2022-02-08\"\n" +
                                    "    }")
                    )
                    .andExpect(status().is(400));
        }

        @Test
        @Transactional
        public void testArbitraryOrder() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/imports")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "        \"items\": [\n" +
                                    "            {\n" +
                                    "                \"type\": \"OFFER\",\n" +
                                    "                \"name\": \"Xomiа Readme 10\",\n" +
                                    "                \"id\": \"b1d8fd7d-2ae3-47d5-b2f9-0f094af800d4\",\n" +
                                    "                \"parentId\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                                    "                \"price\": 59999\n" +
                                    "            },\n" +
                                    "            {\n" +
                                    "                \"type\": \"OFFER\",\n" +
                                    "                \"name\": \"jPhone 13\",\n" +
                                    "                \"id\": \"863e1a7a-1304-42ae-943b-179184c077e3\",\n" +
                                    "                \"parentId\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                                    "                \"price\": 79999\n" +
                                    "            },\n" +
                                    "            {\n" +
                                    "                \"type\": \"CATEGORY\",\n" +
                                    "                \"name\": \"Смартфоны\",\n" +
                                    "                \"id\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                                    "                \"parentId\": null\n" +
                                    "            }\n" +
                                    "        ],\n" +
                                    "        \"updateDate\": \"2022-02-09T12:00:00.000Z\"\n" +
                                    "    }")
                    )
                    .andExpect(status().is(200));
        }
    }

    @Nested
    class DeleteTest {
        @Test
        @Transactional
        public void testDeleteExistentNode() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/imports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "        \"items\": [\n" +
                            "            {\n" +
                            "                \"type\": \"CATEGORY\",\n" +
                            "                \"name\": \"Товары\",\n" +
                            "                \"id\": \"069cb8d7-bbdd-47d3-ad8f-82ef4c269df1\",\n" +
                            "                \"parentId\": null\n" +
                            "            }\n" +
                            "        ],\n" +
                            "        \"updateDate\": \"2022-02-10T12:00:00.000Z\"\n" +
                            "    }")
            )
                    .andExpect(status().is(200));
            mockMvc.perform(MockMvcRequestBuilders
                            .delete("/delete/069cb8d7-bbdd-47d3-ad8f-82ef4c269df1"))
                    .andExpect(status().is(200));
        }

        @Test
        @Transactional
        public void testDeleteNonExistentNode() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .delete("/delete/069cb8d7-bbdd-47d3-ad8f-82ef4c269df1"))
                    .andExpect(status().is(404));
        }
    }

    @Nested
    class NodesTest {
        @Test
        @Transactional
        public void testExistentNode() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/imports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "        \"items\": [\n" +
                            "            {\n" +
                            "                \"type\": \"CATEGORY\",\n" +
                            "                \"name\": \"Смартфоны\",\n" +
                            "                \"id\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                            "                \"parentId\": null\n" +
                            "            },\n" +
                            "            {\n" +
                            "                \"type\": \"OFFER\",\n" +
                            "                \"name\": \"jPhone 13\",\n" +
                            "                \"id\": \"863e1a7a-1304-42ae-943b-179184c077e3\",\n" +
                            "                \"parentId\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                            "                \"price\": 0\n" +
                            "            },\n" +
                            "            {\n" +
                            "                \"type\": \"OFFER\",\n" +
                            "                \"name\": \"Xomiа Readme 10\",\n" +
                            "                \"id\": \"b1d8fd7d-2ae3-47d5-b2f9-0f094af800d4\",\n" +
                            "                \"parentId\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                            "                \"price\": 0\n" +
                            "            }\n" +
                            "        ],\n" +
                            "        \"updateDate\": \"2022-02-11T12:00:00.000Z\"\n" +
                            "    }")
                    )
                    .andExpect(status().is(200));
            mockMvc.perform(MockMvcRequestBuilders.get("/nodes/d515e43f-f3f6-4471-bb77-6b455017a2d2"))
                    .andExpect(status().is(200))
                    .andExpect(content().json("{\n" +
                            "    \"type\": \"CATEGORY\",\n" +
                            "    \"name\": \"Смартфоны\",\n" +
                            "    \"id\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                            "    \"parentId\": null,\n" +
                            "    \"price\": 0,\n" +
                            "    \"date\": \"2022-02-11T12:00:00.000Z\",\n" +
                            "    \"children\": [\n" +
                            "        {\n" +
                            "            \"type\": \"OFFER\",\n" +
                            "            \"name\": \"jPhone 13\",\n" +
                            "            \"id\": \"863e1a7a-1304-42ae-943b-179184c077e3\",\n" +
                            "            \"parentId\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                            "            \"price\": 0,\n" +
                            "            \"date\": \"2022-02-11T12:00:00.000Z\",\n" +
                            "            \"children\": null\n" +
                            "        },\n" +
                            "        {\n" +
                            "            \"type\": \"OFFER\",\n" +
                            "            \"name\": \"Xomiа Readme 10\",\n" +
                            "            \"id\": \"b1d8fd7d-2ae3-47d5-b2f9-0f094af800d4\",\n" +
                            "            \"parentId\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                            "            \"price\": 0,\n" +
                            "            \"date\": \"2022-02-11T12:00:00.000Z\",\n" +
                            "            \"children\": null\n" +
                            "        }\n" +
                            "    ]\n" +
                            "}"));
            mockMvc.perform(MockMvcRequestBuilders.get("/nodes/b1d8fd7d-2ae3-47d5-b2f9-0f094af800d4"))
                    .andExpect(status().is(200))
                    .andExpect(content().json("{\n" +
                            "    \"type\": \"OFFER\",\n" +
                            "    \"name\": \"Xomiа Readme 10\",\n" +
                            "    \"id\": \"b1d8fd7d-2ae3-47d5-b2f9-0f094af800d4\",\n" +
                            "    \"parentId\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                            "    \"price\": 0,\n" +
                            "    \"date\": \"2022-02-11T12:00:00.000Z\",\n" +
                            "    \"children\": null\n" +
                            "}"));
            mockMvc.perform(MockMvcRequestBuilders.get("/nodes/863e1a7a-1304-42ae-943b-179184c077e3"))
                    .andExpect(status().is(200))
                    .andExpect(content().json("{\n" +
                            "    \"type\": \"OFFER\",\n" +
                            "    \"name\": \"jPhone 13\",\n" +
                            "    \"id\": \"863e1a7a-1304-42ae-943b-179184c077e3\",\n" +
                            "    \"parentId\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                            "    \"price\": 0,\n" +
                            "    \"date\": \"2022-02-11T12:00:00.000Z\",\n" +
                            "    \"children\": null\n" +
                            "}"));
        }
        @Test
//        @Transactional
        public void testDeletedNode() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/imports")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "        \"items\": [\n" +
                                    "            {\n" +
                                    "                \"type\": \"CATEGORY\",\n" +
                                    "                \"name\": \"Смартфоны\",\n" +
                                    "                \"id\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                                    "                \"parentId\": null\n" +
                                    "            },\n" +
                                    "            {\n" +
                                    "                \"type\": \"OFFER\",\n" +
                                    "                \"name\": \"jPhone 13\",\n" +
                                    "                \"id\": \"863e1a7a-1304-42ae-943b-179184c077e3\",\n" +
                                    "                \"parentId\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                                    "                \"price\": 0\n" +
                                    "            },\n" +
                                    "            {\n" +
                                    "                \"type\": \"OFFER\",\n" +
                                    "                \"name\": \"Xomiа Readme 10\",\n" +
                                    "                \"id\": \"b1d8fd7d-2ae3-47d5-b2f9-0f094af800d4\",\n" +
                                    "                \"parentId\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                                    "                \"price\": 0\n" +
                                    "            }\n" +
                                    "        ],\n" +
                                    "        \"updateDate\": \"2022-02-11T12:00:00.000Z\"\n" +
                                    "    }")
                    )
                    .andExpect(status().is(200));
            mockMvc.perform(MockMvcRequestBuilders.get("/nodes/d515e43f-f3f6-4471-bb77-6b455017a2d2"))
                    .andExpect(status().is(200));
            mockMvc.perform(MockMvcRequestBuilders.delete("/delete/d515e43f-f3f6-4471-bb77-6b455017a2d2"))
                    .andExpect(status().is(200));
            mockMvc.perform(MockMvcRequestBuilders.get("/nodes/d515e43f-f3f6-4471-bb77-6b455017a2d2"))
                    .andExpect(status().is(404));
        }
        @Test
        @Transactional
        public void testChangeParent() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/imports")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "        \"items\": [\n" +
                                    "            {\n" +
                                    "                \"type\": \"CATEGORY\",\n" +
                                    "                \"name\": \"Товары\",\n" +
                                    "                \"id\": \"069cb8d7-bbdd-47d3-ad8f-82ef4c269df1\",\n" +
                                    "                \"parentId\": null\n" +
                                    "            }\n" +
                                    "        ],\n" +
                                    "        \"updateDate\": \"2022-02-01T12:00:00.000Z\"\n" +
                                    "    }")
                    )
                    .andExpect(status().is(200));
            mockMvc.perform(MockMvcRequestBuilders.post("/imports")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "        \"items\": [\n" +
                                    "            {\n" +
                                    "                \"type\": \"CATEGORY\",\n" +
                                    "                \"name\": \"Смартфоны\",\n" +
                                    "                \"id\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                                    "                \"parentId\": null\n" +
                                    "            },\n" +
                                    "            {\n" +
                                    "                \"type\": \"OFFER\",\n" +
                                    "                \"name\": \"jPhone 13\",\n" +
                                    "                \"id\": \"863e1a7a-1304-42ae-943b-179184c077e3\",\n" +
                                    "                \"parentId\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                                    "                \"price\": 79999\n" +
                                    "            }\n" +
                                    "        ],\n" +
                                    "        \"updateDate\": \"2022-02-02T12:00:00.000Z\"\n" +
                                    "    }")
                    )
                    .andExpect(status().is(200));
            mockMvc.perform(MockMvcRequestBuilders.get("/nodes/069cb8d7-bbdd-47d3-ad8f-82ef4c269df1"))
                    .andExpect(status().is(200))
                    .andExpect(content().json("{\n" +
                            "    \"type\": \"CATEGORY\",\n" +
                            "    \"name\": \"Товары\",\n" +
                            "    \"id\": \"069cb8d7-bbdd-47d3-ad8f-82ef4c269df1\",\n" +
                            "    \"parentId\": null,\n" +
                            "    \"price\": null,\n" +
                            "    \"date\": \"2022-02-01T12:00:00.000Z\",\n" +
                            "    \"children\": []\n" +
                            "}"));
            mockMvc.perform(MockMvcRequestBuilders.get("/nodes/069cb8d7-bbdd-47d3-ad8f-82ef4c269df1"))
                    .andExpect(status().is(200))
                    .andExpect(content().json("{\n" +
                            "    \"type\": \"CATEGORY\",\n" +
                            "    \"name\": \"Товары\",\n" +
                            "    \"id\": \"069cb8d7-bbdd-47d3-ad8f-82ef4c269df1\",\n" +
                            "    \"parentId\": null,\n" +
                            "    \"price\": null,\n" +
                            "    \"date\": \"2022-02-01T12:00:00.000Z\",\n" +
                            "    \"children\": []\n" +
                            "}"));
            mockMvc.perform(MockMvcRequestBuilders.get("/nodes/d515e43f-f3f6-4471-bb77-6b455017a2d2"))
                    .andExpect(status().is(200))
                    .andExpect(content().json("{\n" +
                            "    \"type\": \"CATEGORY\",\n" +
                            "    \"name\": \"Смартфоны\",\n" +
                            "    \"id\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                            "    \"parentId\": null,\n" +
                            "    \"price\": 79999,\n" +
                            "    \"date\": \"2022-02-02T12:00:00.000Z\",\n" +
                            "    \"children\": [\n" +
                            "        {\n" +
                            "            \"type\": \"OFFER\",\n" +
                            "            \"name\": \"jPhone 13\",\n" +
                            "            \"id\": \"863e1a7a-1304-42ae-943b-179184c077e3\",\n" +
                            "            \"parentId\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                            "            \"price\": 79999,\n" +
                            "            \"date\": \"2022-02-02T12:00:00.000Z\",\n" +
                            "            \"children\": null\n" +
                            "        }\n" +
                            "    ]\n" +
                            "}"));
        }
    }
    @Nested
    class SalesTest {
        @Test
        @Transactional
        public void testInvalidDate() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                    .get("/sales/?date=2022-0528T21:12:01.000Z"))
                    .andExpect(status().is(400));
        }
        @Test
        @Transactional
        public void testValidDate() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/imports")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "        \"items\": [\n" +
                                    "            {\n" +
                                    "                \"type\": \"CATEGORY\",\n" +
                                    "                \"name\": \"Смартфоны\",\n" +
                                    "                \"id\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                                    "                \"parentId\": null\n" +
                                    "            },\n" +
                                    "            {\n" +
                                    "                \"type\": \"OFFER\",\n" +
                                    "                \"name\": \"jPhone 13\",\n" +
                                    "                \"id\": \"863e1a7a-1304-42ae-943b-179184c077e3\",\n" +
                                    "                \"parentId\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                                    "                \"price\": 79999\n" +
                                    "            },\n" +
                                    "            {\n" +
                                    "                \"type\": \"OFFER\",\n" +
                                    "                \"name\": \"Xomiа Readme 10\",\n" +
                                    "                \"id\": \"b1d8fd7d-2ae3-47d5-b2f9-0f094af800d4\",\n" +
                                    "                \"parentId\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                                    "                \"price\": 59999\n" +
                                    "            }\n" +
                                    "        ],\n" +
                                    "        \"updateDate\": \"2022-02-01T13:00:00.000Z\"\n" +
                                    "    }"))
                    .andExpect(status().is(200));
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/imports")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "        \"items\": [\n" +
                                    "            {\n" +
                                    "                \"type\": \"OFFER\",\n" +
                                    "                \"name\": \"jPhone 13\",\n" +
                                    "                \"id\": \"863e1a7a-1304-42ae-943b-179184c077e3\",\n" +
                                    "                \"parentId\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                                    "                \"price\": 0\n" +
                                    "            },\n" +
                                    "            {\n" +
                                    "                \"type\": \"OFFER\",\n" +
                                    "                \"name\": \"Xomiа Readme 10\",\n" +
                                    "                \"id\": \"b1d8fd7d-2ae3-47d5-b2f9-0f094af800d4\",\n" +
                                    "                \"parentId\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                                    "                \"price\": 0\n" +
                                    "            }\n" +
                                    "        ],\n" +
                                    "        \"updateDate\": \"2022-02-03T12:00:00.000Z\"\n" +
                                    "    }"))
                    .andExpect(status().is(200));
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/sales?date=2022-02-02T13:00:00.000Z"))
                    .andExpect(status().is(200))
                    .andExpect(content().json("{\n" +
                            "    \"items\": [\n" +
                            "        {\n" +
                            "            \"type\": \"OFFER\",\n" +
                            "            \"name\": \"Xomiа Readme 10\",\n" +
                            "            \"id\": \"b1d8fd7d-2ae3-47d5-b2f9-0f094af800d4\",\n" +
                            "            \"parentId\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                            "            \"price\": 0,\n" +
                            "            \"date\": \"2022-02-03T12:00:00.000Z\",\n" +
                            "            \"children\": null\n" +
                            "        },\n" +
                            "        {\n" +
                            "            \"type\": \"OFFER\",\n" +
                            "            \"name\": \"jPhone 13\",\n" +
                            "            \"id\": \"863e1a7a-1304-42ae-943b-179184c077e3\",\n" +
                            "            \"parentId\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                            "            \"price\": 0,\n" +
                            "            \"date\": \"2022-02-03T12:00:00.000Z\",\n" +
                            "            \"children\": null\n" +
                            "        }\n" +
                            "    ]\n" +
                            "}"));
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/sales?date=2022-02-03T13:00:00.000Z"))
                    .andExpect(status().is(200))
                    .andExpect(content().json("{\n" +
                            "    \"items\": [\n" +
                            "        {\n" +
                            "            \"type\": \"OFFER\",\n" +
                            "            \"name\": \"Xomiа Readme 10\",\n" +
                            "            \"id\": \"b1d8fd7d-2ae3-47d5-b2f9-0f094af800d4\",\n" +
                            "            \"parentId\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                            "            \"price\": 0,\n" +
                            "            \"date\": \"2022-02-03T12:00:00.000Z\",\n" +
                            "            \"children\": null\n" +
                            "        },\n" +
                            "        {\n" +
                            "            \"type\": \"OFFER\",\n" +
                            "            \"name\": \"jPhone 13\",\n" +
                            "            \"id\": \"863e1a7a-1304-42ae-943b-179184c077e3\",\n" +
                            "            \"parentId\": \"d515e43f-f3f6-4471-bb77-6b455017a2d2\",\n" +
                            "            \"price\": 0,\n" +
                            "            \"date\": \"2022-02-03T12:00:00.000Z\",\n" +
                            "            \"children\": null\n" +
                            "        }\n" +
                            "    ]\n" +
                            "}"));
        }
    }
    @Nested
    class StatisticTest {
        @Test
        public void testInvalidInterval() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/node/069cb8d7-bbdd-47d3-ad8f-82ef4c269df1/statistic?" +
                                    "dateStart=2022-02-01T12:00:00.000Z&" +
                                    "dateEnd=2021-02-01T12:00:00.000Z"))
                    .andExpect(status().is(400));
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/node/069cb8d7-bbdd-47d3-ad8f-82ef4c269df1/statistic?" +
                                    "dateStart=2022-02-01T12:00:00.000Z&" +
                                    "dateEnd=2021-02-01T12:00:00.000Z"))
                    .andExpect(status().is(400));
        }
        @Test
        public void testInvalidDates() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/node/069cb8d7-bbdd-47d3-ad8f-82ef4c269df1/statistic?" +
                                    "dateStart=01-02-2022T12:00:00.000Z&" +
                                    "dateEnd=01-03-2022T12:00:00.000Z"))
                    .andExpect(status().is(400));
        }
        @Test
//        @Transactional
        public void testChangeParent() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/imports")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "        \"items\": [\n" +
                                    "            {\n" +
                                    "                \"type\": \"CATEGORY\",\n" +
                                    "                \"name\": \"Товары\",\n" +
                                    "                \"id\": \"069cb8d7-bbdd-47d3-ad8f-82ef4c269df1\",\n" +
                                    "                \"parentId\": null\n" +
                                    "            }\n" +
                                    "        ],\n" +
                                    "        \"updateDate\": \"2022-02-01T12:00:00.000Z\"\n" +
                                    "    }"))
                    .andExpect(status().is(200));
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/imports")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "        \"items\": [\n" +
                                    "            {\n" +
                                    "                \"type\": \"CATEGORY\",\n" +
                                    "                \"name\": \"Телевизоры\",\n" +
                                    "                \"id\": \"1cc0129a-2bfe-474c-9ee6-d435bf5fc8f2\",\n" +
                                    "                \"parentId\": null\n" +
                                    "            },\n" +
                                    "            {\n" +
                                    "                \"type\": \"OFFER\",\n" +
                                    "                \"name\": \"Samson 70\\\" LED UHD Smart\",\n" +
                                    "                \"id\": \"98883e8f-0507-482f-bce2-2fb306cf6483\",\n" +
                                    "                \"parentId\": \"1cc0129a-2bfe-474c-9ee6-d435bf5fc8f2\",\n" +
                                    "                \"price\": 32999\n" +
                                    "            },\n" +
                                    "            {\n" +
                                    "                \"type\": \"OFFER\",\n" +
                                    "                \"name\": \"Phyllis 50\\\" LED UHD Smarter\",\n" +
                                    "                \"id\": \"74b81fda-9cdc-4b63-8927-c978afed5cf4\",\n" +
                                    "                \"parentId\": \"1cc0129a-2bfe-474c-9ee6-d435bf5fc8f2\",\n" +
                                    "                \"price\": 49999\n" +
                                    "            }\n" +
                                    "        ],\n" +
                                    "        \"updateDate\": \"2022-02-03T12:00:00.000Z\"\n" +
                                    "    }  "))
                    .andExpect(status().is(200));
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/imports")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "        \"items\": [\n" +
                                    "            {\n" +
                                    "                \"type\": \"OFFER\",\n" +
                                    "                \"name\": \"Goldstar 65\\\" LED UHD LOL Very Smart\",\n" +
                                    "                \"id\": \"73bc3b36-02d1-4245-ab35-3106c9ee1c65\",\n" +
                                    "                \"parentId\": \"1cc0129a-2bfe-474c-9ee6-d435bf5fc8f2\",\n" +
                                    "                \"price\": 69999\n" +
                                    "            }\n" +
                                    "        ],\n" +
                                    "        \"updateDate\": \"2022-02-03T15:00:00.000Z\"\n" +
                                    "    }\n"))
                    .andExpect(status().is(200));
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/imports")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "        \"items\": [\n" +
                                    "            {\n" +
                                    "                \"type\": \"OFFER\",\n" +
                                    "                \"name\": \"Товар\",\n" +
                                    "                \"id\": \"73bc3b36-02d1-4245-ab35-3106c9ee1c65\",\n" +
                                    "                \"parentId\": \"069cb8d7-bbdd-47d3-ad8f-82ef4c269df1\",\n" +
                                    "                \"price\": 0\n" +
                                    "            }\n" +
                                    "        ],\n" +
                                    "        \"updateDate\": \"2022-02-03T16:00:00.000Z\"\n" +
                                    "    }\n"))
                    .andExpect(status().is(200));
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/node/069cb8d7-bbdd-47d3-ad8f-82ef4c269df1/statistic?" +
                                    "dateStart=2022-02-01T12:00:00.000Z&" +
                                    "dateEnd=2022-02-03T16:00:01.000Z"))
                    .andExpect(status().is(200))
                    .andExpect(content().json("{\n" +
                            "    \"items\": [\n" +
                            "        {\n" +
                            "            \"type\": \"CATEGORY\",\n" +
                            "            \"name\": \"Товары\",\n" +
                            "            \"id\": \"069cb8d7-bbdd-47d3-ad8f-82ef4c269df1\",\n" +
                            "            \"parentId\": null,\n" +
                            "            \"price\": null,\n" +
                            "            \"date\": \"2022-02-01T12:00:00.000Z\",\n" +
                            "            \"children\": null\n" +
                            "        },\n" +
                            "        {\n" +
                            "            \"type\": \"CATEGORY\",\n" +
                            "            \"name\": \"Товары\",\n" +
                            "            \"id\": \"069cb8d7-bbdd-47d3-ad8f-82ef4c269df1\",\n" +
                            "            \"parentId\": null,\n" +
                            "            \"price\": 0,\n" +
                            "            \"date\": \"2022-02-03T16:00:00.000Z\",\n" +
                            "            \"children\": null\n" +
                            "        }\n" +
                            "    ]\n" +
                            "}"));
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/node/1cc0129a-2bfe-474c-9ee6-d435bf5fc8f2/statistic?" +
                                    "dateStart=2022-02-01T12:00:00.000Z&" +
                                    "dateEnd=2022-02-03T16:00:01.000Z"))
                    .andExpect(status().is(200))
                    .andExpect(content().json("{\n" +
                            "    \"items\": [\n" +
                            "        {\n" +
                            "            \"type\": \"CATEGORY\",\n" +
                            "            \"name\": \"Телевизоры\",\n" +
                            "            \"id\": \"1cc0129a-2bfe-474c-9ee6-d435bf5fc8f2\",\n" +
                            "            \"parentId\": null,\n" +
                            "            \"price\": 41499,\n" +
                            "            \"date\": \"2022-02-03T12:00:00.000Z\",\n" +
                            "            \"children\": null\n" +
                            "        },\n" +
                            "        {\n" +
                            "            \"type\": \"CATEGORY\",\n" +
                            "            \"name\": \"Телевизоры\",\n" +
                            "            \"id\": \"1cc0129a-2bfe-474c-9ee6-d435bf5fc8f2\",\n" +
                            "            \"parentId\": null,\n" +
                            "            \"price\": 50999,\n" +
                            "            \"date\": \"2022-02-03T15:00:00.000Z\",\n" +
                            "            \"children\": null\n" +
                            "        },\n" +
                            "        {\n" +
                            "            \"type\": \"CATEGORY\",\n" +
                            "            \"name\": \"Телевизоры\",\n" +
                            "            \"id\": \"1cc0129a-2bfe-474c-9ee6-d435bf5fc8f2\",\n" +
                            "            \"parentId\": null,\n" +
                            "            \"price\": 41499,\n" +
                            "            \"date\": \"2022-02-03T16:00:00.000Z\",\n" +
                            "            \"children\": null\n" +
                            "        }\n" +
                            "    ]\n" +
                            "}"));
            mockMvc.perform(MockMvcRequestBuilders
                    .delete("/delete/069cb8d7-bbdd-47d3-ad8f-82ef4c269df1"));
            mockMvc.perform(MockMvcRequestBuilders
                    .delete("/delete/1cc0129a-2bfe-474c-9ee6-d435bf5fc8f2"));

        }
    }
}

