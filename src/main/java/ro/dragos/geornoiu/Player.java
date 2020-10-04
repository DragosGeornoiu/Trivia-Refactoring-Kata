/**
 *  Copyright Murex S.A.S., 2003-2020. All Rights Reserved.
 * 
 *  This software program is proprietary and confidential to Murex S.A.S and its affiliates ("Murex") and, without limiting the generality of the foregoing reservation of rights, shall not be accessed, used, reproduced or distributed without the
 *  express prior written consent of Murex and subject to the applicable Murex licensing terms. Any modification or removal of this copyright notice is expressly prohibited.
 */
package ro.dragos.geornoiu;

public class Player {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private final String name;
    private int place;
    private int coins;
    private boolean inPenaltyBox;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors 
    //~ ----------------------------------------------------------------------------------------------------------------

    public Player(String name) {
        this.name = name;
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void putInPenaltyBox() {
        this.inPenaltyBox = true;
    }

    public int getPlace() {
        return place;
    }

    public void move(int roll) {
//              place = (place + roll) % 12;
        place += roll;
        if (place >= 12) {
            place -= 12;
        }
    }

    public String getName() {
        return name;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoin() {
        coins++;
    }

}
