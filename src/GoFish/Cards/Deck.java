package GoFish.Cards;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Deck {
    public final int deal = 14;
    public Queue<Card> gameDeck = new LinkedList<>();

    public Deck() throws JsonProcessingException {
        setDeck();
    }

    // Reads from JSON, writes to a list, shuffles it, and adds to Queue
    public void setDeck() {
        try {
            ObjectMapper om = new ObjectMapper();
            // From root project directory
            File file = new File("src/GoFish/cardData.json");
            List<Card> list = om.readValue(file, new TypeReference<List<Card>>() {});
            // Shuffle and add to queue
            Collections.shuffle(list);
            gameDeck.addAll(list);
        } catch(JsonProcessingException e) {
            System.out.println("JSON Error: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Removes from queue and alternates until reaching deal amount
    public HashMap<Integer, List<Card>> dealDeck() {
        List<Card> player1 = new ArrayList<>();
        List<Card> player2 = new ArrayList<>();
        int count = 1;
        while(count <= deal) {
            if(count%2 != 0) {
                player1.add(gameDeck.poll());
            } else {
                player2.add(gameDeck.poll());
            }
            count++;
        }

        HashMap<Integer, List<Card>> map = new HashMap<>();
        map.put(0, player1);
        map.put(1, player2);
        return map;
    }
}
