package GoFish.Game;

import GoFish.Cards.Card;
import GoFish.Cards.Deck;
import GoFish.Players.AI;
import GoFish.Players.Player;

import java.util.HashMap;
import java.util.Scanner;

public class GameManagement {
    public int turn;
    public boolean game = false;

    public GameManagement(int num) {
        turn = num;
    }

    public int playerRound(Player player, Scanner scanner) {
        player.displayHand();
        System.out.println("Select the card you would like to ask...");
        int valid = 0;

        while(valid == 0) {
            valid = validScannerInput(scanner, player.hand);
        }
        return valid;
    }

    public void endRound(Player player, AI cpu) {
        if(turn == player.getId()) {
            if(player.emptyHand()) {
                game = player.emptyHand();
                System.out.println("Player 1 wins!!!!");
            } else {
                turn = cpu.getId();
            }
        } else {
            if(cpu.emptyHand()) {
                game = cpu.emptyHand();
                System.out.println("The CPU won...You never stood a chance");
            } else {
                turn = player.getId();
            }
        }
    }

    public void correctChoice(Player player, AI cpu, Card c, int num, Deck d) {
        if(turn == player.getId()) {
            for(Integer i : cpu.hand.keySet()) {
                if (cpu.hand.get(i).value == c.value) {
                    cpu.hand.remove(i);
                    player.hand.remove(num);
                    System.out.println("Niiiiiice");
                    return;
                }
            }
            player.draw(d);
        } else {
            for(Integer i : player.hand.keySet()) {
                if (player.hand.get(i).value == c.value) {
                    player.hand.remove(i);
                    cpu.hand.remove(num);
                    System.out.println("Hehehe");
                    return;
                }
            }
            cpu.draw(d);
        }
    }

    // Check for valid inout from the User
    public int validScannerInput(Scanner scanner, HashMap<Integer, Card> map) {
        if(!scanner.hasNextInt()) {
            System.out.println("Invalid data type. Please type in valid number");
            return 0;
        }
        int input = scanner.nextInt();
        if(!map.containsKey(input)) {
            System.out.println("Invalid number. Please select one of the options above where your hand is displayed");
            return 0;
        }
        return input;
    }
}
