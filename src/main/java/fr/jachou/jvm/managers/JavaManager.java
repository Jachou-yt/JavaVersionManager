package fr.jachou.jvm.managers;

import fr.jachou.jvm.managers.utils.JavaVersionList;
import fr.jachou.jvm.utils.ListToString;
import fr.jachou.jvm.utils.Logger;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Map;

public class JavaManager {

    private JavaVersionManager javaVersionManager;
    private boolean useJavaVersionManager;

    /**
     * @param javaVersionManager The JavaVersionManager object
     * @param useJavaVersionManager If the JavaVersionManager object should be used
     */
    public JavaManager(JavaVersionManager javaVersionManager, boolean useJavaVersionManager) {
        this.javaVersionManager = javaVersionManager;
        this.useJavaVersionManager = useJavaVersionManager;
    }

    /**
     * The constructor without parameters
     */

    public JavaManager() {
        useJavaVersionManager = false;
    }

    /**
     * Executes a jar file with the Java version manager
     * @param jarPathFile The path of the jar file
     */

    public void executeJarWithSpecificVersion(Path jarPathFile)  {
        if (useJavaVersionManager) {
            Logger.log("Java version manager is enabled");
            try {
                Runtime.getRuntime().exec("\""+javaVersionManager.getPathOfJarExe()+"\" -jar -version:" + ListToString.JavaVersionListToString(javaVersionManager.getList()) + "\""+jarPathFile+"\"");
            } catch (IOException e) {
                Logger.logError("An error occurred while executing the jar file with the Java version manager" ,e);
            }

            Logger.logPass("The jar file has been executed successfully");
        } else {
            Logger.logError("Java version manager is not enabled");
        }
    }

    /**
     * Executes a jar file with the Java version manager
     * @param jarPathFile The path of the jar file
     * @param jarExe The path of the jar.exe file
     * @param javaVersionList The Java version list
     */

    public void executeJarWithSpecificVersion(Path jarPathFile, Path jarExe, JavaVersionList javaVersionList) {
        Logger.logWarning("You should use the JavaVersionManagerBuilder class to create a JavaVersionManager object and not use this method");
        if (!jarPathFile.endsWith(".jar")) {
            Logger.logError("The file is not a jar file");
            return;
        }
        if (!jarExe.endsWith(".exe")) {
            Logger.logError("The file is not an exe file");
            return;
        }
        try {
            Runtime.getRuntime().exec("\""+jarExe+"\" -jar -version:" + ListToString.JavaVersionListToString(javaVersionList) + "\""+jarPathFile+"\"");
        } catch (IOException e) {
            Logger.logError("An error occurred while executing the jar file with the Java version manager" ,e);
        }

        Logger.logPass("The jar file has been executed successfully");
    }

    /**
     * Execute a JAR file using a specific version of Java
     * @param jarPathFile The path of the JAR file
     * @param javaVersion The Java version to use
     */
    public void executeJarWithSpecificJavaVersion(String jarPathFile, String javaVersion) {
        String javaHome = System.getProperty("java.home");
        String javaExecutable = javaHome + File.separator + "bin" + File.separator + "java";

        String command = "\"" + javaExecutable + "\" -jar -version:" + javaVersion + " \"" + jarPathFile + "\"";

        try {
            Runtime.getRuntime().exec(command);
            Logger.logPass("The JAR file has been executed successfully with Java version: " + javaVersion);
        } catch (IOException e) {
            Logger.logError("An error occurred while executing the JAR file with the specified Java version", e);
        }
    }

    /**
     * Delete Java
     */

    public void deleteJava(Path java) {
        File file = new File(String.valueOf(java));
        if (!file.exists()) {
            Logger.logWarning("The Java folder does not exist");
            return;
        }
        if (!file.isDirectory()) {
            Logger.logWarning("The Java folder is not a directory");
            return;
        }
        Logger.log("Deleting Java folder...");
        file.delete();
        Logger.logPass("The Java folder has been deleted successfully");
     }

