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
    
  }
  
  private void byteStream(File file) throws IOException{
    int fileLength = file.length();
    FileInputStream input1 = new FileInputStream(file);
    //BufferedInputStream
  }
  
}
