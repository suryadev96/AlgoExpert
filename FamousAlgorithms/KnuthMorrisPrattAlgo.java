class Program {
    public static boolean knuthMorrisPrattAlgorithm(String string, String substring) {
        int M = string.length();
        int N = substring.length();
        int[] lps = new int[N];
        computeLPS(lps, substring);

        int i = 0;
        int j = 0;

        while (i < M) {
            if (string.charAt(i) == substring.charAt(j)) {
                i++;
                j++;
                if (j == N) return true;
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return false;
    }

    public static void computeLPS(int[] lps, String substring) {
        int N = substring.length();
        lps[0] = 0;

        //length of longest prefix suffix for prev index
        int len = 0;
        int i = 1;

        while (i < N) {
            if (substring.charAt(i) == substring.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

    }
}
