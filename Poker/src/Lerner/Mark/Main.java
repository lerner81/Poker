package Lerner.Mark;

public class Main {

    public static void main(String[] args) {


        for (int i=0; i<25; i++){
            Deck deck= new Deck();
            Hand hand= new Hand(deck);
            Hand hand2= new Hand(deck);
            System.out.print(" White has a ");
            hand.display();
            hand.displayAll();
            System.out.print(" Black has a ");
            hand2.display();
            hand2.displayAll();
            System.out.println(hand.compares(hand2));
            System.out.println("=============================================================================");

        }
    }
}
