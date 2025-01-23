package Util;

public class Interval {
    private int lower;
    private int upper; 

    public Interval(int lower, int upper) {
        if (lower > upper) {
            throw new IllegalArgumentException("Cannot create interval with lower > upper");
        }
        
        this.lower = lower;
        this.upper = upper;
    }

    public int low() { return lower; }
    public int up() { return upper; }
}
