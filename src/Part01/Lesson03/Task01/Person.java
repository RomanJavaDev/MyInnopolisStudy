package Part01.Lesson03.Task01;

import java.util.Objects;

/**
 * Person.
 *
 * @author Roman Khokhlov
 */
public class Person {
    String name;
    int age;
    Sex sex;

    public Person(String name, int age, Sex sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name) &&
                sex == person.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, sex);
    }
}
