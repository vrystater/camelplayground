package com.seibels.integration;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PageCounter {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("/home/qg/Downloads/meh.pdf");
        byte[] data = Files.readAllBytes(path);
        int count = PDDocument.load(data).getNumberOfPages();

        System.out.println("count = " + count);
    }
}
