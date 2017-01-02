/* App Store Coursework
 * Abstract container class for AppManager and UserManager classes
 * Instantiates hashmap with access methods
*/
/* @author joshstringfellow */

package appstorev3;

import java.io.Serializable;
import java.util.HashMap;

/*Implements Serializable but only the HashMap is stored, not the Manager (or inherited) objects or the entire app state.
 * Class is abstract as it is purely for inheriting by AppManager and UserManager */
public abstract class Manager implements Serializable {
    HashMap<String, Object> list;
    
    //Manager constructor, just creates empty hashmap of type String and Object
    Manager(){
        this.list = new HashMap<>();
    }
    
    //Used to store data with serialization
    public HashMap getList(){
        return list;
    }
    
    //Used to restore data when deserializing
    public void setList(HashMap<String, Object> newList){
        this.list = newList;
    }
    
    public void addToList(String name, Object listItem){
        this.list.put(name, listItem);
    }
    
    public void removeFromList(String name){
        this.list.remove(name);
    }
    
    public Object getListItem(String name){
        return this.list.get(name);
    }
        
    public boolean checkListFor(String searchKey){
        return this.list.containsKey(searchKey);
    }
    
    //Prints all the keys stored in the hashmap
    public void printList(){
        System.out.println("List:");
        /* Ignore Netbeans' recommendation to use a lambda/functional operation
        the code is more complex and not compatible with Java 7 */
        for(String key : list.keySet()){
            System.out.println("Name: " + key);
        }
    }
}