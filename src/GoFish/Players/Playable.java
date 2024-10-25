package GoFish.Players;

import GoFish.Cards.Card;
import GoFish.Cards.Deck;

import java.util.HashMap;
import java.util.List;

abstract class Playable {
    public HashMap<Integer, Card> hand = new HashMap<>();
    public int mapIndex;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Playable(List<Card> list, int id) {
        setId(id);

        int count = 1;
        for(Card c : list) {
            hand.put(count, c);
            count++;
        }

        mapIndex = count;
    }

    public void draw(Deck d) {
        System.out.println("Go Fish");
        Card c = d.gameDeck.poll();
        if(c != null) {
            hand.put(mapIndex + 1, c);
            mapIndex++;
        }
    }

    public void displayHand() {
        for(Integer i : hand.keySet()) {
            System.out.println(i + ". " + hand.get(i).value + " of " + hand.get(i).suit);
        }
    }

    public boolean emptyHand() {
        return hand.isEmpty();
    }

    public abstract Card makeChoice(int key);
}
