
import java.net.*;
import java.io.*;
 
public class server {
    public static void main(String[] args) throws IOException {
        int portNumber;
        if(args.length >= 1)
        {
            portNumber = Integer.parseInt(args[0]);
        } else {
            portNumber = 5244;
        }

        System.out.println("In attesa della connessione del client sulla porta " + portNumber + ".");
        try (
            ServerSocket serverSocket =
                new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);   
        		
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        		
        ) {
            System.out.println("Connessione al client effettuata. \n");
            String inputLine, outputLine;
            protocol p = new protocol();
            out.println("Inserisci operazione da svolgere. (Sintassi: operando1 +|-|*|/ operando2. \"Esci\" per terminare.)");
            while ((inputLine = in.readLine()) != null) {
            	outputLine = p.elabora(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Chiusura..."))
                {
                    System.out.println("Server terminato.");
                    break;
                }
            }
            
        } catch (IOException e) {
            System.err.println("Errore durante tentativo di ascolto della porta "
                + portNumber + " o tentativo di connessione");
            System.err.println(e.getMessage());
        }
    }
}

