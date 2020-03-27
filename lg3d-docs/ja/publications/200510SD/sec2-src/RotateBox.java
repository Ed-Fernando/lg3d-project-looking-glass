import java.io.FileNotFoundException;
import java.io.IOException;
import javax.vecmath.Vector3f;
import org.jdesktop.lg3d.sg.Appearance;
import org.jdesktop.lg3d.sg.Shape3D;
import org.jdesktop.lg3d.utils.action.RotateActionBoolean;
import org.jdesktop.lg3d.utils.actionadapter.ToggleAdapter;
import org.jdesktop.lg3d.utils.c3danimation.NaturalMotionAnimation;
import org.jdesktop.lg3d.utils.eventadapter.MousePressedEventAdapter;
import org.jdesktop.lg3d.utils.shape.Box;
import org.jdesktop.lg3d.utils.shape.SimpleAppearance;
import org.jdesktop.lg3d.wg.Component3D;
import org.jdesktop.lg3d.wg.Frame3D;
import org.jdesktop.lg3d.wg.Thumbnail;

public class RotateBox {
    private Component3D component;
    private Component3D thumbComponent;

    public RotateBox() throws FileNotFoundException, IOException {
        Frame3D frame = new Frame3D();
        component = createComponent(frame);

        Thumbnail thumbnail = new Thumbnail();
        thumbComponent = createComponent(thumbnail);
        frame.setThumbnail(thumbnail);

        initEventAdapter();

        frame.changeEnabled(true);
        frame.changeVisible(true);        
    }

    private void initEventAdapter() {
        // ���炩�ɓ��������߂̂���
        component.setAnimation(new NaturalMotionAnimation(1000));

        // �}�E�X�{�^���������ꂽ�Ƃ��̃C�x���g����
        MousePressedEventAdapter adapter = new MousePressedEventAdapter(
                new RotateActionBoolean(component, (float)(Math.PI * 4.0), 10000));

        // �R���|�[�l���g�ɃC�x���g�A�_�v�^��o�^
        component.addListener(adapter);

        thumbComponent.setAnimation(new NaturalMotionAnimation(1000));
        MousePressedEventAdapter thumbAdapter = new MousePressedEventAdapter(
                new RotateActionBoolean(thumbComponent, (float)(Math.PI * 4.0), 10000));
        component.addListener(thumbAdapter);

        // �e�ɃC�x���g��`�d����
        component.setMouseEventPropagatable(true);
    }

    private Component3D createComponent(Component3D parent) 
                    throws FileNotFoundException, IOException {
        Box box = new Box(0.10f, 0.08f, 0.06f,
                          Box.GENERATE_TEXTURE_COORDS, null);
        setTextures(box);

        Component3D component = new Component3D();
        component.addChild(box);

        parent.addChild(component);
        parent.setPreferredSize(new Vector3f(0.10f, 0.08f, 0.06f));

        return component;
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
            new RotateBox();
        } catch (FileNotFoundException ex) {
            System.err.println("�e�N�X�`�����I�[�v���ł��܂���");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("�e�N�X�`���̓ǂݍ��݂Ɏ��s���܂���");
            ex.printStackTrace();
        }
    }
}
