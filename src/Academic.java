/* App Store Coursework 
 * Inherits from Customer class
 * @author joshstringfellow */

package appstorev3;

import java.io.Serializable;
import java.util.Scanner;

public class Academic extends Customer implements Serializable {
    
    //Constructor overloading for testing purposes
    public Academic(String userName, String fullName, String numAndStreet, String town, String county,
            String postCode, String profession, int trialsUsed, int appsPurchased, double invoice) {    
        super(userName, fullName, numAndStreet, town, county, postCode, profession, trialsUsed, appsPurchased, invoice);
    }
    
    //Constructor calls Customer input constructor
    public Academic(Scanner userInfo, String userName) {
        super(userInfo, userName);
    }
    
    //Overrides Customer setInvoice with a specific discount, also rounds double
    @Override
    public void setInvoice(double price){
        price *= 0.9;
        price = Math.round(price*100);
        this.invoice += price/100;
    }
}