package Lerner.Mark;

import java.util.ArrayList;
import java.util.Random;

public class Deck{

    private ArrayList<Card> cards;

    Deck(){
        cards = new ArrayList<>();
        int i1, i2;
        Random random = new Random();
        Card temp;

        //adds cards to deck
        for (int i=0; i<4; i++){
            for (int j=0; j<13; j++){
                cards.add( new Card(i,j));
            }
        }
        int size = cards.size() -1;

        //shuffles the deck
        for (int i=0; i<52; i++){
            i1 = random.nextInt( size );
            i2 = random.nextInt( size );

            temp = cards.get(i2);
            cards.set(i2, cards.get(i1) );
            cards.set(i1, temp);
        }
    }

    //removes card from the deck
    public Card drawFromDeck(){
        return cards.remove( cards.size()-1 );
    }
}

