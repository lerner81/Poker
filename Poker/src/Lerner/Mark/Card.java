package Lerner.Mark;

// The purpose of this class is to create card objects that will be used in the Deck
public class Card {

    private int rank, suit;

    private static String[] suits = { "H", "S", "D", "C" };
    private static String[] ranks  = {  "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };


//Card constructor sets the rank and suit
    Card(int suit, int rank){
        this.rank = rank;
        this.suit = suit;
    }

    public static String stringRank(int intRank){
        return ranks[intRank];
    }

//getter for the rank and suit
    public int getRank(){
        return rank;
    }

    public int getSuit(){
        return suit;
    }

    //prints the card's rank and suit
    public String toString(){
        return ranks[rank] + suits[suit];
    }
}

