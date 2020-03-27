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
 * $Date: 2006-05-09 17:46:22 $
 * $State: Exp $
 */

Inoffizielle Übersetzung:
/**
 * Projekt Looking Glass
 *
 * $RCSfile: Tutorial1.java,v $
 *
 * Copyright (c) 2004, Sun Microsystems, Inc., All Rights Reserved
 *
 * Bei Weiterverteilung in Form des Quellcodes müssen das obenstehende Copyright und diese
 * Bedingung reproduziert werden.
 *
 * Der Inhalt dieser Date unterliegt der GNU General Public
 * License, Version 2 (die "Lizenz"); diese Datei darf nur 
 * entsprechend den Bestimmungen der Lizenz verwendet werden. Eine Kopie der Lizenz ist
 * unter http://www.opensource.org/licenses/gpl-license.php verfügbar.
 *
 * Revision: 0.0 
 * Date: 0000/00/00 00:00:00 
 * State: 000 
 */


// org.jdesktop.lg3d.wg.* -- Projekt Looking Glass Core Komponenten
import org.jdesktop.lg3d.wg.Frame3D;
import org.jdesktop.lg3d.wg.Component3D;
// org.jdesktop.lg3d.utils.shape.* -- Nützliche Formen u.a.
// Klassen, die in Beziehung zu Shape3D stehen.
import org.jdesktop.lg3d.utils.shape.Box;
import org.jdesktop.lg3d.utils.shape.Primitive;
import org.jdesktop.lg3d.utils.shape.SimpleAppearance;

/**
 * Stunde 1 -- Setzen einer blassgrünen Box in den 3D-Raum
 */
public class Tutorial1 {
    public static void main(String[] args) {
        new Tutorial1();
    }
    
