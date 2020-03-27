import org.jdesktop.lg3d.sg.Texture;
 
public class TextureInfo {
    private Texture texture;
    private float widthRatio;
    private float heightRatio;
 
    TextureInfo(Texture texture, int width, int height) {
        this.texture = texture;
        if (width > height) {
            widthRatio = 1.0f;
            heightRatio = (float)height / width;
        } else {
            widthRatio = (float)width / height;
            heightRatio = 1.0f;
        }
    }
    
    public Texture getTexture() {
        return texture;
    }
    
    public float getWidthRatio() {
        return widthRatio;
    }
  
    public float getHeightRatio() {
        return heightRatio;
    }
}
