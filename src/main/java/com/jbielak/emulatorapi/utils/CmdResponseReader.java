package com.jbielak.emulatorapi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;

public abstract class CmdResponseReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmdResponseReader.class);

    public static String readResponse(BufferedReader bufferedReader) {
        LOGGER.info("Reading socket started.");

        StringBuffer sb = new StringBuffer();
        String line;
        try {
            if (!bufferedReader.ready()) {
                Thread.sleep(2000);
            }
            while (bufferedReader.ready()) {
                line = bufferedReader.readLine();
                sb.append(line).append(" ");
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
