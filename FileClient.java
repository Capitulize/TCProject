import java.net.*;
import java.io.*;

public class FileClient{
public static void main(String args[]){
  
  //Initialize socket
        Socket socket = new Socket(InetAddress.getByName("localhost"), 55555);
        byte[] fileBytes = new byte[10000];
  InputStream is = socket.getInputStream();
  System.out.println("Connected.");
  
  //Receive file names from server
  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  String files = inFromServer.readLine();
  System.out.println(files);
  
  //Send choice to FileServer
  BufferedReader inFromUser = new BufferedReader(new InputStreamRead(System.in));
  DataOutputStrem outToServer = new DataOutputStream(socket.getOutputStream());
  String choice = inFromUser.readLine();
  outToServer.writeBytes(choice+'\n');
  
 //Initialize in  choice "if" statements as to prevent empty files from being created
        FileOutputStream fileOut;//creates new file
        if(choice.equals(""))
          System.exit(0);
        if(choice.equals("1")){
          fileOut = new FileOutputStream("C:\\...nameOfNewFile1.txt");
          BufferedOutputStream buffOut = new BufferedOutputStream(fileOut);
          
          //buffOut.write(fileBytes);
          buffOut.flush();
        }
        if(choice.equals("2")){
          fileOut = new FileOutputStream("C:\\...nameOfNewFile2.txt");
          BufferedOutputStream buffOut = new BufferedOutputStream(fileOut);
          
          //buffOut.write(fileBytes);
          buffOut.flush();
        }
        if(choice.equals("3")){
          fileOut = new FileOutputStream("C:\\...nameOfNewFile3.txt");
          BufferedOutputStream buffOut = new BufferedOutputStream(fileOut);
          
          //buffOut.write(fileBytes);
          buffOut.flush();
        }
      System.out.println("File saved successfully.");
  
        
  }
}
