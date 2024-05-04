//Initialize an array ‘STATE’ of size 'N' + 1 and an array ‘RES’ to store the result.
//        Run a loop ‘i’: 1 to ‘N’
//        Run a loop ‘j’: ‘i’ to ‘N’ to visit all multiples of ‘i‘ and in each iteration increment j by ‘i‘.
//        If ‘STATE[ j ]’ is ‘ 0 ‘, update ‘STATE[ j ]’ = 1 and vice-versa.
//        Iterate through the ‘STATE’ array - ‘i’: 1 to ‘N’:
//        If 'STATE[ i ]' = 1, push ‘i‘ to ‘RES’.
//        Return ‘RES’.
//For example, if n is 10, the output should be “2, 3, 5, 7”. If n is 20, the output should be “2, 3, 5, 7, 11, 13, 17, 19”.

import java.util.*;
//class IO{
//     static int calculate(int a){
//
//        return  a;
//    }
//    static void run(){
//        System.out.println("i am running");
//    }
//    public static void main(String[] args) {
//
//
//
//        int v = IO.calculate(10);
//        System.out.println(v);
//        IO.run();
//
//
//
//    }
//}



class SieveOfEratosthenes
{
    void sieveOfEratosthenes(int n) //30
    {
        // Create a boolean array "prime[0..n]" and initialize
        // all entries it as true. A value in prime[i] will
        // finally be false if i is Not a prime, else true.
        boolean prime[] = new boolean[n+1];
        for(int i=0;i<=n;i++)
            prime[i] = true;  //{true, true, true, true,false,............,true}

        for(int p = 2; p*p <=n; p++) //36> 30(value of n)
        {
            // If prime[p] is not changed, then it is a prime
            if(prime[p] == true)
            {
                // Update all multiples of p

                for(int i = p*p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        // Print all prime numbers
        for(int i = 2; i <= n; i++)
        {
            if(prime[i] == true)
                System.out.print(i + " ");
        }
    }

    // Driver Program to test above function
    public static void main(String args[])
    {
        int n = 30;
        System.out.print("Following are the prime numbers ");
        System.out.println("smaller than or equal to " + n);
        SieveOfEratosthenes g = new SieveOfEratosthenes();
        g.sieveOfEratosthenes(n);
    }
}







// 1,2,3, 4, 5,6,7,8,9,10,11,12,13,14
//true,true,
//true, true, true,false, true, false,true,false,false
// for (2 to 14)
//    if(n is true){
//        print n
//            }







//import java.util.ArrayList;
//import java.util.List;

//public class IO {
//
//    public static List<Integer> generatePrimes(int N) {
//        // Initialize an array 'STATE' of size 'N' + 1 and an array 'RES' to store the result.
//        int[] STATE = new int[N + 1];
//        List<Integer> RES = new ArrayList<>();
//
//        // Run a loop 'i': 1 to 'N'
//        for (int i = 1; i <= N; i++) {
//            // Run a loop 'j': 'i' to 'N' to visit all multiples of 'i' and in each iteration increment j by 'i'.
//            for (int j = i; j <= N; j += i) {
//                // If 'STATE[j]' is '0', update 'STATE[j]' = 1 and vice-versa.
//                STATE[j] = 1 - STATE[j];
//            }
//        }
//
//        // Iterate through the 'STATE' array - 'i': 1 to 'N':
//        for (int i = 1; i <= N; i++) {
//            // If 'STATE[i]' = 1, push 'i' to 'RES'.
//            if (STATE[i] == 1) {
//                RES.add(i);
//            }
//        }
//
//        // Return 'RES'.
//        return RES;
//    }
//
//    public static void main(String[] args) {
//        int N = 30; // Replace this with the desired value of N
//        List<Integer> result = generatePrimes(N);
//        System.out.println("Prime numbers up to " + N + ": " + result);
//    }
//}
