package Part01.Lesson10.Task01;

/**
 * MemoryLeakGenerator.
 * Необходимо создать программу, которая продемонстрирует утечку памяти в Java.
 * При этом объекты должны не только создаваться, но и периодически частично удаляться,
 * чтобы GC имел возможность очищать часть памяти. Через некоторое время программа должна завершиться
 * с ошибкой OutOfMemoryError c пометкой Java Heap Space.
 * <p>
 * Доработать программу так, чтобы ошибка OutOfMemoryError возникала в Metaspace /Permanent Generation
 *
 * @author Roman Khokhlov
 */

public class MemoryLeakGenerator {
    public static void main(String[] args) throws Exception {
        MemoryLeakGenerator leakGenerator = new MemoryLeakGenerator();
        leakGenerator.createArrays();
    }

    public void createArrays() {
        int arraySize = 20;
        while (true) {
            System.out.println("Available memory (in bytes): " + Runtime.getRuntime().freeMemory());
            int[] fillMemory = new int[arraySize];
            arraySize = arraySize * 5;
        }
    }
}