package com.example.service;

import com.example.exception.IncorrectAvailableException;
import com.example.exception.IncorrectTimeException;

import java.util.Arrays;
import java.util.List;

public class ArgsService {
    private final List<String> args;
    private String fileName;
    private double minAvailablePercentage;
    private double minTimeInMc;

    public ArgsService(String[] args) {
        this.args = Arrays.asList(args);

        fileName = "access.log";
    }

    public void verifyStart() throws IncorrectTimeException, IncorrectAvailableException {
        if (argsIsEmpty()) throw new IllegalArgumentException("Arguments count must be greater than 0");
        readArgs();
    }

    private boolean argsIsEmpty() {
        return args.size() == 0;
    }

    private void readArgs() throws IncorrectAvailableException, IncorrectTimeException {
        if (!args.contains("-u"))
            throw new IncorrectAvailableException("Parameter -u is not specified");
        minAvailablePercentage = Double.parseDouble(args.get(args.indexOf("-u") + 1));

        if (!args.contains("-t"))
            throw new IncorrectTimeException("Parameter -t is not specified");
        minTimeInMc = Double.parseDouble(args.get(args.indexOf("-t") + 1));

        if (args.contains("-f"))
            fileName = args.get(args.indexOf("-f") + 1);
    }

    public double getMinAvailablePercentage() {
        return minAvailablePercentage;
    }

    public double getMinTimeInMc() {
        return minTimeInMc;
    }

    public String getFileName() {
        return fileName;
    }
}