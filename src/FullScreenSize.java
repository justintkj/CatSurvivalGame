import java.awt.*;

public class FullScreenSize {
    public static int width;
    public static int height;

    public static void update() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int)screenSize.getWidth();
        height = (int)screenSize.getHeight();
    }
}
