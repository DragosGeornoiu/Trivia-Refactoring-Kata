/**
 *  Copyright Murex S.A.S., 2003-2020. All Rights Reserved.
 * 
 *  This software program is proprietary and confidential to Murex S.A.S and its affiliates ("Murex") and, without limiting the generality of the foregoing reservation of rights, shall not be accessed, used, reproduced or distributed without the
 *  express prior written consent of Murex and subject to the applicable Murex licensing terms. Any modification or removal of this copyright notice is expressly prohibited.
 */
package ro.dragos.geornoiu;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class GameTest {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    @Test
    public void caracterizationTest() {
        for (int seed = 1; seed < 10_000; seed++) {
            String actualOutput = extractOutput(new Random(seed), new Game());
            String expectedOutput = extractExpectedOutput(new Random(seed), new GameMock());
            assertEquals(actualOutput, expectedOutput);
        }
    }

    private String extractOutput(Random rand, Game game) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try(PrintStream inmemory = new PrintStream(baos)) {
            System.setOut(inmemory);

            game.add("Chet");
            game.add("Pat");
            game.add("Sue");

            do {
                game.roll(rand.nextInt(5) + 1);

                if (rand.nextInt(9) == 7) {
                    game.onWrongAnswer();
                } else {
                    game.onCorrectAnswer();
                }

            } while (!game.isPlayerWin());
        }
        String output = new String(baos.toByteArray());
        return output;
    }
    
    private String extractExpectedOutput(Random rand, GameMock gameMock) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try(PrintStream inmemory = new PrintStream(baos)) {
            System.setOut(inmemory);

            gameMock.add("Chet");
            gameMock.add("Pat");
            gameMock.add("Sue");

            boolean notAWinner = false;
            do {
                gameMock.roll(rand.nextInt(5) + 1);

                if (rand.nextInt(9) == 7) {
                    notAWinner = gameMock.onWrongAnswer();
                } else {
                    notAWinner = gameMock.onCorrectAnswer();
                }

            } while (notAWinner);
        }
        String output = new String(baos.toByteArray());
        return output;
    }
}
