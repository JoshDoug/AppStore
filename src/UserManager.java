/* App Store Coursework 
 * UserManager for managing and accessing users such as customers, students and academics
 * inherits from Manager
   @author joshstringfellow */

package appstorev3;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

public class UserManager extends Manager implements Serializable {
    
    public UserManager(){
        super();
    }
    
    /*Checks user doesn't already exist before calling specifc constructor
    if an incorrect input is made user is returned to home/main  */
    public String addUser(Scanner userInfo){
        String userName;
        do {
        System.out.println("Please enter a unique username.");
        userName = userInfo.nextLine();
        } while(this.checkListFor(userName) || userName.equals(""));
        System.out.println("Are you a student (1), academic (2), or neither (0)"
                + "\nPlease enter the corresponding number.");
        String userType = userInfo.nextLine();
        switch(userType){
            case "0":
                Customer newCustomer = new Customer(userInfo, userName);
                this.addToList(newCustomer.getUserName(), newCustomer);
            break;
            case "1":
                Student newStudent = new Student(userInfo, userName);
                this.addToList(newStudent.getUserName(), newStudent);
            break;
            case "2":
                Academic newAcademic = new Academic(userInfo, userName);
                this.addToList(newAcademic.getUserName(), newAcademic);
            break;
            default:
                System.out.println("Invalid input, please try again.");
        }
        return userName;
    }
    
    public void loginUser(Scanner userInfo, AppManager appManager){
        System.out.println("Enter your username");
        String userName = userInfo.nextLine();
        if(this.checkListFor(userName)){
            System.out.println("Logging in...");
            Customer tempUser = (Customer) this.getListItem(userName);
            tempUser.manageUser(userInfo, appManager, this);
        } else {
            System.out.println("Username not recognised, please try again.");
        }
    }
    
    public void loginUser(Scanner userInfo, AppManager appManager, String userName){
        if(this.checkListFor(userName)){
            System.out.println("Logging in...");
            Customer tempUser = (Customer) this.getListItem(userName);
            tempUser.manageUser(userInfo, appManager, this);
        }
    }
    
    public void printFullList(){
        //Again, ignore Netbeans' recommendation
        for (HashMap.Entry<String, Object> entry : list.entrySet()) {
            System.out.println("Name: " + entry.getKey());
            Customer temp = (Customer) entry.getValue();
            temp.printInfo();
            System.out.println("_________________________________________");
        }
    }
    
    public void searchListItem(String searchTerm){
        if(checkListFor(searchTerm)){
            Customer tempObject = (Customer) getListItem(searchTerm);
            tempObject.printInfo();
        } else {
            System.out.println("That user doesn't exist.");
        }
    }
    
    public int numberOfCustomers(){
        return list.size();
    }
    
    //Similar to loop in main, displays user choices and takes user input
    public void manageUsers(Scanner userInput){
        String userChoice;
        do {
            System.out.println("\n_________________________________________\n"
                    + "User Management, do you want to:\n"
                    + "(1) list users,\n"
                    + "(2) list users with details,\n"
                    + "(3) search users,\n"
                    + "(4) add a user,\n"
                    + "(5) edit a user details,\n"
                    + "(6) remove a user,\n"
                    + "(7) check the total number of users,\n"
                    + "(0) or exit the user manager.\n"
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
                    System.out.println("Enter name:");
                    searchListItem(userInput.nextLine());
                break;
                case "4":
                    addUser(userInput);
                break;
                case "5":
                    System.out.println("Enter username of user you want to edit.");
                    String userName = userInput.nextLine();
                    if (this.checkListFor(userName)){
                        Customer tempUser = (Customer) getListItem(userName);
                        tempUser.editInfo(userInput, this);
                    } else {
                        System.out.println("That user doesn't exist.");
                    }
                break;
                case "6":
                    System.out.println("Please enter the user you want to remove.");
                    String removeUser = userInput.nextLine();
                    if(this.checkListFor(removeUser)){
                        removeFromList(removeUser);
                        System.out.println("User removed.");
                    } else {
                        System.out.println("This user doesn't exist.");
                    }
                break;
                case "7":
                    System.out.println("There are " + numberOfCustomers() + "user(s).");
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