package O1.Server;


import O1.Server.ExchangeRate;
import org.junit.jupiter.api.Test;


public class ExchangeRateTest {
    @Test
    void checkRate() {
        ExchangeRate er=new ExchangeRate();
        System.out.println(er.rate("EUR","USD"));
    }
}