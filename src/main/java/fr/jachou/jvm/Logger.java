package fr.jachou.jvm;

import java.io.File;
import java.net.URISyntaxException;

public class Logger {
    private static fr.flowarg.flowlogger.Logger logger;

    static {
        try {
            logger = new fr.flowarg.flowlogger.Logger("[JavaVersionManager]", new File(Logger.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath(), "jvm.log").toPath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public static fr.flowarg.flowlogger.Logger getLogger() {
        return logger;
    }
}
