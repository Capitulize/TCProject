/**
* Sends files to client
*/
import java.io.*;
import java.net.*;
public class FileServer{
  static OutputStream outPut;
  public static void main (String args[]){
    ServerSocket serSock = new ServerSocket(45000);
    Socket sock = serSock.accept();//returns socket used for specific slient
    
    InetAddress adr = InetAddress.getByName("localhost");//change name to whatever is needed
    File file1 = new File("aye.txt");//change to whatever
    
    sendFileNames();//sends a list of available files to the client upon connection
    String choice;
    outPut = sock.getOutputStream();
    BufferedReader inFromClient = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    choice = inFromClient.readLine();//lets client choose what file to receive
    
    if(choice.equals(""))
      System.exit(0);
    if(choice.equals("1"))//Add as many choices as there are files to send
       byteStream(file1);
    
    sock.close();
    serSock.close();
                                                     
    
    
  }
  
  private void byteStream(File file) throws IOException{
    long fileLength = file.length();
    FileInputStream fInput = new FileInputStream(file);
    BufferedInputStream buffIn = new BufferedInputStream(fInput);

    int fLength = (int)fileLength;//had to typecast to put into byte array
    byte[] fileByte= new byte[fLength];
    buffIn.read(fileByte,0,fLength);
    outPut.write(fileByte);
    
  }
  private static void sendFileNames() throws IOException{
    DataOutputStream outToCLient - newDataOutputStream(socket.getOutputStream());
    String fileList = "Available Files:  (1)"+file1.getName;//add for however many files there are
    outToClient.writeBytes(fileList+"\n");
  }
  
}
