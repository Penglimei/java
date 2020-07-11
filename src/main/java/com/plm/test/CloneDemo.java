package com.plm.test;

import java.io.*;

/**
 *  浅拷贝、深拷贝：
 *      浅拷贝：
 *          1、对基本数据类型的成员变量进行值传递，对其中一个对象成员变量的值进行修改，
 *          不会影响另一个对象成员变量的值；
 *          2、对引用数据类型(String)成员变量，进行引用传递，将内存地址复制给新的对象，
 *          在一个对象中修改成员变量的值，会影响拷贝对象的值。
 *
 *          实现方式：Object类的 clone()方法
 *                      实现 Cloneable 接口则可以使用java.lang.Object 的clone()方法，
 *                      否则会抛出CloneNotSupportedException 异常.
 *
 *                   java.lang.object规范中对clone方法的约定：
 *                   1、对任何的对象x，都有x.clone() !=x 因为克隆对象与原对象不是同一个对象;
 *                   2、对任何的对象x，都有x.clone().getClass()==x.getClass();
 *                   3、如果对象x的equals()方法定义恰当，那么x.clone().equals(x)应该成立。
 *
 *                  谨慎的使用clone方法，或者尽量避免使用。
 *
 *      深拷贝：
 *          1、对基本数据类型的成员变量进行值传递，对其中一个对象成员变量的值进行修改，
 *          不会影响另一个对象成员变量的值；
 *          2、对引用数据类型的成员变量，不仅要创建一个新的对象，而且复制其内容，
 *          在一个对象中修改成员变量的值，不会影响拷贝对象的值。
 *
 *          实现方式：
 *              1、实现 Cloneable 接口并重写 Object类的clone()方法；
 *              2、实现 Serializable 接口，通过对象序列化、反序列化实现克隆。
 *
 *
 */

// 浅拷贝
class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
class Teacher implements Cloneable {

    private String name;
    private int age;
    private Student student;

    public Teacher(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Teacher(String name, int age, Student student) {
        this.name = name;
        this.age = age;
        this.student = student;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", student=" + student +
                '}';
    }
}

// 深拷贝
// 1、实现 Cloneable 接口并重写 Object类的clone()方法
class Student1 implements Cloneable {
    private String name;
    private int age;

    public Student1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
class Teacher1 implements Cloneable {
    private String name;
    private int age;
    private Student1 student1;

    public Teacher1(String name, int age, Student1 student1) {
        this.name = name;
        this.age = age;
        this.student1 = student1;
    }

    // 覆盖
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Teacher1 temp = null;
        temp = (Teacher1)super.clone();
        temp.student1 = (Student1) student1.clone();
        return temp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student1 getStudent1() {
        return student1;
    }

    public void setStudent1(Student1 student1) {
        this.student1 = student1;
    }

    @Override
    public String toString() {
        return "Teacher1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", student1=" + student1 +
                '}';
    }
}

// 深拷贝
// 2、实现 Serializable 接口，通过对象序列化、反序列化实现克隆
class Student2 implements Serializable {
    private String name;
    private int age;

    public Student2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Teacher2 implements Serializable {
    private String name;
    private int age;
    private Student2 student2;

    public Teacher2(String name, int age, Student2 student2) {
        this.name = name;
        this.age = age;
        this.student2 = student2;
    }

    // 深克隆
    public Object deepClone() throws IOException,ClassNotFoundException {
        // 序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        // 反序列化
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student2 getStudent2() {
        return student2;
    }

    public void setStudent2(Student2 student2) {
        this.student2 = student2;
    }

    @Override
    public String toString() {
        return "Teacher2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", student2=" + student2 +
                '}';
    }
}

public class CloneDemo {
    public static void main(String[] args) {
        System.out.println("==============浅拷贝：基本数据类型==============");
        Teacher origin = new Teacher("t1",12);
        System.out.println("克隆前origin对象的信息 ： "+origin.toString());
        try {
            Teacher clone = (Teacher) origin.clone();
            clone.setName("t2");
            clone.setAge(21);
            System.out.println("clone对象修改信息后origin对象的信息 ： "+origin.toString());
            System.out.println("修改后的克隆对象clone的信息 ： "+clone.toString());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("==============浅拷贝：引用数据类型==============");
        Student student1 = new Student("s1",11);
        Teacher teacher1 = new Teacher("t1",11,student1);
        System.out.println("克隆前teacher1对象的信息 ："+teacher1.toString());
        try {
            Teacher teacher2 = (Teacher)teacher1.clone();
            System.out.println("克隆前后teacher1和teacher2的引用的student1对象是否相同 ："
                    +(teacher1.getStudent().getClass() == teacher2.getStudent().getClass()));
            student1.setName("s2");
            student1.setAge(12);
            System.out.println("修改teacher1对象的引用student1后teacher1的信息 ："+teacher1.toString());
            System.out.println("修改teacher1对象的引用student1后teacher2的信息 ："+teacher2.toString());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("==============深拷贝：引用数据类型(Cloneable实现)==============");
        Student1 student11 = new Student1("s1",11);
        Teacher1 teacher11 = new Teacher1("t1",11,student11);
        System.out.println("克隆前teacher11对象的信息 ："+teacher11.toString());
        try {
            Teacher1 teacher12 = (Teacher1) teacher11.clone();
            teacher12.getStudent1().setName("s2");
            teacher12.getStudent1().setAge(12);
            System.out.println("teacher12修改引用对象student11信息后teacher11的信息 ："+teacher11.toString());
            System.out.println("teacher12修改引用对象student11信息后teacher12的信息 ："+teacher12.toString());

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("==============深拷贝：引用数据类型(Serializable实现)==============");
        Student2 student21 = new Student2("s1",11);
        Teacher2 teacher21 = new Teacher2("t1",11,student21);
        System.out.println("克隆前teacher21对象的信息 ："+teacher21.toString());

        try {
            Teacher2 teacher22 = (Teacher2)teacher21.deepClone();
            teacher22.getStudent2().setName("s2");
            teacher22.getStudent2().setAge(12);
            System.out.println("teacher22修改引用对象student21信息后teacher21的信息 ："+teacher21.toString());
            System.out.println("teacher22修改引用对象student21信息后teacher22的信息 ："+teacher22.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
