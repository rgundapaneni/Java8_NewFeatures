/**
 * Copyright (c) aretelife.org, 2015. All Rights Reserved.
 */
package org.arete.java8.packagescanner;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PackageScanner {

    public static void main(String[] args) throws URISyntaxException {

        String packageName = getPackageName();
        List<String> classNames = findAllClassesInPackage(packageName);

        classNames.stream().forEach(PackageScanner::processClassAsNeeded);
    }

    private static void processClassAsNeeded(String className) {

        try {
            Class klass = Class.forName(className);
            Object instance = klass.newInstance();

            if(Object.class.isInstance(instance)) {
                System.out.println(className);
            }

        } catch (ClassNotFoundException|InstantiationException|IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static List<String> findAllClassesInPackage(String packageName) {

        List<String> classList = new ArrayList<>();

        String packageFullPath = findPackageFullPath(packageName);
        File[] allFiles = (new File(packageFullPath)).listFiles();
        for (File file : allFiles) {
            if (file.getName().contains(".class") && !file.getName().contains("$")) {
                 classList.add(packageName + "." + file.getName().split("\\.")[0]);
            }
        }

        return classList;
    }

    private static String getPackageName() {

        String siblingPackageName = (new PackageScanner()).getClass().getPackage().getName();
        return siblingPackageName.replaceAll("\\.\\w+$", ".newfeatures");
    }

    private static String findPackageFullPath(String packageName) {

        Matcher dotMatcher = Pattern.compile("(\\w+)[\\.]?").matcher(packageName);

        String packagePath = getClassPathRoot();

        while (dotMatcher.find()) {

            if(packagePath.endsWith(File.separator)) {
                packagePath += dotMatcher.group(1);
            } else {
                packagePath += File.separator + dotMatcher.group(1);
            }

        }

        return packagePath;
    }

    private static String getClassPathRoot() {

        String classPathRoot = "";

        try {
            classPathRoot = PackageScanner.class.getProtectionDomain().getCodeSource()
                                                        .getLocation().toURI().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return classPathRoot;
    }
}
