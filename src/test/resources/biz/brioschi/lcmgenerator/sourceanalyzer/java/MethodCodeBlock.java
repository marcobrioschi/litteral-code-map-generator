import a.b.c;
import com.sun.jdi.InterfaceType;

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

    public void hiddenMethod() {
        // TODO add it to the test
//        InterfaceType v = new InterfaceType() {
//
//            @Override
//            public void method1() {
//                return;
//            }
//
//            @Override
//            // @LiterateMapBlock()
//            public int method2() {
//                return 10;
//            }
//
//        };
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
        public void doSomething(Boolean value, String ... strings) {
            return;
        }

    }

}