import javax.vecmath.Vector3f;

import org.jdesktop.lg3d.sg.Appearance;
import org.jdesktop.lg3d.utils.shape.GlassyPanel;
import org.jdesktop.lg3d.utils.shape.SimpleAppearance;
import org.jdesktop.lg3d.wg.Component3D;
import org.jdesktop.lg3d.wg.Frame3D;

public class Monolith1 {
    public Monolith1() {
        Frame3D frame = new Frame3D();

        // Appearance の設定
        Appearance appearance = new SimpleAppearance(1.0f, 0.6f, 0.6f, 1.0f, 
                                                     SimpleAppearance.DISABLE_CULLING);

        // パネルの生成
        GlassyPanel panel = new GlassyPanel(0.2f, 0.2f, 0.01f, 0.005f, appearance);

        // パネルをコンポーネントに追加
        Component3D component = new Component3D();
        component.addChild(panel);

        frame.addChild(component);

        // フレームの大きさを設定
        frame.setPreferredSize(new Vector3f(0.2f, 0.2f, 0.01f));

        // フレームの表示
        frame.changeEnabled(true);
        frame.changeVisible(true);        
    }

    public static void main(String[] args) {
        new Monolith1();
    }
}
