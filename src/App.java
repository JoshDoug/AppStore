/* App Store Coursework
*  App class, objects instantiated by AppManager
*  @author joshstringfellow */

package appstorev3;

import java.io.Serializable;
import static java.lang.Double.parseDouble;
import java.util.Scanner;

public class App implements Serializable {
    private String appName;
    private String devName;
    private String appDesc;
    private String appType;
    private double appCost;
    private int appPopularity;
    private boolean freeTrial;
    
    //Constructor overloaded for testing purposes
    App(String appName, String devName, String appDesc, String appType, double appCost, boolean trial, int appPopularity){
        this.appName = appName;
        this.devName = devName;
        this.appDesc = appDesc;
        this.appType = appType;
        this.appCost = appCost;
        this.freeTrial = trial;
        this.appPopularity = 0;
    }
    
    //App constructor for user input
    App(Scanner userInput, String appName){
        this.appName = appName;
        System.out.println("Please enter the developer name.");
        this.devName = userInput.nextLine();
        System.out.println("Please enter a description of the app.");
        this.appDesc = userInput.nextLine();
        System.out.println("Please enter the type of app.");
        this.appType = userInput.nextLine();
        System.out.println("Please enter the app cost.");
        this.appCost = setAppCost(userInput.nextLine());
        System.out.println("Is the app a free trial? (y/n)");
        String trial = userInput.nextLine();
        setFreeTrial(trial);
        this.appPopularity = 0;
    }

    public String getAppName() {
        return appName;
    }

    //Checks name doesn't exist before renaming the key and the appName attribute
    public void setAppName(String newName, AppManager appManager, String appName) {
        if(!appManager.checkListFor(newName)){
            App tempApp = appManager.getListItem(appName);
            tempApp.appName = newName;
            appManager.addToList(tempApp.getAppName(), tempApp);
            appManager.removeFromList(appName);
        } else {
            System.out.println("Sorry that name is already taken.");
        }
    }
    
    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public double getAppCost() {
        return appCost;
    }

    //Basic type safety ensures user input won't crash program
    public final double setAppCost(String appCost) {
        try {
            return this.appCost = parseDouble(appCost);
        } catch(NumberFormatException e) {
            System.out.println("Number not recognised, app cost defaulted to 0.\nThis can be edited in the app editor.");
            return this.appCost = 0;
        }
    }

    public int getAppPopularity() {
        return appPopularity;
    }

    public void setAppPopularity() {
        this.appPopularity++;
    }

    public boolean isFreeTrial() {
        return freeTrial;
    }

    //inverts boolean if user wants to change free trial to paid or vice versa
    public void setFreeTrial() {
        this.freeTrial = !freeTrial;
    }
    
    //Takes user input to set free trial, fallbacks to no free trial if user input is incorrect
    private void setFreeTrial(String freeTrial){
        if(freeTrial.equalsIgnoreCase("y") || freeTrial.equalsIgnoreCase("yes")){
            this.freeTrial = true;
        } else if(freeTrial.equalsIgnoreCase("n") || freeTrial.equalsIgnoreCase("no")) {
            this.freeTrial = false;
        } else {
            System.out.println("Input not understood, app defaulted to free trial turned off."
                    + "\nThis can be changed by editing app info.");
            this.freeTrial = false;
        }
    }
    
    //Allows changes to be made to the app
    public void editApp(Scanner userInput, AppManager appManager){
        String userChoice;
        do {
            System.out.println("_________________________________________\n"
                    + "Change app details:\n"
                    + "(1) Edit app name\n"
                    + "(2) Edit developer name\n"
                    + "(3) Edit description\n"
                    + "(4) Edit app type\n"
                    + "(5) Edit app cost\n"
                    + "(6) Add/remove free trial\n"
                    + "(0) Exit app editor\n"
                    + "_________________________________________");
            userChoice = userInput.nextLine();
            
            switch(userChoice)
            {
                case "1":
                    System.out.println("Enter new app name");
                    setAppName(userInput.nextLine(), appManager, getAppName());
                break;
                case "2":
                    System.out.println("Enter new dev name");
                    setDevName(userInput.nextLine());
                break;
                case "3":
                    System.out.println("Enter new description");
                    setAppDesc(userInput.nextLine());
                break;
                case "4":
                    System.out.println("Enter new app type");
                    setAppType(userInput.nextLine());
                break;
                case "5":
                    System.out.println("Enter new app cost");
                    setAppCost(userInput.nextLine());
                break;
                case "6":
                    System.out.println("Add/Remove free trial");
                    setFreeTrial();
                break;
                case "0":
                    userChoice = "0";
                break;
                default:
                    System.out.println("Invalid input.");
            }
        } while(!userChoice.equals("0"));
    }
    
    //Prints all the info about the app
    public void printInfo(){
        System.out.println("App name: " + this.getAppName()
                    + "\nDeveloper name: "+ this.getDevName()
                    + "\nApp Description: " + this.getAppDesc()
                    + "\nApp Popularity: " + this.getAppPopularity()
                    + "\nApp Cost: £" + this.getAppCost()
                    + "\nApp has free trial: " + this.isFreeTrial()
                    + "\n_________________________________________");
    }
}