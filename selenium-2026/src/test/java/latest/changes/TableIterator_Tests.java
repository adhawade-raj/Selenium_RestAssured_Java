package latest.changes;

import org.testng.annotations.Test;
import pages.Helper;
import utilities.Utils;

public class TableIterator_Tests {

    Utils launch = new Utils();
    Helper helper = new Helper();

    @Test(enabled=true)
    public void testGetAllTableData_01() {
        launch.launchDriver();
        helper.getAllTableData();
    }

    @Test(enabled=false)
    public void testGetAllTableData_OneRow_02() {
        launch.launchDriver();
        helper.getAllTableData_OneRow();
    }

    @Test
    public void testGetAllTableData_SpecificData_03() {
        launch.launchDriver();
        helper.getAllTableData_SpecificData();
    }

    @Test
    public void testGetAllTableData_NamesFromSpecificCity_04() {
        launch.launchDriver();
        helper.getAllTableData_FromSpecificLocation();
    }

    @Test
    public void testGetAllTableData_PersonWithHighestAmount_05() {
        launch.launchDriver();
        helper.getAllTableData_PersonWithHighestAmount();
    }
}
