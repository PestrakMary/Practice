package practice;

import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        Undergraduate und1 = new Undergraduate("Fedorov", "fedorov12345", "fedorov@mail.ru", "Kalinin");
        Undergraduate und2 = new Undergraduate("Sidorov", "sidorov12345", "sidorov@mail.ru", "Markov");
        Postgraduate post1 = new Postgraduate("Lavrov", "lavrov123", "lavrov@mail.ru", "Kalinin");
        Postgraduate post2 = new Postgraduate("Ivanov", "ivanov123", "ivanon@yandex.ru", "Kalinin");
        Postgraduate post3 = new Postgraduate("Petrov", "petrov12345", "petrov@yandex.ru", "Markov");
        Set<Student> studentSet = new TreeSet<Student> ();
        studentSet.add(und1);
        studentSet.add(und2);
        studentSet.add(post1);
        studentSet.add(post2);
        studentSet.add(post3);

        Course course = new Course("Programming", studentSet);
        Notifier notifier = new Notifier(course.getPostgraduates("Markov"));
        notifier.doNotifyAll("Lecture in the audience 521");

    }
}
