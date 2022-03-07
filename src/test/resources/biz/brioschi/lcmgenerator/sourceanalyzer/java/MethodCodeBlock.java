import a.b.c;

public class TestClass {

    private String string;

    /*
    * @LiterateMapBlock()
    */
    public void aCostructor(Integer integer) {
        this.string = integer.toString();
    }

    // @LiterateMapBlock()
    public int aMethod(Integer integer) {
        return integer + 1;
    }

    public interface TestInterface {
        /*
        * @LiterateMapBlock()
        */
        public void interfaceMethod1(String a1, String b1);

        // @LiterateMapBlock()
        public List<String> interfaceMethod2(Integer a2, boolean b2);
    }

    private enum TestEnum {
        a, z;

        /*
        * @LiterateMapBlock()
        * */
        public String getEnumStringValue() {
            return null;
        }

        // @LiterateMapBlock()
        public void doSomething() {
            return;
        }

    }

}