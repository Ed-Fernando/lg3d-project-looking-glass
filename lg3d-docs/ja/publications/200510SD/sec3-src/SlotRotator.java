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

    // verosity は回転/秒を表す
    public SlotRotator (Component3D target, int verosity) {
        if (target == null) {
            throw new IllegalArgumentException("target cannot be null");
        }

        this.target = target;

        // 速度から 90 度回すための時間を算出
        delay = 250 / verosity;

        // 初期状態のコンポーネントの回転角度を取得
        this.angle = target.getRotationAngle();

        // アニメーションをなめらかにする
        target.setAnimation(new NaturalMotionAnimation(1000));

        // 回転軸を x 軸に設定
        target.setRotationAxis(1.0f, 0.0f, 0.0f);

        // 回転をおこなうための ExecutorService を生成
        executor = Executors.newSingleThreadExecutor();
    }

    // 回転の開始
    public void start() {
        future = executor.submit(new Runnable() {
                public void run() {
                    try {
                        while (true) {
                            // 90 度づつ回転させる
                            angle += (float)(Math.PI/2.0);
                            target.changeRotationAngle(angle, delay);
                            
                            TimeUnit.MILLISECONDS.sleep(delay);
                        }
                    } catch (InterruptedException ex) {
                    }
                }
            });
    }

    // 回転の停止
    public void stop() {
        future.cancel(true);
    }
}
