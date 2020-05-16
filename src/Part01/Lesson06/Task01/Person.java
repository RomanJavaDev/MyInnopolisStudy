package Part01.Lesson06.Task01;

import java.io.Serializable;

/**
 * Person.
 * Класс, объекты которого мы сериализуем в данном задании.
 *
 * @author Roman Khokhlov
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 100100100100L;
    private int id;
    private int age;
    private String name;
    private Course course;

    public Person(int id, int age, String name, Course course) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.course = course;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", course=" + course +
                '}';
    }
}
