import Math.Matrix;

public class App {
    public static void main(String[] args) throws Exception {
        Matrix A = new Matrix("1,2,3;4,5,6;7,8,9");

        System.out.println(A.cofactor(2,0));
    }
}
