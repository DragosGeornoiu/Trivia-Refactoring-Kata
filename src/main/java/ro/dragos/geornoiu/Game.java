/**
 * Copyright Murex S.A.S., 2003-2020. All Rights Reserved.
 * <p>
 * This software program is proprietary and confidential to Murex S.A.S and its affiliates ("Murex") and, without limiting the generality of the foregoing reservation of rights, shall not be accessed, used, reproduced or distributed without the
 * express prior written consent of Murex and subject to the applicable Murex licensing terms. Any modification or removal of this copyright notice is expressly prohibited.
 */
package ro.dragos.geornoiu;

import java.util.ArrayList;
import java.util.List;


public class Game {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private final List<Player> players = new ArrayList<>();

    private Player currentPlayer;
    private int currentPlayerIndex = -1;

    private final GameQuestions gameQuestions;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors 
    //~ ----------------------------------------------------------------------------------------------------------------

    public Game() {
        gameQuestions = new GameQuestions();
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public void add(String playerName) {
        Player player = new Player(playerName);
        players.add(player);

        if (currentPlayer == null) {
            currentPlayer = player;
        }

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }

    public void roll(int roll) {
        changeCurrentPlayer();
        System.out.println(currentPlayer.getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.isInPenaltyBox()) {
            currentPlayer.updateGettingOutOfPenaltyBox(roll);
            if (currentPlayer.isGettingOutOfPenaltyBox()) {
                System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
                takeTurn(roll);
            } else {
                System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
            }
        } else {
            takeTurn(roll);
        }
    }

    private void takeTurn(int roll) {
        currentPlayer.move(roll);
        System.out.println(currentPlayer.getName() + "'s new location is " + currentPlayer.getPlace());
        System.out.println("The category is " + currentCategory());
        askQuestion(currentCategory());
    }

    public void onCorrectAnswer() {
        if (!currentPlayer.isInPenaltyBox() || currentPlayer.isGettingOutOfPenaltyBox()) {
            System.out.println("Answer was correct!!!!");
            incrementPurse(currentPlayer);
        }
    }

    boolean isPlayerWin() {
        return (!currentPlayer.isInPenaltyBox() || currentPlayer.isGettingOutOfPenaltyBox()) && currentPlayer.getPurse() == 6;
    }

    public void onWrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer.getName() + " was sent to the penalty box");
        currentPlayer.putInPenaltyBox();
    }

    private void changeCurrentPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        currentPlayer = players.get(currentPlayerIndex);
    }

    private void incrementPurse(Player currentPlayer) {
        currentPlayer.incrementPurse();
        System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getPurse() + " Gold Coins.");
    }

    private void askQuestion(String currentCategory) {
        System.out.println(gameQuestions.retrieveQuestion(currentCategory));
    }

    private String currentCategory() {
        int place = currentPlayer.getPlace();
        return gameQuestions.getQuestionCategory(place);
    }
}
