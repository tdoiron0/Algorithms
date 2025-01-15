import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import Math.Matrix;

public class App {
    public static void main(String[] args) throws Exception {
        BigDecimal x = new BigDecimal("3");
        BigDecimal y = Math.exp(Math.ln(x));
        System.out.println(y);
        System.out.println(Math.acc);

        //BigDecimal x = new BigDecimal("0.25");
        //BigDecimal y = new BigDecimal("0.25");
        //System.out.println(x.multiply(y));
    }
}
