package ru.betterstop.headfirst.dotcom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameHelper {

    private static final String alphabet = "abcdefg";
    private final int gridLength = 7;
    private final  int gridSize = 49;
    private final  int[] grid = new int[gridSize];
    private int comCount = 0;

    public String getUserInput(String prompt){
        String inputLine = null;
        System.out.print(prompt + " ");
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            inputLine = br.readLine();
            if(inputLine.length() == 0) return null;
        }catch (IOException e){
            System.out.println("IOException: " + e);
        }
        assert inputLine != null;
        return inputLine.toLowerCase();
    }

    public ArrayList<String> placeDotCom(int comSize) {
        ArrayList<String> alphaCells = new ArrayList<>();
        //String[] alphacoords = new String[comSize];
        String temp;
        int[] coords = new int[comSize];
        int attempts = 0;
        boolean success = false;
        int location = 0;

        comCount++;
        int incr = 1;
        if ((comCount % 2) == 1) {
            incr = gridLength;
        }

        while (!success & attempts++ < 300) {
            if (!ifClean(location)) {
                coords = new int[comSize];
            }
            incr = incr == 1 ? gridLength : 1;
            location = (int) (Math.random() * gridSize);
//            System.out.println("");
//            System.out.println("Попытка -- " + attempts);
//
//            int k = 0;
//            for (int i = 0; i < gridLength; i++) {
//                System.out.print(i + "  ");
//                for (int j = 0; j < gridLength; j++, k++) {
//                    if (grid[k] == 0) System.out.print("-" + "  ");
//                    else if (grid[k] == 1) System.out.print("*" + "  ");
//                    else if (grid[k] == 3) System.out.print("·" + "  ");
//                    else if (grid[k] == 2) System.out.print("x" + "  ");
//                }
//                System.out.println("");
//            }
//            System.out.println(incr);
//            System.out.print("Пробуем " + location);
            int x = 0;
            success = ifClean(location);
            //success = true;
            while (success && x < comSize) {
                //System.out.print("Пробуем --- " + location);
                if (ifClean(location)) {
                    //System.out.println(" - OK");
                    coords[x++] = location;
                    location += incr;
                    //success = ifClean(location);
//                    if (location >= gridSize){
//                        success = false;
//                    }
                    if (x > 0 && x < 3 && (location % gridLength == 0)) {
                        success = false;
                    }
                } else {
                    success = false;
                }
            }
        }
        int x = 0;
        int row;
        int column;
        if (coords[1] != 0 && coords[2] != 0) {
            while (x < comSize) {
                grid[coords[x]] = 1;
                row = (int) (coords[x] / gridLength);
                column = coords[x] % gridLength;
                temp = String.valueOf(alphabet.charAt(column));
                alphaCells.add(temp.concat(Integer.toString(row)));
                x++;
            }
        }
        //System.out.println(alphaCells);
        return alphaCells;
    }

    public int getGridLength(){
        return gridLength;
    }
    public int[] getGrid(){
        return grid;
    }

    public void takeShot(String userGuid){
        int shot = Integer.parseInt(String.valueOf(userGuid.charAt(1))) * gridLength;
        if (userGuid.charAt(0) == 'a') shot += 0;
        else if (userGuid.charAt(0) == 'b') shot += 1;
        else if (userGuid.charAt(0) == 'c') shot += 2;
        else if (userGuid.charAt(0) == 'd') shot += 3;
        else if (userGuid.charAt(0) == 'e') shot += 4;
        else if (userGuid.charAt(0) == 'f') shot += 5;
        else if (userGuid.charAt(0) == 'g') shot += 6;
        if (grid[shot] == 0) grid[shot] = 3;
        else if (grid[shot] == 1) grid[shot] = 2;

    }

    private boolean ifClean(int loc) {
        if (loc < 0 || loc > 48) {
            return false;
        } else if (loc == 0) {
            return grid[loc] != 1 && grid[loc + 1] != 1 && grid[loc + gridLength] != 1 && grid[loc + gridLength + 1] != 1;
        } else if (loc == gridLength - 1) {
            return grid[loc] != 1 && grid[loc - 1] != 1 && grid[loc + gridLength] != 1 && grid[loc + gridLength - 1] != 1;
        } else if (loc == gridSize - gridLength) {
            return grid[loc] != 1 && grid[loc + 1] != 1 && grid[loc - gridLength] != 1 && grid[loc - gridLength + 1] != 1;
        } else if (loc == gridSize - 1) {
            return grid[loc] != 1 && grid[loc - 1] != 1 && grid[loc - gridLength] != 1 && grid[loc - gridLength - 1] != 1;
        } else if (loc > 0 && loc < gridLength-1){
            return grid[loc] != 1 && grid[loc - 1] != 1 && grid[loc + 1] != 1 && grid[loc + gridLength] != 1 && grid[loc + gridLength - 1] != 1 && grid[loc + gridLength + 1] != 1;
        } else if (loc > gridSize - gridLength && loc < gridSize-1) {
            return grid[loc] != 1 && grid[loc - 1] != 1 && grid[loc + 1] != 1 && grid[loc - gridLength] != 1 && grid[loc - gridLength - 1] != 1 && grid[loc - gridLength + 1] != 1;
        } else if (loc % gridLength == 0) {
            return grid[loc] != 1 && grid[loc + 1] != 1 && grid[loc - gridLength] != 1 && grid[loc - gridLength + 1] != 1 && grid[loc + gridLength] != 1 && grid[loc + gridLength + 1] != 1;
        } else if ((loc + 1) % gridLength == 0) {
            return grid[loc] != 1 && grid[loc - 1] != 1 && grid[loc - gridLength] != 1 && grid[loc - gridLength - 1] != 1 && grid[loc + gridLength] != 1 && grid[loc + gridLength - 1] != 1;
        } else return grid[loc] != 1 && grid[loc - 1] != 1 && grid[loc + 1] != 1 && grid[loc - gridLength] != 1 && grid[loc - gridLength - 1] != 1 && grid[loc - gridLength + 1] != 1 && grid[loc + gridLength] != 1 && grid[loc + gridLength - 1] != 1 && grid[loc + gridLength + 1] != 1;
    }

    public void gridInStart() {
        for(int i = 0; i < grid.length; i++){
            grid[i] = 0;
        }
    }

    public void cleanScreen(){
       /* try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }
}
