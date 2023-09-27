package com.betofroes.desafiotecnicobetofroes.service;

import com.betofroes.desafiotecnicobetofroes.DesafioTecnicoBetofroesApplication;
import com.betofroes.desafiotecnicobetofroes.exceptions.ExecutionServiceException;
import com.betofroes.desafiotecnicobetofroes.processors.FileProcessor;
import com.betofroes.desafiotecnicobetofroes.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class ExecutionService {

    private static Logger logger = Logger.getLogger(ExecutionService.class.getName());

    private final String pathInput;
    private final String pathOutput;
    private final FileProcessor input;
    private final FileProcessor output;

    private ExecutionService(String pathInput, String pathOutput, FileProcessor input, FileProcessor output) {
        this.pathInput = pathInput;
        this.pathOutput = pathOutput;
        this.input = input;
        this.output = output;
    }

    public static ExecutionService getInstance(String pathInput, String pathOutput, FileProcessor input, FileProcessor output) {
        return new ExecutionService(pathInput, pathOutput, input, output);
    }

    public void execution() {

        logger.info("method execution");
        executionProcess();
        removeProcessedFiles();
    }

    private void removeProcessedFiles() {
        FileUtil.removeAllFilesInFolder(new File(this.pathInput));
    }

    private void executionProcess() {
        List<String> allFilesInFolder = FileUtil.findAllFilesInFolder(new File(this.pathInput));

        AtomicInteger count = new AtomicInteger(0);
        allFilesInFolder.forEach(file -> {
            String fileTxt = input.processFile(file);
            String fileJson = output.processFile(fileTxt);
            try {
                FileUtil.writeOutPutFile(fileJson, this.pathOutput+"_" + count.incrementAndGet() + ".json");
            } catch (IOException e) {
                throw new ExecutionServiceException(e);
            }
        });
    }
}
