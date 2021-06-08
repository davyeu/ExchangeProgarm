/**
 * David BM 302518097
 *
 * Full documentation is in MultiServer class file.
 */

package O1;

import O1.Server.ExchangeRate;
import O1.Server.RequestHandleThread;

import java.io.Serializable;

public class ExchangeRequest implements Serializable {
    private String from;// kind of coin we want to exchange("x)
    private String to;//  to the kind of coin we want to exchange to him.("y")
    private double num; //the number of coins we want to be exchange
    private double exchange; // the exchange rate (in "y" coin).

    public ExchangeRequest(String f, String t, double n){
        from=f;
        to=t;
        num=n;
    }
    //getters
    public double getExchange(){
        exchange=ExchangeRate.rate(from,to);
        return  exchange;
    }

    public String getFrom() {
        return from;
    }
    public double getNum(){
        return num;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "ExchangeRequest{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", num=" + num +
                ", exchange=" + exchange +
                '}';
    }
}
