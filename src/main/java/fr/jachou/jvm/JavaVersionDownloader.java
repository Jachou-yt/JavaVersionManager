package fr.jachou.jvm;

import fr.jachou.jvm.managers.utils.JavaVersionList;
import fr.jachou.jvm.ui.JVMGui;
import fr.jachou.jvm.utils.CustomOutputStream;
import fr.jachou.jvm.utils.Logger;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class JavaVersionDownloader {
    // Class

    /**
     * Colors class
     * This class is used to color the text in the console
     */
    public static class Colors {
        public static final String HEADER = "\033[95m";
        public static final String OKBLUE = "\033[94m";
        public static final String OKCYAN = "\033[96m";
        public static final String OKGREEN = "\033[92m";
        public static final String WARNING = "\033[93m";
        public static final String FAIL = "\033[91m";
        public static final String ENDC = "\033[0m";
        public static final String BOLD = "\033[1m";
        public static final String UNDERLINE = "\033[4m";
    }

    /**
     * Main method
     * @param args The arguments
     */

    public static void main(String[] args) {

        if (args != null) {
            if (Objects.equals(args[0], "-p") || Objects.equals(args[0], "--python")) {
                Logger.log("Python mod enabled!");

                String pythonFile = "src/main/java/fr/jachou/jvm/py/java_dl.py";

                try {
                    Runtime runtime = Runtime.getRuntime();
                    Process process = runtime.exec("python " + pythonFile);

                    // Lire la sortie du processus Python
                    InputStream inputStream = process.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }

                    // Attendre que le processus Python se termine
                    int exitCode = process.waitFor();
                    System.out.println("Le processus Python s'est terminé avec le code de sortie : " + exitCode);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (Objects.equals(args[0], "-g") || Objects.equals(args[0], "--gui")) {
                Logger.log("GUI mod enabled!");

                new JVMGui();
            }
        } else {
            boolean loop = true;

            while (loop) {
                // Message JVM = JavaVersionManager
                System.out.println(Colors.HEADER + " \n"
                        + "____________    ________  ___\n"
                        + "______  /__ |  / /___   |/  /\n"
                        + "___ _  / __ | / / __  /|_/ /\n"
                        + "/ /_/ /  __ |/ /  _  /  / / \n"
                        + "\\____/   _____/   /_/  /_/  \n"
                        + "                              \n");

                try {
                    System.out.print(Colors.OKCYAN + "Choose your version (only number) : ");
                    Scanner scanner = new Scanner(System.in);
                    int version = Integer.parseInt(scanner.nextLine());
                    if (version == 0) {
                        Logger.log("Exiting...");
                        System.exit(0);
                    }
                    downloader(version);
                } catch (NumberFormatException e) {
                    System.out.println(Colors.FAIL + "You must enter an integer!");
                }
            }
        }
    }

    /**
     * Unzip a file
     * @param version The version of java to unzip
     */

    private static void downloader(int version) {
        try {
            String versionString = "Java_" + version;
            JavaVersionList.valueOf(versionString);
        } catch (IllegalArgumentException e) {
            System.out.println(Colors.FAIL + "[X] The version " + version + " does not exist!");
            return;
        }

        Logger.logPass("The version " + version + " exists!");

        System.out.print(Colors.BOLD + "[~] Checking if the link is good...");
        String url = String.format("https://chiss.fr/jvm/download/Java_%d.zip", version);
        try {
            URL link = new URL(url);
            link.openStream().close();
            System.out.println(Colors.OKGREEN + "[✓] The link is good!");
            Thread.sleep(1000);
            String path = System.getProperty("user.home") + "/.jdks";
            System.out.println(Colors.OKCYAN + "The JDK " + version + " will be downloaded in the folder '" + path + "'");
            File directory = new File(path);
            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    System.out.println(Colors.OKGREEN + "[✓] The folder has been created!");
                }
            } else {
                System.out.println(Colors.WARNING + "[~] The folder already exists");
            }
            Thread.sleep(1000);
            System.out.print(Colors.BOLD + "[~] Downloading...");
            Path destination = Paths.get(path + "/Java_" + version + ".zip");
            Files.copy(link.openStream(), destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(Colors.OKGREEN + "[✓] The JDK " + version + " has been downloaded!");
            Thread.sleep(1000);
            System.out.print(Colors.BOLD + "[~] Unzipping...");
            unzip(destination.toString(), path + "/Java_" + version);
            System.out.println(Colors.OKGREEN + "[✓] The JDK " + version + " has been unzipped!");
            Thread.sleep(1000);
            System.out.print(Colors.BOLD + "[~] Deleting zip file...");
            Files.delete(destination);
            System.out.println(Colors.OKGREEN + "[✓] The JDK " + version + " has been deleted!");
            Thread.sleep(1000);
            System.out.println(Colors.OKGREEN + "[✓] The JDK " + version + " has been downloaded and installed!");
            Thread.sleep(1000);
        } catch (IOException e) {
            System.out.println(Colors.FAIL + "[X] The link does not exist!");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Unzip it
     * @param zipFilePath input zip file
     * @param destinationDir zip file output folder
     * @throws IOException if an I/O error has occurred
     */

    private static void unzip(String zipFilePath, String destinationDir) throws IOException {
        File dir = new File(destinationDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        byte[] buffer = new byte[1024];
        try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(Paths.get(zipFilePath)))) {
            ZipEntry entry = zis.getNextEntry();
            while (entry != null) {
                String fileName = entry.getName();
                File newFile = new File(destinationDir + File.separator + fileName);
                if (entry.isDirectory()) {
                    newFile.mkdirs();
                } else {
                    new File(newFile.getParent()).mkdirs();
                    Files.copy(zis, newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                zis.closeEntry();
                entry = zis.getNextEntry();
            }
        }
    }
}

