package com.stateless.security.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PracticeOne {

    public static void main(String[] args) {
        Path path = Paths.get("src/main/resources/test1.txt");
        try {
            Path createdFilePath = Files.createFile(path);
            System.out.println("File created successfully and path is :: " + createdFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
