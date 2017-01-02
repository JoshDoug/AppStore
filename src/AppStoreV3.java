/* App Store Coursework
*  AppStore main class
*  Instantiates container classes, stores and restores data.
*/
/* @author joshstringfellow */

package appstorev3;

import java.util.Scanner;

public class AppStoreV3 {

    /** @param args the command line arguments */
    public static void main(String[] args) {
        //Instantiation of Storage object
        Storage storeAppStore = new Storage();
        
        //Instantiation of App Container object
        AppManager appManager = new AppManager();
        //Call method to deserialize and restore data from apps.ser into appManager HashMap
        appManager.setList(storeAppStore.deSerializeData(appManager.getList(), "apps.ser"));
        
        //Instantiation of User Container object
        UserManager userManager = new UserManager();
        //Call method to deserialize and restore data from users.ser into userManager HashMap
        userManager.setList(storeAppStore.deSerializeData(userManager.getList(), "users.ser"));
        
        //Instantiate Scanner object and String to get user input
        Scanner userInput = new Scanner(System.in);
        String userChoice;
        //Start user input loop and interaction with CLI
        do {
            System.out.println("_________________________________________\n"
                    + "Welcome to the App Store, what do you want to do?\n"
                    + "(1) Do you want to login,\n"
                    + "(2) register as a new customer,\n"
                    + "(3) manage users,\n"
                    + "(4) manage apps,\n"
                    + "(0) or exit the program.\n"
                    + "Please enter the corresponding number.\n"
                    + "_________________________________________");
            userChoice = userInput.nextLine();
            
            switch(userChoice){
                case "1":
                    /*User can login with their username, then edit their info,
                    list and search for apps, choose apps and pay for them*/
                    userManager.loginUser(userInput, appManager);
                break;
                case "2":
                    //User registers and is then immediately logged in
                    userManager.loginUser(userInput, appManager, userManager.addUser(userInput));
                break;
                case "3":
                    //Access the user manager to add, list, edit and delete users
                    userManager.manageUsers(userInput);
                break;
                case "4":
                    //Access the app manager to add, list, edit and delete apps
                    appManager.manageApps(userInput);
                break;
                case "5":
                    createDemoObjects(userManager, appManager);
                break;
                case "0":
                    //End loop and finish program
                    userChoice = "0";
                break;
                default:
                    //If invalid input is given the user is notified
                    System.out.println("Invalid input.");
            }
        } while(!userChoice.equals("0"));

        //Call function/object method to serialize data/hashmap from hashmaps into files
        storeAppStore.serializeData(appManager.getList(), "apps.ser");
        storeAppStore.serializeData(userManager.getList(), "users.ser");
    }
    
    //Quickly add apps and users to flesh out demo instead of doing so manually
    public static void createDemoObjects(UserManager userManager, AppManager appManager){
        Customer testUser1 = new Customer("BloggerJoe", "Joe Bloggs", "12, Desmond Rd", "Raynes Park", "Surrey", "SW16 8PB", "Developer", 2, 1, 12.00);
        Student testUser2 = new Student("TesterMike", "Mike Smith", "Nice House", "Decent Town", "Okay County", "WR15 6FB", "Tester", 8, 6, 58.00);
        Academic testUser3 = new Academic("LecturerJan", "Jan Appleseed", "Nicer House", "Great Town", "Green County", "NW 9GK", "Lecturer", 4, 2, 7.00);
        userManager.addToList(testUser1.getUserName(), testUser1);
        userManager.addToList(testUser2.getUserName(), testUser2);
        userManager.addToList(testUser3.getUserName(), testUser3);
        
        App testApp1 = new App("Network News", "John Bee", "App for checking the news", "News", 15.00, false, 54);
        App testApp2 = new App("Rain's a pain", "Michelle Stormzy", "Don't get caught in the rain with this weather app!", "Weather App", 5, true, 36);
        App testApp3 = new App("Crazy Games", "Jess Hung", "These games are crazy", "Game", 10, true, 4);
        App testApp4 = new App("Procrastintor", "Jeff Netflix", "You're not getting any work done with this app", "Time Waster", 0, false, 17);
        appManager.addToList(testApp1.getAppName(), testApp1);
        appManager.addToList(testApp2.getAppName(), testApp2);
        appManager.addToList(testApp3.getAppName(), testApp3);
        appManager.addToList(testApp4.getAppName(), testApp4);
    }
}