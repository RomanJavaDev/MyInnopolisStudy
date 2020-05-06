package Part01.Lesson01.Task01;

/**
 * HelloWorld.
 * This is a test program ”Hello, World!” which throws "NullPointerException",
 * "ArrayIndexOutOfBoundsException" and "ArithmeticException".
 *
 *  Version 1.0
 *
 * @author Roman Khokhlov
 */
public class HelloWorld {

    /**
     * main method
     * @param args - array of strings
     * @throws NullPointerException - случай, когда вызывается метод у null
     * @throws ArrayIndexOutOfBoundsException - случай, когда обращаемся к элементу, чей индекс вне массива
     * @throws ArithmeticException - арифметическая ошибка
     */
    public static void main(String[] args) throws NullPointerException, ArrayIndexOutOfBoundsException, ArithmeticException {

        String smth = null;
        try {
            if (smth.equals("smth")) {
                System.out.println("Smth");
            }
        } catch (NullPointerException e) {
            System.out.println("Ошибка, вызывается метод у null");
        }

        try {

            int[] arr = {1, 2, 3, 4, 5};
            for (int i = 0; i <= arr.length; i++) {
                System.out.println(arr[i]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка, проверьте заданную область массива или индекс");
        }

        int a = 1;
        try {
            System.out.println(a / 0);
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Недопустимая операция. Возможно, вы выполняете деление на ноль");
        }
        finally {
            System.out.println("Hello, World!");
        }

    }
}
