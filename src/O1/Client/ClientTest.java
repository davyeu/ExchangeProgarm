/**
 * David BM 302518097
 *
 * Full documentation is in MultiServer class file.
 */

package O1.Client;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClientTest  {
    /**
     *
     * create some clients.
     * the executing of the client will be from their ctor.
     */
    public static void main(String [] args){
        Client c1=new Client();
        Client c2=new Client();
        Client c3=new Client();

    }
}
