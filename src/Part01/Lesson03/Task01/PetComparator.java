package Part01.Lesson03.Task01;

import java.util.Comparator;

/**
 * PetComparator.
 *
 * @author Roman Khokhlov
 */
public class PetComparator implements Comparator<Pet> {
    @Override
    public int compare(Pet p1, Pet p2) {

        int result = (p1.person.name.compareTo(p2.person.name));
        if (result != 0)
            return result;


        result = (p1.name.compareTo(p2.name));
        if (result != 0)
            return result;

        result = p1.weight - p2.weight;
        if (result != 0)
            return result;


        return 0;

    }


}
