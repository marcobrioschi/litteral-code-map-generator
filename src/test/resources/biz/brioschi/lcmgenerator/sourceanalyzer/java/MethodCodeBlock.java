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