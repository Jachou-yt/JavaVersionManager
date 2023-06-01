package fr.jachou.jvm.managers;

import java.io.File;
import java.util.ArrayList;

public class JavaManager {

    private JavaVersionManager javaVersionManager;

    public JavaManager(JavaVersionManager javaVersionManager) {
        this.javaVersionManager = javaVersionManager;
    }

    /*
    Private methods
     */

    /**
     * Extracts the version information from the java.exe file
     * @param jdkDir The directory of the JDK
     * @return The version information
     */
    private static String getVersionFromJdkDir(File jdkDir) {
        String jdkDirName = jdkDir.getName();
        int versionStartIndex = jdkDirName.indexOf("jdk") + 3;
        int versionEndIndex = jdkDirName.indexOf('_', versionStartIndex);
        if (versionEndIndex == -1) {
            versionEndIndex = jdkDirName.length();
        }
        return jdkDirName.substring(versionStartIndex, versionEndIndex);
    }

    /*
    Public methods
     */

    /**
     * List all installed JDKs and Java versions on the PC
     * @return An ArrayList containing the installed JDKs and Java versions
     */

    public static ArrayList<String> listInstalledJDKs() {
        ArrayList<String> jdkList = new ArrayList<>();

        // Search for JDK installations in the Program Files/Java directory
        File programFilesJavaDir = new File(System.getenv("ProgramFiles") + "/Java");
        File[] jdkDirs = programFilesJavaDir.listFiles(File::isDirectory);
        if (jdkDirs != null) {
            for (File jdkDir : jdkDirs) {
                String jdkName = jdkDir.getName();
                jdkList.add(jdkName);
            }
        }

        // Get the Java version of the currently running JVM
        String currentJavaVersion = System.getProperty("java.version");
        jdkList.add("");
        jdkList.add("Current Java version: " + currentJavaVersion);

        return jdkList;
    }

    /**
     * Checks if a specific version of Java is installed
     * @param version The version to check
     * @return True if the version is installed, false if not
     */

    public static boolean isASpecificVersionInstalled(String version) {
        ArrayList<String> jdkList = listInstalledJDKs();
        for (String element : jdkList) {
            if (element.contains(version)) {
                return true;
            }
        }
        return false;
    }
}
