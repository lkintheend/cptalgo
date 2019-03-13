package vn.framgia.process;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {
	public static BinaryImage loadImage() {
		BufferedImage image;
		try {
			image = ImageIO.read(new File("D:/photo.png"));
			System.out.println(image.getRGB(502, 500));
			byte[][] pixels = new byte[image.getWidth()][];

			for (int x = 0; x < image.getWidth(); x++) {
				pixels[x] = new byte[image.getHeight()];

				for (int y = 0; y < image.getHeight(); y++) {
					int i = image.getRGB(x, y);
					pixels[x][y] = (byte) (i == Color.BLACK.getRGB() ? 1 : 0);
				}
			}
			return new BinaryImage(image.getWidth(), image.getHeight(),pixels);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static void makeImage(BinaryImage binaryImage) {
		try {
			int width = binaryImage.getWidth();
			int height = binaryImage.getHeight();
			byte[][] pixels = binaryImage.getImage();
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
			Graphics2D g2d = bufferedImage.createGraphics();
			g2d.setColor(Color.white);
	        g2d.fillRect(0, 0, width, height);

			g2d.setColor(Color.black);
			for (int i = 0; i < width; i++) {
				for(int j= 0; j<height;j++) {
					if(pixels[i][j]==1) {
						g2d.drawLine(i, j, i, j);
						g2d.setColor(Color.black);
//						g2d.dispose();
					} else {
						g2d.drawLine(i, j, i, j);
						g2d.setColor(Color.WHITE);
//						g2d.dispose();
					}
				}
			}
			g2d.dispose();
//			// Save as PNG
//			File file = new File("/home/framgia/myimage.png");
//			ImageIO.write(bufferedImage, "png", file);

			// Save as JPEG
			File file = new File("D:/demoimage1.jpg");
			ImageIO.write(bufferedImage, "jpg", file);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		BinaryImage bs = loadImage();
		makeImage(bs);
	}
//	public static void main(String... args) {
//
//        try {
//
//            File input = new File("D:/pho1.jpg");
//            BufferedImage image = ImageIO.read(input);
//
//            BufferedImage result = new BufferedImage(
//                    image.getWidth(),
//                    image.getHeight(),
//                    BufferedImage.TYPE_BYTE_BINARY);
//
//            Graphics2D graphic = result.createGraphics();
//            graphic.drawImage(image, 0, 0, Color.WHITE, null);
//            graphic.dispose();
//
//            File output = new File("D:/demoimage.jpg");
//            ImageIO.write(result, "jpg", output);
//
//        }  catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}
