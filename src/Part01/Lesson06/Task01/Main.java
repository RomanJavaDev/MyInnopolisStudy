package Part01.Lesson06.Task01;

import static Part01.Lesson06.Task01.ObjectSerializer.deSerialize;
import static Part01.Lesson06.Task01.ObjectSerializer.serialize;

/**
 * Main.
 * Класс, в котором происходит запуск задания с примерными данными.
 *
 * @author Roman Khokhlov
 */
public class Main {
    public static void main(String[] args) {
        Person person = new Person(2, 20, "Bob", new Course("Java"));
        serialize(person, "C:\\Users\\Roman\\Desktop\\Serialize.txt");
        System.out.println(deSerialize("C:\\Users\\Roman\\Desktop\\Serialize.txt"));
    }
}
