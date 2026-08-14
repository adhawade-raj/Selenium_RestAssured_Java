package latest.changes;

import org.testng.annotations.Test;
import pages.Code01_Helper;
import pages.Code02_Helper;
import utilities.Utils;

public class Code02_IterateOneRow {

    Utils launch = new Utils();
    Code02_Helper helper = new Code02_Helper();

    @Test
    public void iterate02() {
        launch.launchDriver();
        helper.getAllTableData();

    }
}
