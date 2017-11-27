
/***************************************************************************************
*    Title: FileClient.java
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
    * This java class file specific purpose is to create a link, and send requests to the server to obtain files in their entirety.
    * Variables:
    * socket     The initialization of the socket to be able to send request and download.
    * socket2    The initialization of the second socket to connect to FileServer and see available files.
    */
public class FileClient {
    //Initialization of sockets
    static Socket socket;
    static Socket socket2;
    /*
     * Main method runs here, most of program contained in while loop to be able to request mutliple files, file names are printed.
     */
    public static void main(String[] args) throws Exception {
            //Socket 2 is defined, BufferedReader is initialized, printFiles is the method that prints available files to download.
            socket2 = new Socket(InetAddress.getLocalHost(), 50016);
            BufferedReader inFromServer;
            printFiles();
    //While loop that performs the main function of the program, sending and receiving files.
        while (true) {
            //Socket is defined.
            socket = new Socket(InetAddress.getLocalHost(), 50015);
            InputStream is = socket.getInputStream();
            
            //Byte array is initialized and defined here to be able to print to file.
            byte[] contents = new byte[10000];
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            
            //User is prompted for choice here.
            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
            System.out.print("Enter the number next to one of the file names to receive it.(Enter nothing to terminate the program): ");
            String sentence = inFromUser.readLine();
            outToServer.writeBytes(sentence + '\n');
            
            //Output is initialized here for later use.
            FileOutputStream fos;
            
            //bytesRead is defined here, used to print till file end.
            int bytesRead = 0;
            //if statement structures below to print files in their entirety, depending on choice of user.
            
            //if the user wants to exit
            if (sentence.equals("")) {
                break;
               }
            //if the user enters 1, prints a file called XD2.txt, tells user where it is saved.
               else if (sentence.equals("1")) {
                File file = new File("XD2.txt");
                fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                while ((bytesRead = is.read(contents)) > 0) {
                    bos.write(contents, 0, bytesRead);
                }
                System.out.println("File saved to "+file.getAbsolutePath()+"\n");

                bos.flush();
                   
            }
            //if the user enters 2, prints a file called Pride and Prejudice 2.txt, tells user where it is saved.
            else if (sentence.equals("2")) {
                File file = new File("Pride and Prejudice 2.txt");
                fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                while ((bytesRead = is.read(contents)) > 0) {
                    bos.write(contents, 0, bytesRead);
                }
                System.out.println("File saved to "+file.getAbsolutePath()+"\n");

                bos.flush();
            }
            //if the user enters 3, prints a file called Lmao2.txt, tells user where it is saved.
            else if (sentence.equals("3")) {
                File file = new File("Lmao2.txt");
                fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                while ((bytesRead = is.read(contents)) > 0) {
                    bos.write(contents, 0, bytesRead);
                }
                System.out.println("File saved to "+file.getAbsolutePath()+"\n");

                bos.flush();
            } 
            //if the user enters nonsense, tells the user it entered nonsense, using a method from FileServer.
            else {
                inFromServer
                        = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String invalid = inFromServer.readLine();
                System.out.println(invalid);
            }
        }

        socket.close();
    }//end of main
    
    /*
    *   printFile is the method used to print the available files that are available from the FileServer.
    *   Variables:
    *   inFromServer     BufferedReader that reads the file names from the FileServer.
    *   files            String that collects all the files in one string. Prints afterwards.
    */

    private static void printFiles() throws IOException {
       BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket2.getInputStream()));

        String files = inFromServer.readLine();
        System.out.println(files+"\n");
    }
}//end of FileClient
