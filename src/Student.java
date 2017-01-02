/* App Store Coursework 
 * Inherits from Customer class
   @author joshstringfellow */

package appstorev3;

import java.io.Serializable;
import java.util.Scanner;

public class Student extends Customer implements Serializable {
    
    //Constructor overloading for testing purposes
    public Student(String userName, String fullName, String numAndStreet, String town, String county,
            String postCode, String profession, int trialsUsed, int appsPurchased, double invoice) {    
        super(userName, fullName, numAndStreet, town, county, postCode, profession, trialsUsed, appsPurchased, invoice);
    }

    //Constructor just calls Customer instructor as there's no new attributes
    public Student(Scanner userInfo, String userName) {
        super(userInfo, userName);
    }
    
    //Overrides Customer setInvoice with a specific discount, also rounds double
    @Override
    public void setInvoice(double price){
        price *= 0.75;
        price = Math.round(price*100);
        this.invoice += price/100;
    }
}