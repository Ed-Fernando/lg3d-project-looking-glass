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
 * $Date: 2006-05-09 17:46:39 $
 * $State: Exp $
 */

/**
 * Lesson 1
 *
 * Aim: To demonstrate the basic steps of creating a 3D application
 *      within the LG3D framework
 *
 * Requirements:
 *      To use this tutorial you will need a functional installation
 *      of the of LG3D (the stable version is the best version to begin with).
 *      This can be found at the lg3d-core web site. Follow the "Getting
 *      started with Project Looking Glass" link for installation instructions.
 *      Note: You do not need to run the lg3d-session version.
 *      Additionally, you need to have the Java 1.5 SDK. This is available
 *      at the Sun Java web site.
 * 
 * Steps:
 *      1. Create a root container
 *      2. Create a box to put in the container
 *      3. Put the box in the container
 *      4. Initialize the container
 *      5. Compile the code
 *      6. Set up the testing environment
 *      7. Run our application
 */

// org.jdesktop.lg3d.wg.* -- Project Looking Glass core components
import org.jdesktop.lg3d.wg.Frame3D;
import org.jdesktop.lg3d.wg.Component3D;
// org.jdesktop.lg3d.utils.shape.* -- Utility shapes and other useful
// classes related to Shape3D.
import org.jdesktop.lg3d.utils.shape.Box;
import org.jdesktop.lg3d.utils.shape.SimpleAppearance;
// javax.vecmath.* -- Classes for vector math operations
import javax.vecmath.Vector3f;


public class Tutorial1 {
    public static void main(String[] args) {
        new Tutorial1();
    }
    
    public Tutorial1() {
        // 
        // Step 1: Create a root container
        // -------------------------------
        // First, we need to create the root container for this 3D application. 
        // The Frame3D class serves for this purpose. 
	
        Frame3D frame3d = new Frame3D();
        
        // 
        // Step 2: Create a box to put in the container
        // --------------------------------------------
        // Now, let's try to put a box of 8cm x 6cm x 4cm at the center 
        // of this application. Predefined shapes found under
        // org.jdekstop.lg3d.utils.shape are handy for this purpose.
        //
        // By the way, what color would you like to paint it? 
        // Let's try pale green for this example. Therefore we need to create
        // an appearance object to handle the color of the box.
        // The API also provides a handy class to specify color, which
        // is SimpleAppearance.
        
        SimpleAppearance app = new SimpleAppearance(0.6f, 0.8f, 0.6f);
        
        // Additionally you can specify the object's color in a more detailed
        // manner, including shininess and what color it shines, translucency
        // and textures applied to it. You can find out more by looking
        // at the API documentation for Appearance, Material and Texture.
        //
        // Now that we have a default appearance, we can create the box
        // of 8cm x 6cm x 4cm.  Note that we need to specify half 
        // the length of each dimension to the constructor of the 
        // predefined Box class.  It creates a box that lies within
        // the bounding box, [-x, -y, -z] and [x, y, z], where x, y and z
        // are the first three arguments to the constructor.
        
        Box box = new Box(0.04f, 0.03f, 0.02f, app);
        
        // Project Looking Glass 3D APIs are based on Java 3D. In Java 3D, 
        // by default, the metric system is used, where one (1) unit equals
        // one meter. And if configured correctly, by drawing an object of
        // size 0.02 units turns into an object approximately 2cm in size
        // on the screen.
        //
        // Please note that the default configuration for LG3D is for
        // a perspective view, therefore the drawing size varies depending
        // upon how far away, or how close the object is. By default,
        // the 3D scene manager of LG3D will position the object at
        // a reasonable position where the size is close to the size intended.
        
        // 
        // Step 3: Put the box in the container
        // ------------------------------------
        // To display the box on the screen, we must add the box to the top
        // level container. The box cannot be added directly to the Frame3D,
        // only a Component3D can be added to Frame3Ds (this functionality
        // is inherited from Container3D). Component3D is the base component
        // for all the LG3D components. One component can have multiple
        // Shape3D objects to define its shape (note that Box inherits Shape3D),
        // and a Frame3D can host multiple Component3Ds (including nested
        // Container3Ds).  Therefore we must create a Component3D.
        
        Component3D comp = new Component3D();
        
        // Then add the box to the Component3D.
        
        comp.addChild(box);
        
        // Now the Component3D can be added to the root container.
        
        frame3d.addChild(comp);
        
        // 
        // Step 4: Initialize the container
        // --------------------------------
        // Now that the box has been added to the frame, the container needs
        // to be initialized to allow interaction, and to make the box visible.
        //
        // To assist the SceneManager (at least in the default scene manager -
        // GlassySceneManager) it is useful to set the size hint for the
        // Frame3D. This is used by the SceneManager to arrange 3D applications
        // in the 3D space it manages.
        
        frame3d.setPreferredSize(new Vector3f(0.08f, 0.06f, 0.04f));
        
        // To make the frame visible two steps are required.
        // First, the frame needs to be added to the scenegraph.
        // The changeEnabled() call does this. More precisely, the call
        // initiates interaction with the 3D SceneManager and the manager
        // handles the details of the policy for presenting the application
        // (e.g. location, size, etc.).
        
        frame3d.changeEnabled(true);
        
        // Second, although the frame is active, it must also be made visible.
        // Note changeVisible() is fairly light-weight compared to the
        // changeEnabled() call, therefore changeVisible() should be used to
        // temporarily hide an object.
        
        frame3d.changeVisible(true);
        
        // 
        // Step 5: Compile the code
        // ------------------------
        // Now we need to compile the source file. The tutorial requires the
        // LG3D core library and Java 1.5 to compile. The core library can be
        // found under the lib directory of the LG3D distribution (replace 
        // $LG3DHOME with the path to the LG3D distribution).
        // 
        //     javac -cp "$LG3DHOME/lib/ext/lg3d-core.jar" Tutorial1.java
        
        // 
        // Step 6: Setup the test environment
        // ----------------------------------
        // To run the application the LG3D environment must be set up correctly
        // to ensure that the display and other configuration parameters are
        // set. To ease this process a shell script is available.
        // Once configured correctly you can run the tutorial easily.
        // Download runtutorial and follow these steps:
        // 
        //   1. Put the runtutorial file in the same directory as your 
        //      Java class
        //   2. Make the file executable
        // 
        //        chmod u+x runtutorial
        // 
        //   3. Edit runtutorial with your favorite text editor and change
        //      the following:
        //
        //        a. Set the bash executable path on the first line, if not
        //           already correct, prepended with a #! (this tells the 
        //           system to run the script using that executable).
        //           To find the correct location of the bash executable,
        //           in a console type:
        //
        //             which bash
        // 
        //        b. Set the LG3DHOME parameter to the base directory of
        //           the LG3D installation
        
        //
        // Step 7: Run our application
        // ---------------------------
        // To run the tutorial, simply execute the runtutorial script with
        // the name of the class file (without the .class extension)
        // 
        //     ./runtutorial Tutorial1
        //
	// The LG3D desktop should be displayed with a pale green screen box
        // in the center. In actual fact, only a pale green square can be
        // seen - in the next tutorial the box will be rotated to make it
        // look like a box.
        // 
        // Note that it is translucent when mouse is away from the application
        // and it becomes opaque when mouse is over it.  You can also grab and
        // move it.  These are the default behavior the SceneManager
        // implements.
    }
}
