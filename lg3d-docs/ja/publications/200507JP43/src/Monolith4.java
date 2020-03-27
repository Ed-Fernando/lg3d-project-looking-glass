import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.vecmath.Vector3f;

import org.jdesktop.lg3d.sg.Appearance;
import org.jdesktop.lg3d.sg.Texture;
import org.jdesktop.lg3d.sg.utils.image.TextureLoader;
import org.jdesktop.lg3d.utils.action.ActionNoArg;
import org.jdesktop.lg3d.utils.action.RotateActionBoolean;
import org.jdesktop.lg3d.utils.actionadapter.ToggleAdapter;
import org.jdesktop.lg3d.utils.c3danimation.NaturalMotionAnimation;
import org.jdesktop.lg3d.utils.eventadapter.MouseClickedEventAdapter;
import org.jdesktop.lg3d.utils.eventadapter.MousePressedEventAdapter;
import org.jdesktop.lg3d.utils.eventadapter.MouseClickedEventAdapter;
import org.jdesktop.lg3d.utils.shape.GlassyPanel;
import org.jdesktop.lg3d.utils.shape.FuzzyEdgePanel;
import org.jdesktop.lg3d.utils.shape.SimpleAppearance;
import org.jdesktop.lg3d.wg.Component3D;
import org.jdesktop.lg3d.wg.Frame3D;
import org.jdesktop.lg3d.wg.Thumbnail;
import org.jdesktop.lg3d.wg.event.LgEventSource;

public class Monolith4 {
    private static final String IMAGE_LIST_FILE = "images.txt";
    private static final float ROUND_RATIO = 1.1f;

    private Component3D component;
    private Thumbnail thumbnail;

    public Monolith4() {
        Frame3D frame = new Frame3D();

        // コンポーネントの設定
        component = new Component3D();
        initComponent(component, 0.2f, 0.2f, 0.01f, 0.01f);

        frame.addChild(component);
        frame.setPreferredSize(new Vector3f(0.2f * ROUND_RATIO,
                                            0.2f * ROUND_RATIO,
                                            0.01f));

        // サムネイルの設定
        thumbnail = new Thumbnail();
        initComponent(thumbnail, 0.2f, 0.2f, 0.01f, 0.01f);
        thumbnail.setPreferredSize(new Vector3f(0.2f * ROUND_RATIO,
                                                0.2f * ROUND_RATIO,
                                                0.01f));
        frame.setThumbnail(thumbnail);
        
        initEventAdapter();

        frame.changeEnabled(true);
        frame.changeVisible(true);        
    }

    private void initComponent(Component3D component, 
                               float width, float height, float depth, float edge) {
        Appearance appearance = new SimpleAppearance(1.0f, 0.6f, 0.6f, 1.0f, 
                                                     SimpleAppearance.DISABLE_CULLING);
        GlassyPanel basePanel = new GlassyPanel(width * ROUND_RATIO,
                                                height * ROUND_RATIO,
                                                depth, edge, appearance);

        Appearance imageAppearance = new SimpleAppearance(1.0f, 1.0f, 1.0f, 1.0f, 
                                                     SimpleAppearance.ENABLE_TEXTURE 
                                                     | SimpleAppearance.DISABLE_CULLING);

        TextureLoader loader = new TextureLoader("texture.jpg", null);
        Texture texture = loader.getTexture();
        imageAppearance.setTexture(texture);

        FuzzyEdgePanel imagePanel = new FuzzyEdgePanel(width, height, 0.001f,
                                                       imageAppearance);

        component.addChild(basePanel);
        component.addChild(imagePanel);
    }

    private void initEventAdapter() {
        component.setAnimation(new NaturalMotionAnimation(1000));

        component.addListener(
            new MouseClickedEventAdapter(
                new ToggleAdapter(
                    new RotateActionBoolean(component, (float)Math.PI * 2.0f, 1000))));

        component.addListener(
            new MouseClickedEventAdapter(
                new ToggleAdapter(
                    new RotateActionBoolean(thumbnail, (float)Math.PI * 2.0f, 1000))));

        component.setMouseEventPropagatable(true);
    }

    public static void main(String[] args) {
        new Monolith4();
    }
}
