import fr.jachou.jvm.managers.JavaVersionManager;
import fr.jachou.jvm.managers.utils.JavaVersionList;

public class Test {
    public static void main(String[] args) {
        JavaVersionManager javaVersionManager = new JavaVersionManager();
        javaVersionManager.downloadVersion(JavaVersionList.Java_8, "C:\\Users\\matis\\IdeaProjects\\JavaVersionManager\\src\\test\\resources");
    }
}
