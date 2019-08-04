package com.util;

public class PathUtil {

    public static String getPath(String path) {
        String s1 = path.replace(".", "/");
        String[] split = s1.split("/");
        return split[1];
    }


}
