import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListeners implements ITestListener {

    public void onTestStart(ITestResult result){
        System.out.println(">>>>>Test Started");
    }

    public void onTestSuccess(ITestResult result){
        System.out.println(">>>>>Test Success");
    }
    public void onTestFailure(ITestResult result){
        System.out.println(result.getName()+"Got failed");
        System.out.println(">>>>>Test Failure");
    }
}
