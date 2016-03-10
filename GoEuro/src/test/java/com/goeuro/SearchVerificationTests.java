package test.java.com.goeuro;

import main.java.com.goeuro.FoundResultsPage;
import main.java.com.goeuro.MainSearchPage;
import main.java.helpers.PrintOptions;
import main.java.report.Logging;
import test.java.com.goeuro.configuration.GeneralTestPreparation;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by xsoroka on 3/6/2016.
 * Class with stores test for technical assignment:
 GoEuro lists the travel results sorted by cheapest, fastest or by time (departure/arrival). Please
 create an automated test that will make a search (Eg.: Berlin Prague)
 and will verify that the
 sorting by price is correct. You are free to choose the tool and language you want, except for
 record and play tools.

 Test was realized with following requirements and assumptions
   - search is done for one-way - by default
   - search is done for  - default "cheapest" tab found results option and on opened "train" tab option
   - search is done without selecting airbnb or booking.com options
   - search is done for tomorrow date - as default
   - search is done with "no BahnCard" option - as default
   - search is performed for 1 person - as default

 No custom reports were implemented
*/

public class SearchVerificationTests extends GeneralTestPreparation
{
    @Test
    public void searchForTravelTest () {
        MainSearchPage page = PageFactory.initElements(driver, MainSearchPage.class);
        Logging.logResult("Opened main page");
        FoundResultsPage pageWithResults = page.findAvailableTrips("Berlin, Germany", "Prague, Czech Republic");
        Logging.logResult("Opened page with results");
        Assert.assertTrue(pageWithResults.isPricesSortedAcs("train"));
        Logging.logResult("Opened page with results");
        Logging.logResult("Assertions is done for train results table");
        pageWithResults.switchTransport("bus");
        Assert.assertTrue(pageWithResults.isPricesSortedAcs("bus"));
        Logging.logResult("Assertions is done for bus results table");
        PrintOptions.print("test 2 done");
    }
}
