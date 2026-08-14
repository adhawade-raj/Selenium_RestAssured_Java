package latest.changes;

import org.testng.annotations.Test;
import pages.Code01_Helper;
import utilities.Utils;

public class Code01_IterateTable {

Utils launch = new Utils();
    Code01_Helper helper = new Code01_Helper();

    @Test
    public void iterate01() {
        launch.launchDriver();
        helper.getAllTableData();
    }
}
