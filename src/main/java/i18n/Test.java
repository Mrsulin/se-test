package i18n;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public class Test {
    static Locale cnLocal = new Locale("zh", "CN");
    static Locale usLocal = new Locale("zh", "US");

    public static void main(String[] args) {
        System.out.println();
        ResourceBundle cnLocalBundle = ResourceBundle.getBundle("sulin", cnLocal);
        ResourceBundle usLocalBundle = ResourceBundle.getBundle("sulin", usLocal);
        String name = getStringValue("name", cnLocalBundle);
        String name2 = getStringValue("name", usLocalBundle);
        System.out.println(name);
        System.out.println(name2);

        String work =  getStringValue("work", cnLocalBundle);
        String work2 =  getStringValue("work", usLocalBundle);
        System.out.println(work);
        System.out.println(work2);
    }

    public static String getStringValue(String name, ResourceBundle bundle) {
        return new String(bundle.getString(name).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }
}