    /**
     * Delete Java
     */

    public void deleteJava() {
        if (useJavaVersionManager) {
            Logger.log("Deleting Java folder...");
            File file = new File(String.valueOf(javaVersionManager.getPathOfJarExe()));
            file.delete();
            Logger.logPass("The Java folder has been deleted successfully");
        } else {
            Logger.logWarning("Java version manager is not enabled. Please sepcify the Java version manager in the constructor or specify a path to the java folder");
        }
    }


    /**
     * Executes dynamically provided Java code.
     * @param code The Java code to execute
     * @return The output of the code execution
     */
    public String executeDynamicCode(String code) {
        try {
            Path sourceFile = Files.createTempFile("DynamicCode", ".java");
            Files.write(sourceFile, code.getBytes(), StandardOpenOption.CREATE);

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            int compilationResult = compiler.run(null, null, null, sourceFile.toAbsolutePath().toString());

            if (compilationResult == 0) {
                String className = sourceFile.getFileName().toString().replace(".java", "");
                Class<?> dynamicClass = Class.forName(className);
                Object instance = dynamicClass.getDeclaredConstructor().newInstance();

                Files.deleteIfExists(sourceFile);

                return "The code has been executed successfully. Output: " + instance.toString();
            } else {
                return "The code has not been executed successfully. Compilation error.";
            }
        } catch (Exception e) {
            return "An eroor has been ocurred" + e.getMessage();
        }
    }

    /**
     * Detects the Java environment variables
     * @return The Java environment variables in an ArrayList
     * @param print If the Java environment variables should be printed
     */

    public ArrayList<String> detectJavaEnvironmentVariables(boolean print) {
        Map<String, String> envVariables = System.getenv();
        String javaHome = envVariables.get("JAVA_HOME");
        String path = envVariables.get("PATH");
        String classPath = envVariables.get("CLASSPATH");

        ArrayList<String> javaEnvironmentVariables = new ArrayList<>();
        javaEnvironmentVariables.add("JAVA_HOME: " + javaHome);
        javaEnvironmentVariables.add("PATH: " + path);
        javaEnvironmentVariables.add("PATH: " + classPath);

        if (print) {
            Logger.log("Java environment variables:");
            for (String javaEnvironmentVariable : javaEnvironmentVariables) {
                Logger.log(javaEnvironmentVariable);
            }
        }

        return javaEnvironmentVariables;
    }

    /**
     * Sets the default Java version
     * @param javaHomePath The path of the Java home
     */

    public void setDefaultJavaVersion(Path javaHomePath) {
        Logger.logWarning("This method is not recommended to use.");
        try {
            System.setProperty("JAVA_HOME", String.valueOf(javaHomePath));

            String currentPath = System.getenv("PATH");
            String newPath = javaHomePath + "/bin:" + currentPath;
            System.setProperty("PATH", newPath);


            Logger.logPass("The default Java version has been set to: " + javaHomePath);
        } catch (Exception e) {
            Logger.logError("An error occurred while setting the default Java version", e);
        }
    }




    /*
     * Getter and setters
     */

    /**
     * @return If the Java version manager should be used
     */

    public boolean isUseJavaVersionManager() {
        return useJavaVersionManager;
    }

    /**
     * @param useJavaVersionManager If the Java version manager should be used
     */

    public void setUseJavaVersionManager(boolean useJavaVersionManager) {
        this.useJavaVersionManager = useJavaVersionManager;
    }

    /**
     * @return The JavaVersionManager object
     */

    public JavaVersionManager getJavaVersionManager() {
        return javaVersionManager;
    }

    /**
     * Sets the JavaVersionManager object
     * @param javaVersionManager The JavaVersionManager object
     */

    public void setJavaVersionManager(JavaVersionManager javaVersionManager) {
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
