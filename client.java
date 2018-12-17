import java.io.*;
import java.net.*;

public class client {

    public static void main(String[] args) throws IOException {
        String hostName;
        int portNumber;

        if (args.length == 1){
            portNumber = Integer.parseInt(args[0]);
            hostName = "localhost";
        } else if (args.length >= 2){
            portNumber = Integer.parseInt(args[0]);
            hostName = args[1];
        } else{
            hostName = "localhost";
            portNumber = 5244;
        }

        System.out.println("Tentativo di connessione al server su " + hostName + " alla porta " + portNumber +".");
        try (
            Socket echoSocket = new Socket(hostName, portNumber);
        		
            PrintWriter out =
                new PrintWriter(echoSocket.getOutputStream(), true); 
        		
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
        		
            BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in));
        ) {
            System.out.println("Connessione al server effettuata." );
            String userInput, rispostaServer;
            while ((rispostaServer = in.readLine()) != null) {
                System.out.println("[" + hostName + "] " + rispostaServer);
                if(rispostaServer.contains("Chiusura..."))
                {
                    break;
                }
                do {
                    System.out.print("> ");
                    userInput = stdIn.readLine();
                } while((userInput.trim().isEmpty()));

                out.println(userInput);
            }

        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Non è possibile connettersi a " +
                hostName);
            System.exit(1);
        } 
    }
}