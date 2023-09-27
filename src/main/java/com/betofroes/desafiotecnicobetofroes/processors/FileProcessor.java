package com.betofroes.desafiotecnicobetofroes.processors;

public interface FileProcessor {

    default String processFile(String input){
        throw new UnsupportedOperationException("Not supported yet.");
    };
}