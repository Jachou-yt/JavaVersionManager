package test;

import fr.jachou.jvm.managers.JavaManager;

import java.util.ArrayList;

import static fr.jachou.jvm.managers.JavaManager.listInstalledJDKs;

public class Test {
    public static void main(String[] args) {
        ArrayList<String> jdkList = listInstalledJDKs();

        for (String s : jdkList) {
            System.out.println(s);
        }

        if (JavaManager.isASpecificVersionInstalled("19")) {
            System.out.println("1.19 is installed");
        } else {
            System.out.println("1.19 is not installed");
        }
    }
}
