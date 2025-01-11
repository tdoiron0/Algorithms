import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class Math {
    public static final int decimalPlace = 10;
    public static final MathContext mc = new MathContext(decimalPlace + 1);
    public static final BigDecimal acc = BigDecimal.ONE.divide(bigPow(BigDecimal.valueOf(10), BigInteger.valueOf(decimalPlace)));

    public BigDecimal exp(BigDecimal a) {

        return null;
    }

    public static BigDecimal ln(BigDecimal a) {
        if (a.compareTo(BigDecimal.ZERO) >= 0) {
            return Mercator(a);
        }
        return null;
    }

    public static BigDecimal Mercator(BigDecimal x) {
        BigDecimal res = new BigDecimal(0, mc);
        BigDecimal prevRes = new BigDecimal(1, mc);

        BigInteger n = BigInteger.ONE;
        while (res.subtract(prevRes).abs().compareTo(acc) > 0) {
            prevRes = res;

            BigDecimal numerator = bigPow(BigDecimal.ONE.negate(mc), n.add(BigInteger.ONE));
            numerator = numerator.multiply(bigPow(x, n), mc);
            res = res.add(numerator.divide(new BigDecimal(n), mc));

            n = n.add(BigInteger.ONE);
        }

        return res;
    }

    public static BigDecimal bigPow(BigDecimal b, BigInteger e) {
        BigDecimal res = new BigDecimal("1", mc);
        while (e.compareTo(BigInteger.ZERO) > 0) {
            res = res.multiply(b, mc);
            e = e.subtract(BigInteger.ONE);
        }
        return res;
    }
}
