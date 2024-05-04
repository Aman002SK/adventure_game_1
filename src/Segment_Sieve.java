import java.util.ArrayList;

public class Segment_Sieve {
    static ArrayList<Integer> generatePrimes(int N) {
        int[] STATE = new int[N + 1];
        ArrayList<Integer> RES = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            for (int j = i; j <= N; j += i) {
                STATE[j] = 1 - STATE[j]; // Toggle 0 to 1 and 1 to 0
            }
        }

        for (int i = 1; i <= N; i++) {
            if (STATE[i] == 1) {
                RES.add(i);
            }
        }

        return RES;
    }

    public static void main(String[] args) {
        int N = 20; // Change N to the desired value
        ArrayList<Integer> result = generatePrimes(N);

        System.out.println("Prime numbers up to " + N + ": " + result);
    }
}
