package Part01.Lesson03.Task01;

import java.util.LinkedHashSet;
import java.util.Set;

import static Part01.Lesson03.Task01.Pet.*;


/**
 * Main.
 *
 * @author Roman Khokhlov
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Person person1 = new Person("Ivan", 30, Sex.MALE);
        Person person2 = new Person("Petr", 25, Sex.MALE);
        Person person3 = new Person("Irina", 20, Sex.FEMALE);
        Person person4 = new Person("Irina", 21, Sex.FEMALE);
        Person person5 = new Person("Irina", 22, Sex.FEMALE);
        Pet pet1 = new Pet("Barsik", person1, 5);
        Pet pet2 = new Pet("Murzik", person2, 3);
        Pet pet3 = new Pet("Sharik", person3, 7);
        Pet pet4 = new Pet("Bobik", person4, 8);
        Pet pet5 = new Pet("Bobik", person5, 7);

        addPetToMap(pet1);
        addPetToMap(pet2);
        addPetToMap(pet3);
        addPetToMap(pet4);
        addPetToMap(pet5);
        Thread.sleep(200);
        System.out.println("Выводим заполненную коллекцию питомцев");
        printPetsMap();
        System.out.println("-------------------------------");
        Set<Integer> id = new LinkedHashSet<>(Pet.pets.keySet());
        Integer randomElementId = 0;
        for(Integer anyElementId: id) {
            randomElementId = anyElementId;
            break;
        }
        Thread.sleep(200);
        changePet(randomElementId, "Murka", person1, 4);
        Thread.sleep(200);
        System.out.println("Выводим коллекцию питомцев после замены одного элемента");
        printPetsMap();

    }
}
