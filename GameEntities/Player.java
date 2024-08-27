package GameEntities;

import HelperClasses.GameplayHelper;

/**
 *
 * @author noahm
 */
public class Player {

    private String name = "Default Player";
    private int rage = 0, patience = 100, energy = 100;

    public Player() {
    }

    public Player(int rage, int patience, int energy) {
        this.rage = rage;
        this.patience = patience;
        this.energy = patience;
    }

    public int getRage() {
        return rage;
    }

    public void adjustRage(int value) {
        rage = GameplayHelper.adjustStat(rage, value);
    }

    public int getPatience() {
        return patience;
    }

    public void adjustPatience(int value) {
        patience = GameplayHelper.adjustStat(patience, value);
    }

    public int getEnergy() {
        return energy;
    }

    public void adjustEnergy(int value) {
        energy = GameplayHelper.adjustStat(energy, value);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        if (name instanceof String) { this.name = name; }
    }
    
}
