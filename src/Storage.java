/* App Store Coursework 
*  Storage class, deals with the hashmap of users and apps.
*  @author joshstringfellow */

package appstorev3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class Storage {
    //Serializes the hashmap data and stores it, add ../ to run from the cmd prompt/terminal
    public void serializeData(HashMap data, String filename){
        try {
            FileOutputStream fos = new FileOutputStream("serFiles/" + filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
            fos.close();
            System.out.println("Serialized HashMap data is saved in " + filename);
        }catch(IOException ioe){
             System.out.println("IOException, oh dear.");
        }
    }
    
    //Deserializes the data and restores it to container object, add ../ to run from the cmd prompt/terminal
    public HashMap<String,Object> deSerializeData(HashMap data, String filename){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("serFiles/" + filename));
            data = (HashMap<String, Object>) ois.readObject();
            System.out.println("Hashmap successfully deserialized");
            ois.close();
            System.out.println("HashMap data deserialized from " + filename);
        }catch(IOException ioe){
            System.out.println("IOException, oh dear.");
        }catch(ClassNotFoundException c){
           System.out.println("Class " + c + " not found");
        }
        return data;
    }
}