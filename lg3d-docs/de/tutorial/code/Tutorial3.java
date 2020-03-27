/*
 * =====================================================================
 * BEGIN COPYRIGHT PLACEHOLDER
 * =====================================================================
 *
 * $RCSfile: Tutorial3.java,v $
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
 * $Date: 2006-05-09 17:46:23 $
 * $State: Exp $
 *
 * =====================================================================
 * END COPYRIGHT PLACEHOLDER
 * =====================================================================
 */

// org.jdesktop.lg3d.wg.* -- Project Looking Glass core components
import org.jdesktop.lg3d.wg.Frame3D;
import org.jdesktop.lg3d.wg.Container3D;
import org.jdesktop.lg3d.wg.Component3D;
import org.jdesktop.lg3d.wg.Cursor3D;
// org.jdesktop.lg3d.utils.shape.* -- Utility shapes and other useful
// classes related to Shape3D.
import org.jdesktop.lg3d.utils.shape.Primitive;
import org.jdesktop.lg3d.utils.shape.Sphere;
import org.jdesktop.lg3d.utils.shape.SimpleAppearance;
// org.jdesktop.lg3d.utils.eventaction.* -- Predefined event actions
import org.jdesktop.lg3d.utils.eventaction.ComponentMover;
// org.jdesktop.lg3d.utils.eventadapter.* -- Utility classes that
// receives events and predigest those into action calls.
import org.jdesktop.lg3d.utils.eventadapter.MouseEnteredEventAdapter;
import org.jdesktop.lg3d.utils.eventadapter.MouseClickedEventAdapter;
import org.jdesktop.lg3d.utils.eventadapter.MouseDraggedEventAdapter;
// org.jdesktop.lg3d.utils.actionadapter.* -- performs some processing 
// and possibly conversion among the Action interfaces.
import org.jdesktop.lg3d.utils.actionadapter.ToggleAdapter;
import org.jdesktop.lg3d.utils.actionadapter.Float2Splitter;
import org.jdesktop.lg3d.utils.actionadapter.FloatDiffer;
import org.jdesktop.lg3d.utils.actionadapter.FloatScaler;
// org.jdesktop.lg3d.utils.action.* -- Behavior classes that accepts
// invocations via Action interface and performs predefined actions.
import org.jdesktop.lg3d.utils.action.ScaleAction;
import org.jdesktop.lg3d.utils.action.RotateAction;
import org.jdesktop.lg3d.utils.action.ResilientRotateAction;
import org.jdesktop.lg3d.utils.action.GenericEventPostAction;
// org.jdesktop.lg3d.wg.event.* -- Core components related events
import org.jdesktop.lg3d.wg.event.Frame3DToFrontEvent;

/**
 * Lesson 3 -- A sphere with Earth texture and a small handle to move it.
 *             The earth rotates 180 degrees back and forth when clicked.
 */
public class Tutorial3 {
    public static void main(String[] args) {
        int step = 100; // run all the steps by default
        if (args.length > 0) {
            step = Integer.parseInt(args[0]);
        }
        new Tutorial3(step);
    }
    
    public Tutorial3(int step) {
        
        Frame3D frame3d = new Frame3D();
        
        // In order to add one more layer of visual feedback (wiggling while 
        // being dragged), insert a Container3D, since we cannot associate
        // two conflicting actions with one Component3D (e.g. the both
        // RotateAction and ResilientRotateAction).
        // Please note that we cannot add Component3D to Component3D.
        // We also cannot add Shape3D to Container3D directly.
        
        Container3D top = new Container3D();
        
        // Leverage SimpleAppearance's handy texture initialization.
        // It uses reasonable initial values that works OK for most of cases.
        
        SimpleAppearance earthApp = null;
        try {
            earthApp = new SimpleAppearance("earth.jpg");
        } catch (Exception e) {
            System.err.println("Failed to load texture: " + e);
        }
        
        // Be sure to specify GENERATE_TEXTURE_COORDS.
        
        Sphere earth
            = new Sphere(0.03f, 
                Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS | Primitive.GEOMETRY_NOT_SHARED,
                    36, earthApp);
        
        Component3D earthComp = new Component3D();
        earthComp.addChild(earth);
        earthComp.setTranslation(0.0f, 0.0f, -0.01f);
        
        // We can specify a cursor per Component3D basis.
        
        earthComp.setCursor(Cursor3D.E_RESIZE_CURSOR);
        
        if (step >= 1) {
            new MouseEnteredEventAdapter(earthComp,
                new ScaleAction(earthComp, 1.1f, 500));
        }
        
        // Here is a simple usage example of an simple action adapter, 
        // ToggleAdapter, which converts ActionNoArg interface to 
        // ActionBoolean interface by toggling the state for the call
        // to performAction() method of the ActionBoolean interface.
        // Don't fotget to set the rotation axis.
        if (step >= 2) {
            earthComp.setRotationAxis(0.0f, 1.0f, 0.0f);
            new MouseClickedEventAdapter(earthComp,
                new ToggleAdapter(
                    new RotateAction(earthComp, (float)Math.PI, 1000)));
        }
        
        if (step >= 3) {
            new MouseClickedEventAdapter(earthComp, 
                    new GenericEventPostAction(
                        Frame3DToFrontEvent.class, frame3d));
        }
        
        top.addChild(earthComp);
        
        SimpleAppearance handleApp 
            = new SimpleAppearance(1.0f, 0.0f, 0.0f, 0.5f);
        Sphere handle = new Sphere(0.003f, Primitive.GENERATE_NORMALS | Primitive.GEOMETRY_NOT_SHARED, handleApp);
        Component3D handleComp = new Component3D();
        handleComp.addChild(handle);
        handleComp.setCursor(Cursor3D.MOVE_CURSOR);
        handleComp.setTranslation(0.0f, 0.05f, 0.0f);
        
        // Notice that this time we have two arguments -- the first one 
        // for specifying the object that is grabbable, and the second
        // one that we move.  Since we move the entire application, 
        // we specify the frame3d object.
        if (step >= 4) {
            new ComponentMover(handleComp, frame3d);
        }
        
        if (step >= 5) {
            new MouseEnteredEventAdapter(handleComp,
                new ScaleAction(handleComp, 1.5f, 200));
        }
        
        top.addChild(handleComp);
        
        // The following lines achieves the wiggling motion when the 
        // application is dragged.  First we set the rotation axis, and
        // use ResilientRotateAction() to actually wiggle the app.
        // Multiple actionadapters are used to convert and adjust the
        // x and y coordinates MouseDraggedEventAdapter() generates into
        // a float value that can be used as input for the rotation action.
        // REMINDER -- this nesting may be too much...
        // REMINDER -- this kind of behavior may be best by implemented by
        // scenemanager.  At this moment, it is still under discussion.
        if (step >= 6) {
            top.setRotationAxis(0.0f, 1.0f, 0.0f);
            new MouseDraggedEventAdapter(handleComp,
                new Float2Splitter(
                    new FloatDiffer(
                        new FloatScaler(500f, (float)Math.toRadians(30),
                            new ResilientRotateAction(top, 1500))),
                    null));
        }
        
        frame3d.addChild(top);
        
        frame3d.setSize(0.06f, 0.10f, 0.08f);
        frame3d.setCapabilities();
        frame3d.setActive(true);
        frame3d.setVisible(true);
    }
}
