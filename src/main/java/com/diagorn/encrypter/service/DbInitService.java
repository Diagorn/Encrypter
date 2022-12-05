package com.diagorn.encrypter.service;

import com.diagorn.encrypter.core.domain.Symbol;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A service that creates all the needed symbols in database on application startup
 *
 * @author Diagorn
 */
@Service
public class DbInitService implements ApplicationContextAware {

    private final MongoTemplate mongoTemplate;
    private ApplicationContext applicationContext;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${db.initFile.name}")
    private String jsonFileName;

    public DbInitService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Parse symbols from file and save to DB
     */
    @PostConstruct
    public void execute() {
        String json = getJson();
        List<Symbol> initialList = parse(json);
        saveToDb(initialList);
    }

    /**
     * Read file with JSON data
     * @return parsed JSON
     */
    private String getJson() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(jsonFileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is), StandardCharsets.UTF_8))) {
            return br.lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            closeContext("Error getting init JSON from file");
            return "";
        }
    }

    /**
     * Parse JSON
     * @param json - json string
     * @return List of Symbol objects
     */
    private List<Symbol> parse(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, new TypeReference<List<Symbol>>() {});
        } catch (JsonProcessingException e) {
            closeContext("Error parsing JSON");
            throw new RuntimeException();
        }
    }

    /**
     * Save all the symbols to database
     * @param initialList - list containing all the symbols
     */
    private void saveToDb(List<Symbol> initialList) {
        try {
            mongoTemplate.dropCollection(Symbol.class);
        } catch (Exception ignored) {}

        try {
            mongoTemplate.insert(initialList, Symbol.class);
        } catch (Exception e) {
            closeContext("Error inserting JSON to database");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private void closeContext(String errorMessage) {
        logger.error(errorMessage + ". Context closing now");
        SpringApplication.exit(applicationContext, () -> -1);
    }
}
