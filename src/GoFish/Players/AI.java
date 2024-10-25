package GoFish.Players;

import GoFish.Cards.Card;

import java.util.List;

public class AI extends Playable {
    public AI(List<Card> list, int id) {
        super(list, id);
    }

    @Override
    public Card makeChoice(int key) {
        int count = 1;
        for(Integer i : hand.keySet()) {
            if(count == key) {
                return hand.get(i);
            } else {
                count++;
            }
        }
        return null;
    }

    public int indexMap() {
        int size = hand.size();
        return (int) Math.round(Math.random() * (size - 1) + 1);
    }
}
