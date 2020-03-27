import javax.vecmath.Vector3f;
import org.jdesktop.lg3d.sg.Appearance;
import org.jdesktop.lg3d.utils.shape.Box;
import org.jdesktop.lg3d.utils.shape.SimpleAppearance;
import org.jdesktop.lg3d.wg.Component3D;
import org.jdesktop.lg3d.wg.Frame3D;
 
public class SimpleBox {
    public SimpleBox() {
        // 1. フレームの生成
        Frame3D frame = new Frame3D();
  
        // 3. シェイプの生成および設定
        // 色を指定した Appearance の設定
        Appearance appearance 
            = new SimpleAppearance(0.6f, 0.6f, 1.0f, 1.0f);
  
        // Box の生成
        Box box = new Box(0.10f, 0.08f, 0.06f, appearance);
  
        // 4. コンポーネントの生成
        Component3D component = new Component3D();
  
       // 5. シェイプをコンポーネントに追加
        component.addChild(box);
  
        // 7. コンポーネントをコンテナに追加
        // ここではコンテナを使用していないので、
        // 直接フレームに追加
        frame.addChild(component);
  
        // 10. フレームの表示
        // フレームの大きさを設定
        frame.setPreferredSize(new Vector3f(0.1f, 0.08f, 0.06f));
  
        // フレームの表示
        frame.changeEnabled(true);
        frame.changeVisible(true);        
    }
  
    public static void main(String[] args) {
        new SimpleBox();
    }
}
