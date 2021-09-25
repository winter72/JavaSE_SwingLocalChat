package clientlogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Сергей Зима
 */
public class UsersCollections {
     private static Map<String,List<String>>usMes=new HashMap<>();
     private static Map<String,String>usCon=new HashMap<>();
     
    public static synchronized void addValuesInMap(String k,String v){
       if(usMes.containsKey(k)){
           usMes.get(k).add(v);
       }
       else{
           List<String>allVal=new ArrayList<>();
           allVal.add(v);
           usMes.put(k,allVal);
       }
   }
   public static synchronized void addUsContact(String k,String v){
       usCon.put(k,v);
   }
   
   public static Map<String,List<String>> getUsMes(){
       return usMes;
   }
    
   public static Map<String,String> getUsCon(){
       return usCon;
   }
}
