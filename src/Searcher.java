import Util.Pair;

public class Searcher {
    /**
     * Implementation of binary search algorithm 
     * 
     * @param <T> data type of elements 
     * @param arr sorted array of elements 
     */
    public static <T extends Comparable<? super T>> int binarySearch(T[] arr, T target) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = (r - l) / 2 + l;
            if (arr[mid].compareTo(target) == 0) {
                return mid;
            } else if (arr[mid].compareTo(target) > 0) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    public static <T extends Comparable<? super T>> int min(T[] arr) {
        return min(arr, 0, arr.length - 1);
    }

    public static <T extends Comparable<? super T>> int min(T[] arr, int l, int r) {
        if (arr.length == 0) {
            throw new IllegalArgumentException("Cannot find the mimumum element of an array of length 0");
        }

        int min = l;
        for (int i = l; i <= r; ++i) {
            if (arr[i].compareTo(arr[min]) < 0) {
                min = i;
            }
        }
        return min;
    }

    public static <T extends Comparable<? super T>> int max(T[] arr) {
        return max(arr, 0, arr.length - 1);
    }

    public static <T extends Comparable<? super T>> int max(T[] arr, int l, int r) {
        if (arr.length == 0) {
            throw new IllegalArgumentException("Cannot find the maximum element of an array of length 0");
        }

        int max = 0;
        for (int i = l; i <= r; ++i) {
            if (arr[i].compareTo(arr[max]) > 0) {
                max = i;
            }
        }
        return max;
    }

    public static Pair<Integer> maxInCol(int[][] mat, int col) {
        int max = 0;
        for (int i = 0; i < mat.length; ++i) {
            if (mat[i][col] > mat[max][col]) {
                max = i;
            }
        }
        return new Pair<Integer>(max, col);
    }

    public static <T extends Comparable<? super T>> Pair<Integer> maxInCol(T[][] mat, int col) {
        int max = 0;
        for (int i = 0; i < mat.length; ++i) {
            if (mat[i][col].compareTo(mat[max][col]) > 0) {
                max = i;
            }
        }
        return new Pair<Integer>(max, col);
    }

    public static Pair<Integer> minInCol(int[][] mat, int col) {
        int min = 0; 
        for (int i = 0; i < mat.length; ++i) {
            if (mat[i][col] > mat[min][col]) {
                min = i;
            }
        }
        return new Pair<Integer>(min, col);
    }

    public static <T extends Comparable<? super T>> Pair<Integer> minInCol(T[][] mat, int col) {
        int min = 0;
        for (int i = 0; i < mat.length; ++i) {
            if (mat[i][col].compareTo(mat[min][col]) < 0) {
                min = i;
            }
        }
        return new Pair<Integer>(min, col);
    }

    /**
     * Calculates the kth smallest number. 
     * Picks pivot using randomness. 
     * 
     * @param <T>
     * @param arr
     * @param k
     * @return
     */
    public static <T extends Comparable<? super T>> int quickSelect(T[] arr, int k) {
        return -1;
    }

    /**
     * Calculates the kth smallest number. 
     * Uses medians of medians to find a good pivot which ensures linear time complexity but increases overhead. 
     * 
     * @param <T>
     * @param arr
     * @param left
     * @param right
     * @param k
     * @return
     */
    public static <T extends Comparable<? super T>> int fastSelect(T[] arr, int left, int right, int k) {
        return -1;
    }

    /**
     * Finds a good pivot 
     * 
     * @param <T>
     * @param arr
     * @return
     */
    public static <T extends Comparable<? super T>> int medianOfMedian(T[] arr) {
        if (arr.length < 10) {
            
        }

        int n = (arr.length % 5 != 0) ? arr.length / 5 + 1 : arr.length / 5;
        T[][] A = (T[][])new Object[n][5];
        int j = 0;
        for (int i = 0; i < n; ++i) {
            A[i][0] = arr[j++];
            A[i][1] = arr[j++];
            A[i][2] = arr[j++];
            A[i][3] = arr[j++];
            A[i][4] = arr[j++];
        }



        return -1;
    }

    private static <T extends Comparable<? super T>> T medianOf5(T[] arr, int left, int right) {
        if (left - right + 1> 5) {
            throw new IllegalArgumentException(String.format("Array of length %d greater than 5", left - right + 1));
        }
        if (left < 0 || right > arr.length) {
            throw new IndexOutOfBoundsException(String.format("Interval of [%d,%d] out of bounds for array of lenght %d", left, right, arr.length));
        }

        T[] temp = (T[])new Object[left - right + 1]; 
        for (int i = left; i <= right; ++i) {
            int min = i;
            for (int j = i + 1; j <= right; ++j) {
                if (arr[j].compareTo(arr[min]) < 0) {
                    min = j;
                }
            }
            temp[i - left] = arr[min];
        }
        return temp[temp.length / 2];
    }
}
