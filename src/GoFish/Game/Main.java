package GoFish.Game;

import GoFish.Cards.Card;
import GoFish.Cards.Deck;
import GoFish.Players.AI;
import GoFish.Players.Player;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        Scanner scanner = new Scanner(System.in);
        Main m = new Main();

        System.out.println("Welcome to Go Fish! Choose the game mode you would like to play?");
        System.out.println("1. Player 1 vs Player 2");
        System.out.println("2. Player vs CPU");

        //
        int input = 0;
        while(input < 1 || input > 2) {
            input = m.validScannerInput(scanner, Arrays.asList(1,2));
        }

        if(input == 1) {
            System.out.println("This will be added in future updates. We will reroute you to Player vs CPU. Thank you for your patience.");
        }

        System.out.println("You have chosen to play against the CPU. Good luck...");

        // Start game here
        Deck d = new Deck();

        HashMap<Integer, List<Card>> map = d.dealDeck();
        List<Card> player1 = map.get(0);
        List<Card> player2 = map.get(1);
        Player player = new Player(player1, 1);
        AI cpu = new AI(player2, 2);

        int playerId = player.getId();

        // May not need this class at all
        GameManagement gm = new GameManagement(playerId);
        while(!gm.game) {
            if(gm.turn == playerId) {
                // Player makes a choice here
                System.out.println("Player turn");
                int i = gm.playerRound(player, scanner);
                Card c = player.makeChoice(i);
                gm.correctChoice(player, cpu, c, i, d);
            } else {
                // CPU turn to make a choice
                System.out.println("CPU turn");
                int i = cpu.indexMap();
                Card c = cpu.makeChoice(i);
                gm.correctChoice(player,cpu, c, i, d);
            }
            gm.endRound(player, cpu);
        }
    }

    public int validScannerInput(Scanner scanner, List<Integer> list) {
        if(!scanner.hasNextInt()) {
            System.out.println("Invalid data type. Please select 1 or 2");
            return 0;
        }
        int input = scanner.nextInt();
        if(!list.contains(input)) {
            System.out.println("Invalid number. Please select 1 or 2");
            return 0;
        }
        return input;
    }
}
