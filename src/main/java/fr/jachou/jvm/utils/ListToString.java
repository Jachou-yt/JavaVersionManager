package fr.jachou.jvm.utils;

import fr.jachou.jvm.managers.utils.JavaVersionList;

public class ListToString {
    public static String JavaVersionListToString(JavaVersionList javaVersionList) {
        switch (javaVersionList) {
            case Java_8:
                return "1.8";
            case Java_9:
                return "9";
            case Java_10:
                return "10";
            case Java_11:
                return "11";
            case Java_13:
                return "13";
            case Java_15:
                return "15";
            case Java_16:
                return "16";
            case Java_17:
                return "17";
            case Java_18:
                return "18";
            case Java_19:
                return "19";
            case Java_20:
                return "20";
            default:
                return null;
        }
    }
}
