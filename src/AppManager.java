/* App Store Coursework
 * AppManager class for managing and accesing app objects
 * inherits from Manager
   @author joshstringfellow */

package appstorev3;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

public class AppManager extends Manager implements Serializable {
    private int totalAppsPurchased;
    
    public AppManager(){
        super();
        totalAppsPurchased = 0;
    }

    public int getTotalAppsPurchased() {
        return totalAppsPurchased;
    }

    public void setTotalAppsPurchased() {
        this.totalAppsPurchased++;
    }
    
    //Checks app doesn't already exist before calling app constructor
    public void addApp(Scanner userInput){
        String appName;
        do {
        System.out.println("Please enter the app name, this should be unique.");
        appName = userInput.nextLine();
        } while(this.checkListFor(appName) || appName.equals(""));
        App newApp = new App(userInput, appName);
        this.addToList(newApp.getAppName(), newApp);
    }
    
    //Overrides getListItem to return an App object instead of a generic Object
    @Override
    public App getListItem(String name){
        return (App) this.list.get(name);
    }
    
    //Checks search item exists before confirming and printing info
    public void searchListItem(Scanner userInput){
        System.out.println("Enter app name:");
        String searchTerm = userInput.nextLine();
        if(checkListFor(searchTerm)){
            App tempObject = getListItem(searchTerm);
            tempObject.printInfo();
        } else {
            System.out.println("That app doesn't seem to exist,\nto see what apps there are choose to print a list.");
        }
    }

    //Prints all info about each app instead of just the key
    public void printFullList(){
        System.out.println("List:");
        //Again, ignore Netbeans' recommendation
        for (HashMap.Entry<String, Object> entry : list.entrySet()) {
            System.out.println("Name: " + entry.getKey());
            App temp = (App) entry.getValue();
            temp.printInfo();
        }
    }
    
    //Similar to loop in main, displays user choices and takes user input
    public void manageApps(Scanner userInput){
        String userChoice;
        do {
            System.out.println("\n_________________________________________\n"
                    + "App Management, do you want to:\n"
                    + "(1) list apps,\n"
                    + "(2) list apps with app info,\n"
                    + "(3) search apps,\n"
                    + "(4) add an app,\n"
                    + "(5) edit an app,\n"
                    + "(6) remove an app,\n"
                    + "(7) check the total number of purchased apps,\n"
                    + "(0) or exit the app manager.\n"
                    + "_________________________________________");
            userChoice = userInput.nextLine();
            
            switch(userChoice)
            {
                case "1":
                    printList();
                break;
                case "2":
                    printFullList();
                break;
                case "3":
                    searchListItem(userInput);
                break;
                case "4":
                    addApp(userInput);
                break;
                case "5":
                    System.out.println("Enter the name of the app you want to edit.");
                    String appName = userInput.nextLine();
                    if (this.checkListFor(appName)){
                        App tempApp = (App) getListItem(appName);
                        tempApp.editApp(userInput, this);
                    } else {
                        System.out.println("That app doesn't exist.");
                    }
                break;
                case "6":
                    System.out.println("Please enter the name of the app you want to remove.");
                    String appToRemove = userInput.nextLine();
                    if (this.checkListFor(appToRemove)){
                        removeFromList(appToRemove);
                        System.out.println("App removed.");
                    } else {
                        System.out.println("That app doesn't exist.");
                    }
                break;
                case "7":
                    System.out.println(getTotalAppsPurchased() + " app(s) have been purchased.");
                break;
                case "0":
                    userChoice = "0";
                break;
                default:
                    System.out.println("Invalid Input.");
            }
        } while(!userChoice.equals("0"));
    }
}