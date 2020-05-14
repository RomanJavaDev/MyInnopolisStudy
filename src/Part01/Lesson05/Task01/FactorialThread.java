package Part01.Lesson05.Task01;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * FactorialThread.
 * <p>
 * Дан массив случайных чисел. Написать программу для вычисления факториалов всех элементов массива. Использовать пул потоков для решения задачи.
 * Особенности выполнения:
 * Для данного примера использовать рекурсию - не очень хороший вариант, т.к. происходит большое выделение памяти, очень вероятен StackOverFlow.
 * Лучше перемножать числа в простом цикле при этом создавать объект типа BigInteger
 * По сути, есть несколько способа решения задания:
 * 1) распараллеливать вычисление факториала для одного числа
 * 2) распараллеливать вычисления для разных чисел
 * 3) комбинированный
 * При чем вычислив факториал для одного числа, можно запомнить эти данные и использовать их для вычисления другого, что будет гораздо быстрее.
 *
 * @author Roman Khokhlov
 */

public class FactorialThread {
    ExecutorService service = Executors.newFixedThreadPool(2);

    private static Callable<BigInteger> factorialThread(Integer iterNumber) {
        return new Callable<BigInteger>() {
            @Override
            public BigInteger call() throws Exception {
                return factorial(iterNumber);
            }
        };
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

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Random random = new Random(47);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(random.nextInt(25));
        }
        Thread.sleep(2000);
        int size = list.size();
        long start = System.currentTimeMillis();
        for (int j = 0; j < size; j++) {
            System.out.println(j);
            Future result = service.submit(factorialThread(list.get(j)));
            result.get();
        }
        long finish = System.currentTimeMillis();
        Thread.sleep(1000);
        System.out.println("Время выполнения: " + (finish - start));
        service.shutdown();
    }
}
