package Part01.Lesson04.Task01;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * TextFileReader.
 * Написать программу, читающую текстовый файл. Программа должна составлять отсортированный по алфавиту список слов,
 * найденных в файле и сохранять его в файл-результат. Найденные слова не должны повторяться, регистр не должен учитываться.
 * Одно слово в разных падежах – это разные слова.
 *
 * @author Roman Khokhlov
 */
public class TextFileReader {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        try (BufferedReader buf = new BufferedReader(new FileReader("C:\\Users\\Roman\\Desktop\\Test.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Roman\\Desktop\\Test2.txt"))) {
            Set<String> words = new TreeSet<String>();
            while (buf.ready()) {
                String[] array = buf.readLine().split(" ");

                for (int i = 0; i < array.length; i++) {
                    words.add(array[i]);
                }
            }
            System.out.println(words.toString());

            for (String str : words) {

                bw.write(str + " ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
