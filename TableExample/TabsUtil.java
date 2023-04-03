package TableExample;

public class TabsUtil {

    private TabsUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static <T extends Element> T declareCustomInFrame(Class<T> type, By xpath, String name) {
        getBrowser().getDriver().switchTo().frame(0);
        Element elem = getElementFactory().getCustomElement(type, xpath, name);
        getBrowser().getDriver().switchTo().defaultContent();
        return type.cast(elem);
    }
}
