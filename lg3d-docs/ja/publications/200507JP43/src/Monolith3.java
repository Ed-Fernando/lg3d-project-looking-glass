import javax.vecmath.Vector3f;

import org.jdesktop.lg3d.sg.Appearance;
import org.jdesktop.lg3d.sg.Texture;
import org.jdesktop.lg3d.sg.utils.image.TextureLoader;
import org.jdesktop.lg3d.utils.shape.GlassyPanel;
import org.jdesktop.lg3d.utils.shape.FuzzyEdgePanel;
import org.jdesktop.lg3d.utils.shape.SimpleAppearance;
import org.jdesktop.lg3d.wg.Component3D;
import org.jdesktop.lg3d.wg.Frame3D;
import org.jdesktop.lg3d.wg.Thumbnail;

public class Monolith3 {
    private static final float ROUND_RATIO = 1.1f;

    public Monolith3() {
        Frame3D frame = new Frame3D();

        // コンポーネントの設定
        Component3D component = new Component3D();
        initComponent(component, 0.2f, 0.2f, 0.01f, 0.01f);

        frame.addChild(component);
        frame.setPreferredSize(new Vector3f(0.2f * ROUND_RATIO,
                                            0.2f * ROUND_RATIO,
                                            0.01f));

        // サムネイルの設定
        Thumbnail thumbnail = new Thumbnail();
        initComponent(thumbnail, 0.2f, 0.2f, 0.02f, 0.01f);
        thumbnail.setPreferredSize(new Vector3f(0.2f * ROUND_RATIO,
                                                0.2f * ROUND_RATIO,
                                                0.01f));
        
        frame.setThumbnail(thumbnail);

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

        // イメージ用のパネルの設定
        FuzzyEdgePanel imagePanel = new FuzzyEdgePanel(width, height, 0.001f,
                                                       imageAppearance);

        component.addChild(basePanel);
        component.addChild(imagePanel);
    }

    public static void main(String[] args) {
        new Monolith3();
    }
}
