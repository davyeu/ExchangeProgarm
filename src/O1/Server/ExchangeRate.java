/**
 * David BM 302518097
 *
 * Full documentation is in MultiServer class file.
 */

package O1.Server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**compute the rate of any coin according to NIS.
 for example the element "$,3.25" represent the exchange from one dollar to NIS.*/
public class ExchangeRate {

    static Map  mapRates;

    /**
     *
     * @param from kind of coin we want to exchange
     * @param to the kind of coin we want to exchange to him.
     * @return the exchange rate
     *
     * @example: rate("EUR',"USD")
     * let x= dollar, y= shekel, x=euro
     * acc. to mapRate:
     * 1) x=3.25y
     * 2) z=3.95y
     * therefore => z= 3.25y+0.7y => z= x+0.7y=> z=x+0.215x => z=1.215x
     * for here the return value will be 1.215 (dollars)
     */
    public static double rate(String from,String to) {
        mapRates=new HashMap();
        mapRates.put("USD",3.25); //USD dollar
        mapRates.put("EUR",3.95); //euro coin
        mapRates.put("GBP",4.6); //Sterling pound
        mapRates.put("JPY",0.03);//Japanese yen
        mapRates.put("RUB",0.045);//Russian ruble
        try{
            if(mapRates.containsKey(from) || from.equals("NIS")
                    && (mapRates.containsKey(to)|| to.equals("NIS"))){
                if(from.equals(to))
                    return 1;
                else if(to.equals("NIS")){
                        return (double)mapRates.get(from);
                }
                else if (from.equals("NIS")){
                   double foreignKey=(double)mapRates.get(to);
                   double res=1/foreignKey;
                   return res;
                }
                else { //both coins are not "NIS", but they exist at the mapRates
                   double x=(double)mapRates.get(from);
                   double y= (double)mapRates.get(to);
                   double biggerCoin =x>=y? x :y;
                   double smallerCoin=x<y? x:y;
                   double diff=biggerCoin-smallerCoin; //at the example attached the diff is "0.7"
                    double rel=diff/smallerCoin; //return the relation between them. At the
                    //example attached the relation is 0.7/3.25=0.215
                    return 1+rel;//At the example attached is "1+0.215"
                }

            }
        else{
                System.out.println("cannot convert the coins");
                System.out.println("make sure you enter correctly the names of the coins");
                return -1;
            }
        }
        catch (Exception e) {e.printStackTrace();  return -1;}
    }
}
