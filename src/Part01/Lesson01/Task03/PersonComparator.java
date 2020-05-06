package Part01.Lesson01.Task03;

import java.util.Comparator;

/**
 * PersonComparator.
 *
 * @author Roman Khokhlov
 */
public class PersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
        int result = (p1.getSex().toString().compareTo(p2.getSex().toString()));
        if (result != 0)
            return result;

        result = p1.getAge() - p2.getAge();
        if (result != 0)
            return result;

        result = (p1.getName().compareTo(p2.getName()));
        return result;
    }
}
