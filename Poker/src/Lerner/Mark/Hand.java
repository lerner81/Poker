package Lerner.Mark;

public class Hand {
    private Card[] cards;
    private int[] value;

    Hand(Deck deck1){
        value = new int[6];
        cards = new Card[5];
        for (int i=0; i<5; i++){
            cards[i] = deck1.drawFromDeck();
        }

        int[] ranks = new int[14];
        int[] sortedRanks = new int[5];
        boolean flush = true, straight = false;
        int sameRank1 = 1, sameRank2 = 1;
        int group1 = 0, group2 = 0;
        int index = 0;
        int topStraight = 0;


        for (int i=0; i<=4; i++){
            ranks[cards[i].getRank()]++;
        }
        for (int i=0; i<4; i++){
            if(cards[i].getSuit() != cards[i+1].getSuit())
                flush=false;
        }

        //this section prepares the hands to be evaluated
        //problem arises when comparing more than one pair in ranks so created groups to assign values
        for (int i=13; i>=0; i--){
            if(ranks[i] > sameRank1){
                if (sameRank1 == 1){
                    group1 = i;
                }else{
                    sameRank2 = sameRank1;
                    group2 = i;
                }
                sameRank1 = ranks[i];

            }else if (ranks[i] > sameRank2){
                sameRank2 = ranks[i];
                group2 = i;
            }
        }

        for (int i=13; i>=0; i--){
            if (ranks[i]==1){
                sortedRanks[index]=i;
                index++;
            }
        }

        for (int i=1; i<=9; i++){
            if (ranks[i]==1 && ranks[i+1]==1 && ranks[i+2]==1 && ranks[i+3]==1 && ranks[i+4]==1){
                straight = true;
                topStraight = i+4;
                break;
            }
        }

        //This section here is the where the hands are evaluated
        if (sameRank1==1){
            value[0]=1;
            value[1]=sortedRanks[0];
            value[2]=sortedRanks[1];
            value[3]=sortedRanks[2];
            value[4]=sortedRanks[3];
            value[5]=sortedRanks[4];
        }

        if (sameRank1==2 && sameRank2==1){
            value[0]=2;
            value[1]=group1; //rank of pair
            value[2]=sortedRanks[0];
            value[3]=sortedRanks[1];
            value[4]=sortedRanks[2];
        }

        //two pair
        if (sameRank1==2 && sameRank2==2){

            value[0]=3;
            value[1]= group1>group2 ? group1 : group2; //rank of greater pair
            value[2]= group1<group2 ? group1 : group2;
            value[3]=sortedRanks[0];  //extra card
        }

        //three of a kind
        if (sameRank1==3 && sameRank2!=2){

            value[0]=4;
            value[1]= group1;
            value[2]=sortedRanks[0];
            value[3]=sortedRanks[1];
        }

        if (straight && !flush){
            value[0]=5;
            value[1]=topStraight;
        }

        if (flush && !straight){
            value[0]=6;
            value[1]=sortedRanks[0];
            value[2]=sortedRanks[1];
            value[3]=sortedRanks[2];
            value[4]=sortedRanks[3];
            value[5]=sortedRanks[4];
        }

        //full house
        if (sameRank1 == 3 && sameRank2 == 2){

            value[0] = 7;
            value[1] = group1;
            value[2] = group2;
        }

        // four of a kind
        if (sameRank1==4){

            value[0]=8;
            value[1]=group1;
            value[2]=sortedRanks[0];
        }

        if (straight && flush){
            value[0]=9;
            value[1]=topStraight;
        }
    }

    //displays the hand values
    void display(){
        String handResult;

        switch(value[0]){
            case 1:
                handResult = "high card:";
                break;
            case 2:
                handResult = "pair of " + Card.stringRank(value[1]) + "\'s:";
                break;
            case 3:
                handResult = "two pair " + Card.stringRank(value[1]) + "\'s and " + Card.stringRank(value[2]) + "\'s:";
                break;
            case 4:
                handResult = "three of a kind " + Card.stringRank(value[1]) + "\'s:";
                break;
            case 5:
                handResult = Card.stringRank(value[1]) + " high straight:";
                break;
            case 6:
                handResult = "flush:";
                break;
            case 7:
                handResult = "full house " + Card.stringRank(value[1]) + " over " + Card.stringRank(value[2])+":";
                break;
            case 8:
                handResult = "four of a kind:" + Card.stringRank(value[1]);
                break;
            case 9:
                handResult = "straight flush " + Card.stringRank(value[1]) + " high:";
                break;
            default:
                handResult = "error in Hand.display: value[0] contains invalid value";
        }
        System.out.print(handResult);
    }

    //prints the individual card values
    void displayAll(){
        for (int i=0; i<5; i++)
            System.out.print(" " + cards[i]);

    }

    //after all the values have been assigned this compares the values and assigns a winner
    String compares(Hand ei){
        for (int i=0; i<6; i++)
        {
            if (this.value[i]>ei.value[i])
                return (" Player wins ");
            else if (this.value[i]<ei.value[i])
                return " Computer wins ";
        }
        return "It is a tie";
    }
}
