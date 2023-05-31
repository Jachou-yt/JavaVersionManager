package fr.jachou.jvm.managers;

import fr.flowarg.flowzipper.ZipUtils;
import fr.jachou.jvm.Logger;
import fr.jachou.jvm.managers.utils.JavaVersionList;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JavaVersionManager {

    private String downloadURL;
    private JavaVersionList list;
    private Path path;

    /**
     * JavaVersionManager constructor
     * @param customUrl The custom url to download the java version on your own server
     */
    public JavaVersionManager(String customUrl) {
        this.downloadURL = customUrl;
    }

    /**
     * JavaVersionManager constructor
     */

    public JavaVersionManager() {
        this.downloadURL = "https://chiss.fr/jvm/download/";
    }

    /**
     * Download a java version
     * @param version The java version to download
     * @param path The path to download the java version
     */

    public void downloadVersion(JavaVersionList version, Path path) {
        this.path = path;
        this.list = version;
        try {
            URL url = new URL(this.downloadURL + version + ".zip");

            Path downloadPath = Paths.get(version + ".zip");
            Files.copy(url.openStream(), downloadPath);

            Logger.getLogger().info("Downloaded " + version + " to " + path.toAbsolutePath().toString() + " successfully!");
        } catch (IOException e) {
            Logger.getLogger().err("Failed to download " + version + " to " + path.toAbsolutePath().toString() + "!");
        }
    }

    /**
     * Download a java version
     * @param version The java version to download
     * @param path The path to download the java version
     */

    public void downloadVersion(JavaVersionList version, String path) {
        this.path = Paths.get(path);
        this.list = version;
        try {
            URL url = new URL(this.downloadURL + version + ".zip");
            Path downloadPath = this.path.resolve(version + ".zip");
            Files.copy(url.openStream(), downloadPath);


            Logger.getLogger().info("Downloaded " + version + " to " + path + " successfully!");
        } catch (IOException e) {
            e.getMessage();
            Logger.getLogger().err("Failed to download " + version + " to " + path + "!");
        }
    }

    /**
     * Unzip a java version
     */

    public void unzipJavaVersion() {
        if (this.path == null) {
            Logger.getLogger().err("Failed to unzip java version because the path is null!");
            return;
        }
        if (this.list == null) {
            Logger.getLogger().err("Failed to unzip java version because the list is null!");
            return;
        }
        try {
            ZipUtils.unzip(this.path, Paths.get(this.path + this.list.toString() +".zip"));
        } catch (IOException e) {
            Logger.getLogger().err("Failed to unzip java version " + this.list + "!");
        }
    }

    /**
     * Unzip a java version
     * @param path The path where the java zip version is downloaded
     * @param list The java version list
     */

    public void unzipJavaVersion(Path path, JavaVersionList list) {
        try {
            Logger.getLogger().info(list.toString());
            ZipUtils.unzip(path, Paths.get(path + "\\" + list.toString() +".zip"));
        } catch (IOException e) {
            e.printStackTrace();
            Logger.getLogger().err("Failed to unzip java version " + list + "!");
        }
    }

    /**
     * Get the download url of the java version
     * @return The download url of the java version
     */
    public String getDownloadURL() {
        return downloadURL;
    }

    /**
     * Set the download url of the java version
     * @param downloadURL The download url of the java version
     */
    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }

    /**
     * Get the java version list
     * @return The java version list
     */

    public JavaVersionList getList() {
        return list;
    }

    /**
     * Set the java version list
     * @param list The java version list
     */

    public void setList(JavaVersionList list) {
        this.list = list;
    }
}
