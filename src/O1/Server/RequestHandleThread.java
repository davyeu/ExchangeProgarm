package O1.Server;

import O1.ExchangeRequest;

import java.io.*;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

public class RequestHandleThread extends Thread implements Serializable {
    private Socket socket = null;
    private ObjectInputStream input;
    private Formatter output;


    /**
     * receive the ExchangedRequest from the client and return the exchange value
     * @param socket the client socket
     */
    public RequestHandleThread(Socket socket) {
        this.socket = socket;
        try {
            output=new Formatter(socket.getOutputStream());
            input=new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("couldn't open I/O on connection");
        }

    }

    public void run() {
        ExchangeRequest request;
            try {
                //receive request object from the client
                 request=(ExchangeRequest)input.readObject();
                 //return the exchange value to the client
                double r=request.getExchange();
                output.format("%f\n",r);
                output.flush();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            finally {
                //output.close();
                try {
                    input.close();
                    //socket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
    }
}