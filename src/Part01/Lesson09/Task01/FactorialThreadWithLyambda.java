package Part01.Lesson09.Task01;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * FactorialThreadWithLyambda.
 * <p>
 * Перевести одну из предыдущих работ на использование стримов и лямбда-выражений там, где это уместно (возможно, жертвуя производительностью)
 *
 * @author Roman Khokhlov
 */

public class FactorialThreadWithLyambda {

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
        ExecutorService service = Executors.newFixedThreadPool(10);
        Random random = new Random();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list.add(random.nextInt(100));
        }
        list.stream().forEach(System.out::println);  // Вывод в консоль с помощью Stream API
        Thread.sleep(2000);
        long start = System.currentTimeMillis();
        for (Integer element : list) {
            Future result = service.submit(() -> factorial(element));  // Использование лямбда - выражения для Callable интерфейса
            result.get();
        }
        long finish = System.currentTimeMillis();
        Thread.sleep(1000);
        System.out.println("Время выполнения: " + (finish - start));
        service.shutdown();
    }
}

