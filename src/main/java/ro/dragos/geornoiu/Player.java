/**
 * Copyright Murex S.A.S., 2003-2020. All Rights Reserved.
 * <p>
 * This software program is proprietary and confidential to Murex S.A.S and its affiliates ("Murex") and, without limiting the generality of the foregoing reservation of rights, shall not be accessed, used, reproduced or distributed without the
 * express prior written consent of Murex and subject to the applicable Murex licensing terms. Any modification or removal of this copyright notice is expressly prohibited.
 */
package ro.dragos.geornoiu;

public class Player {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private final String name;
    private int place;
    private int purse;
    private boolean isInPenaltyBox;
    private boolean isGettingOutOfPenaltyBox;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors 
    //~ ----------------------------------------------------------------------------------------------------------------

    public Player(String name) {
        this.name = name;
        this.place = 0;
        this.purse = 0;
        this.isInPenaltyBox = false;
        this.isGettingOutOfPenaltyBox = false;
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public int getPlace() {
        return place;
    }

    public int getPurse() {
        return purse;
    }

    public void move(int roll) {
        place = (place + roll) % 12;
    }

    public void incrementPurse() {
        purse++;
    }

    public boolean isInPenaltyBox() {
        return isInPenaltyBox;
    }

    public void putInPenaltyBox() {
        this.isInPenaltyBox = true;
    }

    public boolean isGettingOutOfPenaltyBox() {
        return isGettingOutOfPenaltyBox;
    }

    public void updateGettingOutOfPenaltyBox(int roll) {
        isGettingOutOfPenaltyBox = (roll % 2) != 0;
    }
}
