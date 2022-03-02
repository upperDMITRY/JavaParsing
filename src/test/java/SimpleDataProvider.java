import org.testng.annotations.DataProvider;

public class SimpleDataProvider {

    @DataProvider(name = "OneInvocationOneStringMethodParameterProvider")
    public static Object[][] data1(){
        return new Object[][]{
                new Object[]{"First string parameter"}};
    }

    @DataProvider(name = "OneInvocationTwoStringMethodParameterProvider")
    public static Object[][] data2(){
        return new Object[][]{
                new Object[]{"First string parameter", "Second string parameter"}};
    }

    @DataProvider(name = "ForeInvocationTwoStringMethodParameterProvider")
    public static Object[][] data3(){
        return new Object[][]{
                new Object[]{"Invocation1 1parameter", "Invocation1 2parameter"},
                new Object[]{"Invocation2 1parameter", "Invocation2 2parameter"},
                new Object[]{"Invocation3 1parameter", "Invocation3 2parameter"},
                new Object[]{"Invocation4 1parameter", "Invocation4 2parameter"}};
    }
}
