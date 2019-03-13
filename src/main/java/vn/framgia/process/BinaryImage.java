package vn.framgia.process;

public class BinaryImage {
	private int width;
	private int height;
	private byte[][] image;
	
	public BinaryImage() {
		super();
	}
	public BinaryImage(int width, int height, byte[][] image) {
		super();
		this.width = width;
		this.height = height;
		this.image = image;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public byte[][] getImage() {
		return image;
	}
	public void setImage(byte[][] image) {
		this.image = image;
	}
	
}
