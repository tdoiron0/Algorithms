import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import Math.Matrix;
import Util.Util;

public class App {
    public static void main(String[] args) throws Exception {
        int[][] A = Util.genRandMatrix(5, 3, -50, 100);
        System.out.println(Util.toString(A));
    }
}
