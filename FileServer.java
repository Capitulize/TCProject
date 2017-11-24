/**
* Sends files to client
*/
import java.io.*;
import java.net.*;
public class FileServer{
  static OutputStream outPut;
  public static void main (String args[]) throws IOException{
    ServerSocket serSock = new ServerSocket(45000);
    Socket sock = serSock.accept();//returns socket used for specific client
    
    File file1 = new File("aye.txt");//change to whatever
    File file2 = new File("cray.txt");
    File file3 = new File("day.txt");
    //Add additional files here
    sendFileNames(sock, file1, file2, file3);//sends a list of available files to the client upon connection
    String choice;
    outPut = sock.getOutputStream();
    BufferedReader inFromClient = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    choice = inFromClient.readLine();//lets client choose what file to receive
    
    if(choice.equals(""))
      System.exit(0);
   else if(choice.equals("1"))
       byteStream(file1);
    else if (choice.equals("2"))
      byteStream(file2);
    else if (choice.equals("3"))
      byteStream(file3);
    /*
    * Add however many other files to send here
    */
    else
      invalidIn(sock);//Sends invalid input message to client upon receiving a request for a nonexistent file
    
    sock.close();
    serSock.close();
                                                     
    
    
  }
  
  private static void byteStream(File file) throws IOException{
	  long fileLength = file.length();
      byte[] contents;
      long current = 0;
      FileInputStream input1 = new FileInputStream(file);
      BufferedInputStream bis = new BufferedInputStream(input1);
      
      while(current!=fileLength){
          int size = 1000000000;
          if(fileLength - current >= size){
              current += size;
          
          }
          else{
              size = (int)(fileLength - current);
              
              current = fileLength;
              
          }
          contents = new byte[size];
          bis.read(contents, 0, size);
          outPut.write(contents);
          System.out.println("Sending file ... "+(current*100)/fileLength+"% complete!");
          bis.close();
      }
    
  }
  private static void sendFileNames(Socket sock, File file1, File file2, File file3) throws IOException{
    DataOutputStream outToClient = new DataOutputStream(sock.getOutputStream());
    String fileList = "Available Files:  (3) " + file1.getName() + " " + file2.getName() + " " + file3.getName();//add for however many files there are
    outToClient.writeBytes(fileList+"\n");
  }
  private static void invalidIn(Socket sock) throws IOException{
    DataOutputStream outToClient = new DataOutputStream(sock.getOutputStream());
    String invalid = "Invalid input. Please select another file to receive.";
    outToClient.writeBytes(invalid+"\n"); 
  }
  
}
