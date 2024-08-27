package HelperClasses;

import Game.Game;
import GameEntities.Controller;
import GameEntities.Player;

/**
 *
 * @author noahm
 */
public class DescriptionHelper {
    
    private DescriptionHelper() {}
    
    public static String getHealthDescription(Controller controller) {
        int health = controller.getHealth();
        if (health == 0) {
            return "Broken";
        }
        else if (health <= 25) {
            return "Near broken";
        }
        else if (health <= 50) {
            return "Coming apart";
        }
        else if (health <= 75) {
            return "Still working";
        }
        else {
            return "Mint condition";
        }
    }
    
    public static String getFilthDescription(Controller controller) {
        int filth = controller.getFilth();
        if (filth <= 25) {
            return "Spotless";
        }
        else if (filth <= 50) {
            return "Could be better";
        }
        else if (filth <= 75) {
            return "Pretty gross";
        }
        else if (filth < 100) {
            return "Undeniably disgusting";
        }
        else {
            return "Untouchable";
        }
        
    }
    
    public static String getBatteryDescription(Controller controller) {
        int battery = controller.getBattery();
        if (battery == 0) {
            return "Dead";
        }
        else if (battery <= 25) {
            return "Near dead";
        }
        else if (battery <= 50) {
            return "Could use charging";
        }
        else if (battery <= 75) {
            return "Pretty full";
        }
        else {
            return "Ready to go";
        }
    }
    
    public static String getRageDescription(Player player) {
        int rage = player.getRage();
        if (rage <= 25) {
            return "Calm";
        }
        else if (rage <= 50) {
            return "Frustrated";
        }
        else if (rage <= 75) {
            return "Angry";
        }
        else if (rage < 100) {
            return "Malding";
        }
        else {
            return "Mega Malding";
        }
    }
    
    public static String getPatienceDescription(Player player) {
        int patience = player.getPatience();
        if (patience == 0) {
            return "Out of patience";
        }
        else if (patience <= 25) {
            return "Very impatient";
        }
        else if (patience <= 50) {
            return "Impatient";
        }
        else if (patience <= 75) {
            return "Slightly impatient";
        }
        else {
            return "Patient";
        }
    }
    
    public static String getEnergyDescription(Player player) {
        int energy = player.getEnergy();
        if (energy == 0.0) {
            return "Passed out";
        }
        if (energy <= 25.0) {
            return "Sleep walking";
        }
        else if (energy <= 50.0) {
            return "Ready for bed";
        }
        else if (energy <= 75.0) {
            return "Awake";
        }
        else {
            return "Wide awake";
        }
    }
    
    public static String[] getRNGDescription(Game game) {
        
        String[] arr = new String [2];
        double rng = game.getRNG();
        if (rng == 0.0) {
            arr[0] = "cursed";
            arr[1] = "HORRENDOUS";
        }
        else if (rng == 0.5) {
            arr[0] = "cursed";
            arr[1] = "bad";
        }
        else if (rng == 1.0) {
            arr[0] = "spared";
            arr[1] = "ordinary";
        }
        else if (rng == 1.5) {
            arr[0] = "blessed";
            arr[1] = "good";
        }
        else {
            arr[0] = "blessed";
            arr[1] = "GODLIKE";
        }
        return arr;
    }
    
    public static String[] getWinConditionDescription(int bossesDefeated, boolean controllerBroken, String playerName, String controllerBrand) {
        String[] arr = new String[2];
        if (bossesDefeated >= 3) {
            arr[0] = "won";
            arr[1] = "by defeating all 3 bosses!";
        } else if (controllerBroken) {
            arr[0] = "lost";
            arr[1] = "by destroying " + playerName + "'s " + controllerBrand + " controller!";
        } else {
            arr[0] = "lost";
            arr[1] = "by running out of time.";
        }
        
        return arr;
    }
    
    public static void printEntityStatus(Controller controller, Player player) {
        
        System.out.println("CONTROLLER:\t\t         PLAYER:");
        
        // displays formatted controller stats 
        String[] controllerStatNames = {"Health:", "Filth:", "Battery:"};
        String[] controllerDescriptions = {getHealthDescription(controller), getFilthDescription(controller), getBatteryDescription(controller)};
        
        String[] playerStatNames = {"Rage:", "Patience:", "Energy:"};
        String[] playerDescriptions = {getRageDescription(player), getPatienceDescription(player), getEnergyDescription(player)};
        
        for (int i = 0; i < controllerStatNames.length; i++) {
            System.out.println(String.format("%-8s %-23s %-9s %s", controllerStatNames[i], controllerDescriptions[i], playerStatNames[i], playerDescriptions[i]));
        }
    }
    
}
