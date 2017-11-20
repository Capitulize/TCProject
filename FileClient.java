import java.net.*;
import java.io.*;

public class FileClient{
public static void main(String args[]){
  
  //Initialize socket
        Socket socket = new Socket(InetAddress.getByName("localhost"), 50005);
        byte[] contents = new byte[10000];
  
  //Send choice to FileServer
  BufferedReader inFromUser = new BufferedReader(new InputStreamRead(System.in));
  DataOutputStrem outToServer = new DataOutputStream(socket.getOutputStream());
  String choice = inFromUser.readLine();
  outToServer.writeBytes(choice+'\n');
  
 //Initialize in  choice "if" statements as to prevent empty files from being created
        FileOutputStream fos1; 
        FileOutputStream fos2; 
        FileOutputStream fos3; 
        BufferedOutputStream bos1; 
        BufferedOutputStream bos2;
        BufferedOutputStream bos3;
  
        InputStream is = socket.getInputStream();
  }
}
