package com.betofroes.desafiotecnicobetofroes.processors;

import com.betofroes.desafiotecnicobetofroes.dto.TxtDto;
import com.betofroes.desafiotecnicobetofroes.exceptions.FileProcessorException;
import com.betofroes.desafiotecnicobetofroes.service.ExecutionService;
import com.betofroes.desafiotecnicobetofroes.util.FileUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TxtProcessor implements FileProcessor {

    private static Logger logger = Logger.getLogger(TxtProcessor.class.getName());

    private final List<TxtDto> configFile;

    public TxtProcessor(List<TxtDto> configFile) {
        if(Objects.isNull(configFile) || configFile.isEmpty()) {
            throw new IllegalArgumentException("Config file is empty");
        }
        this.configFile = configFile.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public String processFile(String fileContent) {
        try {
            logger.info("TxtProcessor.class method processFile");

            if (Objects.isNull(fileContent) || fileContent.isEmpty()) {
                throw new IllegalArgumentException("File content is empty");
            }

            String[] lines = extractedLines(fileContent);
            List<Map<String, Object>> listValues = Stream.of(lines).map(this::convertedValues).collect(Collectors.toList());

            return FileUtil.mapper.writeValueAsString(listValues);
        } catch (JsonProcessingException e) {
            throw new FileProcessorException(e);
        } finally {
            logger.info("FINISHED Processing TXT file INPUT");
        }
    }

    private Map<String, Object> convertedValues(String line) {
        AtomicInteger index = new AtomicInteger(0);
        Map<String, Object> mapValues = new HashMap<>();
        configFile.forEach(config -> {
            if(line.isEmpty() || line.length() < (index.get() + config.getSize())) {
                throw new IllegalArgumentException("Line is empty or size is less than config size  line: " + line);
            }
            String value = line.substring(index.get(), index.get() + config.getSize()).trim();
            index.addAndGet(config.getSize());

            logger.info("key: " + config.getIdField() + " - Value: " + value);

            mapValues.put(config.getIdField(), value);
        });
        return mapValues;
    }

    private static String[] extractedLines(String fileContent) {
        try {
            return fileContent.split("\n");
        } catch (Exception e) {
            throw new IllegalArgumentException("Error get lines: " + e.getMessage());
        }
    }
}
