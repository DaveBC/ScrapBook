//
//  nonogramSolver.java
//  Daily Programmer Hard Challenge #251
//  https://www.reddit.com/r/dailyprogrammer/comments/42x90t/20160127_challenge_251_hard_solve_a_nonogram_bonus/
// 
//  Created by David Banwell-Clode on 24/01/2016.
//  Copyright (c) 2016 David Banwell-Clode. All rights reserved.
//

import java.io.*;
import java.lang.*;
import java.util.*;

public class nonogramSolver {
    public static void main(String arg[]){

        String input = "nonogramSolver.txt";
        String line = null;
        int index = 0;
        
        ArrayList<List<Integer>> row = new ArrayList<List<Integer>>();
        ArrayList<List<Integer>> col = new ArrayList<List<Integer>>();
        Boolean flag = false; // when '-' is encountered.
        
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(input);

            // Wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                for(int i = 0; i< line.length(); i++) {
                    if(Character.isDigit(line.charAt(i))) {
                        int num = 0;
                        if(line.indexOf(" ", i) == -1) {
                            num = Integer.parseInt(line.substring(i,line.length()));
                        }
                        else {
                            num = Integer.parseInt(line.substring(i,line.indexOf(" ",i)));
                            i += String.valueOf(num).length();
                        }
//                        System.out.println(num);
                        if(!flag) {
                            if(row.size() <= index) {
                                List<Integer> list = new ArrayList<Integer>();
                                row.add(list);
                            }
                            row.get(index).add(num);
                            index++;
                        }
                        else {
                            if(index == 0) {
                                List<Integer> list = new ArrayList<Integer>();
                                col.add(list);
                            }
                            col.get(col.size()-1).add(num);
                            index++;
                        }
                    }
                    else if(line.charAt(i) == '-') {
                        flag = true;
                        index = 0;
                        System.out.println("Switch to col");
                    }
                    else {
                        System.out.println("Error.");
                        return;
                    }
                }
                index = 0;
                System.out.println("New line");
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
        
//        for(int i = 0; i < row.size(); i++) {
//            System.out.println(row.get(i));
//        }
//        System.out.println("");
//        for(int i = 0; i < col.size(); i++) {
//            System.out.println(col.get(i));
//        }
//        System.out.println(row.size());
//        System.out.println(col.size());
        solve(row, col);

    }
    
    public static void solve(ArrayList<List<Integer>> row, ArrayList<List<Integer>> col) {
        char[][] grid = new char[row.size()][col.size()];
        
        ArrayList<List<String>> possibleRows = populatePossibilities(row);
        
        
//        ArrayList<String> str = new ArrayList<String>(row.size());
//        while(!satisfied(grid)) {
//            rows = generateRows(row);
//        }
    }
    
    public static char[][] genGrid(char[][] grid, ArrayList<String> rows) {
        System.out.println(grid[0].length);
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                grid[i][j] = rows.get(i).charAt(j);
            }
        }
        return grid;
    }
    
    public static ArrayList<List<String>> populatePossibilities(ArrayList<List<Integer>> row) {
        ArrayList<List<String>> pos = new ArrayList<List<String>>();
        char[] rowStr = new char[row.size()];
        int index = 0;
        
        for(int i = 0; i < row.size(); i++) {
            rowStr[i] = ' ';
        }
        
        for(int i = 0; i < row.size(); i++) {
            for(int j = 0; j < row.get(i).size(); j++) {
                if(row.get(i).get(j) > 0) {
                    for(int k = 0; k < row.get(i).get(j); k++) {
                        rowStr[index] = '*';
                        index++;
                    }
                    index++; // Adds a space.
                }
            }
            System.out.println(rowStr);
            index = 0;
            for(int x = 0; x < row.size(); x++) {
                rowStr[x] = ' ';
            }  
//            while(rowStr.indexAt(rowStr.length()-1) != '*') {
//                int index = 0;
//            }
        }
        return null;
    }
}