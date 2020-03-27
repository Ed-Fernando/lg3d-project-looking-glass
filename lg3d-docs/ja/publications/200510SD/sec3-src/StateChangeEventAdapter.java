import org.jdesktop.lg3d.utils.action.ActionBoolean;
import org.jdesktop.lg3d.utils.eventadapter.EventAdapter;
import org.jdesktop.lg3d.wg.event.LgEvent;

public class StateChangeEventAdapter implements EventAdapter {
    private static final Class[] targetEventClasses = {
        StateChangeEvent.class
    };

    private ActionBoolean action;

    public StateChangeEventAdapter(ActionBoolean action) {
        // StateChangeEventAdapter で扱うアクションは
        // ActionBoolean のみ
        this.action = action;
    }

    public Class[] getTargetEventClasses() {
        return targetEventClasses;
    }

    public void processEvent(LgEvent event) {
        // イベントが StateChangeEvent であれば、
        // アクションをコールする
        if (event instanceof StateChangeEvent) {
            boolean state = ((StateChangeEvent)event).getState();
            action.performAction(event.getSource(), state);
        }
    }
}
