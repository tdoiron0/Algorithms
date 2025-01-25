import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import Math.Matrix;
import Util.Util;

public class App {
    public static void main(String[] args) throws Exception {
        int[][] A = {{1,1,1},{1,200,1},{27,13,81},{81,81,64}};
        System.out.println(Util.toStringSpaced(A));
    }
}
