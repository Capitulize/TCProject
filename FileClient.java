import java.net.*;
import java.io.*;


public class FileClient{
public static void main(String args[]) throws IOException{
  
	//Initialize requirements
	byte[] contents = new byte[10000];
	int bytesRead = 0;
	
  //Initialize socket
  Socket socket = new Socket(InetAddress.getByName("localhost"), 45000);//change to whatever the Server's name or IP address is
        
  System.out.println("Connected.");
  
  //Receive file names from server
  
  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  String files = inFromServer.readLine();
  System.out.println(files);
  
  //Send choice to FileServer
  BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
  DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
  String choice = inFromUser.readLine();
  outToServer.writeBytes(choice+'\n');
  InputStream is = socket.getInputStream();
  
 //Initialize in  choice "if" statements as to prevent empty files from being created
        FileOutputStream fileOut;//creates new file
        while((bytesRead=is.read(contents))!=-1) {
        if(choice.equals(""))
          System.exit(0);
        else if(choice.equals("1")){
          fileOut = new FileOutputStream("nameOfNewFile1.txt");
          BufferedOutputStream buffOut = new BufferedOutputStream(fileOut);
          buffOut.write(contents, 0, bytesRead);
          //buffOut.write(fileBytes);
          buffOut.close();
          buffOut.flush();
        }
        else if(choice.equals("2")){
          fileOut = new FileOutputStream("nameOfNewFile2.txt");
          BufferedOutputStream buffOut = new BufferedOutputStream(fileOut);
          buffOut.write(contents, 0, bytesRead);
          //buffOut.write(fileBytes);
          buffOut.close();
          buffOut.flush();
        }
       else if(choice.equals("3")){
          fileOut = new FileOutputStream("nameOfNewFile3.txt");
          BufferedOutputStream buffOut = new BufferedOutputStream(fileOut);
          buffOut.write(contents, 0, bytesRead);
          //buffOut.write(fileBytes);
          buffOut.close();
          buffOut.flush();
        }
        else{
          inFromServer= new BufferedReader(new InputStreamReader(socket.getInputStream()));
          String invalid = inFromServer.readLine();
          System.out.println(invalid);
        }
        }
        socket.close();
    
        
  }
}
