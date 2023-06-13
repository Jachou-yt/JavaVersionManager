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

        JProgressBar progressBar = new JProgressBar();

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
                    JavaVersionDownloader.downloaderWithProgressBar(version.ordinal() + 8, progressBar);
                }
            });
            add(button);
        }


        progressBar.setPreferredSize(new Dimension(300, 30));
        progressBar.setStringPainted(true);
        add(progressBar);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
