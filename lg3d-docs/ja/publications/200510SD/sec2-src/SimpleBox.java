import javax.vecmath.Vector3f;
import org.jdesktop.lg3d.sg.Appearance;
import org.jdesktop.lg3d.utils.shape.Box;
import org.jdesktop.lg3d.utils.shape.SimpleAppearance;
import org.jdesktop.lg3d.wg.Component3D;
import org.jdesktop.lg3d.wg.Frame3D;
 
public class SimpleBox {
    public SimpleBox() {
        // 1. �t���[���̐���
        Frame3D frame = new Frame3D();
  
        // 3. �V�F�C�v�̐�������ѐݒ�
        // �F���w�肵�� Appearance �̐ݒ�
        Appearance appearance 
            = new SimpleAppearance(0.6f, 0.6f, 1.0f, 1.0f);
  
        // Box �̐���
        Box box = new Box(0.10f, 0.08f, 0.06f, appearance);
  
        // 4. �R���|�[�l���g�̐���
        Component3D component = new Component3D();
  
       // 5. �V�F�C�v���R���|�[�l���g�ɒǉ�
        component.addChild(box);
  
        // 7. �R���|�[�l���g���R���e�i�ɒǉ�
        // �����ł̓R���e�i���g�p���Ă��Ȃ��̂ŁA
        // ���ڃt���[���ɒǉ�
        frame.addChild(component);
  
        // 10. �t���[���̕\��
        // �t���[���̑傫����ݒ�
        frame.setPreferredSize(new Vector3f(0.1f, 0.08f, 0.06f));
  
        // �t���[���̕\��
        frame.changeEnabled(true);
        frame.changeVisible(true);        
    }
  
    public static void main(String[] args) {
        new SimpleBox();
    }
}
