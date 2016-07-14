/**
 * Copyright (c) aretelife.org, 2015. All Rights Reserved.
 */
package org.arete.java8.newlibraries;

import java.util.StringJoiner;

public class StringJoinerClient {

    public static void main(String[] args) {

        StringJoiner operator1 = new StringJoiner(",");
        operator1.add("Hello").add("World");
        System.out.println(operator1.toString());

        StringJoiner operator2 = new StringJoiner(",", "[ ", " ]");
        operator2.add("Hello").add("World");
        System.out.println(operator2.toString());
    }
}
