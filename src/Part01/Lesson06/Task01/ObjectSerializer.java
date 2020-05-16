package Part01.Lesson06.Task01;

import java.io.*;

/**
 * ObjectSerializer.
 * <p>
 * Задание 1. Необходимо разработать класс, реализующий следующие методы:
 * void serialize (Object object, String file);
 * Object deSerialize(String file);
 * Методы выполняют сериализацию объекта Object в файл file и десериализацию объекта из этого файла.
 * Обязательна сериализация и десериализация "плоских" объектов (все поля объекта - примитивы, или String).
 * Задание 2. Предусмотреть работу c любыми типами полей (полями могут быть ссылочные типы).
 *
 * @author Roman Khokhlov
 */
public class ObjectSerializer {

    public static void serialize(Object object, String file) {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(object);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static Object deSerialize(String file) {
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Object object = (Object) ois.readObject();
            return object;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
        return new Exception();
    }

}
