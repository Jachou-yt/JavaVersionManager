package fr.jachou.jvm.ui;

import fr.jachou.jvm.JavaVersionDownloader;
import fr.jachou.jvm.managers.utils.JavaVersionList;
import fr.jachou.jvm.utils.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class JVMGui extends JFrame {
    public JVMGui() {
        setTitle("Java Version Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JTextField textField = new JTextField("Press a button to download a JDK");
        textField.setEditable(false);
        textField.setPreferredSize(new Dimension(300, 30));
        add(textField);

        // Créer les boutons pour chaque version de Java
        for (JavaVersionList version : JavaVersionList.values()) {
            JButton button = new JButton(version.toString().replace("_", " "));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Appeler la méthode downloader avec la version correspondante
                    downloader(version.ordinal() + 8);
                }
            });
            add(button);
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static void downloader(int version) {
        try {
            String versionString = "Java_" + version;
            JavaVersionList.valueOf(versionString);
        } catch (IllegalArgumentException e) {
            System.out.println(JavaVersionDownloader.Colors.FAIL + "[X] The version " + version + " does not exist!");
            return;
        }

        Logger.logPass("The version " + version + " exists!");

        System.out.print(JavaVersionDownloader.Colors.BOLD + "[~] Checking if the link is good...");
        String url = String.format("https://chiss.fr/jvm/download/Java_%d.zip", version);
        try {
            URL link = new URL(url);
            link.openStream().close();
            System.out.println(JavaVersionDownloader.Colors.OKGREEN + "[✓] The link is good!");
            Thread.sleep(1000);
            String path = System.getProperty("user.home") + "/.jdks";
            System.out.println(JavaVersionDownloader.Colors.OKCYAN + "The JDK " + version + " will be downloaded in the folder '" + path + "'");
            File directory = new File(path);
            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    System.out.println(JavaVersionDownloader.Colors.OKGREEN + "[✓] The folder has been created!");
                }
            } else {
                System.out.println(JavaVersionDownloader.Colors.WARNING + "[~] The folder already exists");
            }
            Thread.sleep(1000);
            System.out.print(JavaVersionDownloader.Colors.BOLD + "[~] Downloading...");
            Path destination = Paths.get(path + "/Java_" + version + ".zip");
            Files.copy(link.openStream(), destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(JavaVersionDownloader.Colors.OKGREEN + "[✓] The JDK " + version + " has been downloaded!");
            Thread.sleep(1000);
            System.out.print(JavaVersionDownloader.Colors.BOLD + "[~] Unzipping...");
            unzip(destination.toString(), path + "/Java_" + version);
            System.out.println(JavaVersionDownloader.Colors.OKGREEN + "[✓] The JDK " + version + " has been unzipped!");
            Thread.sleep(1000);
            System.out.print(JavaVersionDownloader.Colors.BOLD + "[~] Deleting zip file...");
            Files.delete(destination);
            System.out.println(JavaVersionDownloader.Colors.OKGREEN + "[✓] The JDK " + version + " has been deleted!");
            Thread.sleep(1000);
            System.out.println(JavaVersionDownloader.Colors.OKGREEN + "[✓] The JDK " + version + " has been downloaded and installed!");
            Thread.sleep(1000);
        } catch (IOException e) {
            System.out.println(JavaVersionDownloader.Colors.FAIL + "[X] The link does not exist!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

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
