package com.green.todotestapp.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfiguration {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Bean
    @Primary
    public ObjectMapper serializingObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer()); // 직렬화 Json
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer()); // 역직렬화 Json
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());// 직렬화 할때 사용
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());// 역직렬화 할때 사용
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }

    public class LocalDateSerializer extends JsonSerializer<LocalDate> { // extends 하는 부분은 지켜 줘야 함
        @Override
        public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializer) throws IOException{
            gen.writeString(value.format(DATE_FORMATTER));
        }
    }
    public class LocalDateDeserializer extends JsonDeserializer<LocalDate>{
        @Override
        public LocalDate deserialize(JsonParser p , DeserializationContext ctxt) throws IOException{
            return LocalDate.parse(p.getValueAsString(), DATE_FORMATTER);
        }
    }
    public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime>{
        @Override
        public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException{
            jsonGenerator.writeString(localDateTime.format(DATETIME_FORMATTER));
        }
    }
    public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return LocalDateTime.parse(jsonParser.getValueAsString(),DATETIME_FORMATTER);
        }
    }
}
