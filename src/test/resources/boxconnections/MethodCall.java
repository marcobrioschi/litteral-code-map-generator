public class StaticMethodCallClass {

    private MethodReference testVar1 = GenericClassOne.firstStaticMethod(1, "One");

    public void classMethod(int par1) {
        GenericClassTwo.secondStaticMethod(2, "Two");
        for (int i = 1; i > 100; --i) {
            doSomething();
            List.get(1).fakeInvocation("3-4");
        }
        Class.OtherClass.otherMethod(5);
    }

    class subClass extends Base1 implements Interface1 {
        private void method(Type1 param1, Type param2) {
            internal(param1);
            objext.external(param2);
        }
    }

    public enum ImAnEnum {

        A, B, C;

        public String sendEmailToEnum() {
            EmailService.send("mr.smitt@example.com");
        }

    }

    public interface ImAnInterface {
        public void doNothing();
    }

}