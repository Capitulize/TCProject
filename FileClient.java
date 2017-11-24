import java.net.*;
import java.io.*;

public class FileClient{
public static void main(String args[]){
  
  //Initialize socket
        Socket socket = new Socket(InetAddress.getByName("localhost"), 55555);//change to whatever the Server's name or IP address is
        byte[] fileBytes = new byte[10000];
  InputStream is = socket.getInputStream();
  System.out.println("Connected.");
  
  //Receive file names from server
  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  String files = inFromServer.readLine();
  System.out.println(files);
  
  //Send choice to FileServer
  BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
  DataOutputStrem outToServer = new DataOutputStream(socket.getOutputStream());
  String choice = inFromUser.readLine();
  outToServer.writeBytes(choice+'\n');
  
 //Initialize in  choice "if" statements as to prevent empty files from being created
        FileOutputStream fileOut;//creates new file
        if(choice.equals(""))
          System.exit(0);
        else if(choice.equals("1")){
          fileOut = new FileOutputStream("C:\\...nameOfNewFile1.txt");
          BufferedOutputStream buffOut = new BufferedOutputStream(fileOut);
          
          //buffOut.write(fileBytes);
          buffOut.flush();
        }
        else if(choice.equals("2")){
          fileOut = new FileOutputStream("C:\\...nameOfNewFile2.txt");
          BufferedOutputStream buffOut = new BufferedOutputStream(fileOut);
          
          //buffOut.write(fileBytes);
          buffOut.flush();
        }
       else if(choice.equals("3")){
          fileOut = new FileOutputStream("C:\\...nameOfNewFile3.txt");
          BufferedOutputStream buffOut = new BufferedOutputStream(fileOut);
          
          //buffOut.write(fileBytes);
          buffOut.flush();
        }
        else{
          inFromServer=newBufferedReader(new InputStreamReader(socket.getInputStream())));
          String invalid = inFromServer.readLine();
          System.out.println(invalid);
        }
  socket.close();
    
        
  }
}
