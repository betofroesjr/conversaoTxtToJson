package com.betofroes.desafiotecnicobetofroes.service;

import com.betofroes.desafiotecnicobetofroes.dto.TxtDto;
import com.betofroes.desafiotecnicobetofroes.processors.JsonProcessor;
import com.betofroes.desafiotecnicobetofroes.processors.TxtProcessor;
import com.betofroes.desafiotecnicobetofroes.util.FileUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ExecutionServiceTest {

    @TempDir
    static Path sharedTempInput;

    @TempDir
    static Path sharedTempOutput;

    @AfterEach
    void tearDown() {
        deleteFilesRecursive(sharedTempInput.toFile());
        deleteFilesRecursive(sharedTempOutput.toFile());
    }

    private void deleteFilesRecursive(File fileFinal){
        if(fileFinal.exists() && fileFinal.isDirectory()) {
            if(fileFinal.isDirectory()){
                File[] files = fileFinal.listFiles();
                for(File f : files) {
                    if(fileFinal.isDirectory()){
                        deleteFilesRecursive(f);
                    } else {
                        f.delete();
                    }
                    f.delete();
                }
            } else {
                fileFinal.delete();
            }
        }
    }

    @Test
    void should_execution_standardized() throws IOException {

        File inputFile = new File(sharedTempInput + "/input.txt");
        Files.write(inputFile.toPath(), Arrays.asList("0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308"));

        List<TxtDto> config = List.of(
                new TxtDto(1, "id_usuario", 10),
                new TxtDto(2, "nome", 45),
                new TxtDto(3, "id_pedido", 10),
                new TxtDto(4, "id_produto", 10),
                new TxtDto(5, "valor_do_produto", 12),
                new TxtDto(6, "data_compra", 8)
        );

        ExecutionService.getInstance(
                sharedTempInput.toFile().getPath(),
                sharedTempOutput.toFile().getPath()+"/data_output",
                new TxtProcessor(config),
                new JsonProcessor()).execution();

        File outPutFile = sharedTempOutput.toFile().listFiles()[0];

        assertTrue(outPutFile.exists());
        String result = FileUtil.getString(outPutFile);

        assertTrue(result.contains("[{\"name\":\"Palmer Prosacco\",\"orders\":[{\"total\":\"1836.74\",\"date\":\"2021-03-08\",\"products\":[{\"value\":\"1836.74\",\"product_id\":3}],\"order_id\":753}],\"user_id\":70}]"));
    }
}