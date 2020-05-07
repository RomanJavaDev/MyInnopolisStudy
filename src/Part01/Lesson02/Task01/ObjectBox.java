package Part01.Lesson02.Task01;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * ObjectBox.
 *
 * Класс ObjectBox, хранит коллекцию Object.
 * У класса должен быть метод addObject, добавляющий объект в коллекцию.
 * У класса должен быть метод deleteObject, проверяющий наличие объекта в коллекции и при наличии удаляющий его.
 * Должен быть метод dump, выводящий содержимое коллекции в строку.
 *
 * @author Roman Khokhlov
 */
public class ObjectBox {

    public Set<Object> objects = new LinkedHashSet<>();


    public ObjectBox() {

    }

    /**
     * Добавляет новый объект во множество HashSet объекта ObjectBox
     *
     * @param obj
     */
    public void addObject(Object obj) {
        if (obj instanceof Number) objects.add(obj);
        else
            throw new UnsupportedOperationException();
    }

    /**
     * Удаляет объект из множества HashSet объекта ObjectBox
     *
     * @param obj
     */
    public void deleteObject(Object obj) {
        this.objects.remove(obj);
    }

    /**
     * Выводит в строку множество HashSet объекта ObjectBox
     */
    public void dump() {
        System.out.println(objects.toString());
    }

}
