package daehee.challengehub.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {

    private LoggerUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void info(String className, String methodName, String message) {
        Logger logger = LoggerFactory.getLogger(className);
        logger.info("[" + methodName + "] " + message);
    }

    public static void error(String className, String methodName, String message, Throwable throwable) {
        Logger logger = LoggerFactory.getLogger(className);
        logger.error("[" + methodName + "] " + message, throwable);
    }

    public static void warn(String className, String methodName, String message) {
        Logger logger = LoggerFactory.getLogger(className);
        logger.warn("[" + methodName + "] " + message);
    }

    public static void debug(String className, String methodName, String message) {
        Logger logger = LoggerFactory.getLogger(className);
        logger.debug("[" + methodName + "] " + message);
    }
}
