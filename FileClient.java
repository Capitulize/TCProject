import java.net.*;
import java.io.*;

public class FileClient{
public static void main(String args[]){
  
  //Initialize socket
        Socket socket = new Socket(InetAddress.getByName("localhost"), 50005);
        byte[] contents = new byte[10000];
  
 //Initialize the FileOutputStream to the output file's full path.
        FileOutputStream fos1 = new FileOutputStream("Howdy2.txt");
        FileOutputStream fos2 = new FileOutputStream("Crowdy2.txt");
        FileOutputStream fos3 = new FileOutputStream("Lmao2.txt");
        BufferedOutputStream bos1 = new BufferedOutputStream(fos1);
        BufferedOutputStream bos2 = new BufferedOutputStream(fos2);
        BufferedOutputStream bos3 = new BufferedOutputStream(fos3);
        InputStream is = socket.getInputStream();
  }
}
