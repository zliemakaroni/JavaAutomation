package TableExample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderDetailsTab extends Form {

    private final Table tblItems = TabsUtil.declareCustomInFrame(Table.class, By.xpath("//table[contains(@id,'proxyItems_x_grdItems_grd')]"), "Items table");

    public OrderDetailsTab() {
        super(By.xpath("//*[@id='body_x_tabc_containerTab_partprxinfo' and @aria-selected='true']"), "Order Details");
    }

    public List<String> getValueListItemsTableColumn(String column) {
        List<String> list = new ArrayList<>();
        tblItems.getColumn(column).forEach(value -> list.add(value.getText()));
        return list;
    }
}