/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package includes;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author kavindu
 */
public class LoggerConfig {

    private static Logger logger = Logger.getLogger(LoggerConfig.class.getName());

    public static Logger getLogger() {
        if (logger.getHandlers().length == 0) {
            try {
                FileHandler fileHandler = new FileHandler("logs/loggers.log", true); // 'true' for append mode

                SimpleFormatter formatter = new SimpleFormatter();
                fileHandler.setFormatter(formatter);

                logger.addHandler(fileHandler);

                logger.setLevel(java.util.logging.Level.ALL); // Log all levels of logs

            } catch (IOException e) {
                logger.severe("Failed to initialize logger: " + e.getMessage());
            }
        }
        return logger;
    }

}
