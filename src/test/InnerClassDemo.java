package test;

public class InnerClassDemo {

    public static void main(String[] args) {
        A obj = new A();
//        A.B obj1 = obj.new B();
        obj.callGreet();
    }
}

class A{
    public void callGreet(){
        B obj = new B();
        obj.greet();
    }

    class B{
        private void greet(){
            System.out.println("Hello from Inner class B");
        }
    }
}
