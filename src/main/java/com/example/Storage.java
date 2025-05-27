/*
* File: Storage.java
* Author: Balog Levente
* Copyright: 2025, Balog Levente
* Group: Szoft I-2-E
* Date: 2025-05-24
* Github: https://github.com/BalogKTCH/
* Licenc: MIT
*/

package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final ArrayList<String> CustomerList = null;

    public static void writeCities(ArrayList<String> customerList) {
        try {
            tryWriteCustomers(CustomerList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void tryWriteCustomers(ArrayList<String> CustomerList)
            throws IOException {
        FileWriter fw = new FileWriter("data.txt",
                Charset.forName("utf-8"));
        for (String customer : CustomerList) {
            fw.write(customer);
            fw.write("\n");
        }
        fw.close();

    }

    public static ArrayList<String> readuserCustomers() {
        try {
            return tryReadCustomers();
        } catch (FileNotFoundException e) {

            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    private static ArrayList<String> tryReadCustomers()
            throws FileNotFoundException {

        ArrayList<String> CustomerList = new ArrayList<>();
        File file = new File("data.txt");
        try (Scanner sc = new Scanner(file)) {
            while(sc.hasNextLine()){
            String line = sc.nextLine();
            CustomerList.add(line);
            }
        }
        return CustomerList;
    }
}