    public Tutorial1() {
        // Als Erstes müssen wir für diese 3D-Anwendung einen root-Container erstellen.
	  // Die Frame3D-Klasse dient diesem Zweck. 
        // Wir können Frame3D erweitern(beerben), oder einfach eine Instanz erstellen und zu ihr die Komponenten hinzufügen.
	  // In diesem Beispiel nehmen wir den zweiten Ansatz
	
        // Frame3D frame3d = new Frame3D();
        // Also, versuchen wir jetzt eine Kiste der Größe 5cm x 4cm x 3cm in das Zentrum 
        // dieser Anwendung zu setzen. 
	  // Vordefinierte Formen, die unter ...utils.shape gefunden werden können, sind für
        // diesen Zweck handlich. Nebenbei, in welcher Farbe willst du sie eigentlich zeichnen?
        // Probieren wir einmal ein blasses Grün in diesem Beispiel aus. 
	  //
        // Die Projekt Looking Glass 3D APIs basieren auf Java 3D. In Java 3D wird standardmäßig das metrische System verwendet, 
        // wobei eine (1) Einheit einem Meter entspricht. Und bei korrekter Konfiguration ergibt das Zeichnen eines Objekts der 
        // Größe 0.02 ein Objekt auf dem Bildschirm, das etwa 2cm groß ist.
        //
        // Beachte aber, dass die Standardkonfiguration für lg3d für eine perspektivische Ansicht erstellt wurde, d.h. die reale 
        // Größe eines Objekts hängt von dessen Entfernung zum Betrachter ab. Standardmäßig wird der 3D Szenenmanager von lg3d das
        // Objekt an eine vernünftige Position setzen, wo die reale Größe der beabsichtigten nahekommt.
        //
        // Die API bietet auch eine praktische Klasse um Farben anzugeben an. Diese ist SimpleAppearance.
	  // Zusätzlich kann die Farbe des Objekts auch auf detailliertere Weise beschrieben werden, 
        // inklusive den Grad des Leuchtens und die Leuchtfarbe, der Durchsichtigkeit und der daraufgelegten 
        // Texturen. Also erstellen wir hier ein appearance-Objekt, dass eine blassgrüne Frabe beinhaltet.
	        
        SimpleAppearance app = new SimpleAppearance(0.6f, 0.8f, 0.6f);
        
        // Wie du wahrscheinlich erraten hast, würde (1.0f, 1.0f, 1.0f) Weiß bedeuten.
        // Wenn du die Frbe des Objekts auf detailliertere Weise angeben möchtest
        // inklusive den Grad des Leuchtens der Leuchtfarbe, etc, lies bitte die
        // Dokumentation zu den Apperance und Material Klassen.
	  //
        // Standardmäßig teilen alle Primitven mit den selben Parametern auch ihre
	  // Geometrie (z.B. kannst du 50 Kugeln in deiner Szene haben, aber die Geometrie
	  // wird nur einmal gespeichert). Deshalb betrifft eine änderung an einer Primitven
	  // dann alle geteilten Knoten. Eine andere Nebenwirkung hiervon ist,
	  // dass die Fähigkeiten (?) der Geometrie geteilt werden,
	  // und dass sie, sobald einer der geteilten Knoten aktiv ist, nicht gesetzt werden
	  // können.
	  // Benutze das GEOMETRY_NOT_SHARED Flag, wenn du dieses Verhalten nicht willst.
        //
	  // Merke dir, dass wenn du deine eigenen Flags etzt, die Standardflags überschrieben
	  // werden; deshalb muss auch GENERATE_NORMALS gesetzt werden, wenn du sie wieder zurück willst. 
        //
        // Jetzt erstellen wir endlich die Box der Größe 5cm x 4cm x 3cm
  	  // mit der gerade erstellten Farbe. 
                
        Box box = new Box(0.05f, 0.04f, 0.03f, Primitive.GENERATE_NORMALS | Primitive.GEOMETRY_NOT_SHARED, app);
        
        // Dann fügen wir die Box zum Toplevel-Container hinzu, sodass 
        // sie auf dem Bildschirm angezeigt wird.  
        // 
        // Es gibt allerdings eine Regel, die berücksichtigt werden muss: wir können NUR Component3D Objekte
        // zu einem Frame3D (präziser: Container3D, der Frame3D erweitert) hinzufügen.
        // Component3D ist die Basiskomponente aller Projekt Looking 
        // Glass 3D Komponenten.  Eine Komponente kann mehrere Shape3Ds beinhalten,
        // die seine Form definieren (Box erbt ja von Shape3D) und
        // ein Frame3D kann mehrere Component3Ds verwalten
        // (inklusive eingebetteter Container3Ds)Also müssen wir ein Component3D erstellen
        //
        // Also müssen wir ein Component3D erstellen
        
        Component3D comp = new Component3D();
        
        // dann fügen wir die Kiste hinzu.
        
        comp.addChild(box);
        
        // Schließlich fügen wir es zum root Container hinzu.
        
        frame3d.addChild(comp);
        
        // Wir müssen noch einige weitere Dinge tun um die Box sichtbar zu machen.
        // 
        // Gewöhnlich müssen wir das Folgende durchführen, bevor wir irgendein Objekt
        // sichtbar machen können. Wenn nicht können wir nicht damit arbeiten.
        // Stell' es dir im Moment einfach als eine magische Funktion vor und 
        // merke dir, nachzusehen, ob du sicher diesen Aufruf durchführst, wenn
        // in eine Situation gerätst, in der der Mauscursor sich nicht über dein Objekt 
        // bewegen lässt.
        

        frame3d.setCapabilities();
        
        // Du bist jetzt vielleicht interessiert, was passiert, wenn du den
        // obigen Aufruf löscht.  Für alle, die Java 3D etwas tiefer kennen
        // -- dieser Aufruf stellt sicher, dass die Fähigkeiten (?) der Formen,
        // die zu diesem Frame3D gehören (i.e. diese Anwendung) so gesetzt werden, dass alle Formen 
        // auswählbar sind.
       
        // Eine andere Sache, die man besser tun sollte, ist eine Größenangabe für die Anwendung zu setzen.
        // Die aktuelle SceneManager Implementation hängt von dieser Größenangabe ab 
        // um die 3D Anwendungen im 3D Raum, den er verwaltet anzuordnen können
        // <=> tutorial1.html sagt anderes! (Anm. d. Ü.)

        frame3d.setSize(0.04f, 0.03f, 0.02f);
        
        // Wenn das Frame3D Objekt erstellr wird, ist es nicht sichtbar -- es ist nicht
        // Teil des Scenegraphs.  Der setActive() Aufruf fügt nun
        // die gegebene 3D Anwendung zum Scenegraph hinzu und macht sie sichtbar. 
        // Präziser ausedrückt, beginnt der Aufruf die Interaktion mit dem 
   	  // 3D SzenenManager und der Manager kümmert sich um die Details 
	  // der Art und Weise, auf die die Anwendung präsentiert wird (z.B. Ort, Größe, etc.).

        frame3d.setActive(true);
        
        // Und nun zum letzten Schritt.
        // Standardmäßig, ist ein Frame3D Objekt, selbst nachdem es zum Scenegraph hinzugefügt wurde unsichtbar
        // Der folgende Aufruf macht es schließlich sichtbar.
        // Merke dir, dass setVisible() im Vergleich zu dem
	  // setActive() Aufruf ein ziemliches Leichtgewicht ist, deshalb sollte auch 
	  // setVisible() verwendet werden, um ein Objekt kurzfristig zu verbergen
        
        frame3d.setVisible(true);
        
        // Jetzt solltest du eine blassgrüne Box auf dem Bildschirm sehen. 
        // Was, du hast nur ein grünes Rechteck gesehen?
        // Wir müssen sie ja erst noch rotieren... ;-)