package ru.betterstop.headfirst.dotcom;

import java.io.IOException;
import java.util.ArrayList;

public class DotComGame {
    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
    private int numOfGuesses = 0;


    public static void main(String[] args) throws IOException {
        DotComGame game = new DotComGame();
        game.setUpGame();
        game.startPlaying();
    }

    public void showPlace(){
        char[] alph = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        int k = 0;
        int gridLen = helper.getGridLength();
        int[] grid = helper.getGrid();
        System.out.print("   ");
        for(int i = 0; i < gridLen; i++) System.out.print(alph[i] + "  ");
        System.out.println("");
        for(int i = 0; i < gridLen; i++){
            System.out.print(i + "  ");
            for (int j = 0; j < gridLen; j++, k++){
                    if(grid[k] == 0) System.out.print("-" +"  ");
                    else if(grid[k] == 1) System.out.print("*" +"  ");
                    else if(grid[k] == 3) System.out.print("·" +"  ");
                    else if(grid[k] == 2) System.out.print("x" +"  ");
            }
           System.out.println("");
        }
    }

    public  void setUpGame(){
        boolean itsOk = false;
        DotCom one = new DotCom();
        one.setName("Pets.com");
        DotCom two = new DotCom();
        two.setName("eToys.com");
        DotCom three = new DotCom();
        three.setName("Go2.com");
        DotCom four = new DotCom();
        four.setName("tsvc.kz");
        DotCom fife = new DotCom();
        fife.setName("congo.kz");

            dotComsList.add(one);
            dotComsList.add(two);
            dotComsList.add(three);
            dotComsList.add(four);
            dotComsList.add(fife);

        System.out.println("Ваша цель - потопить пять кораблей");
        System.out.println("Бисмарк, Конго, Тирпиц, Фусо, Ямато");
        System.out.println("Попытайтесь потомить их за минимальное количество ходов");
        while(!itsOk) {
            itsOk = true;
            for (DotCom dotComToSet : dotComsList) {
                ArrayList<String> newLocation = helper.placeDotCom(3);
                if (newLocation.isEmpty()) {
                    itsOk = false;
                    helper.gridInStart();
                } else dotComToSet.setLocationCells(newLocation);
            }
        }
        showPlace();
//        System.out.println(dotComsList.get(0).getLocationCells());
//        System.out.println(dotComsList.get(1).getLocationCells());
//        System.out.println(dotComsList.get(2).getLocationCells());
    }

    public void startPlaying() throws IOException {
        while (!dotComsList.isEmpty()){
            String userGuess = null;
            boolean flag = true;
            while (flag) {
                userGuess = helper.getUserInput("Сделайте ход: ");
                if (userGuess != null && userGuess.length() == 2 &&
                        (userGuess.charAt(1) >= '0' && userGuess.charAt(1) <= '9') &&
                        (userGuess.charAt(0) >= 'a' && userGuess.charAt(0) <= 'g')) flag = false;
                else System.out.println("Не корекный ввод!");
            }
            checkUserGuess(userGuess);
            helper.takeShot(userGuess);
            showPlace();
        }
        finishGame();
    }

    public void checkUserGuess(String userGuess){
        numOfGuesses++;
        String result = "Мимо";
        for (DotCom dotComToTest: dotComsList){
            result = dotComToTest.checkYourself(userGuess);
            if (result.equals("Попал")){
                break;
            }
            if (result.equals("Потопил")){
                dotComsList.remove(dotComToTest);
                break;
            }
        }
        System.out.println(result);
    }

    public void finishGame() throws IOException {
        System.out.println("Все корабли ушли ко дну!");
        if (numOfGuesses <= 18){
            System.out.println("Это заняло у Вас всего " + numOfGuesses + " попыток.");
            System.out.println("Вы показали хороший результат.");
        } else{
            System.out.println("Это заняло у Вас довольно много времени. " + numOfGuesses + " попыток.");
            System.out.println("Тем неменее Вы победили.");
        }
        System.in.read();
    }
}
