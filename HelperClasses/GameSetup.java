package HelperClasses;

import Game.Game;
import GameEntities.Controller;
import GameEntities.Player;
import java.util.Random;

/**
 *
 * @author noahm
 */
public class GameSetup {
    
    private GameSetup() {}
    
    public static Game setupGame() {
        
        System.out.println("Welcome to Dim Souls!");
        
        String difficultyText = "Please choose a difficulty:\n"
                           + "1) Easy\n"
                           + "2) Normal\n"
                           + "3) Hard\n";
        int difficulty = MenuHelper.displayMenu(difficultyText, 1, 3);
        
        String playerText = "Please choose a player archetype:\n"
                           + "1) Standard\n"
                           + "2) Random\n"
                           + "3) Custom\n";
        Player player = setupPlayer(MenuHelper.displayMenu(playerText, 1, 3));
        
        String controllerText = "Please choose a controller type:\n"
                           + "1) Standard\n"
                           + "2) Random\n"
                           + "3) Custom\n";
        Controller controller = setupController(MenuHelper.displayMenu(controllerText, 1, 3));
        
        System.out.println("");
        System.out.println(player.getName() + " wakes up to a glorious three day weekend, a brand new " + controller.getName() + " controller, and a copy of the cult classic videogame Dim Souls.");
        System.out.println("Can " + player.getName() + " beat the 3 bosses of Dim Souls before the weekend is over? Or will their short temper get the best of them?");
        
        return new Game(difficulty, player, controller);
        
    }
    
    public static Controller setupController(int option) {
        
        Controller controller = new Controller();
        
        // if option is 2 or 3 then create special controller
        switch (option) {
                
            // creates controller with randomized stats
            case 2:
                Random rand = new Random();
                controller = new Controller(rand.nextInt(100)+1,rand.nextInt(100)+1,rand.nextInt(100)+1);
                break;
                
            // creates custom controller using user inputs
            case 3:
                int health = MenuHelper.displayMenu("Please enter an initial HEALTH amount for your controller with 0 being broken.\n", 0, 100);
                int filth  = MenuHelper.displayMenu("Please enter an initial FILTH amount for your controller with 0 being spotless.\n", 0, 100);
                int energy = MenuHelper.displayMenu("Please enter an initial BATTERY amount for your controller with 0 being dead.\n", 0, 100);
                controller = new Controller(health, filth, energy);
                break;
                
        }
        
        controller.setName(MenuHelper.getInput("Please enter a brand name for your controller: "));
        
        return controller;
        
    }
    
    public static Player setupPlayer(int option) {
        
        Player player = new Player();
        
        // if option is 2 or 3 then create special player
        switch (option) {
                
            // creates controller with randomized stats
            case 2:
                Random rand = new Random();
                player = new Player(rand.nextInt(100)+1, rand.nextInt(100)+1, rand.nextInt(100)+1);
                break;
                
            // creates custom controller using user inputs
            case 3:
                int rage = MenuHelper.displayMenu("Please enter an initial RAGE amount for yourself with 0 being calm.\n", 0, 100);
                int patience = MenuHelper.displayMenu("Please enter an initial PATIENCE amount for yourself with 0 being extremely impatient.\n", 0, 100);
                int energy  = MenuHelper.displayMenu("Please enter an initial ENERGY amount for yourself with 0 being extremely exhausted.\n", 0, 100);
                player = new Player(rage, patience, energy);
                break;
                
        }
        
        player.setName(MenuHelper.getInput("Please enter a name for yourself: "));
        
        return player;
       
    }
    
}
