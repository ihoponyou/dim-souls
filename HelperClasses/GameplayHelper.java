package HelperClasses;

/**
 *
 * @author Noah
 */
public class GameplayHelper {
    
    public static int adjustStat(int stat, int value) {
        //System.out.print(stat);
        if (stat + value > 100) {
            stat = 100;
        }
        else if (stat + value < 0) {
            stat = 0;
        }
        else {
            stat += value;
        }
        //System.out.println(" -> " + stat);
        return stat;
        
    }
    
}
