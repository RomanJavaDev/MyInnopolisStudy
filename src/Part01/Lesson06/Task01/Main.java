package Part01.Lesson06.Task01;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static Part01.Lesson06.Task01.ObjectSerializer.deSerialize;
import static Part01.Lesson06.Task01.ObjectSerializer.serialize;

/**
 * Main.
 * Класс, в котором происходит запуск задания с примерными данными.
 *
 * @author Roman Khokhlov
 */
public class Main {
    public static void main(String[] args) throws IOException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        Person person = new Person(2, 20, "Bob");
        serialize(person, "C:\\Users\\Roman\\Desktop\\Serialize.xml");
        Person person2 = (Person) deSerialize("C:\\Users\\Roman\\Desktop\\Serialize.xml");
        System.out.println(person2);
    }
}
