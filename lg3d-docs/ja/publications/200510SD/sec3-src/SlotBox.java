import java.io.FileNotFoundException;
import java.io.IOException;
import javax.vecmath.Vector3f;
import org.jdesktop.lg3d.sg.Appearance;
import org.jdesktop.lg3d.sg.Shape3D;
import org.jdesktop.lg3d.utils.action.ActionBoolean;
import org.jdesktop.lg3d.utils.eventadapter.EventAdapter;
import org.jdesktop.lg3d.utils.shape.Box;
import org.jdesktop.lg3d.utils.shape.ImagePanel;
import org.jdesktop.lg3d.utils.shape.SimpleAppearance;
import org.jdesktop.lg3d.wg.Component3D;
import org.jdesktop.lg3d.wg.Container3D;
import org.jdesktop.lg3d.wg.Frame3D;
import org.jdesktop.lg3d.wg.Thumbnail;
import org.jdesktop.lg3d.wg.event.LgEventSource;

public class SlotBox {
    private static final float BOX_SIZE = 0.035f;
    private static final int VEROCITY = 2;

    private static final float IMAGE_WIDTH = 0.05f;
    private static final float IMAGE_HEIGHT = 0.06f;
    private static final int IMAGE_SIZE = 4;
    private static final String IMAGE_BASE = "contributor";
    private static final String IMAGE_EXT = ".jpg";

    private Container3D container;
    private Container3D thumbContainer;

    private SlotRotator rotator;
    private SlotRotator thumbRotator;

    public SlotBox() throws FileNotFoundException, IOException {
        Frame3D frame = new Frame3D();

        container = createSlotBox();
        frame.addChild(container);

        Component3D button = createToggleButton();
        frame.addChild(button);

        Thumbnail thumbnail = new Thumbnail();
        thumbContainer = createSlotBox();
        thumbnail.addChild(thumbContainer);
        thumbnail.setPreferredSize(new Vector3f(BOX_SIZE, BOX_SIZE, BOX_SIZE));
        frame.setThumbnail(thumbnail);

        initRotator();

        frame.setPreferredSize(new Vector3f(BOX_SIZE * 2.0f, BOX_SIZE, BOX_SIZE));
        frame.changeEnabled(true);
        frame.changeVisible(true);        
    }

    private Component3D createToggleButton()
                    throws FileNotFoundException, IOException {
        ToggleButton button = new ToggleButton();
        
        // イベント処理
        EventAdapter adapter = new StateChangeEventAdapter(
                new ActionBoolean() {
                    public void performAction(LgEventSource source, boolean state) {
                        if (state) {
                            rotator.start();
                            thumbRotator.start();
                        } else {
                            rotator.stop();
                            thumbRotator.stop();
                        }
                    }
                });
        button.addListener(adapter);

        // ToggleButton を移動させる
        button.setTranslation(BOX_SIZE * 2.0f, 0.0f, 0.0f);

        return button;
    }

    private Container3D createSlotBox() 
                    throws FileNotFoundException, IOException {
        Container3D container = new Container3D();

        // ボックスの生成
        Component3D boxComponent = createBox();
        container.addChild(boxComponent);

        // イメージパネルを含むコンテナの生成
        Container3D imageContainer = createImageContainer();
        container.addChild(imageContainer);

        return container;
    }

    private Component3D createBox() {
        Appearance appearance 
            = new SimpleAppearance(0.6f, 0.6f, 1.0f, 0.5f);

        Box box = new Box(BOX_SIZE, BOX_SIZE, BOX_SIZE, appearance);

        Component3D component = new Component3D();
        component.addChild(box);

        return component;
    }

    private Container3D createImageContainer()
                    throws FileNotFoundException, IOException {
        Container3D container = new Container3D();

        // 4 枚分のコンポーネントを作成する
        for (int i = 0; i < IMAGE_SIZE; i++) {
            Component3D component = createImageComponent(i);
            container.addChild(component);
        }

        return container;
    }

    private Component3D createImageComponent(int index)
                    throws FileNotFoundException, IOException {
        String filename = IMAGE_BASE + index + IMAGE_EXT;
        ImagePanel panel = new ImagePanel(filename,
                                          IMAGE_WIDTH, IMAGE_HEIGHT);
        
        Component3D component = new Component3D();
        component.addChild(panel);

        // イメージを x 軸を中心に回転させる
        // 回転量は -index * 90度
        component.setRotationAxis(1.0f, 0.0f, 0.0f);
        component.setRotationAngle(-index * (float)Math.PI/2.0f);

        // イメージを移動させて、ボックスの面の上に位置するようにする
        float y = BOX_SIZE * 1.05f * (float)Math.sin(Math.PI / 2.0 * index);
        float z = BOX_SIZE * 1.05f * (float)Math.cos(Math.PI / 2.0 * index);
        component.setTranslation(0.0f, y, z);

        return component;
    }

    private void initRotator() {
        // SlotRotator の生成
        rotator = new SlotRotator(container, VEROCITY);
        thumbRotator = new SlotRotator(thumbContainer, VEROCITY);
    }

    public static void main(String[] args) throws Exception {
        try {
            new SlotBox();
        } catch (FileNotFoundException ex) {
            System.err.println("イメージファイルをオープンできません");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("イメージファイルのロードに失敗しました");
            ex.printStackTrace();
        }
    }
}
