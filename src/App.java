import java.math.BigDecimal;
import java.math.BigInteger;

import Math.Matrix;

public class App {
    public static void main(String[] args) throws Exception {
        BigDecimal x = new BigDecimal("1");
        BigDecimal y = Math.exp(x);
        System.out.println(y);
    }
}
