package main.java.report;

import org.testng.Reporter;

/**
 * Created by xsoroka on 3/10/2016.
 */
public class Logging {
    public static void logResult(String result){
        Reporter.log(result + "<br>");
    }
}
