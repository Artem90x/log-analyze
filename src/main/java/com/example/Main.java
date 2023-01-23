package com.example;

import com.example.exception.IncorrectAvailableException;
import com.example.exception.IncorrectTimeException;
import com.example.service.ArgsService;
import com.example.service.ReadService;

import java.io.File;

public class Main {
    public static void main(String[] args) throws IncorrectTimeException, IncorrectAvailableException {
        ArgsService argsService = new ArgsService(args);
        argsService.verifyStart();

        ReadService readManager = new ReadService(new File(argsService.getFileName()),
                argsService.getMinAvailablePercentage(), argsService.getMinTimeInMc());
        readManager.start();
    }
}
