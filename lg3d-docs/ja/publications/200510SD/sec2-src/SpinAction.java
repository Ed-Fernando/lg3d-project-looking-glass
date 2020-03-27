import org.jdesktop.lg3d.utils.action.ActionNoArg;
import org.jdesktop.lg3d.wg.Component3D;
import org.jdesktop.lg3d.wg.event.LgEventSource;

public class SpinAction implements ActionNoArg {
    private Component3D target;
    private float diff;
    private int duration;
    private float angle;

    // コンストラクタ
    // diff     回転させる角度 (ラジアン)
    // duration 回転に要する時間 (ミリ秒)
    public SpinAction(Component3D target, float diff, int duration) {
        if (target == null) {
            throw new IllegalArgumentException("target cannot be null");
        }

        this.target = target;
        this.diff = diff;
        this.duration = duration;
        
        this.angle = target.getRotationAngle();
    }

    // イベント処理をおこなうためのメソッド
    public void performAction(LgEventSource source) {
        angle += diff;
        target.changeRotationAngle(angle, duration);
    }
}
