package Part01.Lesson07.Task01;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * CompilerAPITest.
 *
 * @author Roman Khokhlov
 */
public class CompilerAPITest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, InterruptedException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        File someClass1 = new File("C:\\Users\\Roman\\Desktop\\SomeClass1.java");
        FileWriter writer = new FileWriter(someClass1);
        StringBuffer sb = new StringBuffer();

        while (true) {
            String s = bf.readLine(); // Вводим тело КЛАССА SomeClass1
            // Как пример:
            // public class SomeClass1 {
            // public void doWork() {
            //    System.out.println("Вызван метод doWork");
            //  }
            // }
            if (s.isEmpty()) {
                System.out.println("Вы ввели пустую строку...");
                break;
            } else {
                sb.append(s);
            }
        }

        if (sb != null) {
            writer.write(sb.toString());  // Записываем строки, введенные с консоли в новый файл SomeClass1.java
        }

        Thread.sleep(500);
        bf.close();
        writer.close();
        Thread.sleep(500);

        JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager sjfm = jc.getStandardFileManager(null, null, null);
        File javaFile = new File("C:\\Users\\Roman\\Desktop\\SomeClass1.java");
        Iterable fileObjects = sjfm.getJavaFileObjects(javaFile);
        String[] options = new String[]{"-d", "c:\\javafiles\\bin"};
        jc.getTask(null, null, null, Arrays.asList(options), null, fileObjects).call();
        sjfm.close();
        System.out.println("Class has been successfully compiled");

        URL[] urls = new URL[]{new URL("file://c:/javafiles/bin/")};
        URLClassLoader ucl = new URLClassLoader(urls);
        Class clazz = ucl.loadClass("SomeClass1");
        System.out.println("Class has been successfully loaded");

        Method method = clazz.getDeclaredMethod("doWork", null);
        Object object = clazz.newInstance();
        method.invoke(object, null);
    }
}
