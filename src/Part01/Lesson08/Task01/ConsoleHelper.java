package Part01.Lesson08.Task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ConsoleHelper.
 *
 * @author Roman Khokhlov
 */
public class ConsoleHelper {
    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    // Выводит сообщение message в консоль
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    // Cчитывает строку с консоли
    public static String readString() {
        while (true) {
            try {
                String s = bf.readLine();
                return s;
            } catch (IOException e) {
                System.out.println("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
            }
        }
    }

    // Возвращает введенное число
    public static int readInt() {
        while (true) {
            try {
                String input = readString();
                return Integer.parseInt(input);

            } catch (NumberFormatException e1) {
                System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            }
        }
    }
}

