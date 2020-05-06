package Part01.Lesson01.Task02;

import java.util.Random;

/**
 * RandGen.
 * This program generates n-random k numbers.
 * For each k square root is being calculated - q (double variable).
 * If square of integer part of q equals to k, k will be printed.
 * If k < 0 then program throws Exception
 *
 * Version 1.0
 *
 * @author Roman Khokhlov
 */
public class RandGen {

    /**
     * main method
     * @param args
     * @throws Exception - throws if k < 0
     */
    public static void main(String[] args) throws Exception {
        Random rand = new Random(47);
        int k = 0;
        double q = 0.0;
        int n = 1000000;
        for (int i = 0; i < n; i++) {
            k = rand.nextInt(); //101

            if (k >= 0) {
                q = Math.sqrt(k); //10.05
                int a = (int) q;
                if (a * a == k) {
                    System.out.println(q);
                }
            }
            else throw new Exception("Число k = " + k + " (меньше нуля)");

        }
    }
}
