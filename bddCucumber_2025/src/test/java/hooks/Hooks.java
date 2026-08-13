package hooks;

import io.cucumber.java.*;
import org.junit.Ignore;

public class Hooks {

    @Before(order=1)
    public void beforeSetup(Scenario sc){
        System.out.println("-----Before Setup-----");
        System.out.println("*******Executing "+sc.getName()+" Scenario*******");
    }

    @Before(order=2)
    public void beforeSetup2(){
        System.out.println("-----Before Setup 2-----");
    }

//    @BeforeStep
//    public void captureScreenshotBefore(){
//        System.out.println("-----Capturing Screenshot-----");
//    }

    @After(order=1)
    public void afterSetup1(){
        System.out.println("========================== After Setup ==========================");
    }
//    @AfterStep
//    public void captureScreenshotAfter(){
//        System.out.println("-----Capturing Screenshot Completed-----");
//    }

    @After(order=2)
    public void afterSetup2(){
        System.out.println("========================== After Setup 2 ==========================");
    }

@Before("@Random")
    public void beforeSmoke(){
        System.out.println("Executing before SmokeTag");
}
    @After("@Random")
    public void afterSmoke(){
        System.out.println("Executing before SmokeTag");
    }

}
