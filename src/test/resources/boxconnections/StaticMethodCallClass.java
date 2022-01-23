public class StaticMethodCallClass {

    private MethodReference testVar1 = GenericClassOne.firstStaticMethod(1, "One");

    public void classMethod(int par1) {
        GenericClassTwo.secondStaticMethod(2, "Two");
        // List.get(1).fakeInvocation("3-4");
        // Class.OtherClass.otherMethod(5);
    }

}