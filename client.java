import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args) throws IOException {

        String hostName = "localhost";
        int portNumber = 5001;

        try (
            Socket echoSocket = new Socket(hostName, portNumber);
        		
            PrintWriter out =
                new PrintWriter(echoSocket.getOutputStream(), true); 
        		
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
        		
            BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in))
                
        ) {
            String userInput, rispostaServer;
            while ((rispostaServer = in.readLine()) != null) {
                System.out.println(rispostaServer);
                
                userInput = stdIn.readLine();
                if(userInput != null)
                {
                	out.println(userInput);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } 
    }
}