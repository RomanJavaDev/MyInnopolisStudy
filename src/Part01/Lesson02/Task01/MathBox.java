package Part01.Lesson02.Task01;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * MathBox.
 *
 * Класс MathBox, реализует следующий функционал:
 * Конструктор на вход получает массив Number. Элементы не могут повторяться. Элементы массива внутри объекта раскладываются в подходящую коллекцию (выбрать самостоятельно).
 * Существует метод summator, возвращающий сумму всех элементов коллекции.
 * Существует метод splitter, выполняющий поочередное деление всех хранящихся в объекте элементов на делитель, являющийся аргументом метода. Хранящиеся в объекте данные полностью заменяются результатами деления.
 * Необходимо правильно переопределить методы toString, hashCode, equals, чтобы можно было использовать MathBox для вывода данных на экран и хранение объектов этого класса в коллекциях (например, hashMap). Выполнение контракта обязательно!
 * Создать метод, который получает на вход Integer и если такое значение есть в коллекции, удаляет его.
 *
 * @author Roman Khokhlov
 */

public class MathBox extends ObjectBox {

    public MathBox(Number[] arr) {
        super.objects = new LinkedHashSet<Object>(Arrays.asList(arr));
    }

    public static void main(String[] args) {
        Number[] arr = new Number[10];
        Random rand = new Random(47);
        for (int i = 0; i < 10; i++) {
            arr[i] = (rand.nextInt(100));
        }
        MathBox mb = new MathBox(arr);
        System.out.println("Выводим массив, поступивший на вход объекта MathBox: ");
        System.out.println(Arrays.toString(arr));
        System.out.println("Выводим элементы коллекции LinkedHashSet: ");
        mb.printHashSet();
        System.out.println("Делим каждый элемент коллекции LinkedHashSet на 2 и выводим результат: ");
        mb.splitter(2);
        mb.printHashSet();
        System.out.println("Добавляем новый элемент 20 в коллекцию LinkedHashSet и выводим результат: ");
        mb.addObject(20);
        mb.printHashSet();
        System.out.println("Удаляем элемент 29 из коллекции LinkedHashSet и выводим результат: ");
        mb.deleteObject(29);
        mb.printHashSet();
        System.out.println("Выводим сумму всех элементов: ");
        System.out.println(mb.summator());
    }

    /**
     * Возвращает сумму элементов HashSet объекта MathBox
     *
     * @return
     */
    public Double summator() {
        Double sum = 0.0;
        for (Object h : super.objects) {
            sum = sum + (Integer) h;
        }
        return sum;
    }

    /**
     * Удаляет элемент d из множества HashSet объекта MathBox
     *
     * @param d
     */
    public void deleter(Number d) {
        this.deleteObject(d);
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "hs=" + super.objects +
                '}';
    }

    /**
     * Производит деление каждого элемента множества HashSet объекта MathBox на число n и передает новый HashSet объекту MathBox
     *
     * @param n
     */
    public void splitter(Number n) {
        Set<Object> split = new LinkedHashSet<>();
        for (Object h : super.objects) {
            h = (Integer) h / (Integer) n;
            split.add(h);
        }
        super.objects.clear();
        super.objects.addAll(split);

    }

    /**
     * Выводит в строку множество HashSet объекта MathBox
     */
    public void printHashSet() {
        this.dump();
    }


}
