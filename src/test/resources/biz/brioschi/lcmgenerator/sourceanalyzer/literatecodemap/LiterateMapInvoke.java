import java.util.noclass;

/* This is the first invocation: @LiterateMapInvoke(1, "ClassA", "MethodA()") */
public class TestClass {

    public TestClass() {
        this.avar = avalue; // @LiterateMapInvoke(2, "ClassB", "MethodB()") the value is taken from ClassB
    }

    // This method delegate @LiterateMapInvoke(3, 'ClassC', 'MethodC()')
    public void doSomething(int number, String string) {
        number = string; /* But not this method @LiterateMapInvoke(4, 'ClassD', 'MethodD()') */
    }

}