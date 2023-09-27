package com.betofroes.desafiotecnicobetofroes.processors;

import com.betofroes.desafiotecnicobetofroes.dto.TxtDto;
import com.betofroes.desafiotecnicobetofroes.util.FileUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TxtProcessorTest {

    @Test
    public void should_return_string_when_process_file_input() throws JsonProcessingException {

        String valuesTest = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";

        List<TxtDto> config = List.of(
                new TxtDto(1, "id_usuário", 10),
                new TxtDto(2, "nome", 45),
                new TxtDto(3, "id_pedido", 10),
                new TxtDto(4, "id_produto", 10),
                new TxtDto(5, "valor_do_produto", 12),
                new TxtDto(6, "data_compra", 8)
        );

        FileProcessor processor = new TxtProcessor(config);

        String result = processor.processFile(valuesTest);

        List<Map<String, Object>> resultConvert = FileUtil.mapper.readValue(result, new TypeReference<List<Map<String, Object>>>() {});

        assertNotNull(result);
        assertEquals(1, resultConvert.size());
        assertEquals(6, resultConvert.get(0).size());
        assertEquals("0000000070", resultConvert.get(0).get("id_usuário"));
        assertEquals("Palmer Prosacco", resultConvert.get(0).get("nome"));
        assertEquals("0000000753", resultConvert.get(0).get("id_pedido"));
        assertEquals("0000000003", resultConvert.get(0).get("id_produto"));
        assertEquals("1836.74", resultConvert.get(0).get("valor_do_produto"));
        assertEquals("20210308", resultConvert.get(0).get("data_compra"));
    }

    @Test
    void should_throw_illegal_argument_exception_when_process_file_with_param_null() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new TxtProcessor(null));
        assertEquals("Config file is empty", exception.getMessage());
    }

    @Test
    void should_throw_illegal_argument_exception_when_process_file_with_values_null() {

        List<TxtDto> config = List.of(
                new TxtDto(1, "id_usuário", 10),
                new TxtDto(2, "nome", 45),
                new TxtDto(3, "id_pedido", 10),
                new TxtDto(4, "id_produto", 10),
                new TxtDto(5, "valor_do_produto", 12),
                new TxtDto(6, "data_compra", 8)
        );

        FileProcessor processor = new TxtProcessor(config);

        var exception = assertThrows(IllegalArgumentException.class, () -> processor.processFile(null));
        assertEquals("File content is empty", exception.getMessage());
    }
}