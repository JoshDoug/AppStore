/* App Store Coursework 
 * User Class, users are instantiated by UserManager
   @author joshstringfellow */

package appstorev3;

import java.io.Serializable;
import java.util.Scanner;

public class Customer implements Serializable {
    private String userName;
    private String fullName;
    private Address address;
    private String profession;
    private int trialsUsed;
    private int appsPurchased;
    protected double invoice;
    
    //Constructor overloaded for testing purposes
    public Customer(String userName, String fullName, String numAndStreet, String town, String county,
            String postCode, String profession, int trialsUsed, int appsPurchased, double invoice) {
        this.userName = userName;
        this.fullName = fullName;
        this.address = new Address(numAndStreet, town, county, postCode);
        this.profession = profession;
        this.trialsUsed = trialsUsed;
        this.appsPurchased = appsPurchased;
        this.invoice = invoice;
    }
    
    //Customer constructor for user input, also calls Address constructor
    Customer(Scanner userInfo, String userName){
        this.userName = userName;
        System.out.println("Please enter your full name.");
        this.fullName = userInfo.nextLine();
        this.address = new Address(userInfo);
        System.out.println("Please enter your profession");
        this.profession = userInfo.nextLine();
        this.trialsUsed = 0;
        this.appsPurchased = 0;
        this.invoice = 0;
    }

    public String getUserName() {
        return userName;
    }

    //Checks name doesn't exist before renaming the key and the userName attribute
    public void setUserName(String newUserName, UserManager userManager, String userName) {
        if(userManager.checkListFor(newUserName)){
            Customer tempUser = (Customer) userManager.getListItem(userName);
            tempUser.userName = newUserName;
            userManager.addToList(tempUser.getUserName(), tempUser);
            userManager.removeFromList(userName);
        } else {
            System.out.println("Sorry that name is already taken.");
        }
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getTrialsUsed() {
        return trialsUsed;
    }

    public void setTrialsUsed() {
        this.trialsUsed++;
    }

    public int getAppsPurchased() {
        return appsPurchased;
    }

    public void setAppsPurchased() {
        this.appsPurchased++;
    }

    public double getInvoice() {
        return invoice;
    }

    //Add an app price to the current invoice
    public void setInvoice(double price){
        this.invoice += price;
    }
    
    //Pays invoice (sets it to 0), could add a confirm option
    public void payInvoice(boolean pay){
        System.out.println("That will be: £" + getInvoice() +
                "\nThankyou for shopping with us!");
        if(pay == true)
            this.invoice = 0;
    }
    
    public void checkInvoice(){
        System.out.println("Your total comes to: " + getInvoice());
    }
    
    //Increments app counters and, if appropiate, adds the app price to the invoice attribute
    public void getApp(App appChosen, AppManager apps){
        if(appChosen.isFreeTrial() == true){
            this.setTrialsUsed();
            System.out.println("This is a free trial app,no charge, enjoy!");
        } else {
            this.setAppsPurchased();
            this.setInvoice(appChosen.getAppCost());
            apps.setTotalAppsPurchased();
            appChosen.setAppPopularity();
            System.out.println("This is a payed app, the cost has been added to your tab.\n"
            + "Please choose the \'Pay\' option when you are done choosing apps.");
        }
    }
    
    //Print info about the user
    public void printInfo(){
        System.out.println("Username: " + this.userName +
                "\nFull name: " + this.fullName +
                "\nAddress: " + this.address.printAddress() +
                "\nProfession: " + this.profession +
                "\nTrials used: " + this.trialsUsed +
                "\nApps purchased: " + this.appsPurchased);
    }
    
    //Edit info loop
    public void editInfo(Scanner userInput, UserManager userManager){
        String userChoice;
        do {
            System.out.println("Change user details:\n"
                    + "(1) edit username\n"
                    + "(2) edit full name\n"
                    + "(3) edit address\n"
                    + "(4) edit profession\n"
                    + "(0) exit user editor\n");
            userChoice = userInput.nextLine();
            switch(userChoice)
            {
                case "1":
                    System.out.println("Enter new username");
                    setUserName(userInput.nextLine(), userManager, getUserName());
                break;
                case "2":
                    System.out.println("Enter new name");
                    setFullName(userInput.nextLine());
                break;
                case "3":
                    System.out.println("Enter new address");
                    Address newAddress = new Address(userInput);
                    this.setAddress(newAddress);
                break;
                case "4":
                    System.out.println("Enter new profession");
                    setProfession(userInput.nextLine());
                break;
                case "0":
                    userChoice = "0";
                break;
                default:
                    System.out.println("Invalid input.");
            }
        } while(!userChoice.equals("0"));
    }
    
    //User options loop
    public void manageUser(Scanner userInfo, AppManager appManager, UserManager userManager){
        String stayLoggedIn;
        do{
            System.out.println("_________________________________________\n"
                    + "Do you want to:\n"
                    + "(1) edit user details,\n"
                    + "(2) list apps,\n"
                    + "(3) list apps with app info,\n"
                    + "(4) search apps,\n"
                    + "(5) get an app\n"
                    + "(6) check total cost,\n"
                    + "(7) pay for apps,\n"
                    + "(0) log out.\n"
                    + "_________________________________________");
            stayLoggedIn = userInfo.nextLine();
            //userInput.nextLine();
            switch(stayLoggedIn)
            {
                case "1":
                    System.out.println("Entering user editor.");
                    editInfo(userInfo, userManager);
                break;
                case "2":
                    appManager.printList();
                break;
                case "3":
                    appManager.printFullList();
                break;
                case "4":
                    appManager.searchListItem(userInfo);
                break;
                case "5":
                    System.out.println("Enter app name to choose it");
                    String chosenApp = userInfo.nextLine();
                    if(appManager.checkListFor(chosenApp)){
                        getApp(appManager.getListItem(chosenApp), appManager);
                    } else {
                        System.out.println("That app doesn't exist.");
                    }
                break;
                case "6":
                    checkInvoice(); //could have getInvoice() in a system.out
                break;
                case "7":
                    System.out.println("Confirm app purchase");
                    payInvoice(true);
                break;
                case "0":
                    stayLoggedIn = "0";
                break;
                default:
                    System.out.println("Invalid input");
            }
        } while(!stayLoggedIn.equals("0"));
    }
}