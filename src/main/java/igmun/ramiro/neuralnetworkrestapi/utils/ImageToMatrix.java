package igmun.ramiro.neuralnetworkrestapi.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ImageToMatrix {
  BufferedImage image;

  public ImageToMatrix(byte[] data) throws IOException {
    this.image = readImage(data);
  }

  private BufferedImage readImage(byte[] data) throws IOException {
    BufferedImage original = ImageIO.read(new ByteArrayInputStream(data));
    BufferedImage result = new BufferedImage(
            original.getWidth(),
            original.getHeight(),
            BufferedImage.TYPE_INT_RGB);
    result.createGraphics().drawImage(original, 0, 0, Color.WHITE, null);
    return result;
  }

  public double[] scaleToMnistSize() throws IOException {

    BufferedImage newImage = new BufferedImage(28, 28,BufferedImage.TYPE_BYTE_GRAY);
    Graphics2D graphics2D = newImage.createGraphics();
    graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    graphics2D.drawImage(image, 0, 0, 28, 28, null);
    graphics2D.dispose();

    double[] array = newImage.getData().getPixels(0,0,28,28,(double[]) null);
    return Arrays.stream(array).map(v -> Math.abs(255-v)/255).toArray();
  }
}
