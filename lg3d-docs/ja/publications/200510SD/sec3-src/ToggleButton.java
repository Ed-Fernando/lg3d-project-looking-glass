import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import javax.vecmath.Vector3f;
import org.jdesktop.lg3d.sg.Texture;
import org.jdesktop.lg3d.sg.utils.image.TextureLoader;
import org.jdesktop.lg3d.sg.Appearance;
import org.jdesktop.lg3d.sg.Shape3D;
import org.jdesktop.lg3d.utils.action.ActionNoArg;
import org.jdesktop.lg3d.utils.eventadapter.MouseClickedEventAdapter;
import org.jdesktop.lg3d.utils.shape.ImagePanel;
import org.jdesktop.lg3d.utils.shape.SimpleAppearance;
import org.jdesktop.lg3d.wg.Component3D;
import org.jdesktop.lg3d.wg.Cursor3D;
import org.jdesktop.lg3d.wg.event.LgEventListener;
import org.jdesktop.lg3d.wg.event.LgEventSource;

public class ToggleButton extends Component3D {
    private static float SIZE = 0.03f;
    private static String ON_IMAGE = "on.png";
    private static String OFF_IMAGE = "off.png";

    private Appearance appearance;
    private Texture offTexture;
    private Texture onTexture;

    private boolean state;

    public ToggleButton() throws FileNotFoundException, IOException {
        appearance = new SimpleAppearance(0.0f, 0.0f, 0.0f, 
                                          SimpleAppearance.DISABLE_CULLING);
        appearance.setCapability(Appearance.ALLOW_TEXTURE_WRITE);

        ClassLoader classloader = this.getClass().getClassLoader();

        InputStream imageStream
            = classloader.getResourceAsStream(OFF_IMAGE);
        if (imageStream == null) {
            imageStream = new FileInputStream(OFF_IMAGE);
        }
        BufferedImage offImage = ImageIO.read(imageStream);
        offTexture = new TextureLoader(offImage).getTexture();

        imageStream = classloader.getResourceAsStream(ON_IMAGE);
        if (imageStream == null) {
            imageStream = new FileInputStream(ON_IMAGE);
        }
        BufferedImage onImage = ImageIO.read(imageStream);
        onTexture = new TextureLoader(onImage).getTexture();

        appearance.setTexture(offTexture);

        ImagePanel panel = new ImagePanel(SIZE, SIZE);
        panel.setAppearance(appearance);
        addChild(panel);

        setCursor(Cursor3D.SMALL_CURSOR);

        addListener(
            new MouseClickedEventAdapter(
                new ActionNoArg() {
                    public void performAction(LgEventSource source) {
                        state = !state;
                        if (state) {
                            appearance.setTexture(onTexture);
                        } else {
                            appearance.setTexture(offTexture);
                        }
                        
                        fireStateChangeEvent();
                    }
                }));

        setMouseEventPropagatable(true);
    }

    // 1. ボタンが押されたときの処理
    private void fireStateChangeEvent() {
        // StateChangeEvent を生成し、ポストする
        StateChangeEvent event = new StateChangeEvent(this, state);
        postEvent(event);
    }
}
