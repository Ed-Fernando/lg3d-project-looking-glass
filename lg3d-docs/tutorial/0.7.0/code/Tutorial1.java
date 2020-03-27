/**
 * Project Looking Glass
 *
 * $RCSfile: Tutorial1.java,v $
 *
 * Copyright (c) 2004-2006, Sun Microsystems, Inc., All Rights Reserved
 *
 * Redistributions in source code form must reproduce the above
 * copyright and this condition.
 *
 * The contents of this file are subject to the GNU General Public
 * License, Version 2 (the "License"); you may not use this file
 * except in compliance with the License. A copy of the License is
 * available at http://www.opensource.org/licenses/gpl-license.php.
 *
 * $Revision: 1.2 $
 * $Date: 2006-06-30 21:52:19 $
 * $State: Exp $
 */

/**
 * Lesson 1
 *
 * Aim: To demonstrate the basic steps of creating a 3D application
 *      within the LG3D framework
 *
 * Requirements:
 *      To use this tutorial you will need a functional installation of
 *      the of lg3d (the stable version is the best version to begin with).
 *      This can be found at the lg3d web site. Follow the "Getting started 
 *      with Project Looking Glass" link for installation instructions.
 *      Additionally, you need to have the Java JDK 5. 
 * 
 * Steps:
 *      1. Create a root container
 *      2. Create a box to put in the container
 *      3. Put the box in the container
 *      4. Initialize the container
 *      5. Compile the code
 *      6. Package the Tutorial to run in LG3D
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
        //*** Now, let's try to put a box of 8cm x 6cm x 4cm at the center 
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
        // of 8cm x 6cm x 4cm. Note that we need to specify half the length 
        // of each dimension to the constructor of the predefined Box class. 
        // It creates a box that lies within the bounding box, [-x, -y, -z] 
        // and [x, y, z], where x, y and z are the first three arguments to 
        // the constructor.
        
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
        // LG3D core library and Java 1.6 to compile. The core library can be
        // found under the lib directory of the LG3D distribution (replace 
        // $LG3DHOME with the path to the LG3D distribution).
        // 
        //     javac -cp "$LG3DHOME/lib/ext/lg3d-core.jar" Tutorial1.java
        
        // 
        // Step 6: Package the Tutorial to run in LG3D
        // -------------------------------------------
        // Starting with release 0.7.0 applications can be easily added to 
        // the taskbar so they can be run. Upon initialization LG3D looks 
        // for packaged applications in the ext/app directory. 
        // Therefore to run our tutorial we just need to create a jar file
        // and specify some parameters in the manifest file.
        // 
        // Manifest files provide basic information about the data contained
        // within the jar file. More information about Manifest files can be
        // read at http://java.sun.com/docs/books/tutorial/jar/manifest/index.html.
        // 
        // We will create a simple manifest file to run this application.
        // Edit a file Tutorial1.MF:
        // 
        //     Implementation-Title: Tutorial 1
        //     Main-Class: Tutorial1
        //
        // These parameters mean:
        // 
        //     Implementation-Title: The name of the application.
        //     Main-Class: This is the standard manifest parameter that 
        //     specifies the class file to execute when executing a jar file
        //     (e.g. java -jar syntax). 
        //
        // Next we create a jar file using the manifiest file and the
        // Tutorial1.class file.
        //
        //     jar cmvf Tutorial1.MF Tutorial1.jar Tutorial1.class
        // 
        // Copy the Tutorial1.jar to $LG3DHOME/ext/app creating the directory
        // if it does not exist.
        
        //
        // Step 7: Run our application
        // ---------------------------
        // Now that the jar file is copied into the ext/app directory
        // you just need to start lg3d as normal, and the tutorial will
        // be added to the taskbar. It uses the default application icon.
        // 
        //     lg3d/bin/lg3d-dev
        // 
        // Click on the Tutorial1 button. The lg3d desktop should be displayed
        // with a pale green screen box in the center.
        // In actual fact, only a pale green square can be seen - in the 
        // next tutorial the box will be rotated to make it look like a box.
    }
}
