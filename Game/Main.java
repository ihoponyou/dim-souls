package Game;

import HelperClasses.GameSetup;
import HelperClasses.MenuHelper;

/**
 *
 * @author noahm
 */
public class Main {
    
    public static void main(String[] args) {
        
        int option = 0;
        do {
            Game game = GameSetup.setupGame();
            game.start();
            option = MenuHelper.displayMenu("Would you like to start a new game? 0 -> No | 1 -> Yes\n", 0, 1);
            System.out.println("");
        } while (option == 1);
        
    }
    
}
