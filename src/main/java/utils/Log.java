package utils;

import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

    // Log4j logger
    private static final Logger logger = LogManager.getLogger(Log.class);

    // Thread-safe ExtentTest for parallel execution
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    // Set ExtentTest from BaseTest or test class
    public static void setExtentTest(ExtentTest test) {
        extentTest.set(test);
    }

    // Remove after test finishes (to prevent memory leak)
    public static void clear() {
        extentTest.remove();
    }

    // Log info level
    public static void info(String message) {
        logger.info(message);
        if (extentTest.get() != null) {
            extentTest.get().info(message);
        }
    }

    // Log warning level
    public static void warn(String message) {
        logger.warn(message);
        if (extentTest.get() != null) {
            extentTest.get().warning(message);
        }
    }

    // Log error level
    public static void error(String message) {
        logger.error(message);
        if (extentTest.get() != null) {
            extentTest.get().fail(message);
        }
    }

    // Log pass explicitly
    public static void pass(String message) {
        if (extentTest.get() != null) {
            extentTest.get().pass(message);
        }
    }

    // Log fail with throwable
    public static void fail(Throwable t) {
        logger.error("Exception: ", t);
        if (extentTest.get() != null) {
            extentTest.get().fail(t);
        }
    }
}
