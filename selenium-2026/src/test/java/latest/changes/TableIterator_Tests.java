package latest.changes;

import org.testng.annotations.Test;
import pages.Helper;
import utilities.Utils;

public class TableIterator_Tests {

    Utils launch = new Utils();
    Helper helper = new Helper();

    @Test(enabled=false)
    public void testGetAllTableData() {
        launch.launchDriver();
        helper.getAllTableData();
    }

    @Test(enabled=false)
    public void testGetAllTableData_OneRow() {
        launch.launchDriver();
        helper.getAllTableData_OneRow();
    }

    @Test
    public void testGetAllTableData_SpecificData() {
        launch.launchDriver();
        helper.getAllTableData_SpecificData();
    }

    @Test
    public void testGetAllTableData_NamesFromSpecifcCity() {
        launch.launchDriver();
        helper.getAllTableData_FromSpecificLocation();
    }
}
