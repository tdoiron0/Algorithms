package Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Util {
    public static int[] genSortedUniqueRandArr(int n, int min, int max) {
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

    public static int[] genRandArr(int n, int min, int max) {
        return IntStream.generate(() -> new Random().nextInt(max - min) + min).limit(n).toArray();
    }

    public static int[][] genRandMatrix(int n, int m, int min, int max) {
        int[][] res = new int[n][];
        for (int i = 0; i < n; ++i) {
            res[i] = genRandArr(m, min, max);
        }
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

    public static String toString(int[][] mat) {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < mat.length; ++row) {
            sb.append('|');
            for (int column = 0; column < mat[row].length; ++column) {
                sb.append(mat[row][column]);
                sb.append((column + 1 < mat[row].length) ? " " : "");
            }
            sb.append("|\n");
        } 
        return sb.toString();
    }

    public static String toStringSpaced(int[][] mat) {
        List<StringBuilder> rows = new ArrayList<>();
        List<Integer> columnWidths = new ArrayList<>();
        for (int i = 0; i < mat[0].length; ++i) {
            columnWidths.add(colWidth(mat, i));
        }

        for (int i = 0; i < mat.length; ++i) {
            rows.add(new StringBuilder());
            rows.get(i).append('|');
            for (int j = 0; j < mat[i].length; ++j) {
                String temp = Integer.toString(mat[i][j]);
                String padding = getPadding(columnWidths.get(j) - temp.length());
                if (j + 1 < mat[i].length) {
                    temp = temp + " ";
                }
                rows.get(i).append(temp);
                rows.get(i).append(padding);
            }
            rows.get(i).append('|');
            if (i + 1 < mat.length) {
                rows.get(i).append('\n');
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows.size(); ++i) {
            sb.append(rows.get(i));
        }
        return sb.toString();
    }

    private static int colWidth(int[][] mat, int col) {
        int max = 0;
        for (int i = 0; i < mat.length; ++i) {
            if (Integer.toString(mat[i][col]).length() > Integer.toString(mat[max][col]).length()) {
                max = i;
            }
        }
        return Integer.toString(mat[max][col]).length();
    }

    public static String getPadding(int n) {
        return " ".repeat(n);
    }

    public static Integer[] boxedArray(int[] primatives) {
        return Arrays.stream(primatives).boxed().toArray(Integer[]::new);
    }
    public static List<Integer> boxedList(int[] primatives) {
        return Arrays.stream(primatives).boxed().collect(Collectors.toList());
    }
    public static int[] unboxArray(Integer[] boxed) {
        return Arrays.stream(boxed).mapToInt(Integer::intValue).toArray();
    }
    public static int[] unboxList(List<Integer> boxed) {
        return boxed.stream().mapToInt(Integer::intValue).toArray();
    }

    public static <T> T[][] deepCopy(T[][] mat) {
        return Arrays.stream(mat).map(el -> el.clone()).toArray($ -> mat.clone());
    }
    public static int[][] copy(int[][] mat) {
        return Arrays.stream(mat).map((int[] row) -> row.clone()).toArray((int length) -> new int[length][]);
    }
}
