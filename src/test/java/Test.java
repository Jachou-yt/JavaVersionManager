import fr.jachou.jvm.managers.JavaVersionManager;
import fr.jachou.jvm.managers.utils.JavaVersionList;

import java.nio.file.Paths;

public class Test {
    public static void main(String[] args) {
        JavaVersionManager javaVersionManager = new JavaVersionManager();
        javaVersionManager.unzipJavaVersion(Paths.get("C:\\Users\\matis\\IdeaProjects\\JavaVersionManager\\src\\test\\resources\\"), JavaVersionList.Java_8);
    }
}
