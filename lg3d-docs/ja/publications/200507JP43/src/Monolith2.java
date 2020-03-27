import javax.vecmath.Vector3f;

import org.jdesktop.lg3d.sg.Appearance;
import org.jdesktop.lg3d.sg.Texture;
import org.jdesktop.lg3d.sg.utils.image.TextureLoader;
import org.jdesktop.lg3d.utils.shape.GlassyPanel;
import org.jdesktop.lg3d.utils.shape.FuzzyEdgePanel;
import org.jdesktop.lg3d.utils.shape.SimpleAppearance;
import org.jdesktop.lg3d.wg.Component3D;
import org.jdesktop.lg3d.wg.Frame3D;

public class Monolith2 {
    public Monolith2() {
        Frame3D frame = new Frame3D();

        // ベースになるパネルの作成
        Appearance appearance = new SimpleAppearance(1.0f, 0.6f, 0.6f, 1.0f, 
                                                     SimpleAppearance.DISABLE_CULLING);
        GlassyPanel basePanel = new GlassyPanel(0.2f, 0.2f, 0.01f, 0.005f, appearance);


        // イメージパネル用のアピアランス
        Appearance imageAppearance = new SimpleAppearance(1.0f, 1.0f, 1.0f, 1.0f, 
                                                     SimpleAppearance.ENABLE_TEXTURE 
                                                     | SimpleAppearance.DISABLE_CULLING);

        // テクスチャの設定
        TextureLoader loader = new TextureLoader("texture.jpg", null);
        Texture texture = loader.getTexture();
        imageAppearance.setTexture(texture);

        // イメージ用のパネルの設定
        FuzzyEdgePanel imagePanel = new FuzzyEdgePanel(0.18f, 0.18f, 0.001f,
                                                       imageAppearance);

        // パネルをコンポーネントに追加
        Component3D component = new Component3D();
        component.addChild(basePanel);
        component.addChild(imagePanel);

        frame.addChild(component);

        frame.setPreferredSize(new Vector3f(0.2f, 0.2f, 0.01f));

        frame.changeEnabled(true);
        frame.changeVisible(true);        
    }

    public static void main(String[] args) {
        new Monolith2();
    }
}
