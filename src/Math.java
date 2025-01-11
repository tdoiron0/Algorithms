import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class Math {
    public static final MathContext mc = new MathContext(100);

    public BigDecimal exp(BigDecimal a) {

        return null;
    }

    public BigDecimal ln(BigDecimal a) {
        if (a.compareTo(BigDecimal.ONE) >= 0) {

        }
        return null;
    }

    public BigDecimal Mercator(BigDecimal a) {
        BigDecimal x = a.subtract(BigDecimal.ONE);

        BigDecimal res = new BigDecimal(1, mc);
        BigDecimal prevRes = new BigDecimal(0, mc);

        BigInteger n = BigInteger.ONE;
        while (prevRes.compareTo(res) != 0) {
            prevRes = res;

            BigDecimal numerator = bigPow(BigDecimal.ONE.negate(), n.add(BigInteger.ONE));
            res = numerator.divide(new BigDecimal(n)).multiply(bigPow(x, n));

            n = n.add(BigInteger.ONE);
        }

        return res;
    }

    private BigDecimal bigPow(BigDecimal b, BigInteger e) {
        BigDecimal res = BigDecimal.ONE;
        while (e.compareTo(BigInteger.ZERO) > 0) {
            res = res.multiply(b);
        }
        return res;
    }
}
