import org.jdesktop.lg3d.utils.action.ActionBoolean;
import org.jdesktop.lg3d.utils.eventadapter.EventAdapter;
import org.jdesktop.lg3d.wg.event.LgEvent;

public class StateChangeEventAdapter implements EventAdapter {
    private static final Class[] targetEventClasses = {
        StateChangeEvent.class
    };

    private ActionBoolean action;

    public StateChangeEventAdapter(ActionBoolean action) {
        // StateChangeEventAdapter �ň����A�N�V������
        // ActionBoolean �̂�
        this.action = action;
    }

    public Class[] getTargetEventClasses() {
        return targetEventClasses;
    }

    public void processEvent(LgEvent event) {
        // �C�x���g�� StateChangeEvent �ł���΁A
        // �A�N�V�������R�[������
        if (event instanceof StateChangeEvent) {
            boolean state = ((StateChangeEvent)event).getState();
            action.performAction(event.getSource(), state);
        }
    }
}
