import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;

public class Sorter {
    public static <T extends Comparable<? super T>> void selection(T[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be equal to null.");
        }

        for (int i = arr.length - 1; i >= 0; --i) {
            int max = i;
            for (int j = 0; j < i; ++j) {
                if (arr[max].compareTo(arr[j]) < 0) {
                    max = j;
                }
            }
            swap(arr, max, i);
        }
    }

    public static <T extends Comparable<? super T>> void insertion(T[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be equal to null.");
        }
        
        for (int i = 1; i < arr.length; ++i) {
            for (int j = i - 1; j >= 0 && arr[j].compareTo(arr[j + 1]) > 0; --j) {
                swap(arr, j + 1, j);
            }
        }
    }

    public static <T extends Comparable<? super T>> void bubble(T[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be equal to null.");
        }

        int stopIndex = arr.length - 1;
        while (stopIndex > 0) {
            int lastSwapIndex = 0; 
            for (int i = 0; i < stopIndex; ++i) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    lastSwapIndex = i;
                }
            }
            stopIndex = lastSwapIndex; // optimization 
        }
    }

    public static <T extends Comparable<? super T>> void cocktail(T[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be equal to null.");
        }

        boolean swapsMade = true;
        int startIndex = 0;
        int endIndex = arr.length - 1;
        while (swapsMade && startIndex < endIndex) {
            int lastSwapIndex = 0;
            swapsMade = false;
            for (int i = startIndex; i < endIndex; ++i) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    swapsMade = true;
                    lastSwapIndex = i;
                }
            }
            endIndex = lastSwapIndex;
            if (swapsMade) {
                swapsMade = false;
                for (int i = endIndex; i > startIndex; --i) {
                    if (arr[i - 1].compareTo(arr[i]) > 0) {
                        swap(arr, i - 1, i);
                        swapsMade = true;
                        lastSwapIndex = i;
                    }
                }
            }
            startIndex = lastSwapIndex;
        }
    }

    public static <T extends Comparable<? super T>> void mergeSort(T[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be equal to null.");
        }

        if (arr.length <= 1) {
            return;
        }
        int length = arr.length;
        int midIndex = length / 2;
        T[] left = (T[]) new Object[midIndex];
        for (int i = 0; i < left.length; ++i) {
            left[i] = arr[i];
        }
        T[] right = (T[]) new Object[length - midIndex];
        for (int i = 0; i < right.length; ++i) {
            right[i] = arr[midIndex + i];
        }
        mergeSort(left);
        mergeSort(right);
        int i = 0;
        int j = 0;
        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j]) <= 0) {
                arr[i + j] = left[i];
                ++i;
            } else {
                arr[i + j] = right[j];
                ++j;
            }
        }
        while (i < left.length) {
            arr[i + j] = left[i];
            ++i;
        }
        while (j < right.length) {
            arr[i + j] = right[j];
            ++j;
        }
    }

    public static <T extends Comparable<? super T>> void quickSort(T[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be equal to null.");
        }

        rQuickSort(arr, new Random(4), 0, arr.length - 1);
    }

    private static <T extends Comparable<? super T>> void rQuickSort(T[] arr, Random rand, int start, int end) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be equal to null.");
        }
        if (rand == null) {
            throw new IllegalArgumentException("Rand cannot be equal to null.");
        }

        if (end - start < 1) {
            return;
        }
        int pivotIndex = rand.nextInt(end - start) + start;
        T pivotVal = arr[pivotIndex];
        swap(arr, start, pivotIndex);
        int i = start + 1;
        int j = end;
        while (i <= j) {
            while (i <= j && arr[i].compareTo(pivotVal) <= 0) {
                i++;
            }
            while (i <= j && arr[j].compareTo(pivotVal) >= 0) {
                j--;
            }
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        swap(arr, start, j);
        rQuickSort(arr, rand, start, j - 1);
        rQuickSort(arr, rand, j + 1, end);
    }

    //TODO
    public static <T extends Comparable<? super T>> void quickSelect(T[] arr) {

    }

    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("arr cannot be equal to null.");
        }

        LinkedList<Integer>[] bucketLists = new LinkedList[19];
        for (int i = 0; i < 19; ++i) {
            bucketLists[i] = new LinkedList<>();
        }
        int largest = 0;
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == Integer.MIN_VALUE) {
                largest = Integer.MAX_VALUE;
                break;
            }
            if (Math.abs(arr[i]) > Math.abs(largest)) {
                largest = Math.abs(arr[i]);
            }
        }
        int k = 0; // if just checking if exponent is larger then run into error with infinite loop and integer overflow of exponent 
        while (largest != 0) {
            k++; 
            largest /= 10;
        }
        int exponent = 1; 
        for (int i = 0; i < k; ++i) {
            for (int j = 0; j < arr.length; ++j) {
                int digit = (arr[j] / exponent) % 10 + 9;
                bucketLists[digit].addLast(arr[j]);
            }
            int index = 0;
            for (LinkedList<Integer> it : bucketLists) {
                while (!it.isEmpty()) {
                    arr[index++] = it.removeFirst();
                }
            }
            exponent *= 10;
        }
    }

    public static <T extends Comparable<? super T>> T[] heapSort(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot equal null.");
        }

        PriorityQueue<T> queue = new PriorityQueue<>(data);
        T[] result = (T[]) new Object[data.size()];
        int i = 0;
        while (!queue.isEmpty()) {
            result[i] = queue.remove();
            ++i;
        }
        return result;
    }

    private static <T extends Comparable<? super T>> void swap(T[] arr, int a, int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}