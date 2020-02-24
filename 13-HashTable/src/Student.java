import java.util.HashMap;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;

public class Student {
    private String name;
    private Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) &&
                Objects.equals(age, student.age);
    }

//    @Override
//    public int hashCode() {
//        int B = 21; // 进制
//        int hash = 0;
//
//        hash = hash * B + age;
//        hash = hash * B + name.hashCode();
//
//        return hash;
//    }


    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public static void main(String[] args) {
        Student stu1 = new Student("lxli", 22);
        Student stu2 = new Student("lxli", 22);

        System.out.println(stu1.hashCode());
        System.out.println(stu2.hashCode());

        String str1 = new String("hello");
        String str2 = new String("hello");
        System.out.println(str1 == str2);


        // HashMap的键为啥不需要实现Comparable接口
    }
}
