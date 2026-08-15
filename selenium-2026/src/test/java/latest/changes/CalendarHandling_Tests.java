package latest.changes;

import org.testng.annotations.BeforeTest;
import pages.IteratorHelper;
import utilities.Utils;

public class CalendarHandling_Tests {

    Utils launch = new Utils();
    IteratorHelper helper = new IteratorHelper();

    @BeforeTest
    public void setup() {
        launch.openUrl("https://www.ixigo.com/flights?&utm_source=Google_Search&utm_medium=paid_search_google&utm_campaign=PMax_Flight_troas_Apr26_NewUser&gad_source=1&gad_campaignid=23787983735&gbraid=0AAAAAC5edWBWB7YdFTClOYG2yZ5E82ycF&gclid=Cj0KCQjw-frTBhCvARIsADv4XY6EmbijT5a-3D-f_rm7OoYe7tDzyzL2eXRYk4TeEs1lmgqB15kACycaAsSWEALw_wcB");
    }
}
