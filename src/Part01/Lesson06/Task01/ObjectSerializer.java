package Part01.Lesson06.Task01;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

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

    public static void serialize(Object object, String file) throws IOException, IllegalAccessException {
        try (FileOutputStream out = new FileOutputStream(file);
             XMLEncoder xmle = new XMLEncoder(out)) {

            Class someClass = object.getClass();
            System.out.println(someClass);
            Field[] fields = someClass.getDeclaredFields();
            xmle.writeObject(someClass);  // 1
            xmle.writeObject(fields.length);  // 2
            for (Field f : fields) {
                f.setAccessible(true);
                int modifiers = f.getModifiers();
                String fName = f.getName();
                Object fType = f.getType();
                Object value = f.get(object);
                xmle.writeObject(fName);  // 3
                xmle.writeObject(fType);  // 4
                xmle.writeObject(value);  // 5
                xmle.writeObject(modifiers);  // 6
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static Object deSerialize(String file) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        try (FileInputStream out = new FileInputStream(file);
             XMLDecoder xmld = new XMLDecoder(out)) {

            Class someClass = (Class) xmld.readObject();  // 1
            System.out.println(someClass);
            int numberOfFields = (int) xmld.readObject();  // 2
            System.out.println("numberOfFields" + numberOfFields);
            Field[] fields = someClass.getDeclaredFields();
            Constructor[] constructors = someClass.getConstructors();
            Constructor constructor2 = constructors[0];
            Constructor constructor =
                    someClass.getConstructor(new Class[]{fields[0].getType(), fields[1].getType(), fields[2].getType()});

            Object[] objects = new Object[numberOfFields];
            int i = 0;
            for (Field f : fields) {
                f.setAccessible(true);
                String fName = (String) xmld.readObject();  // 3
                Object fType = xmld.readObject();  // 4
                objects[i] = xmld.readObject(); // value  // 5
                i++;
                int modifiers = (int) xmld.readObject();  // 6
            }
            return constructor2.newInstance(objects);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
        return new Exception("Что-то пошло не так");
    }

}
