import javax.vecmath.Vector3f;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.jdesktop.lg3d.sg.Appearance;
import org.jdesktop.lg3d.sg.Shape3D;
import org.jdesktop.lg3d.utils.shape.Box;
import org.jdesktop.lg3d.utils.shape.SimpleAppearance;
import org.jdesktop.lg3d.wg.Component3D;
import org.jdesktop.lg3d.wg.Frame3D;
import org.jdesktop.lg3d.wg.Thumbnail;

public class ThumbnailBox {
    public ThumbnailBox() throws FileNotFoundException, IOException {
        Frame3D frame = new Frame3D();

        // Box �̐���
        Box box = new Box(0.10f, 0.08f, 0.06f,
                          Box.GENERATE_TEXTURE_COORDS, null);
        setTextures(box);

        Component3D component = new Component3D();
        component.addChild(box);
        frame.addChild(component);

        // �T���l�C���p�� Box �̐���
        Box thumbBox = new Box(0.10f, 0.08f, 0.06f,
                               Box.GENERATE_TEXTURE_COORDS, null);
        setTextures(thumbBox);
        Component3D thumbComponent = new Component3D();
        thumbComponent.addChild(thumbBox);

        // �T���l�C���̐���
        Thumbnail thumbnail = new Thumbnail();
        thumbnail.addChild(thumbComponent);
        thumbnail.setPreferredSize(new Vector3f(0.10f, 0.08f, 0.06f));

        // �T���l�C���̒ǉ�
        frame.setThumbnail(thumbnail);

        frame.setPreferredSize(new Vector3f(0.10f, 0.08f, 0.06f));
        frame.changeEnabled(true);
        frame.changeVisible(true);        
    }

    private void setTextures(Box box)
                    throws FileNotFoundException, IOException {
        // �e�ʂɃe�N�X�`����\��
        Shape3D shape = box.getShape(Box.FRONT);
        shape.setAppearance(new SimpleAppearance("texture0.jpg"));

        shape = box.getShape(Box.RIGHT);
        shape.setAppearance(new SimpleAppearance("texture1.jpg"));

        shape = box.getShape(Box.LEFT);
        shape.setAppearance(new SimpleAppearance("texture2.jpg"));

        shape = box.getShape(Box.TOP);
        shape.setAppearance(new SimpleAppearance("texture3.jpg"));

        shape = box.getShape(Box.BOTTOM);
        shape.setAppearance(new SimpleAppearance("texture4.jpg"));

        shape = box.getShape(Box.BACK);
        shape.setAppearance(new SimpleAppearance("texture5.jpg"));
    }

    public static void main(String[] args) throws Exception {
        try {
            new ThumbnailBox();
        } catch (FileNotFoundException ex) {
            System.err.println("�e�N�X�`�����I�[�v���ł��܂���");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("�e�N�X�`���̓ǂݍ��݂Ɏ��s���܂���");
            ex.printStackTrace();
        }
    }
}
