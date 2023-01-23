package com.example.analysis;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OutputLogLine {

    private double percentage;
    private String startTime;
    private String endTime;

    @Override
    public String toString() {
        return startTime + "\t" + endTime + "\t" + String.format("%.1f", percentage);
    }
}
