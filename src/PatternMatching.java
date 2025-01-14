import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatternMatching {
    public static int BruteForce(CharSequence text, CharSequence pattern) {
        for (int t = 0; t <= text.length() - pattern.length(); ++t) {
            int i = 0; 
            while (i <= pattern.length() - 1) {
                if (pattern.charAt(i) == text.charAt(i + t)) {
                    if (i < pattern.length() - 1) {
                        ++i; 
                        continue;
                    } else {
                        return t;
                    }
                } else {
                    break;
                }
            }
        }
        return -1;
    }

    public static List<Integer> boyerMoore(CharSequence text, CharSequence pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot be equal to null.");
        }
        if (pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern cannot have length 0.");
        }
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be equal to null.");
        }

        ArrayList<Integer> result = new ArrayList<>();
        Map<Character, Integer> last = buildLastTable(pattern);
        int i = 0;
        while (i <= text.length() - pattern.length()) {
            int j = pattern.length() - 1;
            while (j >= 0 && text.charAt(i + j) == pattern.charAt(j)) {
                --j;
            }
            if (j == -1) {
                result.add(i);
                ++i;
            } else {
                int shift = last.getOrDefault(text.charAt(i + pattern.length() - 1), -1);
                if (shift < j) {
                    i = i + j - shift;
                } else {
                    ++i;
                }
            }
        }
        return result;
    } 

    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        HashMap<Character, Integer> last = new HashMap<>();
        for (int i = 0; i < pattern.length(); ++i) {
            last.put(pattern.charAt(i), i);
        }
        return last;
    }

    public static List<Integer> kmp(CharSequence pattern, CharSequence text) {
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot be equal to null.");
        }
        if (pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern cannot have length 0.");
        }
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be equal to null.");
        }

        int j = 0;
        int k = 0;
        int[] f = buildFailureTable(pattern);
        ArrayList<Integer> result = new ArrayList<>();
        while (k <= text.length() - pattern.length()) {
            while (j < pattern.length() && pattern.charAt(j) == text.charAt(k)) {
                ++k;
                ++j;
            }
            if (j == pattern.length()) {
                result.add(k - j);
                j = f[j - 1];
            } else if (j == 0) {
                ++k;
            } else {
                j = f[j - 1];
            }
        }
        return result;
    }

    private static int[] buildFailureTable(CharSequence pattern) {
        int i = 0;
        int j = 1;
        int[] f = new int[pattern.length()];
        f[0] = 0;
        while (j < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                f[j] = i + 1;
                ++j;
                ++i;
            } else {
                if (i == 0) {
                    f[j] = 0;
                    ++j;
                } else {
                    i = f[i - 1];
                }
            }
        }
        return f;
    }
}
