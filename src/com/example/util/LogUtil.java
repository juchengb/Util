package com.example.util;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {
	private static final String LOG_FILE_PATH = "C:\\Users\\User\\Downloads\\application.log";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

    public static void log(String level, String message) {
        String timestamp = dateFormat.format(new Date());
        String logMessage = String.format("[%s] %s: %s", timestamp, level, message);
        System.out.println(logMessage);
        writeToFile(logMessage);
    }

    public static void info(String message) {
        log("INFO", message);
    }

    public static void warn(String message) {
        log("WARN", message);
    }

    public static void error(String message) {
        log("ERR", message);
    }

    private static void writeToFile(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE_PATH, true)) {
            writer.write(message + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Failed to write log to file: " + e.getMessage());
        }
    }
}
