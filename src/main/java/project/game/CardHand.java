package project.game;

import java.util.ArrayList;

public class CardHand {
    ArrayList<String> cardhand;

    //Instantiation
    CardHand() { cardhand = new ArrayList<>(); }

    //Clear's player hand
    public void clearHand() {
        this.cardhand.clear();
    }

    //Get player hand
    public String getHand(){
        return this.cardhand.toString();
    }

    //Adds given card to hand
    public void addCardToHand(String card){
        this.cardhand.add(card);
    }

    //Removes given card from hand
    public boolean removeCardFromHand(String card){
        //iterate through player's hand
        for(int i = 0; i < this.cardhand.size(); i++){
            //if specified card is found, perform removal
            if (this.cardhand.get(i).equals(card)){
                this.cardhand.remove(i);
                return true;
            }
        }
        return false;
    }

    //Sets player's hand to given str array
    public void setHand(String[] cards){
        this.cardhand.clear();
        for (int i = 0; i < cards.length; i++){
            this.cardhand.add(cards[i]);
        }
    }

    public ArrayList<String> getHandList() {
        return this.cardhand;
    }

}
