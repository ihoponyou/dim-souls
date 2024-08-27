package Game;

import GameEntities.Controller;
import GameEntities.Player;
import HelperClasses.DescriptionHelper;
import HelperClasses.MenuHelper;
import java.util.Random;

/**
 *
 * @author noahm
 */
public class Game {
    
    private Random rand = new Random();
    
    private int bossesDefeated = 0, baseBossBeatChance = 0, bossBeatChance = 0, difficulty = 2, day = 1, time = 1;
    private double rng = 0.0; 
    private Player player;
    private Controller controller;
    
    public Game() {}
    
    public Game(int difficulty, Player player, Controller controller) {
        this.difficulty = difficulty;
        this.player = player;
        this.controller = controller;
        
        // changes base chance to beat boss based on difficulty
        switch (difficulty) {
            case 1:
                baseBossBeatChance = 50;
                break;
            case 2:
                baseBossBeatChance = 25;
                break;
            case 3:
                baseBossBeatChance = 10;
        }
        bossBeatChance = baseBossBeatChance; // sets running boss beat chance to base at start
    }
    
    public Controller getController() {
        return controller;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public double getRNG() {
        return rng;
    }
    
    public int getDifficulty() {
        return difficulty;
    }
    
    public int getBossesDefeated() {
        return bossesDefeated;
    }
    
    private boolean checkControllerUsable() {
        if (controller.getBattery() > 0 && controller.getFilth() < 100) {
            return true;
        }
        else {
            System.out.println("Your " + controller.getName() + " controller is not usable.");
            return false;
        }
    }
    
    private boolean checkPlayerCanPlay() {
        if (player.getPatience() > 0 && player.getEnergy() > 0) {
            return true;
        }
        else {
            System.out.println(player.getName() + " is physically unable to play.");
            return false;
        }
    }
    
    private boolean checkPlayerMaxRage() {
        return player.getRage() == 100;
    }
    
    private boolean checkWinCondition() {
        return (bossesDefeated >= 3);
    }
    
    private void setNewRNG() {
        // limits possible rng by difficulty
        switch (difficulty) {
            // easy can only get ordinary, good, or godlike
            case 1:
                rng = rand.nextInt(3)+2; // 2, 3, 4
                break;
            // normal can get any of them
            case 2:
                rng = rand.nextInt(5); // 0, 1, 2, 3, 4 
                break;
            // hard can only get horrendous, bad, or ordinary
            case 3:
                rng = rand.nextInt(3); // 0, 1, 2
        }
        rng *= 0.5; // 0 -> 0.0, 1 -> 0.5, 2 -> 1.0, 3 -> 1.5, 4 -> 2.0
        
    }
    
    private void processAttemptBoss() {
        
        // debugging statements
        //System.out.println("chance to beat boss: " + bossBeatChance * rng);
        //System.out.println("rage multiplier: " + ((0.5 * day) + 0.5));
        
        // random chance to beat boss, guaranteed success if godlike rng
        if ((rand.nextInt(100)+1 <= (bossBeatChance * rng)) || rng == 2.0) {
            System.out.println(player.getName() + " successfully beats a boss!");
            bossesDefeated += 1;
            bossBeatChance = baseBossBeatChance;
        }
        else {
            
            // changes how much player loses/gains based on difficulty
            int stake = 0;
            switch (difficulty) {
                case 1:
                    stake = 20;
                    break;
                case 2:
                    stake = 25;
                    break;
                case 3:
                    stake = 34;
            }
            
            System.out.println(player.getName() + " fails a boss.");
            player.adjustRage((int) (stake * ((0.5 * day) + 0.5)));
            player.adjustPatience(-stake * (4 - day));
            
            // random chance to throw controller at wall on failed boss attempt
            if (rand.nextInt(2) == 1) {
                
                System.out.println(player.getName() + " throws their " + controller.getName() + " controller at the wall.");
                
                // damages controller based on difficulty
                switch (difficulty) {
                    case 1:
                        controller.adjustHealth(-stake);
                        break;
                    case 2:
                        controller.adjustHealth(-stake);
                        break;
                    case 3:
                        controller.adjustHealth(-50);
                }
                
                if (controller.getHealth() == 0) { System.out.println("The " + controller.getName() + " controller breaks."); }
            }
        }
        
        // normal battery drain for normal rng, no drain for godlike, increased drain for horrendous
        if (rng != 2.0) {
            controller.adjustBattery(-15);
        }
        else if (rng == 0.0) {
            controller.adjustBattery(-30);
        }
        controller.adjustFilth(10);
    }
    
    private void processFarmSouls() {
        //System.out.println("patience loss multiplier: " + (4 - day));
        
        System.out.println(player.getName() + " farms souls. Good practice for boss fights, but very tedious.");
        
        // changes how much player loses/gains based on difficulty
        int stake = 0;
        switch (difficulty) {
            case 1:
                stake = 3;
                bossBeatChance += 20;
                break;
            case 2:
                stake = 5;
                bossBeatChance += 15;
                break;
            case 3:
                stake = 7;
                bossBeatChance += 10;
        }
        
        player.adjustEnergy(-5 * stake + 15);
        player.adjustPatience(-5 * stake * (4 - day) + 20);
        
        // normal battery drain for normal rng, no drain for godlike, increased drain for horrendous
        if (rng != 2.0) {
            controller.adjustBattery(-10);
        }
        else if (rng == 0.0) {
            controller.adjustBattery(-20);
        }
        controller.adjustFilth(5 * stake - 10);
        
    }
    
    private void processEatSnack() {
        
        System.out.println(player.getName() + " eats a snack and feels revitalized, but dirties their " + controller.getName() + " controller as a result.");
        
        // changes how much player loses/gains based on difficulty
        int stake = 0;
        switch (difficulty) {
            case 1:
                stake = 15;
                break;
            case 2:
                stake = 0;
                break;
            case 3:
                stake = -15;
        }
        
        if (time == 1) {
            player.adjustEnergy(35 + stake);
            player.adjustPatience(35 + stake);
        }
        else {
            player.adjustEnergy(15 + stake);
            player.adjustPatience(15 + stake);
        }
        
        controller.adjustFilth(15 + (-stake));
        
    }
    
    private void processTakeNap() {
        
        System.out.println(player.getName() + " takes a nap.");
        
        // changes how much player loses/gains based on difficulty
        int stake = 0;
        switch (difficulty) {
            case 1:
                stake = 25;
                break;
            case 2:
                stake = 0;
                break;
            case 3:
                stake = -25;
        }
        
        switch (time) {
            case 1:
                //System.out.println("morning?");
                player.adjustEnergy(25 + stake);
                break;
            case 2:
                //System.out.println("afternoon?");
                player.adjustEnergy(75 + stake);
                break;
            case 3:
                //System.out.println("evening?");
                player.adjustEnergy(100 + stake);
                break;
            case 4:
                //System.out.println("night?");
                player.adjustEnergy(100 + stake);
        }
        time ++;
        
        player.adjustPatience(100);
        player.adjustRage(-100);        
        
    }
    
    private void processCleanController() {
        
        System.out.println(player.getName() + " cleans their " + controller.getName() + " controller.");
        
        // changes how much player loses/gains based on difficulty
        int stake = 0;
        switch (difficulty) {
            case 1:
                stake = 15;
                break;
            case 2:
                stake = 0;
                break;
            case 3:
                stake = -10;
        }
        
        player.adjustEnergy(-5 - stake);
        
        controller.adjustFilth(-25 - stake);
        
    }
    
    private void processReplaceBatteries() {
        
        System.out.println(player.getName() + " replaces the batteries of their " + controller.getName() + " controller.");
        
        controller.adjustBattery(100);
        
        // changes how much player loses/gains based on difficulty
        int stake = 0;
        switch (difficulty) {
            case 1:
                stake = 5;
                break;
            case 2:
                stake = 0;
                break;
            case 3:
                stake = -10;
        }
        
        player.adjustEnergy(-5 + stake);
        
    }
    
    private void processAction() {
        String[] actions = {"1) Attempt boss\n",
                            "2) Farm souls\n",
                            "3) Eat snack\n",
                            "4) Clean controller\n",
                            "5) Replace batteries\n",
                            "6) Take nap\n"};
        String actionMenu = "\nWhat would you like to do?\n";
        int startingActionIndex = 0;
        if (player.getEnergy() <= 0) {
            startingActionIndex = 5;
        } 
        else if (!checkControllerUsable() || !checkPlayerCanPlay()) {
            startingActionIndex = 2;
        }
        for (int i = startingActionIndex; i < actions.length; i++) { actionMenu = actionMenu + actions[i]; }
        switch (MenuHelper.displayMenu(actionMenu, startingActionIndex+1, 6)) {
            case 1:
                processAttemptBoss();
                break;
            case 2:
                processFarmSouls();
                break;
            case 6:
                processTakeNap();
                break;
            case 3:
                processEatSnack();
                break;
            case 4:
                processCleanController();
                break;
            case 5:
                processReplaceBatteries();       
        }
    }
    
    public void start() {
        
        while (day < 4) {
            
            // prints formatted day
            System.out.print("\n\t");
            switch (day) {
                    case 1:
                        System.out.print("  - - - -~= FRIDAY");
                        break;
                    case 2:
                        System.out.print(" - - - -~= SATURDAY");
                        break;
                    case 3:
                        System.out.print("  - - - -~= SUNDAY");
                }
            System.out.println(" =~- - - -");
            
            setNewRNG();
            
            String rngText = "RNGesus has " + DescriptionHelper.getRNGDescription(this)[0] + " you with " + DescriptionHelper.getRNGDescription(this)[1] + " RNG.";
            // pads rngtext with spaces (big fan of one line loops)
            for (int s = 0; s < (23 - rngText.length()/2); s++) { System.out.print(" "); }
            System.out.println(rngText);
            
            time = 1;
            
            while (time < 5) {
                
                // prints formatted time of day
                System.out.println("");
                switch (time) {
                    case 1:
                        System.out.print("\t\t--| MORNING");
                        break;
                    case 2:
                        System.out.print("\t       --| AFTERNOON");
                        break;
                    case 3:
                        System.out.print("\t\t--| EVENING");
                        break;
                    case 4:
                        System.out.print("\t\t --| NIGHT");
                }
                System.out.println(" |--");
                
                String bossText = "Bosses defeated: " + bossesDefeated;
                // pads rngtext with spaces (big fan of one line loops)
                for (int s = 0; s < (23 - bossText.length()/2); s++) { System.out.print(" "); }
                System.out.println(bossText);
                
                // PRE-action stuff here
                
                // passive energy/battery drain and filth gain
                player.adjustEnergy(-17);
                controller.adjustBattery(-10);
                controller.adjustFilth(5);
                
                DescriptionHelper.printEntityStatus(controller, player);
                
                // DURING action stuff here
                
                processAction();
                
                // POST-action stuff here
                
                // when player hits max rage, they snap their controller (controller health set to 0)
                if (checkPlayerMaxRage() && controller.getHealth() > 0) {
                    System.out.println(player.getName() + " snaps their " + controller.getName() + " controller in half.");
                    controller.adjustHealth(-100);                    
                }
                
                // checks win/loss condition every time of day
                if (checkWinCondition() || (controller.getHealth() == 0)) {
                    break;
                }
                
                time++;
                // skips a day if player napped during evening/night
                if (time > 4) { break; }
                
            }
            
            // check win/loss condition every day
            if (checkWinCondition() || (controller.getHealth() == 0)) {
                break;
            }
            day++;
            
        }
        
        // displays win/loss text
        String[] wOrLDescription = DescriptionHelper.getWinConditionDescription(bossesDefeated, (controller.getHealth() <= 0), player.getName(), controller.getName());
        System.out.println("\nYou have " + wOrLDescription[0] + " Dim Souls " + wOrLDescription[1]);
        
    }
    
}
