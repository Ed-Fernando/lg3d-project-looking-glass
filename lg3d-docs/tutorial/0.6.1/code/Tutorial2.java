/*
 * =====================================================================
 * BEGIN COPYRIGHT PLACEHOLDER
 * =====================================================================
 *
 * $RCSfile: Tutorial2.java,v $
 *
 * Copyright (c) 2004 Sun Microsystems, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Sun Microsystems, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sun.
 *
 * NOTE: We need to replace this with the actual copyright that we
 * intend to use for the public source release of jz-core.
 *
 * $Revision: 1.1 $
 * $Date: 2006-05-09 17:46:36 $
 * $State: Exp $
 *
 * =====================================================================
 * END COPYRIGHT PLACEHOLDER
 * =====================================================================
 */


// org.jdesktop.lg3d.wg.* -- Project Looking Glass core components
import org.jdesktop.lg3d.wg.Frame3D;
import org.jdesktop.lg3d.wg.Component3D;
import org.jdesktop.lg3d.wg.Cursor3D;
// org.jdesktop.lg3d.utils.shape.* -- Utility shapes and other useful
// classes related to Shape3D.
import org.jdesktop.lg3d.utils.shape.Box;
import org.jdesktop.lg3d.utils.shape.Primitive;
import org.jdesktop.lg3d.utils.shape.SimpleAppearance;
// org.jdesktop.lg3d.utils.eventaction.* -- Predefined event actions
import org.jdesktop.lg3d.utils.eventaction.ComponentMover;
// org.jdesktop.lg3d.utils.eventadapter.* -- Utility classes that
// receives events and predigest those into action calls.
import org.jdesktop.lg3d.utils.eventadapter.MouseEnteredEventAdapter;
import org.jdesktop.lg3d.utils.eventadapter.MouseClickedEventAdapter;
// org.jdesktop.lg3d.utils.action.* -- Behavior classes that accepts
// invocations via Action interface and performs predefined actions.
import org.jdesktop.lg3d.utils.action.ScaleAction;
import org.jdesktop.lg3d.utils.action.GenericEventPostAction;
// org.jdesktop.lg3d.wg.event.* -- Core components related events
import org.jdesktop.lg3d.wg.event.Frame3DToFrontEvent;

/**
 * Lesson 2 -- Rotate the pale green box in the 3D space as well as
 *             adding a bit of user interaction
 */
public class Tutorial2 {
    public static void main(String[] args) {
        int step = 0xffff; // run all the steps by default
        if (args.length > 0) {
            step = Integer.parseInt(args[0]);
        }
        new Tutorial2(step);
    }
    
    public Tutorial2(int step) {
        // So far, we got a pale green box displayed in the 3D space, but 
        // it is not that exciting.  Let's make it a bit more interesting.
        
        Frame3D frame3d = new Frame3D();
        // Try a bit of change -- make it translucent :)
        SimpleAppearance app = new SimpleAppearance(0.6f, 0.8f, 0.6f, 0.7f);
        Box box = new Box(0.05f, 0.04f, 0.03f, Primitive.GENERATE_NORMALS | Primitive.GEOMETRY_NOT_SHARED, app);
        Component3D comp = new Component3D();
        comp.addChild(box);
        
        // First, let's rotate it so that we can prove that it is really a
        // box.
        if (step >= 1) {
            // Component3D provides an easy way to rotate shapes attached to it.
            // We first specify the axis around which we rotate the component.
            
            comp.setRotationAxis(1.0f, 0.5f, 0.0f);
            
            // Then specify the angle.  The API uses radians, but usually we
						// have a feel to angles in degrees. Let us use Math.toRadians()
            // method to convert degrees to radian.
            
            comp.setRotationAngle((float)Math.toRadians(60));
            
            // Now, you should see a bit more interesting shape on the screen.
            // 
            // Please note that it shows reasonable lighting on each surface.
            // It is scenemanager's responsibility to lighten the world in
            // a reasonable manner.  So, if you go wild, you can write a such 
            // a scenemanager that changes lighting depending on time of day, 
            // or depending on the work load of your machine ;)
        }
        
        // So far, there is no interaction with the object. Here is a useful
        // utility class to achieve mouse motion interaction quickly.
        if (step >= 2) {
            // ComponentMover is one of predefined event action classes that
            // moves a Component3D according to mouse drag.
            // It implements mouse event listeners and translational operator 
            // under the cover in order to achieve the action.  The usage is
            // fairly simple -- just specify a component you'd like to move
            // as an argument of its constructor.
            
            new ComponentMover(comp);
        }
        
        // Now we can move the object.  How about changing the shape of the
        // cursor in order to indicate the object is movable?
        if (step >= 3) {
            // In order to do it, we can simply use predefined cursor shapes
            // and setCursor() method in order to associate the cursor to
            // the component.
            
            comp.setCursor(Cursor3D.MOVE_CURSOR);
        }
        
        // Let's implement one more user feedback.  The Project Looking Glass
        // API supports building blocks to implement visual user feedback
        // relatively easily.
        if (step >= 4) {
            // Two key concepts are "event adapter" and "action".
            // An event adapter is a listener that listens to specific events
            // and predigest it in a form that can invoke "action" via
            // the Action interface.
            // An action is a class that implements at least one of 
            // subinterface of the Action interface.  It accepts predigested
            // event and performs predefined action accordingly.
            // The following example uses MouseEnteredEventAdapter that
            // listens to mouse enter and exit events, and invokes
            // performAction(boolean) method (which is declared in 
            // ActionBoolean) in order to propagate the stimulus.
            // ScaleAction in turn implements the ActionBoolean interface 
            // and the performAction(boolean) method, which is used to 
            // receive the stimulus.  It scales the specified component
            // by the factor of its float argument (1.2f).  The duration of
            // the animation is specified by the third argument in miliseconds.
            
            new MouseEnteredEventAdapter(comp,
                new ScaleAction(comp, 1.2f, 500));
        }
        
        // As the final step in this lesson, let's implement a very simple 
        // interaction with the scenemanager, and let the manager brings this
        // 3D application to front (closest to the user) when its body gets
        // clicked. 
        if (step >= 5) {
            // Currently, we use an event object of Frame3DToFrontEvent
            // in order for a Frame3D object (3D app) to inform the
            // scenemanager about app's interest in being brought up to front.
            // We use MouseClickedEventAdapter for capturing the click event
            // against this component, and use GenericEventPostAction to fire 
            // off a Frame3DToFrontEvent against frame3d.  Most of the
            // scenemanager will listens to this event and brings up the event
            // source, which is this 3D application.
            
            new MouseClickedEventAdapter(comp, 
                new GenericEventPostAction(Frame3DToFrontEvent.class, frame3d));
        }
        
        frame3d.addChild(comp);
        // Since we rotate and scale the object, let's give a bit larger size
        // hint to the scenemanager in order to avoid conflicts with other 3D
        // apps.
        frame3d.setSize(0.06f, 0.06f, 0.06f);
        frame3d.setCapabilities();
        frame3d.setActive(true);
        frame3d.setVisible(true);
    }
}
