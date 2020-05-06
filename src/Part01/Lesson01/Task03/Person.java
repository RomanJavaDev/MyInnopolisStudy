package Part01.Lesson01.Task03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Person.
 *
 * @author Roman Khokhlov
 */
public class Person {
    private int age;
    private Sex sex;
    private String name;

    public Person() {
    }

    public Person(int age, Sex sex, String name) {
        this.age = age;
        this.sex = sex;
        this.name = name;
    }

    public static String nameGenerator() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Person[] persons = new Person[100];

        List<Person> per = new ArrayList<>();
        Random rand = new Random(47);

        for (int i = 0; i < 10000; i++) {
            if (i % 2 == 0) {
                per.add(new Person(rand.nextInt(101), Sex.MAN, nameGenerator()));
            } else
                per.add(new Person(rand.nextInt(101), Sex.WOMAN, nameGenerator()));
        }
        PersonComparator myPriceComparator = new PersonComparator();
        // Реализация быстрой сортировки:
        long startTime = System.currentTimeMillis();

        per.sort(myPriceComparator);
        for (Person p : per) {
            System.out.println(p);
        }
        long endTime = System.currentTimeMillis();
        long timeSpent = endTime - startTime;


        Person[] per1 = new Person[10000];
        for (int i = 0; i < 10000; i++) {
            if (i % 2 == 0) {
                per1[i] = (new Person(rand.nextInt(101), Sex.MAN, nameGenerator()));
            } else
                per1[i] = (new Person(rand.nextInt(101), Sex.WOMAN, nameGenerator()));
        }
        PersonComparator myPriceComparator1 = new PersonComparator();
        // Реализация сортировки слиянием:
        long startTime1 = System.currentTimeMillis();

        Arrays.sort(per1, myPriceComparator1);

        for (Person p : per1) {
            System.out.println(p);
        }
        long endTime1 = System.currentTimeMillis();
        long timeSpent1 = endTime1 - startTime1;
        System.out.println("программа выполнялась " + timeSpent + " миллисекунд");
        System.out.println("программа Arrays.sort() выполнялась " + timeSpent1 + " миллисекунд");
        System.out.println(per.get(0).getSex().equals(per.get(2).getSex()));
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", sex=" + sex +
                ", name='" + name + '\'' +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = this.sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
