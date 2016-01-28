import javax.smartcardio.Card;

/**
 * Created by Jan on 25.01.2016.
 */
public class CardDeck {

    public static void main(String[] args){
        String[] deck = new String[52];
        String[] suit = new String[4];
        int[] card = new int[13];

        for(int i=0;i<card.length;i++){card[i] = i+1;}

        suit[0] = "Clubs";
        suit[1] = "Diamonds";
        suit[2] = "Hearts";
        suit[3] = "Spades";

        String CardName = "";
        for(int i=0;i<4;i++){
            for(int x=0;x<13;x++){
                if(x+1 == 1){
                    CardName = "Ace";
                }
                else if(x+1 == 11){
                    CardName = "Jack";
                }
                else if(x+1 == 12){
                    CardName = "Queen";
                }
                else if(x+1 == 13){
                    CardName = "King";
                }
                else{
                    CardName = Integer.toString(card[x]);
                }
                deck[i*13+x] = suit[i]+" "+ CardName;
            }
        }
        for(int i=0;i<52;i++){
            //System.out.println(deck[i]);
            printCard(deck[i]);
        }

    }
    public static void printCard(String Name) {
        if (Name.split(" ")[0].equals("Diamonds") | Name.split(" ")[0].equals("Hearts")) {
            System.out.println((char)27 + "[31m"+Name + (char)27 + "[0m");

        }
        if (Name.split(" ")[0].equals("Clubs") | Name.split(" ")[0].equals("Spades")) {
            System.out.println(Name);
        }
    }

}
