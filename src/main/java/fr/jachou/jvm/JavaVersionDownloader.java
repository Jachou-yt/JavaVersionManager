package fr.jachou.jvm;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class JavaVersionDownloader {
    // Class

    /**
     * Colors class
     * This class is used to color the text in the console
     */
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
    }

    /**
     * Main method
     * @param args The arguments
     */

    public static void main(String[] args) {
        boolean loop = true;

        while (loop) {
            Scanner scanner = new Scanner(System.in);

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
                int version = Integer.parseInt(scanner.nextLine());
                downloader(version);
            } catch (NumberFormatException e) {
                System.out.println(Colors.FAIL + "You must enter an integer!");
            }

            System.out.println();
        }
    }

    /**
     * Unzip a file
     * @param version The version of java to unzip
     */

    private static void downloader(int version) {
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

