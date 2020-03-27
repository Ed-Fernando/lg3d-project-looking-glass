/**
 * Project Looking Glass
 *
 * $RCSfile: Tutorial1.java,v $
 *
 * Copyright (c) 2004, Sun Microsystems, Inc., All Rights Reserved
 *
 * Redistributions in source code form must reproduce the above
 * copyright and this condition.
 *
 * The contents of this file are subject to the GNU General Public
 * License, Version 2 (the "License"); you may not use this file
 * except in compliance with the License. A copy of the License is
 * available at http://www.opensource.org/licenses/gpl-license.php.
 *
 * $Revision: 1.1 $
 * $Date: 2006-05-09 17:46:36 $
 * $State: Exp $
 */

// org.jdesktop.lg3d.wg.* -- Project Looking Glass core components
import org.jdesktop.lg3d.wg.Frame3D;
import org.jdesktop.lg3d.wg.Component3D;
// org.jdesktop.lg3d.utils.shape.* -- Utility shapes and other useful
// classes related to Shape3D.
import org.jdesktop.lg3d.utils.shape.Box;
import org.jdesktop.lg3d.utils.shape.Primitive;
import org.jdesktop.lg3d.utils.shape.SimpleAppearance;

/**
 * Lesson 1 -- Put a pale green box in the 3D space
 */
public class Tutorial1 {
    public static void main(String[] args) {
        new Tutorial1();
    }
    
    public Tutorial1() {
        // First, we need to create the root container for this 3D
        // application.  The Frame3D class serves for this purpose.
        // We can extend Frame3D, or simply create one and add components
        // to it.  In this example, we'll take the later approach.
	
        Frame3D frame3d = new Frame3D();
        
        // First, let's try to put a box of 5cm x 4cm x 3cm at the center of
        // this application.  Predefined shapes found under ...utils.shape
        // are handy for this purpose.  By the way, what color would you like 
        // to paint it?  Let's try pale green for this example.
        //
        // Project Looking Glass 3D APIs are based on Java 3D.  In Java 3D,
        // by default, the metrics system is used, where one (1) unit equals 
        // to  1 meter.  And if configured correctly, by drawing an object 
        // of size 0.02 unit turns into an object of approzimitely 2cm in 
        // size on the screen.
        //
        // Please note that the size changes if we locate far or close to 
        // your eyes.  By default, a 3D scenemanager will position the object 
        // at a reasonable position where you see it with size close to you
        // intended. 
        //
        // The API also provides handy class to specify color, which is
        // SimpleAppearance.  Throughout the API, we use Appearance class
        // to define object's appearance including color, translucency and
        // texture applied to it.  Here let's create an appearance object
        // that expresses a pale green color.
        
        SimpleAppearance app = new SimpleAppearance(0.6f, 0.8f, 0.6f);
        
        // As you would have guessed, (1.0f, 1.0f, 1.0f) means white.
        // If you'd like to specify object's color in more detailed manner,
        // including shininess and what color it shines, etc, please reference
        // to Appearance and Material classes.
	// 
        // By default all primitives with the same parameters share their
        // geometry (e.g., you can have 50 shperes in your scene, but the
        // geometry is stored only once). A change to one primitive will
        // effect all shared nodes.  Another implication of this
        // implementation is that the capabilities of the geometry are shared,
        // and once one of the shared nodes is live, the capabilities cannot
        // be set. Use the GEOMETRY_NOT_SHARED flag if you do not wish to
        // share geometry among primitives with the same parameters.
	// Note that if you set your own flags the default flags are overridden,
	// therefore you will also need to add the default GENERATE_NORMALS if
	// you want it again.
        // 
        // Now finally we create a box with the size of 5cm x 4cm x 3cm
        // with the color we've just created.
        
        Box box = new Box(0.05f, 0.04f, 0.03f, Primitive.GENERATE_NORMALS | Primitive.GEOMETRY_NOT_SHARED, app);
        
        // Then we add the box to the top level container so that it 
        // appears on the screen.  
        // 
        // There is one rule to remember -- we can add only Component3D to
        // Frame3D (more precisely Container3D which Frame3D extends).
        // Component3D is the base component for all the Project Looking 
        // Glass 3D components.  One component can have multiple Shape3Ds
        // to define its shape (note that Box inherits Shape3D), and
        // a Frame3D can host multiple Component3Ds (including nested
        // Container3Ds).
        //
        // So, let's create a new Component3D in order to host the box shape.
        
        Component3D comp = new Component3D();
        
        // Then add the box to it.
        
        comp.addChild(box);
        
        // Finally, add it to the root container object.
        
        frame3d.addChild(comp);
        
        // We need to do a few more things to make it visible.
        // 
        // We usually need to do the following before making any object
        // visible.  Otherwise, we won't be able to operate on it.
        // At this moment, please consider this is a magic function, and 
        // remember to check if you surely make the following call whenever
        // you encounter a situation where the mouse cursor does not go 
        // on top of your object.
        
        frame3d.setCapabilities();
        
        // You may be interested in eliminating the above call and see how
        // it changes the behavior.  For those who knows Java 3D a bit deeper
        // -- this call ensures that capabilities of the shapes belonging to
        // this Frame3D (i.e. this application) are set right so that all the 
        // shapes are pickable.
        
        // Another thing better to do is to set size hint of this application.
        // The current SceneManager implementation depends on this size hint
        // for arranging 3D applications (Frame3D objects) in the 3D space
        // it manages.
        
        frame3d.setSize(0.04f, 0.03f, 0.02f);
        
        // When the Frame3D object created, it is not visible -- it is not
        // a part of the scenegraph.  The setActive() call actually adds
        // the given 3D app to the scenegraph and make it visible. 
        // More precisely, the call initiates interaction with the 3D
        // SceneManager and the manager deals with the details of the policy
        // for presenting the application (e.g, location, size, etc).
        
        frame3d.setActive(true);
        
        // And this is the final step.
        // By default, a Frame3D object is invisible even after being
        // added to the scenegraph.  The following call makes it finally
        // visible to you.  Note that setVisible() is fairly lightweight
        // compared to the setActive() call, so you want to use setVisible()
        // for temprarily hide an object.
        
        frame3d.setVisible(true);
        
        // Now you should see a pale green box on the screen. 
        // You saw just a green rectangle?
        // Well, we need to rotate it to make it look like a box.
        // We'll do it in the next lesson...
    }
}
