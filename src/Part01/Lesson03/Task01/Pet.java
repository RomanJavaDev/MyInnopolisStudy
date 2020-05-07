package Part01.Lesson03.Task01;

import java.util.*;

/**
 * Pet.
 * Разработка программы – картотеки домашних животных.
 * У каждого животного есть уникальный идентификационный номер,
 * кличка, хозяин (объект класс Person с полями – имя, возраст, пол), вес.
 * <p>
 * Реализовать:
 * метод добавления животного в общий список (учесть, что добавление дубликатов должно приводить к исключительной ситуации)
 * поиск животного по его кличке (поиск должен быть эффективным)
 * изменение данных животного по его идентификатору
 * вывод на экран списка животных в отсортированном порядке. Поля для сортировки –  хозяин, кличка животного, вес.
 *
 * @author Roman Khokhlov
 */
public class Pet {

    static Map<Integer, Pet> pets = new TreeMap();
    String name;
    Person person;
    int weight;

    public Pet(String name, Person person, int weight) {
        this.name = name;
        this.person = person;
        this.weight = weight;
    }

    public static Integer getUniqPetId() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        return list.get(0);
    }

    public static void addPetToMap(Pet pet) {
        for (Map.Entry<Integer, Pet> p : pets.entrySet()) {
            if (p.equals(pet)) throw new UnsupportedOperationException();
        }
        pets.put(getUniqPetId(), pet);
    }

    public static void changePet(Integer id, String name, Person person, int weight) {
        for (Map.Entry<Integer, Pet> p : pets.entrySet()) {
            if (p.getKey() == id) {

                p.setValue(new Pet(name, person, weight));
            }
        }
    }

    public static void printPetsMap() {
        PetComparator myPetComparator = new PetComparator();
        List<Pet> list = new ArrayList<>();
        for (Map.Entry<Integer, Pet> p : pets.entrySet()) {
            list.add(p.getValue());
        }
        list.sort(myPetComparator);
        for (Pet p : list) {
            System.out.println(p);
        }

    }

    public Pet getPetByName(String name) throws NoSuchElementException {
        for (Map.Entry<Integer, Pet> p : pets.entrySet()) {
            if (p.getValue().name.equals(name)) {
                return p.getValue();
            }
            throw new NoSuchElementException();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", person=" + person +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return  weight == pet.weight &&
                Objects.equals(name, pet.name) &&
                Objects.equals(person, pet.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, person, weight);
    }

}
