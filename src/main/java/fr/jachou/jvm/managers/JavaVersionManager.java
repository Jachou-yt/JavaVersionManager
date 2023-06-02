package fr.jachou.jvm.managers;

import fr.flowarg.flowzipper.ZipUtils;
import fr.jachou.jvm.managers.utils.DownloadURL;
import fr.jachou.jvm.utils.Logger;
import fr.jachou.jvm.managers.utils.JavaVersionList;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JavaVersionManager {

    private String downloadURL;
    private JavaVersionList list;
    private Path path;
    private Path jarPath;
    private DownloadURL downloadURLList;

    public JavaVersionManager(JavaVersionManagerBuilder builder) {
        this.downloadURL = builder.downloadURL;
        this.list = builder.list;
        this.path = builder.path;
        this.downloadURLList = builder.downloadURLList;
    }

    public static class JavaVersionManagerBuilder {
        private String downloadURL;
        private JavaVersionList list;
        private Path path;
        private DownloadURL downloadURLList;

        /**
         * @param downloadURL The download URL of the java version (not necessary)
         * @return The builder
         */

        public JavaVersionManagerBuilder setMethodDownload(DownloadURL downloadURL) {
            this.downloadURLList = downloadURL;
            return this;
        }

        /**
         * @param downloadURL The download URL of the java version
         * @return The builder
         */

        public JavaVersionManagerBuilder setDownloadURL(String downloadURL) {
            this.downloadURL = downloadURL;
            return this;
        }

        /**
         * @param list The java version list
         * @return The builder
         */

        public JavaVersionManagerBuilder setJavaVersionList(JavaVersionList list) {
            this.list = list;
            return this;
        }

        /**
         * @param path The path where the java version will be downloaded
         * @return The builder
         */

        public JavaVersionManagerBuilder setPath(Path path) {
            this.path = path;
            return this;
        }

        /**
         * @return The JavaVersionManager
         */

        public JavaVersionManager build() {
            return new JavaVersionManager(this);
        }
    }

    /**
     * Download the java version
     */

    public void downloadVersion() {
        if (this.path == null) {
            Logger.logError("Failed to download java version because the path is null!");
            return;
        }
        if (this.list == null) {
            Logger.logError("Failed to download java version because the list is null!");
            return;
        }
        if (this.downloadURLList == DownloadURL.CUSTOM && this.downloadURL == null) {
            Logger.logError("Failed to download java version because the download URL is null!");
            return;
        }

        if (this.downloadURLList == DownloadURL.DEFAULT) {
            downloadFile("https://chiss.fr/jvm/download/" + this.list + ".zip", this.path, this.list);
        } else if (this.downloadURLList == DownloadURL.CUSTOM) {
            downloadFile(this.downloadURL, this.path, this.list);
        }


    }

    /**
     * Unzip the java version
     */

    public void unzipJavaVersion() {
        if (this.path == null) {
            Logger.logError("Failed to unzip java version because the path is null!");
            return;
        }
        if (this.list == null) {
            Logger.logError("Failed to unzip java version because the list is null!");
            return;
        }
        try {
            Logger.log("Unzipping java version " + this.list + "...");
            ZipUtils.unzip(this.path, Paths.get(this.path + "\\" + this.list.toString() + ".zip"));
            Logger.logPass("Unzipped java version " + this.list + " successfully!");

            Logger.log("Deleting " + this.list + ".zip...");
            Files.delete(Paths.get(this.path + "\\" + this.list.toString() + ".zip"));
            Logger.logPass("Deleted " + this.list + ".zip successfully!");

            Logger.log("Set the path of the java version to " + this.path + "\\" + this.list.toString() + "\\bin\\java.exe");
            this.jarPath = Paths.get(this.path + "\\" + this.list.toString() + "\\bin\\java.exe");
            Logger.logPass("Set the path of the java version to " + this.path + "\\" + this.list.toString() + "\\bin\\java.exe successfully!");
        } catch (IOException e) {
            Logger.logError("Failed to unzip java version " + this.list + "!", e);
        }
    }

    public void deleteVersion() {
        if (this.path == null) {
            Logger.logError("Failed to delete java version because the path is null!");
            return;
        }
        if (this.list == null) {
            Logger.logError("Failed to delete java version because the list is null!");
            return;
        }
        Logger.log("Deleting java version " + this.list + "...");
        deleteFolder(Paths.get(this.path + "\\" + this.list.toString()));
        Logger.logPass("Deleted java version " + this.list + " successfully!");
    }

    /**
     * @return The download URL
     */

    public String getDownloadURL() {
        return downloadURL;
    }

    /**
     * @return The java version list
     */

    public JavaVersionList getList() {
        return list;
    }

    /**
     * @return The path where the java version will be downloaded
     */

    public Path getPathOfJarExe() {
        return jarPath;
    }


    /*
    ----------------------------------------
    Private Methods
    ----------------------------------------
     */

    private static void downloadFile(String downloadURL, Path path, JavaVersionList list) {
        try {
            URL url = new URL(downloadURL);
            Path downloadPath = path.resolve(list + ".zip");
            if (Files.exists(downloadPath)) {
                Logger.log("The file " + list + " already exists, deleting...");
                Files.delete(downloadPath);
                Logger.log("The file " + list + " has been deleted successfully!");
            }
            Logger.log("Downloading " + list + " to " + path.toAbsolutePath().toString() + "...");
            Files.copy(url.openStream(), downloadPath);
            Logger.logPass("Downloaded " + list + " to " + path.toAbsolutePath().toString() + " successfully!");
        } catch (IOException e) {
            Logger.logError("Failed to download " + list + " to " + path.toAbsolutePath().toString() + "!", e);
        }
    }

    private static void deleteFolder(Path folder) {
        try {
            Files.walk(folder)
                    .sorted(java.util.Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            Logger.logError("Failed to delete " + folder.toAbsolutePath().toString() + "!", e);
        }
    }

    /*
    ----------------------------------------
    Public Methods
    ----------------------------------------
     */
}
