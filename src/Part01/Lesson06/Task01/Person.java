package Part01.Lesson06.Task01;


/**
 * Person.
 * Класс, объекты которого мы сериализуем в данном задании.
 *
 * @author Roman Khokhlov
 */
public class Person{
    private int id;
    private int age;
    private String name;
//    private Course course;

    public Person(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
//        this.course = course;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name +
//                ", course='" + course +
                '\'' +
                '}';
    }
}
