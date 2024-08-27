package HelperClasses;

import java.util.Scanner;

/**
 *
 * @author noahm
 */
public class MenuHelper {
    
    private MenuHelper() {}
    
    public static int displayMenu(String menuText, int min, int max) {
        
        // both displays menu text and processes input
        
        Scanner scan = new Scanner(System.in);
        
        System.out.print(menuText);
        
        int input = -1;
        while (input < min || input > max) {
            
            System.out.print("Please enter a corresponding integer " + min + " to " + max + ": ");
            
            while (!scan.hasNextInt()) {
                scan.nextLine();
                System.out.print("Please enter a corresponding integer " + min + " to " + max + ": ");
            }
            
            input = scan.nextInt();
            
        }
        
        return input;
        
    }
    
    public static String getInput(String prompt) {
        
        Scanner scan = new Scanner(System.in);
        
        System.out.print(prompt);
        return scan.nextLine();
        
    }
    
}
