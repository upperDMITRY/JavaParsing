import org.testng.annotations.Test;

import java.util.Map;

public class TestNGTest {

    @Test(dataProvider = "OneInvocationOneStringMethodParameterProvider", dataProviderClass = SimpleDataProvider.class)
    public void test1(String oneStringParameter) {
        System.out.println(oneStringParameter);
    }

    @Test(dataProvider = "OneInvocationTwoStringMethodParameterProvider", dataProviderClass = SimpleDataProvider.class)
    public void test2(String oneStringParameter, String secondStringParameter) {
        System.out.println(oneStringParameter + '\n' + secondStringParameter);
    }

    @Test(dataProvider = "ForeInvocationTwoStringMethodParameterProvider", dataProviderClass = SimpleDataProvider.class)
    public void test3(String oneStringParameter, String secondStringParameter) {
        System.out.println(oneStringParameter + "; " + secondStringParameter);
    }

    @Test(dataProvider = "LoginPageDataProvider", dataProviderClass = ExcelDataProvider.class)
    public void test(Map<String, String> inputMatrix){
        System.out.println("Running test" + inputMatrix.get("TestCase"));
        System.out.println("Username" + inputMatrix.get("username"));
        System.out.println("Password" + inputMatrix.get("password"));
    }
}
