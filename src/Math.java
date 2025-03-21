import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class Math {
    public static final BigDecimal e = new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664274274663919320030599218174135966290435729003342952605956307381323286279434907632338298807531952510190115738341879307021540891499348841675092447614606680822648001684774118537423454424371075390777449920695517027618386062613313845830007520449338265602976067371132007093287091274437470472306969772093101416928368190255151086574637721112523897844250569536967707854499699679468644549059879316368892300987931277361782154249992295763514822082698951936680331825288693984964651058209392398294887933203625094431173012381970684161403970198376793206832823764648042953118023287825098194558153017567173613320698112509961818815930416903515988885193458072738667385894228792284998920868058257492796104841984443634632449684875602336248270419786232090021609902353043699418491463140934317381436405462531520961836908887070167683964243781405927145635490613031072085103837505101157477041718986106873969655212671546889570350354");
    
    public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.CEILING;
    public static final int DEC_NUM = 100;
    public static final MathContext mc = new MathContext(DEC_NUM, DEFAULT_ROUNDING_MODE);
    public static final BigDecimal acc = BigDecimal.ONE.divide(bigPow(BigDecimal.valueOf(10), BigInteger.valueOf(DEC_NUM).add(BigInteger.valueOf(2))));

    public static BigDecimal exp(BigDecimal x) {
        BigDecimal res = new BigDecimal(0);
        BigDecimal prevRes = new BigDecimal(1);
        BigInteger n = BigInteger.ZERO;
        while (res.abs().subtract(prevRes.abs()).abs().compareTo(acc) > 0) {
            prevRes = res;

            BigDecimal numerator = bigPow(x, n);
            int scale = (res.scale() > numerator.scale()) ? res.scale() : numerator.scale();
            BigDecimal temp = numerator.divide(new BigDecimal(bigFactorial(n)), scale, DEFAULT_ROUNDING_MODE); 
            res = res.add(temp);
            n = n.add(BigInteger.ONE);
        }

        return res;
    }

    public static BigDecimal ln(BigDecimal a) {
        BigDecimal x = BigDecimal.ONE.divide(a, DEC_NUM + 2, DEFAULT_ROUNDING_MODE).subtract(BigDecimal.ONE);
        return mercator(x).setScale(DEC_NUM + 2, DEFAULT_ROUNDING_MODE).negate();
    }

    public static BigDecimal mercator(BigDecimal x) {
        BigDecimal res = new BigDecimal(0);
        BigDecimal prevRes = new BigDecimal(1);
        BigInteger n = BigInteger.ONE;
        while (res.abs().subtract(prevRes.abs()).abs().compareTo(acc) > 0) {
            prevRes = res;

            BigDecimal numerator = bigPow(BigDecimal.ONE.negate(mc), n.add(BigInteger.ONE));
            numerator = numerator.multiply(bigPow(x, n));
            int scale = (res.scale() > numerator.scale()) ? res.scale() : numerator.scale();
            res = res.add(numerator.divide(new BigDecimal(n), scale, DEFAULT_ROUNDING_MODE));

            n = n.add(BigInteger.ONE);
        }

        return res;
    }

    public static BigDecimal bigPow(BigDecimal b, BigInteger n) {
        BigDecimal res = BigDecimal.ONE;
        while (n.compareTo(BigInteger.ZERO) > 0) {
            res = res.multiply(b);
            n = n.subtract(BigInteger.ONE);
        }
        return res;
    }

    public static BigInteger bigFactorial(BigInteger n) {
        BigInteger res = BigInteger.ONE;
        while (n.compareTo(BigInteger.ZERO) > 0) {
            res = res.multiply(n);
            n = n.subtract(BigInteger.ONE);
        }
        return res;
    }

    public static int abs(int x) {
        return (x < 0) ? -x : x;
    }
}
