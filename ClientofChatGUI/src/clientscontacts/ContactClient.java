package clientscontacts;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Сергей Зима
 */
public class ContactClient {
    
   private static void createNewContact() throws IOException{
      Path pathToFile=Paths.get("src","clientscontacts","new_contacts.txt");
       Path pathToFile1=Paths.get("src","clientscontacts","all_contacts.txt");
     if(Files.notExists(pathToFile)) {
         Files.createFile(pathToFile);
      }
    
      if(Files.notExists(pathToFile1))
         Files.createFile(pathToFile1);
   } 
   
   public static void writeNewContact(String w,String p){
      
      Path path = Paths.get("src","clientscontacts",p);
      String w1=w+ System.getProperty("line.separator");
         try {
              createNewContact();
            Files.write(path, w1.getBytes(),APPEND);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    /* OpenOption[] options1={CREATE,WRITE,READ};
     Set<OpenOption> oo=new HashSet<>(Arrays.asList(options1));
       AsynchronousFileChannel fileChannel;
       try {
          fileChannel = AsynchronousFileChannel.open(path,options1);
              long position = 0;
          ByteBuffer bb=ByteBuffer.allocate(1024);
          bb.put(w1.getBytes(StandardCharsets.UTF_8));
        bb.flip();
        fileChannel.write(bb, position,bb, new CompletionHandler<Integer, ByteBuffer>(){
              @Override
              public void completed(Integer result, ByteBuffer attachment) {
                   System.out.println ("записано байтов:" +result);
              }

              @Override
              public void failed(Throwable exc, ByteBuffer attachment) {
                 
              }
          });
   /*  Future<Integer> operation = fileChannel.write(bb, position);
       bb.clear();

     while(!operation.isDone());*/
     /*   } catch (IOException ex) {
           
            Logger.getLogger(ContactClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch(UnsupportedOperationException ex){
             System.out.println(ex.getMessage());
                   Logger.getLogger(ContactClient.class.getName()).log(Level.SEVERE, null, ex);
        }*/

      System.out.println("Write done");
        
   }
   
   public static ArrayList<String> readContacts(String myLink){
       String z="";
        Path path = Paths.get("src","clientscontacts",myLink);
       try {
             createNewContact();
         AsynchronousFileChannel   fileChannel = AsynchronousFileChannel.open(path,READ);
               ByteBuffer buffer = ByteBuffer.allocate(1024);
         long position = 0;

        Future<Integer> operation = fileChannel.read(buffer, position);

        while(!operation.isDone());

       buffer.flip();
       byte[] data = new byte[buffer.limit()];
     buffer.get(data);
     z=new String(data);
  //System.out.println(new String(data));
        buffer.clear();
       } catch (IOException ex) {
           Logger.getLogger(ContactClient.class.getName()).log(Level.SEVERE, null, ex);
       }
     if(z.length()>0)
        return new ArrayList<>(Arrays.asList(z.split(";")));
     else
       return null;
   }
   
}
