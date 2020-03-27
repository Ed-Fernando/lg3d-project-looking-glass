import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.jdesktop.lg3d.utils.action.ActionBoolean;
import org.jdesktop.lg3d.utils.c3danimation.NaturalMotionAnimation;
import org.jdesktop.lg3d.wg.Component3D;
import org.jdesktop.lg3d.wg.event.LgEventSource;

public class SlotRotator {
    private Component3D target;
    private float angle;
    private int delay;

    private ExecutorService executor;
    private Future future;

    // verosity �͉�]/�b��\��
    public SlotRotator (Component3D target, int verosity) {
        if (target == null) {
            throw new IllegalArgumentException("target cannot be null");
        }

        this.target = target;

        // ���x���� 90 �x�񂷂��߂̎��Ԃ��Z�o
        delay = 250 / verosity;

        // ������Ԃ̃R���|�[�l���g�̉�]�p�x���擾
        this.angle = target.getRotationAngle();

        // �A�j���[�V�������Ȃ߂炩�ɂ���
        target.setAnimation(new NaturalMotionAnimation(1000));

        // ��]���� x ���ɐݒ�
        target.setRotationAxis(1.0f, 0.0f, 0.0f);

        // ��]�������Ȃ����߂� ExecutorService �𐶐�
        executor = Executors.newSingleThreadExecutor();
    }

    // ��]�̊J�n
    public void start() {
        future = executor.submit(new Runnable() {
                public void run() {
                    try {
                        while (true) {
                            // 90 �x�Â�]������
                            angle += (float)(Math.PI/2.0);
                            target.changeRotationAngle(angle, delay);
                            
                            TimeUnit.MILLISECONDS.sleep(delay);
                        }
                    } catch (InterruptedException ex) {
                    }
                }
            });
    }

    // ��]�̒�~
    public void stop() {
        future.cancel(true);
    }
}
