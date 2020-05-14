package Part01.Lesson05.Task01;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * FactorialThread2.
 *
 * @author Roman Khokhlov
 */
public class FactorialThread2 {
    private static Thread factorialThread(Integer iterNumber) {
        return new Thread() {
            public void run() {
                factorial(iterNumber);
            }
        };
    }

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random(47);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(random.nextInt(25));
        }
        Thread.sleep(2000);
        int size = list.size();
        long start = System.currentTimeMillis();
        for (int j = 0; j < size; j++) {
            Thread.sleep(5);
            System.out.println(j);
            factorialThread(list.get(j)).start();
        }
        long finish = System.currentTimeMillis();
        Thread.sleep(1000);
        System.out.println("Время выполнения: " + (finish - start));
    }

    public static BigInteger factorial(Integer number) {
        BigInteger result = BigInteger.ONE;
        if (number == 0 || number == 1) {
            System.out.println("Факториал числа " + number + " = " + result);
            return result;
        }
        for (int i = 2; i <= number; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        System.out.println("Факториал числа " + number + " = " + result);
        return result;
    }
}
