import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import javax.imageio.ImageIO;
import javax.vecmath.Vector3f;
import org.jdesktop.lg3d.sg.Appearance;
import org.jdesktop.lg3d.sg.utils.image.*;
import org.jdesktop.lg3d.utils.action.*;
import org.jdesktop.lg3d.utils.actionadapter.*;
import org.jdesktop.lg3d.utils.c3danimation.*;
import org.jdesktop.lg3d.utils.eventadapter.*;
import org.jdesktop.lg3d.utils.shape.*;
import org.jdesktop.lg3d.wg.*;
import org.jdesktop.lg3d.wg.event.LgEventSource;
 
public class MonolithViewer {
    private static final String IMAGE_LIST_FILE = "images.txt";
    private static final float ROUND_RATIO = 1.1f;
    private static final float WIDTH = 0.2f;
    private static final float HEIGHT = 0.2f;
    private static final float DEPTH = 0.01f;
    private static final float EDGE = 0.01f;
    private static final float BLUR = 0.001f;
    private List<TextureInfo> textureInfo;
    private int index;
    private ScheduledExecutorService service;
    private FuzzyEdgePanel imagePanel;
    private FuzzyEdgePanel thumbnailPanel;
    private Component3D component;
    private Thumbnail thumbnail;
  
    public MonolithViewer() {
        try {
            textureInfo = loadTextures();
        } catch (IOException ex) {
            System.err.println("イメージのロードに失敗しました");
            ex.printStackTrace();
            return;
        }
        service = Executors.newScheduledThreadPool(1);
        Frame3D frame = new Frame3D();
        // コンポーネントの設定
        component = new Component3D();
        imagePanel = initComponent(component, textureInfo.get(0));
        frame.addChild(component);
        frame.setPreferredSize(new Vector3f(WIDTH * ROUND_RATIO,
                                            HEIGHT * ROUND_RATIO,
                                            DEPTH));
        // サムネイルの設定
        thumbnail = new Thumbnail();
        thumbnailPanel = initComponent(thumbnail, textureInfo.get(0));
        thumbnail.setPreferredSize(new Vector3f(WIDTH * ROUND_RATIO,
                                                HEIGHT * ROUND_RATIO,
                                                DEPTH));
        frame.setThumbnail(thumbnail);
        initEventAdapter();
        frame.changeEnabled(true);
        frame.changeVisible(true);        
    }
  
    // イメージをロードし、テクスチャを作成する
    private List<TextureInfo> loadTextures() throws IOException {
        List<TextureInfo> textureInfo = new ArrayList<TextureInfo>();
        ClassLoader classloader = this.getClass().getClassLoader();
        InputStream stream 
            = classloader.getResourceAsStream(IMAGE_LIST_FILE);
        if (stream == null) {
            stream = new FileInputStream(IMAGE_LIST_FILE);
        }
        BufferedReader reader
            = new BufferedReader(new InputStreamReader(stream));
        while (true) {
            String imageFilename = reader.readLine();
            if (imageFilename == null) {
                break;
            }
            InputStream imageStream
                = classloader.getResourceAsStream(imageFilename);
            if (imageStream == null) {
                imageStream = new FileInputStream(imageFilename);
            }
            BufferedImage image = ImageIO.read(imageStream);
            TextureLoader loader = new TextureLoader(image);
            TextureInfo info = new TextureInfo(loader.getTexture(),
                                               image.getWidth(),
                                               image.getHeight());
            textureInfo.add(info);
        }
        if (textureInfo.size() == 0) {
            throw new IOException();
        }
        return textureInfo;
    }
  
    // パネル、アピアランスを生成しコンポーネントに追加
    private FuzzyEdgePanel initComponent(Component3D component,
                                         TextureInfo info) {
        Appearance appearance
            = new SimpleAppearance(1.0f, 0.6f, 0.6f, 1.0f, 
                                   SimpleAppearance.DISABLE_CULLING);
        GlassyPanel basePanel 
            = new GlassyPanel(WIDTH * ROUND_RATIO,
                              HEIGHT * ROUND_RATIO,
                              DEPTH, EDGE, appearance);
        Appearance imageAppearance
            = new SimpleAppearance(1.0f, 1.0f, 1.0f, 1.0f, 
                                   SimpleAppearance.ENABLE_TEXTURE 
                                   | SimpleAppearance.DISABLE_CULLING);
        imageAppearance.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
        imageAppearance.setTexture(info.getTexture());
         FuzzyEdgePanel panel 
            = new FuzzyEdgePanel(WIDTH * info.getWidthRatio(),
                                 HEIGHT * info.getHeightRatio(),
                                 BLUR, imageAppearance);
        component.addChild(basePanel);
        component.addChild(panel);
        return panel;
    }
 
    // イベントリスナの登録
    private void initEventAdapter() {
        component.setAnimation(new NaturalMotionAnimation(1000));
        component.addListener(
            new MouseClickedEventAdapter(
                new ActionNoArg() {
                    public void performAction(LgEventSource source) {
                        if (source != null) {
                            actionPerfomed();
                        }
                    }
                }));
        component.addListener(
            new MouseClickedEventAdapter(
                new ToggleAdapter(
                    new RotateActionBoolean(component,
                                    (float)Math.PI * 2.0f, 1000))));
        component.addListener(
            new MouseClickedEventAdapter(
                new ToggleAdapter(
                    new RotateActionBoolean(thumbnail,
                                    (float)Math.PI * 2.0f, 1000))));
        component.setMouseEventPropagatable(true);
    }
 
    // イベント処理
    private void actionPerfomed() {
        Runnable runnable = new Runnable() {
                public void run() {
                    changeTexture();
                }
            };
        service.schedule(runnable, 200l, TimeUnit.MILLISECONDS);
    }
 
    // テクスチャの切り替え
    private void changeTexture() {
        index++;
        if (index == textureInfo.size()) {
            index = 0;
        }
        TextureInfo info = textureInfo.get(index);
        imagePanel.setSize(WIDTH * info.getWidthRatio(),
                           HEIGHT * info.getHeightRatio(),
                           1.0f, 1.0f, 0, 0, 0.0f, 0.0f);
        thumbnailPanel.setSize(WIDTH * info.getWidthRatio(),
                               HEIGHT * info.getHeightRatio(),
                               1.0f, 1.0f, 0, 0, 0.0f, 0.0f);
        imagePanel.getAppearance().setTexture(info.getTexture());
        thumbnailPanel.getAppearance().setTexture(info.getTexture());
    }
 
    public static void main(String[] args) {
        new MonolithViewer();
    }
}
