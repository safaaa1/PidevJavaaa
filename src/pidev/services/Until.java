/*package pidev.services;


import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author islem
 */
/*

*/
/*
public class Until {


    public static void configureLogging() {
        String value = System.getenv("QUICKSTART_DEBUG");
        if (value != null) {
            Handler handler = new ConsoleHandler();
            handler.setLevel(Level.FINEST);
            Logger logger = Logger.getLogger("com.nexmo");
            logger.setLevel(Level.FINEST);
            logger.addHandler(handler);
        }
    }



    public static String envVar(String key) {
        String value = System.getenv(key);
        if (value == null) {
            throw new IllegalArgumentException("You must provide the " + key + " environment variable!");
        }
        return value;
    }



    public static boolean booleanEnvVar(String key) {
        String stringValue = System.getenv(key);
        if (stringValue == null) {
            return false;
        } else {
            stringValue = stringValue.trim().toLowerCase();
        }

        if ("1".equals(stringValue) || "true".equals(stringValue) || "on".equals(stringValue)) {
            return true;
        } else if ("0".equals(stringValue) || "false".equals(stringValue) || "off".equals(stringValue)) {
            return false;
        } else {
            throw new IllegalArgumentException(
                    String.format("The value \"%s\" could not be converted to a boolean value", stringValue));
        }
    }
}*/
