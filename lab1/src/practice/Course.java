package practice;

import java.util.Set;
import java.util.TreeSet;

public class Course {
    Set<Student> students;
    String name;

    public Course(String name, Set<Student> students) {
        this.name = name;
        this.students = students;
    }

    public Set<Postgraduate> getPostgraduates(String nameOfSupervisor) {
        Postgraduate postgraduate = new Postgraduate("", "", "", "");
        Set<Postgraduate> postgraduates = new TreeSet<>();
        for (Student student : this.students) {
            if (student.getClass().isInstance(postgraduate)) {
                if (((Postgraduate) student).getSupervisor().getName().equals(nameOfSupervisor)) {
                    postgraduates.add((Postgraduate) student);
                }
            }
        }
        return postgraduates;
    }

}
