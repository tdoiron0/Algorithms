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

    public static <T extends Comparable<? super T>> int median(T[] arr) {
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

    public static <T extends Comparable<? super T>> int fastSelect(T[] arr, int k) {
        return -1;
    }
}
