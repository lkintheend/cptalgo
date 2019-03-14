package vn.framgia.process;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {
	public static BinaryImage loadImage(String path) {
		BufferedImage image;
		try {
			image = ImageIO.read(new File(path));
			byte[] pixels = new byte[image.getWidth() * image.getHeight()];

			for (int x = 0; x < image.getWidth(); x++) {
				for (int y = 0; y < image.getHeight(); y++) {
					pixels[x * image.getWidth() + y] = (byte) (image.getRGB(x, y) == Color.BLACK.getRGB() ? 1 : 0);
				}
			}
			return new BinaryImage(image.getWidth(), image.getHeight(), pixels);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static void makeImage(BinaryImage binaryImage, String savePath) {
		try {
			int width = binaryImage.getWidth();
			int height = binaryImage.getHeight();
			byte[] pixels = binaryImage.getImage();
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
			Graphics2D g2d = bufferedImage.createGraphics();

			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					if (pixels[i * width + j] == 1) {
						g2d.setColor(Color.black);
						g2d.drawLine(i, j, i, j);
					} else {
						g2d.setColor(Color.white);
						g2d.drawLine(i, j, i, j);
					}
				}
			}
			g2d.dispose();
			File file = new File(savePath);
			ImageIO.write(bufferedImage, "png", file);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		BinaryImage bs = loadImage();
//		makeImage(bs);
//	}
	public static void main(String... args) {

        try {

            File input = new File("/home/framgia/images.jpeg");
            BufferedImage image = ImageIO.read(input);

            BufferedImage result = new BufferedImage(
                    image.getWidth(),
                    image.getHeight(),
                    BufferedImage.TYPE_BYTE_BINARY);

            Graphics2D graphic = result.createGraphics();
            graphic.drawImage(image, 0, 0, Color.WHITE, null);
            graphic.dispose();

            File output = new File("/home/framgia/demo4.png");
            ImageIO.write(result, "png", output);

        }  catch (IOException e) {
            e.printStackTrace();
        }

    }
}
