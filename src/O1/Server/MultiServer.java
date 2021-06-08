/**
 * David BM 302518097
 * 08.06.21
 *
 *
 *This is exchange multiThreading program.
 * ByDefault the server is the "local host" and the connection is in port 7777.
 * The connection is by TCP protocol.
 * The MultiServer receive some  exchange requests from the some clients.
 * for each client the MultiServer create thread(RequestHandleThread) that receive the exchange request from the client
 * and by using the ExchangeRate class he receive the client the exchangeRate.
 *
 *the classes that take part at this program:
 * 1) ExchangeRequest: her job is to create the request from the client entries and calculate the exchange rate by ExchangeRate
 * 2) ExchangeRate: this class have map of coins rate, and she implement the logic of the exchanging.
 * 3) RequestHandleThread: receive  the request and send the answer to the client.
 * 4) Client: handle the user requests by GUI
 */

package O1.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultiServer {
    public static Lock clientLock;
    public static Condition clientCond;
    public static void main(String[] args) throws IOException {
        clientLock=new ReentrantLock();
        clientCond=clientLock.newCondition();
        ServerSocket serverSocket = null;
        boolean listening=true;
        try {
            serverSocket = new ServerSocket(7777);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 7777");
            System.exit(1);
        }
        System.out.println("server ready");
        Socket socket = null;

        while (listening){
            try {
                clientLock.lock();
                socket = serverSocket.accept(); //receive socket from the client
                RequestHandleThread requestHandleThread=new RequestHandleThread(socket);
                requestHandleThread.start();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            finally {
                clientLock.unlock();
            }

        }


    }
    }

