package pe.intercorp.retail.customers.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

public class ReadJson {
    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());;

    public static <T> T convertJsonFileToObject(String filePath, Class<T> targetClass) throws IOException {
        File file = new File(filePath);
        return objectMapper.readValue(file, targetClass);
    }

    public static <T> T convertJsonFileToObject(String filePath, TypeReference<T> targetClass) throws IOException {
        File file = new File(filePath);
        return objectMapper.readValue(file, targetClass);
    }



}
