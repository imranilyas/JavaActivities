package GoFish.Players;

import GoFish.Cards.Card;

import java.util.List;

public class Player extends Playable {
    public Player(List<Card> list, int id) {
        super(list, id);
    }

    @Override
    public Card makeChoice(int key) {
        return hand.getOrDefault(key, null);
    }
}
