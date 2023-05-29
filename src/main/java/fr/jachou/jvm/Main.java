package fr.jachou.jvm;

import fr.flowarg.flowlogger.Logger;
import fr.jachou.jvm.managers.JavaVersionManager;
import fr.jachou.jvm.managers.utils.JavaVersionList;

import java.io.File;
import java.net.URISyntaxException;

public class Main {
    private static Logger logger;

    static {
        try {
            logger = new Logger("[JavaVersionManager]", new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath(), "jvm.log").toPath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JavaVersionManager javaVersionManager = new JavaVersionManager();
        javaVersionManager.downloadVersion(JavaVersionList.Java_8, "C:\\");
        javaVersionManager.unzipJavaVersion();
    }

    public static Logger getLogger() {
        return logger;
    }
}
