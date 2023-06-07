package fr.jachou.jvm.utils;

public class Logger {

    private static final String PREFIX = "[JVM] ";

    static class Colors {
        public static final String HEADER = "\033[95m";
        public static final String OKBLUE = "\033[94m";
        public static final String OKCYAN = "\033[96m";
        public static final String OKGREEN = "\033[92m";
        public static final String WARNING = "\033[93m";
        public static final String FAIL = "\033[91m";
        public static final String ENDC = "\033[0m";
        public static final String BOLD = "\033[1m";
        public static final String UNDERLINE = "\033[4m";
        public static final String RESET = "\033[0m";
    }

    /**
     * Log a warning
     * @param message The message
     */

    public static void logWarning(String message) {
            System.out.println(PREFIX + Colors.WARNING + message + Colors.RESET);
    }

    /**
     * Log a pass
     * @param message The message
     */

    public static void logPass(String message) {
            System.out.println(PREFIX + Colors.OKGREEN + message + Colors.RESET);
    }

    /**
     * Log a message
     * @param message The message
     */
    public static void log(String message) {
            System.out.println(PREFIX + Colors.OKBLUE + message + Colors.RESET);
        }

    /**
     * Log an error
     * @param message The message
     */

    public static void logError(String message) {
            System.err.println(PREFIX + Colors.FAIL + message + Colors.RESET);
        }

    /**
     * Log an error
     * @param e The exception
     */

    public static void logError(Exception e) {
            System.err.println(e.getMessage());
        }

    /**
     * Log an error
     * @param message The message
     * @param e The exception
     */

    public static void logError(String message, Exception e) {
            System.err.println(PREFIX + Colors.FAIL + message + Colors.RESET);
            System.err.println(e.getMessage());
    }
}
