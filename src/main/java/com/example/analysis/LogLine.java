package com.example.analysis;

import com.example.exception.LogException;
import lombok.AllArgsConstructor;

import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class LogLine {
    private String str;

    private static final Pattern ERROR_STATUS = Pattern.compile("(\\s)(5)\\d{2}(\\s)");
    private static final Pattern RESPONSE_TIME_PATTERN = Pattern.compile("(\\s)(\\d+\\.\\d+)(\\s)");
    private static final Pattern TIME_PATTERN = Pattern.compile("(:)(\\d{2}:\\d{2}:\\d{2})(\\s)");

    public boolean isFailed(double minTime) {
        Matcher errorMatcher = ERROR_STATUS.matcher(str);
        Matcher timeMatcher = RESPONSE_TIME_PATTERN.matcher(str);
        if (errorMatcher.find() && (Integer.parseInt(errorMatcher.group().trim()) >= 500 ||
                Integer.parseInt(errorMatcher.group().trim()) <= 599)) {
            return true;
        }
        if (timeMatcher.find() && Double.parseDouble(timeMatcher.group().trim()) > minTime) {
            return true;
        }
        return false;
    }

    public String getStartTime() throws LogException {
        Matcher matcher = TIME_PATTERN.matcher(str);
        if (matcher.find()) {
            return matcher.group().substring(1).trim();
        }
        throw new LogException(String.format("Can't find time in log file with line: %s", str));
    }

    public String getEndTime() throws LogException {
        String startTime = getStartTime();
        String[] arr = startTime.split(":");
        LocalTime localTime = LocalTime.of(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]),
                Integer.parseInt(arr[2]));

        Matcher timeMatcher = RESPONSE_TIME_PATTERN.matcher(str);
        if (timeMatcher.find()) {
            return localTime.plusSeconds((long) (Double.parseDouble(timeMatcher.group().trim()) / 1000)).toString();
        }

        throw new LogException(String.format("Cant create end time for log-line %s", str));
    }
}