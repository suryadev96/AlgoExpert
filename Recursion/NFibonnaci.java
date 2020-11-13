class Question {

    public static int getNFib(int n) {
        if (n == 2) return 1;
        else if (n == 1) return 0;
        else return getNFib(n - 1) + getNFib(n - 2);
    }

    public static int getNFib(int n) {
        Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
        cache.put(1, 0); //1 fibonacci is 0
        cache.put(2, 1); //2 fibonacci is 1
        return getNFib(n, cache);
    }

    public static int getNFib(int n, Map<Integer, Integer> cache) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        } else {
            cache.put(n, getNFib(n - 1, cache) + getNFib(n - 2, cache));
            return cache.get(n);
        }
    }

    public static int getNFib(int n) {
        int[] lastTwo = {0, 1};
        int counter = 3;

        while (counter <= n) {
            int nextFib = lastTwo[0] + lastTwo[1];
            lastTwo[0] = lastTwo[1];
            lastTwo[1] = nextFib;
            counter++;
        }
        return n > 1 ? lastTwo[1] : lastTwo[0];
    }
}

import java.util.*;

class Program {
    public static int getNthFib(int n) {
        int a = 0;
        int b = 1;

        if (n == 1) return a;

        int counter = 3;
        while (counter <= n) {
            int c = a + b;
            a = b;
            b = c;
            counter++;
        }
        return b;
    }
}
