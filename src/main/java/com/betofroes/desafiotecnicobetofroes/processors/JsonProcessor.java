package com.betofroes.desafiotecnicobetofroes.processors;

import com.betofroes.desafiotecnicobetofroes.dto.JsonDto;
import com.betofroes.desafiotecnicobetofroes.dto.TxtToJsonDto;
import com.betofroes.desafiotecnicobetofroes.exceptions.FileProcessorException;
import com.betofroes.desafiotecnicobetofroes.util.DateUtil;
import com.betofroes.desafiotecnicobetofroes.util.FileUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JsonProcessor implements FileProcessor {

    private static Logger logger = Logger.getLogger(JsonProcessor.class.getName());

    @Override
    public String processFile(String values) {

        logger.info("JsonProcessor.class method processFile");

        try {
            List<JsonDto> valuesConvert = convertedValues(values);
            return FileUtil.mapper.writeValueAsString(valuesConvert);
        } catch (JsonProcessingException e) {
            throw new FileProcessorException(e);
        }finally {
            logger.info("END - Processing JSON file OUTPUT");
        }
    }

    private List<JsonDto> convertedValues(String values) {

        try {
            List<TxtToJsonDto> txtToJsonDto = FileUtil.mapper.readValue(values, new TypeReference<List<TxtToJsonDto>>() {});

            List<JsonDto> listResult = new ArrayList<>();

            txtToJsonDto.forEach( txt -> {
                JsonDto jsonDto = new JsonDto();
                jsonDto.setId(txt.getId());

                if(listResult.contains(jsonDto)) {
                    jsonDto = listResult.get(listResult.indexOf(jsonDto));
                } else {
                    jsonDto.setName(txt.getNome());
                    listResult.add(jsonDto);
                }

                JsonDto.Order order = new JsonDto.Order();
                order.setId(txt.getIdPedido());

                if(jsonDto.getOrders().contains(order)) {
                    order = jsonDto.getOrders().get(jsonDto.getOrders().indexOf(order));
                } else {
                    order.setTotal(txt.getValor());
                    order.setDate(DateUtil.format(txt.getDataCompra(), "yyyyMMdd", "yyyy-MM-dd"));
                    jsonDto.getOrders().add(order);
                }

                JsonDto.Product product = new JsonDto.Product();
                product.setId(txt.getIdProduto());
                product.setValue(txt.getValor());
                order.getProducts().add(product);
            });

            return listResult;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}