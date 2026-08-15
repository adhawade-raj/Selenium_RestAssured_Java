package latest.changes;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.CalendarHandling;
import pages.IteratorHelper;
import utilities.Utils;

public class CalendarHandling_Tests {

    Utils launch = new Utils();
    CalendarHandling calendarHandling = new CalendarHandling();

    @BeforeTest
    public void setup() {
        launch.openUrl("https://www.ixigo.com/flights");
    }

    @Test
    public void testSelectCurrentDate_01() throws InterruptedException {
        calendarHandling.selectDate();
    }

    @Test
    public void selectMonth_02() throws InterruptedException {
        calendarHandling.selectMonth();
    }

    @Test
    public void datePicker_03() throws InterruptedException {
        calendarHandling.datePicker("October", "2026", "21");
    }
}
