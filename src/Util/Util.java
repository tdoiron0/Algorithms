package Util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Util {
    public static int[] genSortedRandArr(int n, int min, int max) {
        if (n > max - min) {
            throw new IllegalArgumentException("Cannot generate more distinct integers than there are integers on an interval");
        }

        Set<Integer> nums = new HashSet<>();
        Random rand = new Random();
        while (nums.size() < n) {
            nums.add(rand.nextInt(max - min) + min); 
        }
        int[] res = nums.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(res);
        return res;
    }

    public static Interval genRandInterval(int min, int max) {
        Random rand = new Random();
        int lower = 0;
        int upper = 0;
        while (lower >= upper) {
            lower = rand.nextInt(max - min) + min;
            upper = rand.nextInt(max - min) + min;
        }
        return new Interval(lower, upper);
    }

    public static int[] genNums(int n) {
        int[] res = new int[n];
        for (int i = 0; i < res.length; ++i) {
            res[i] = i;
        }
        return res;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            
        }
    }
}
