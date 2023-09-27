package com.betofroes.desafiotecnicobetofroes.processors;

import com.betofroes.desafiotecnicobetofroes.dto.JsonDto;
import com.betofroes.desafiotecnicobetofroes.exceptions.FileProcessorException;
import com.betofroes.desafiotecnicobetofroes.util.FileUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonProcessorTest {

    @Test
    public void should_return_string_when_process_file_input() throws JsonProcessingException {
        // given
        JsonProcessor jsonProcessor = new JsonProcessor();

        String values = "[{\"id_usuário\":\"0000000070\",\"valor_do_produto\":\"1836.74\",\"nome\":\"Palmer Prosacco\",\"id_pedido\":\"0000000753\",\"id_produto\":\"0000000003\",\"data_compra\":\"20210308\"}]";
        // when
        String result = jsonProcessor.processFile(values);

        List<JsonDto> listResult = FileUtil.mapper.readValue(result, new TypeReference<List<JsonDto>>() {});

        // then
        assertNotNull(result);
        assertEquals(1, listResult.size());
        assertEquals(70, listResult.get(0).getId());
        assertEquals("Palmer Prosacco", listResult.get(0).getName());
        assertEquals(1, listResult.get(0).getOrders().size());
        assertEquals(753, listResult.get(0).getOrders().get(0).getId());
        assertEquals("2021-03-08", listResult.get(0).getOrders().get(0).getDate());
        assertEquals(1, listResult.get(0).getOrders().get(0).getProducts().size());
        assertEquals(3, listResult.get(0).getOrders().get(0).getProducts().get(0).getId());
        assertEquals("1836.74", listResult.get(0).getOrders().get(0).getProducts().get(0).getValue());
    }

    @Test
    public void should_return_string_when_process_file_input_with_two_orders_two_products() throws JsonProcessingException {
        // given
        JsonProcessor jsonProcessor = new JsonProcessor();

        String values = "[{\"id_usuário\":\"0000000070\",\"valor_do_produto\":\"1836.74\",\"nome\":\"Palmer Prosacco\",\"id_pedido\":\"0000000753\",\"id_produto\":\"0000000003\",\"data_compra\":\"20210308\"},\n" +
                " {\"id_usuário\":\"0000000070\",\"valor_do_produto\":\"1578.57\",\"nome\":\"Palmer Prosacco\",\"id_pedido\":\"0000000753\",\"id_produto\":\"0000000003\",\"data_compra\":\"20210308\"},\n" +
                " {\"id_usuário\":\"0000000070\",\"valor_do_produto\":\"942.54\",\"nome\":\"Palmer Prosacco\",\"id_pedido\":\"0000000751\",\"id_produto\":\"0000000005\",\"data_compra\":\"20210307\"},\n" +
                " {\"id_usuário\":\"0000000070\",\"valor_do_produto\":\"1051.17\",\"nome\":\"Palmer Prosacco\",\"id_pedido\":\"0000000751\",\"id_produto\":\"0000000006\",\"data_compra\":\"20210307\"}]";
        // when
        String result = jsonProcessor.processFile(values);

        List<JsonDto> listResult = FileUtil.mapper.readValue(result, new TypeReference<List<JsonDto>>() {});

        // then
        assertNotNull(result);
        assertEquals(1, listResult.size());
        assertEquals(70, listResult.get(0).getId());
        assertEquals("Palmer Prosacco", listResult.get(0).getName());

        assertEquals(2, listResult.get(0).getOrders().size());
        assertEquals(753, listResult.get(0).getOrders().get(0).getId());
        assertEquals(751, listResult.get(0).getOrders().get(1).getId());

        assertEquals("2021-03-08", listResult.get(0).getOrders().get(0).getDate());
        assertEquals(2, listResult.get(0).getOrders().get(0).getProducts().size());
        assertEquals(3, listResult.get(0).getOrders().get(0).getProducts().get(0).getId());
        assertEquals("1836.74", listResult.get(0).getOrders().get(0).getProducts().get(0).getValue());
        assertEquals("2021-03-08", listResult.get(0).getOrders().get(0).getDate());
    }

    @Test
    void should_throw_illegal_argument_exception_when_process_file_with_values_null() {
        JsonProcessor jsonProcessor = new JsonProcessor();
        assertThrows(IllegalArgumentException.class, () -> jsonProcessor.processFile(null));
    }
}