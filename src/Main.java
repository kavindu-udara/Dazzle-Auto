
import com.formdev.flatlaf.IntelliJTheme;
import views.SplashWindow;

public class Main {

    public static void main(String[] args) {

        IntelliJTheme.setup(SplashWindow.class.getResourceAsStream(
                "/resources/themes/arc-theme.theme.json"));

        // show splash window
        new SplashWindow().setVisible(true);
    }
}
