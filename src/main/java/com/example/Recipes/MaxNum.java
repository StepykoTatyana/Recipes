package com.example.Recipes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class MaxNum {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Spring\\RecipesMy\\src\\main\\java\\com\\example\\Recipes\\dataset_91007.txt");
        int maxNum = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                int s = scanner.nextInt();
                if (s > maxNum) {
                    maxNum = s;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + file);
        }

        System.out.println(maxNum);

        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> arrayList = new ArrayList<>();
        int s = scanner.nextInt();
        for (int i = 0; i < s; i++) {
            arrayList.add(scanner.nextInt());
        }
        Collections.reverse(arrayList);
        arrayList.forEach(System.out::println);
    }
}
