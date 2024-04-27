package com.coelho.brasileiro.expensetrack.message.transform;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.Getter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Getter(AccessLevel.PACKAGE)
public  class DefaultTransform{

    private ObjectMapper objectMapper;
    private byte[] data;
    private JsonNode node;
    @Getter(AccessLevel.PRIVATE)
    private Map<String, JsonNode> properties;


    protected DefaultTransform(byte[] data) throws IOException {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.node = this.objectMapper.readTree(data);
        this.data = data;
        this.buildProperties();

    }

    private void buildProperties(){
        properties = new HashMap<>();
        for (Iterator<Map.Entry<String, JsonNode>> it = this.node.fields(); it.hasNext(); ) {
            Map.Entry<String, JsonNode> entry = it.next();
            properties.put(entry.getKey(), entry.getValue());
        }
    }
    protected JsonNode getPropertyValue(String property){
        return this.properties.getOrDefault(property, null);
    }

}
