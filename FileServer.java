/***************************************************************************************
*    Title: FileServer.java
*    Author: William Burcham, Steven Hogenson
*    Date: 11/27/2017
*    Code version: V1
*    Availability: https://github.com/Capitulize/TCProject
*
***************************************************************************************/
import java.io.*;
import java.net.*;
   /*
    * This program was developed by William and Steven to be used as a file transfer system in TCP.
    * This java class file specific purpose is to accept a link, and send files to the client after is receives requests from it.
    * @param socket     The initialization of the socket to send files in their entirety.
    * @param socket2    The initialization of the second socket to connect to FileClient and send available file names.
    * @param os         Output stream to send file
    * @param fLength    Length of the file being requested.
    * @param file1, file2, file3    The files that are available to be sent.
    */
public class FileServer {

    static OutputStream os;
    public static int fLength;
    static Socket socket;
    static Socket socket2;
    static File file1 = new File("XD.txt");
    static File file2 = new File("Pride and Prejudice.txt");
    static File file3 = new File("Lmao.txt");
    /*
     * Main method runs here, most of program contained in while loop to be able to send mutliple files. Receives requests from FileClient.
     */
    public static void main(String[] args) throws Exception {
        //The starting of the server, once a socket is created, file names are sent to the client. socket2 is defined here.
        System.out.println("Running...");
        ServerSocket ssock2 = new ServerSocket(50016);
        socket2 = ssock2.accept();
        sendFileNames();
        //While loop that contains the program.
        while (true) {
            //socket is defined here, used to send files.
            ServerSocket ssock = new ServerSocket(50015);
            socket = ssock.accept();           
            
            //os is defined here, used to send the file over in a packet.
            os = socket.getOutputStream();
            
            //The request is received from the client here.
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String choice = inFromClient.readLine();
            //if nothing is received, FileServer closes.
            if (choice.equals("")) {
                System.exit(0);
            } 
            //if 1 is received, it sends file1.
            else if (choice.equals("1")) {
                byteStream(file1);
                System.out.println("File sent successfully!");
            } 
            //if 2 is received, it sends file2.
            else if (choice.equals("2")) {
                byteStream(file2);
                System.out.println("File sent successfully!");
            } 
            //if 3 is received, it sends file3.
            else if (choice.equals("3")) {
                byteStream(file3);
                System.out.println("File sent successfully!");
            } 
            //if nonsense was sent, it tells the user on client side that it sent nonsense request.
            else {
                invalidIn();
                
            }

            os.flush();
           
            socket.close();
            ssock.close();
            
        } 
    }//end of main
    /*
    *    byteStream is called whenever a file needs to be sent. 
    *    
    *    Variables:
    *    @param file    The file is passed into the method to be read.
    *    fileLength     Length of the file being sent.
    *    fInput         The input of the file.
    *    fileByte       The length of the file put into an array.
    */
    private static void byteStream(File file) throws IOException {
        
        //Everything that is needed for this method is initialized here.
        long fileLength = file.length();
        FileInputStream fInput = new FileInputStream(file);
        BufferedInputStream buffIn = new BufferedInputStream(fInput);
        
        //File is made into a packet and sent here.
        fLength = (int) fileLength;//had to typecast to put into byte array.
        byte[] fileByte = new byte[fLength];
        buffIn.read(fileByte, 0, fLength);
        os.write(fileByte);
    }
    /*
    *   This method is called when the client wants to know the file names that are available.
    *   Variables:
    *   outToClient The object that sends to client.
    *   sentence    The string that tells the user what files are sent.
    */
    private static void sendFileNames() throws IOException {
        DataOutputStream outToClient = new DataOutputStream(socket2.getOutputStream());
        String sentence = "Available Files:  (1):" + file1.getName() + "   (2):" + file2.getName() + "   (3):" + file3.getName();
        outToClient.writeBytes(sentence + "\n");

    }
    /*
    *   This method is called whenever an invalid input is sent, and it will tell the client that.
    *   Variables:
    *   outToClient     The object that sends to client.
    *   sentence        The sentence to be printed
    */
    private static void invalidIn() throws IOException {
        DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
        String sentence = "Invalid Input";
        outToClient.writeBytes(sentence + "\n");

    }

}//end of FileServer
