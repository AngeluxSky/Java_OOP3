package org.example;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

class Student {
    private String name;
    private int id;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }
    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
class StudyGroup implements Iterable<Student> {
    private List<Student> students = new ArrayList<>();
    public void addStudent(Student student) {
        students.add(student);
    }
    public List<Student> getStudents() {
        return students;
    }
    private class StudyGroupIterator implements Iterator<Student> {
        private int currentIndex = 0;
        @Override
        public boolean hasNext() {
            return currentIndex < students.size();
        }
        @Override
        public Student next() {
            return students.get(currentIndex++);
        }
        @Override
        public void remove() {
            students.remove(currentIndex - 1);
            currentIndex--;
        }
    }
    @Override
    public Iterator<Student> iterator() {
        return new StudyGroupIterator();
    }
}
class StreamComparator implements Comparator<StudyGroup> {
    @Override
    public int compare(StudyGroup group1, StudyGroup group2) {
        return Integer.compare(group1.getStudents().size(), group2.getStudents().size());
    }
}
class StreamService {
    public void sortStudyGroups(List<StudyGroup> studyGroups) {
        studyGroups.sort(new StreamComparator());
    }
}
class Controller {
    private StreamService streamService;
    public Controller(StreamService streamService) {
        this.streamService = streamService;
    }
    public void sortStudyGroups(List<StudyGroup> studyGroups) {
        streamService.sortStudyGroups(studyGroups);
    }
}
public class Main {
    public static void main(String[] args) {
        Student student1 = new Student("Иванов", 1);
        Student student2 = new Student("Петров", 2);
        Student student3 = new Student("Сидоров", 3);

        StudyGroup group = new StudyGroup();
        group.addStudent(student1);
        group.addStudent(student2);
        group.addStudent(student3);

        Iterator<Student> iterator = group.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            System.out.println(student);
        }
        iterator = group.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getId() == 2) {
                iterator.remove();
                System.out.println("Студент с ID 2 был удален из учебной группы.");
            }
        }
        for (Student student : group.getStudents()) {
            System.out.println(student);
        }
    }
}