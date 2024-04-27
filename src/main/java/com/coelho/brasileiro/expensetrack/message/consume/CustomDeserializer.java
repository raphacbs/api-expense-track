package com.coelho.brasileiro.expensetrack.message.consume;

import com.coelho.brasileiro.expensetrack.message.transform.Transform;
import com.coelho.brasileiro.expensetrack.message.transform.TransformEnum;
import com.coelho.brasileiro.expensetrack.message.transform.TransformFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.lang3.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class CustomDeserializer<T> implements Deserializer<T> {

    private ObjectMapper objectMapper;
    private Class<T> type;

    public CustomDeserializer(Class<T> type) {
        this.objectMapper = new ObjectMapper();
        this.type = type;
    }
    public CustomDeserializer() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Método para configurar este deserializador
        type = (Class<T>) configs.get("value.deserializer.type");
        System.out.println("configs = " + configs.get("value.deserializer.type"));
    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        return null;
    }

    @Override
    public T deserialize(String topic, Headers headers, byte[] data) {
        try {
            TransformEnum transformEnum = TransformEnum.fromType(type);
            T object = (T) TransformFactory.create(transformEnum, data).toObject();
            return object;
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    @Override
    public void close() {
        // Método para fechar este deserializador
    }
}


