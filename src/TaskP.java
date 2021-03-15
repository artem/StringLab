import java.util.Scanner;

public class TaskP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = sc.nextInt();
        Integer[] pref = new Integer[n];

        pref[0] = 0;

        for (int i = 1; i < n; i++) {
            pref[i] = sc.nextInt();
        }

        long ans = c;

        for (int i = 1; i < n; i++) {
            if (pref[i] == 0) {
                int j = pref[i - 1];
                int q = 1;
                while (j != 0) {
                    if (pref[j] == 0) {
                        q++;
                    }
                    j = pref[j - 1];
                }
                ans *= c - q;
                ans %= 1000000007;
            }
        }

    }
}
