package test;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TestTimeLogger {
    private static final String LOGGER_FILE = "loggerFile.log";
    public static void setDataPrintableIntoFile(String data) {
        Logger logger = Logger.getLogger("Time logging");
        FileHandler fh;
        try {
            fh = new FileHandler(LOGGER_FILE, true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.info(data);
            fh.close();
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }
}
