package Part01.Lesson06.Task01;

import java.io.Serializable;

/**
 * Course.
 *
 * @author Roman Khokhlov
 */
public class Course {

    private String name;
    public Course(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return '\'' + name + '\'' +
                '}';
    }
}
