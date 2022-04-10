package tju.steel.zjx;



public class Test {

    public static void main(String[] args) {
        Student student = new Student();
        student.setName("a");
        student.setAge(1);
        int a = 10;
        changeName(student, a);
        System.out.println(student.getName());
        System.out.println(student.getAge());
        System.out.println(a);
    }

    private static void changeName(Student student, int a){
        student.setName("xiaoliu");
        student.setAge(18);
        a = 5;

    }
}
