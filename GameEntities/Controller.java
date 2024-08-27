package GameEntities;

import HelperClasses.GameplayHelper;

/**
 *
 * @author noahm
 */
public class Controller {
    
    private String name = "Default Controller";
    private int health = 100, filth = 0, battery = 100;
    
    public Controller() {}
    
    public Controller(int health, int filth, int energy) {
        this.health = health;
        this.filth = filth;
        this.battery = energy;
    }
    
    public int getHealth() {
        return health;
    }
    
    public void adjustHealth(int value) {
        health = GameplayHelper.adjustStat(health, value);
    }
    
    public int getFilth() {
        return filth;
    }
    
    public void adjustFilth(int value) {
        filth = GameplayHelper.adjustStat(filth, value);
    }
    
    public int getBattery() {
        return battery;
    }
    
    public void adjustBattery(int value) {
        battery = GameplayHelper.adjustStat(battery, value);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        if (name instanceof String) { this.name = name; }
    }
    
}
