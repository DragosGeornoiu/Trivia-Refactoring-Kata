/**
 *  Copyright Murex S.A.S., 2003-2020. All Rights Reserved.
 * 
 *  This software program is proprietary and confidential to Murex S.A.S and its affiliates ("Murex") and, without limiting the generality of the foregoing reservation of rights, shall not be accessed, used, reproduced or distributed without the
 *  express prior written consent of Murex and subject to the applicable Murex licensing terms. Any modification or removal of this copyright notice is expressly prohibited.
 */
package ro.dragos.geornoiu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Game implements IGame {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private List<Player> players = new ArrayList<>();
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    List<String> popQuestions = new LinkedList<>();
    List<String> scienceQuestions = new LinkedList<>();
    List<String> sportsQuestions = new LinkedList<>();
    List<String> rockQuestions = new LinkedList<>();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors 
    //~ ----------------------------------------------------------------------------------------------------------------

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.add("Pop Question " + i);
            scienceQuestions.add(("Science Question " + i));
            sportsQuestions.add(("Sports Question " + i));
            rockQuestions.add(createRockQuestion(i));
        }
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public String createRockQuestion(int index) {
        return "Rock Question " + index;
    }

    public boolean add(String playerName) {
        players.add(new Player(playerName));
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(currentPlayer().name() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if ((roll % 2) != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(currentPlayer().name() + " is getting out of the penalty box");
                movePlayer(roll);

                System.out.println(currentPlayer().name() +
                    "'s new location is " + currentPlayer().place());
                System.out.println("The category is " + currentCategory());
                askQuestion();
            } else {
                System.out.println(currentPlayer().name() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {
            movePlayer(roll);
            System.out.println(currentPlayer().name() +
                "'s new location is " + currentPlayer().place());
            System.out.println("The category is " + currentCategory());
            askQuestion();
        }

    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                purses[currentPlayer]++;
                System.out.println(currentPlayer().name() +
                    " now has " + purses[currentPlayer] +
                    " Gold Coins.");

                boolean winner = didPlayerWin();
                currentPlayer++;
                if (currentPlayer == players.size())
                    currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == players.size())
                    currentPlayer = 0;
                return true;
            }

        } else {

            System.out.println("Answer was corrent!!!!");
            purses[currentPlayer]++;
            System.out.println(currentPlayer().name() +
                " now has " + purses[currentPlayer] +
                " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size())
                currentPlayer = 0;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer().name() + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == players.size())
            currentPlayer = 0;
        return true;
    }

    private void movePlayer(int roll) {
        currentPlayer().move(roll);
    }

    private void askQuestion() {
        String currentCategory = currentCategory();
        String question = "";
        if (currentCategory.equals("Pop")) {
            question = popQuestions.remove(0);
        } else if (currentCategory.equals("Science")) {
            question = scienceQuestions.remove(0);
        } else if (currentCategory.equals("Sports")) {
            question = sportsQuestions.remove(0);
        } else if (currentCategory.equals("Rock")) {
            question = rockQuestions.remove(0);
        }
        System.out.println(question);
    }

    private String currentCategory() {
        if (currentPlayer().place() == 0)
            return "Pop";
        if (currentPlayer().place() == 4)
            return "Pop";
        if (currentPlayer().place() == 8)
            return "Pop";
        if (currentPlayer().place() == 1)
            return "Science";
        if (currentPlayer().place() == 5)
            return "Science";
        if (currentPlayer().place() == 9)
            return "Science";
        if (currentPlayer().place() == 2)
            return "Sports";
        if (currentPlayer().place() == 6)
            return "Sports";
        if (currentPlayer().place() == 10)
            return "Sports";
        return "Rock";
    }

    private Player currentPlayer() {
        return players.get(currentPlayer);
    }

    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }
}

/**
 * extract method (replace duplicate code)
 * Inline
 * renamed
 * baby steps: don't break compilation at any moment while refactoring
 * Move Method ---> Player.move(roll) -- with invariants
 * switch [expression]: 1) one lines/case, 2) default 3) no extra code in that method
 * Alt-J
 * Any field you create let it be private final at the start. NOT creating gettters and setters. If you have to create them, create them individually
 * records Java 16+ - immutable structs
 */
