package vn.framgia.process;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {
	public static byte[][] loadImage() {
		BufferedImage image;
		try {
			image = ImageIO.read(new File("/home/framgia/photo.jpg"));
			byte[][] pixels = new byte[image.getWidth()][];

			for (int x = 0; x < image.getWidth(); x++) {
				pixels[x] = new byte[image.getHeight()];

				for (int y = 0; y < image.getHeight(); y++) {
					pixels[x][y] = (byte) (image.getRGB(x, y) == 0x00FFFFFF ? 0 : 1);
					System.out.println(image.getRGB(x, y));
				}
			}
			return pixels;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static void makeImage(byte[][] pixels, int width, int height) {
		try {

			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
			Graphics2D g2d = bufferedImage.createGraphics();
			g2d.setColor(Color.black);
			for (int i = 0; i < width; i++) {
				for(int j= 0; j<height;j++) {
					if(pixels[i][j]==1) {
					g2d.drawRect(i, j, 1, 1);}
				}
			}
			// Save as PNG
			File file = new File("/home/framgia/myimage.png");
			ImageIO.write(bufferedImage, "png", file);

			// Save as JPEG
			file = new File("/home/framgia/myimage.jpg");
			ImageIO.write(bufferedImage, "jpg", file);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		byte[][] bs =loadImage();
		makeImage(bs, 100, 100);
	}
}
