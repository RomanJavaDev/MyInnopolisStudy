package Part01.Lesson04.Task02;

import java.util.Random;

/**
 * TextFileGenerator.
 *
 * Создать генератор текстовых файлов, работающий по следующим правилам:
 *
 * Предложение состоит из 1<=n1<=15 слов. В предложении после произвольных слов могут находиться запятые.
 * Слово состоит из 1<=n2<=15 латинских букв
 * Слова разделены одним пробелом
 * Предложение начинается с заглавной буквы
 * Предложение заканчивается (.|!|?)+" "
 * Текст состоит из абзацев. в одном абзаце 1<=n3<=20 предложений. В конце абзаца стоит разрыв строки и перенос каретки.
 * Есть массив слов 1<=n4<=1000. Есть вероятность probability вхождения одного из слов этого массива в следующее предложение (1/probability).
 * Необходимо написать метод getFiles(String path, int n, int size, String[] words, int probability),
 * который создаст n файлов размером size в каталоге path. words - массив слов, probability - вероятность.
 *
 * @author Roman Khokhlov
 */
public class TextFileGenerator {
    public static String wordGenerator() {
        Random random = new Random();
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = random.nextInt(15) + 1;


        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }

    public static String firstWordGenerator() {
        Random random = new Random();
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int letterA = 65; // letter 'A'
        int letterZ = 90; // letter 'Z'
        int targetStringLength = random.nextInt(15) + 1;


        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            if (i == 0) {
                int randomLimitedInt = letterA + (int) (random.nextFloat() * (letterZ - letterA + 1));
                buffer.append((char) randomLimitedInt);
            } else {
                int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
                buffer.append((char) randomLimitedInt);
            }
        }
        String generatedString = buffer.toString();

        return generatedString;
    }

    public static String getRandomPunctuation() {
        Random random = new Random();
        int numPun = random.nextInt(3);

        String[] end = {". ", "! ", "? "};

        return end[numPun];
    }

    public static String getSpaceOrComma() {
        Random random = new Random();
        int numSmth = random.nextInt(2);

        String[] smth = {", ", " "};

        return smth[numSmth];
    }

    public static String sentenceGen() {

        Random random = new Random();


        int targetSentenceLength = random.nextInt(15) + 1;


        StringBuilder buffer = new StringBuilder(targetSentenceLength);
        for (int i = 0; i < targetSentenceLength; i++) {
            if (i == 0) {
                if (i == targetSentenceLength - 1) {
                    buffer.append(firstWordGenerator() + getRandomPunctuation());
                } else
                    buffer.append(firstWordGenerator() + getSpaceOrComma());
            } else if ((i == targetSentenceLength - 1)) {
                buffer.append(wordGenerator() + getRandomPunctuation());
            } else
                buffer.append(wordGenerator() + getSpaceOrComma());
        }
        String generatedString = buffer.toString();

        return generatedString;

    }

    public static StringBuilder paragraphGen() {
        Random random = new Random();
        int paragraphLength = random.nextInt(20) + 1;
        StringBuilder bufferPar = new StringBuilder(paragraphLength);
        for (int p = 0; p < paragraphLength; p++) {
            bufferPar.append(sentenceGen());
        }
        return bufferPar;
    }

    public static void main(String[] args) {
        int incr = 0;
        for (int b = 0; b <= 1000; b++) {
            if (incr % 20 == 0)
                incr++;
            System.out.println(paragraphGen());
        }
    }

    public void getFiles(String path, int n, int size, String[] words, int probability) {

    }

}
