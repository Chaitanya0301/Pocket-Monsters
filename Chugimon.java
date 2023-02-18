//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Chugimon
// Course:   CS 300 Fall 2022
//
// Author:   Chaitanya Sharma
// Email:    csharma4@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
// Persons:         None
// Online Sources:  None
///////////////////////////////////////////////////////////////////////////////

/**
 * Chugimon class
 * @author Chaitanya Sharma
 */
public class Chugimon extends Object implements Comparable<Chugimon>{
    public static final int MIN_ID = ChugidexUtility.MIN_CHUGI_ID; //The minimum ID number
    public static final int MAX_ID = ChugidexUtility.MAX_CHUGI_ID; // The maximum ID number
    private final String NAME; //The name of the Chugimon
    private final int FIRST_ID; //The first ID of the Chugimon
    private final int SECOND_ID; //The second ID of the Chugimon
    private final ChugiType PRIMARY_TYPE; //The primary type of the Chugimon
    private final ChugiType SECONDARY_TYPE; //The secondary type of the Chugimon
    private final double HEIGHT; //The height of the Chugimon in meters
    private final double WEIGHT; //The weight of the Chugimon in kilograms

    /**
     *
     * @param firstID  the first ID of the Chugimon, between 1-151
     * @param secondID  the second ID of the Chugimon, between 1-151
     * @throws IllegalArgumentException if the first and second ID are out of bounds or equal to each other.
     */
    public Chugimon(int firstID, int secondID) throws IllegalArgumentException{
        this.FIRST_ID = firstID;
        this.SECOND_ID = secondID;

        this.NAME = ChugidexUtility.getChugimonName(firstID,secondID);
        this.HEIGHT = ChugidexUtility.getChugimonHeight(firstID,secondID);
        this.WEIGHT = ChugidexUtility.getChugimonWeight(firstID,secondID);

        this.PRIMARY_TYPE = ChugidexUtility.getChugimonTypes(firstID,secondID)[0];
        this.SECONDARY_TYPE = ChugidexUtility.getChugimonTypes(firstID,secondID)[1];
    }

    /**
     * Gets the name of this Chugimon
     * @return the name of this Chugimon
     */
    public String getName(){
        return ChugidexUtility.getChugimonName(FIRST_ID,SECOND_ID); //gets the name
    }

    /**
     * Gets the first ID of this Chugimon
     * @return the first ID of this Chugimon
     */
    public int getFirstID(){
        return FIRST_ID; // getter first ID
    }

    /**
     * Gets the second ID of thid Chugimon
     * @return the second ID of thid Chugimon
     */
    public int getSecondID(){
        return SECOND_ID; // getter second ID
    }

    /**
     * Gets the primary type of this Chugimon
     * @return The primary type of this Chugimon
     */
    public ChugiType getPrimaryType(){
        return PRIMARY_TYPE;
    }
    /**
     * Gets the secondary type of this Chugimon
     * @return The secondary type of this Chugimon
     */
    public ChugiType getSecondaryType(){
        return SECONDARY_TYPE;
    }

    /**
     * Gets the height of this Chugimon
     * @return the height of this Chugimon
     */
    public double getHeight(){
        return HEIGHT;
    }

    /**
     * Gets the weight of the Chugimon.
     * @return The weight of the Chugimon.
     */
    public double getWeight(){
        return WEIGHT;
    }

    /**
     * Determines the ordering of Chugimon.
     * @param otherChugi the object to be compared.
     * @return a negative int if this Chugimon is less than other,a positive int
     * if this Chugimon is greater than other,or 0 if this and the other Chugimon are equal.
     */
    @Override
    public int compareTo(Chugimon otherChugi) {
        if(otherChugi.getName().equals(getName())){ // if names are equal
            if(getFirstID() > otherChugi.getFirstID()){ // if first ID is greater for this chugi
                return 1;
            }
            if(getFirstID() < otherChugi.getFirstID()){ // first ID less for this chugi
                return -1;
            }
            if(getFirstID() == otherChugi.getFirstID()){ // if name and first ID are same
                if(getSecondID() > otherChugi.SECOND_ID){ // second ID greater
                    return 1;
                }
                if(getSecondID() < otherChugi.SECOND_ID){ // second ID smaller
                    return -1;
                }
                if(getSecondID() == otherChugi.SECOND_ID){ // everything same / chugi identical
                    return 0;
                }
            }
        }
        if(getName().compareTo(otherChugi.getName())>0){ // name greater alphabetically
            return 1;
        }
        return -1; // name lesser alphabetically
    }

    /**
     * A Chugimon's String representation is its name followed
     * by "#FIRST_ID.SECOND_ID"
     * @return a String representation of this Chugimon
     */
    public String toString(){
        return getName() + "#" + getFirstID() + "." + getSecondID();
    }

    /**
     * Equals method for Chugimon. This Chugimon equals another object if other is a Chugimon
     * with the exact same name, and their both first and second IDs match.
     * @param other   the reference object with which to compare.
     * @return true if this Chugimon and other Object are equal, false otherwise
     */
    public boolean equals(Object other){
        if(other instanceof Chugimon){
            if(this.getName().equals(((Chugimon) other).getName())){ // same name
                if(this.getFirstID() == ((Chugimon) other).getFirstID() // same firstID
                        &&this.getSecondID() == ((Chugimon) other).getSecondID()){ //same second ID
                    return true;
                }
            }
        }
        return false;
    }
}
