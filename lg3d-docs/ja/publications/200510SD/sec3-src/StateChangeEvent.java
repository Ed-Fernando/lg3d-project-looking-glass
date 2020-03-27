import org.jdesktop.lg3d.wg.event.LgEvent;
import org.jdesktop.lg3d.wg.event.LgEventSource;

public class StateChangeEvent extends LgEvent {
    private LgEventSource source;
    private Class sourceClass;
    private boolean state;

    public StateChangeEvent(LgEventSource source, boolean state) {
        this.source = source;
        this.sourceClass = source.getClass();

        this.state = state;
    }

    public LgEventSource getSource() {
        return source;
    }

    public Class getSourceClass() {
        return sourceClass;
    }

    public boolean getState() {
        return state;
    }
}
