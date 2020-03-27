import java.io.FileNotFoundException;
import java.io.IOException;
import javax.vecmath.Vector3f;
import org.jdesktop.lg3d.sg.Appearance;
import org.jdesktop.lg3d.utils.shape.Box;
import org.jdesktop.lg3d.utils.shape.SimpleAppearance;
import org.jdesktop.lg3d.wg.Component3D;
import org.jdesktop.lg3d.wg.Frame3D;

public class TextureBox {
    public TextureBox() throws FileNotFoundException, IOException {
        Frame3D frame = new Frame3D();

        // Texture ���w�肵�� Appearance �̐���
        Appearance appearance = new SimpleAppearance("texture.jpg");

        // Box �̐���
        Box box = new Box(0.10f, 0.08f, 0.06f,
                          Box.GENERATE_TEXTURE_COORDS, appearance);

        Component3D component = new Component3D();
        component.addChild(box);
        frame.addChild(component);
        
        frame.setPreferredSize(new Vector3f(0.10f, 0.08f, 0.06f));
        frame.changeEnabled(true);
        frame.changeVisible(true);        
    }

    public static void main(String[] args) {
        try {
            new TextureBox();
        } catch (FileNotFoundException ex) {
            System.err.println("texture.jpg ���I�[�v���ł��܂���");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("texture.jpg �̓ǂݍ��݂Ɏ��s���܂���");
            ex.printStackTrace();
        }
    }
}
