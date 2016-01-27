//
//  nonogram.java
//  Daily Programmer Intermediate Challenge #249
//  https://www.reddit.com/r/dailyprogrammer/comments/40h9pd/20160111_challenge_249_easy_playing_the_stock/
// 
//  Created by David Banwell-Clode on 24/01/2016.
//  Copyright (c) 2016 David Banwell-Clode. All rights reserved.
//

import java.io.*;
import java.lang.*;
import java.util.*;

public class nonogram{
    public static void main(String arg[]){

        String input = "nonogram.txt";
        String line = null;
        
        ArrayList<String> grid = new ArrayList<String>(5);
        
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(input);

            // Wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                grid.add(line);
            }   

            // Close file.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + input + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + input + "'");                  
        }
        System.out.println("");
        
        ArrayList<List<Integer>> row = new ArrayList<List<Integer>>(grid.size());
        ArrayList<List<Integer>> col = new ArrayList<List<Integer>>(grid.size());
        int consec = 0;
        int maxcol = 0; // Max number of counts in a single column.
        int maxrow = 0; // Max number of counts in a single row.
        
        // Count along rows.
        for(int i = 0; i < grid.size(); i++) {
            List<Integer> list = new ArrayList<Integer>(grid.size());
            row.add(list);
            String rowStr = grid.get(i);
            for(int j = 0; j < rowStr.length(); j++) {
                if(rowStr.charAt(j) == '*') {
                    consec++;
                }
                if(rowStr.charAt(j) == ' ') {
                    if(consec > 0) {
                        row.get(i).add(consec);
                        consec = 0;
                    }
                }
            }
            if(row.get(i).size() == 0 || consec > 0) {
                row.get(i).add(consec);
            }
            consec = 0;
        }
        
        // Count along columns.
        consec = 0;
        for(int i = 0; i < grid.get(0).length(); i++) {
            List<Integer> list = new ArrayList<Integer>(grid.size());
            col.add(list);
            for(int j = 0; j < grid.size(); j++) {
                if(grid.get(j).charAt(i) == '*') {
                    consec++;
                }
                if(grid.get(j).charAt(i) == ' ') {
                    if(consec > 0) {
                        col.get(i).add(consec);
                        consec = 0;
                    }
                }
            }
            if(col.get(i).size() == 0 || consec > 0) {
                col.get(i).add(consec);
            }
            consec = 0;
        }
        
        // Look for maximum number of seperate instances of consecutives.
        for(int i = 0; i < col.size(); i++) {
            if(col.get(i).size() > maxcol) maxcol = col.get(i).size();
        }
        for(int i = 0; i < row.size(); i++) {
            if(row.get(i).size() > maxrow) maxrow = row.get(i).size();
        }
        
//        System.out.println("Maxrow: " + maxrow + " Maxcol: " + maxcol);
//        System.out.println("Columns:");
        for(int j = maxcol-1; j >= 0; j--) {
            for(int i = 0; i < maxrow; i++) {
                System.out.print("   ");
            }
            for(int i = 0; i < col.size(); i++) {
                if(col.get(i).size() < j+1) {
                    System.out.print("   ");
                }
                else {
                    System.out.print(" " + col.get(i).get(col.get(i).size()-1-j) + " ");
                }
            }
            System.out.println("");
        }
//        System.out.println("");
//        System.out.println("Rows:");
        for(int i = 0; i < row.size(); i++) {
            for(int j = maxrow-1; j >= 0; j--) {
                if(row.get(i).size() < j+1) {
                    System.out.print("   ");
                }
                else {
                    System.out.print(" " + row.get(i).get(row.get(i).size()-1-j) + " ");
                }
            }
                            System.out.println("");
        }
    }
}