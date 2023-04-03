package TableExample;

import java.util.ArrayList;
import java.util.List;

public class Table extends Element {

    private final List<String> columnNames;
    private final String tableId;

    private static final By ITEM_ROW_RELATIVE_LOC = By.xpath("//tr[@oid]");

    private final String CELL_XPATH_TEMPLATE = "//tbody//tr[%d]//td[count(//table[@id='%s']//thead//th[contains(., '%s')]//preceding-sibling::th) + 1]";
    private final String COLUMN_XPATH_TEMPLATE = "//tbody//td[count(//table[@id='%s']//thead//th[contains(., '%s')]//preceding-sibling::th) + 1]";
    private final String AREA_EXPAND_XPATH = "//ancestor::div[contains(@class, 'frame-container')]//button[contains(@class, 'iv-toggle-frame')]";

    protected Table(By loc, String nameOf, ElementState stateOf) {
        super(loc, nameOf, stateOf);
        openTableAreaIfNeed();
        tableId = this.getAttribute(ATTRIBUTE_ID);
        List<ILabel> headerLabels = this.findChildElements(By.xpath("//thead//th"), "Header",
                ILabel.class,ElementState.EXISTS_IN_ANY_STATE, ElementsCount.MORE_THEN_ZERO);
        columnNames = new ArrayList<>();
        headerLabels.forEach(header -> columnNames.add(header.getText()));
    }

    @Override
    protected String getElementType() {
        return "Table";
    }

    public ILabel getCell(int rowNumber, String columnName) {
        if(!isColumnExist(columnName)) {
            throw new IllegalArgumentException(String.format("Cannot find column %s in table %s", columnName, this.getName()));
        }
        return this.findChildElement(By.xpath(String.format(CELL_XPATH_TEMPLATE, rowNumber, tableId, columnName)), "Value in cel",
                ILabel.class, ElementState.EXISTS_IN_ANY_STATE);
    }

    public List<ILabel> getColumn(String columnName) {
        if(!isColumnExist(columnName)) {
            throw new IllegalArgumentException(String.format("Cannot find column %s in table %s", columnName, this.getName()));
        }
        return this.findChildElements(By.xpath(String.format(COLUMN_XPATH_TEMPLATE, tableId, columnName)), "Value in column" + columnName,
                ILabel.class, ElementState.EXISTS_IN_ANY_STATE, ElementsCount.MORE_THEN_ZERO
        );
    }

    public List<ILabel> getListItems() {
        return this
                .findChildElements(ITEM_ROW_RELATIVE_LOC, "Item row", ILabel.class, ElementState.EXISTS_IN_ANY_STATE);
    }

    private void openTableAreaIfNeed() {
        IButton btnOpenArea = this.findChildElement(By.xpath(AREA_EXPAND_XPATH), IButton.class);
        if(btnOpenArea.getAttribute(ATTRIBUTE_ARIA_EXPANDED).equalsIgnoreCase(FALSE)) {
            btnOpenArea.click();
        }

    }

    private boolean isColumnExist(String column) {
        return columnNames.contains(column);
    }
}

