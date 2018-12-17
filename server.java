
import java.net.*;
import java.io.*;
 
public class server {
    public static void main(String[] args) throws IOException {
         
        int portNumber = 5001;
         
        try (
            ServerSocket serverSocket =
                new ServerSocket(portNumber);
        		
            Socket clientSocket = serverSocket.accept();   
        		
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);   
        		
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        		
        ) {
            String inputLine, outputLine;
            protocol p = new protocol();
            out.println("Inserisci operazione");
            while ((inputLine = in.readLine()) != null) {
            	outputLine = p.elabora(inputLine);
                out.println(outputLine);
            }
            
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}

