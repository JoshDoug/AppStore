/* App Store Coursework 
 * Address class for the Address attribute for customers and inherited classes
 * @author joshstringfellow */

package appstorev3;

import java.io.Serializable;
import java.util.Scanner;

public class Address implements Serializable {
    private String numAndStreet;
    private String town;
    private String county;
    private String postCode;
    
    //Constructor overloading for testing purposes

    public Address(String numAndStreet, String town, String county, String postCode) {
        this.numAndStreet = numAndStreet;
        this.town = town;
        this.county = county;
        this.postCode = postCode;
    }
    
    //Address constructor for customer and inherited classes
    Address(Scanner userInfo){
        System.out.println("Please enter your house name or number, and street.");
        this.numAndStreet = userInfo.nextLine();
        System.out.println("Please enter your town/city.");
        this.town = userInfo.nextLine();
        System.out.println("Please enter your county.");
        this.county = userInfo.nextLine();
        System.out.println("Please enter your postcode.");
        this.postCode = userInfo.nextLine();
    }

    public String getNumAndStreet() {
        return numAndStreet;
    }

    public void setNumAndStreet(String numAndStreet) {
        this.numAndStreet = numAndStreet;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
    
    //Returns string instead of using to println so it can be called within customer printInfo println
    public String printAddress(){
        return "House name/number & street: " + this.numAndStreet + "\nTown or City: " + this.town + "\nCounty: " + this.county + "\nPostcode: " + this.postCode;
    }
}