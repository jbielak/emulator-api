package com.jbielak.emulatorapi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;

public abstract class CmdResponseReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmdResponseReader.class);

    private static final int DEFAULT_WAITING_TIME_MILLIS = 1600;

    public static String readResponse(BufferedReader bufferedReader, boolean oneLineConcat) {
        return readResponse(bufferedReader, oneLineConcat, DEFAULT_WAITING_TIME_MILLIS);
    }

    public static String readResponse(BufferedReader bufferedReader, boolean oneLineConcat,
                                      Integer waitingTimeMillis) {
        LOGGER.info("Reading socket started.");

        String delimiter = oneLineConcat ? " " : "\n";

        StringBuffer sb = new StringBuffer();
        String line;
        try {
            Thread.sleep(waitingTimeMillis);
            while (bufferedReader.ready()) {
                line = bufferedReader.readLine();
                sb.append(line).append(delimiter);
            }
        } catch (Exception e) {
            LOGGER.error("Error while reading from socket.", e);
            e.printStackTrace();
        }

        LOGGER.info("Socket output:" + sb.toString().trim());
        LOGGER.info("Reading socket finished.");
        return sb.toString().trim();
    }

    public static void clearOutput(BufferedReader bufferedReader) {
        LOGGER.info("Clearing socket started.");

        StringBuffer sb = new StringBuffer();
        String line;

        try {
            while (bufferedReader.ready()) {
                line = bufferedReader.readLine();
                sb.append(line).append(" ");
            }
        } catch (Exception e) {
            LOGGER.error("Error while reading from socket.", e);
        }
        LOGGER.info("Socket output:" + sb.toString().trim());
        LOGGER.info("Clearing socket finished.");
    }
}
