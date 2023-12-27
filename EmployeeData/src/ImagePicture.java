import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 */

/**
 * @author Nawaf
 * Date: March 2023
 * Description: Inherits from the Picture class. Draws an ImageIcon.
 *
 */
public class ImagePicture extends Picture {

	/**
	 * Private data
	 */
	private ImageIcon image;

	/**
	 *
	 */
	public ImagePicture(ImageIcon img) {
		// init the private data
		super(); // calls the Picture() constructor
		this.image = img;
		setMyWidth(img.getIconWidth());
		setMyHeight(img.getIconHeight());
	}

	/**
	 * Overloaded constructor
	 */
	public ImagePicture(ImageIcon img, int x, int y) {
		// init the private data
		super(x, y, img.getIconWidth(), img.getIconHeight());
		this.image = img;
	}

	/**
	 * Override the paint method
	 */
	public void paint(Graphics g) {
		// paint the icon in this.image
		this.image.paintIcon(this, g, getxPos(), getyPos());
	}

	public void setImage(ImageIcon img) {
		this.image=img; // Updates the Imgae
		setMyWidth(img.getIconWidth());
		setMyHeight(img.getIconHeight());
	}


	public ImageIcon getImage() {
		return image;
	}

	/**
	 * @param args
	 * self-testing for ImagePicture
	 */
	public static void main(String[] args) {
		// Create JFrame
		JFrame f = new JFrame("Testing only!");

		f.setSize(400, 350);

		// create ImagePicture object
		ImagePicture p = new ImagePicture(new ImageIcon("minion.png"));

		// add the object to the frame
		f.add(p);

		// set frame visible
		f.setVisible(true);

		// get the width and height of the ImagePicture p
		System.out.println("Width: " + p.getMyWidth() + " Height: " + p.getMyHeight());

		// wait
		JOptionPane.showMessageDialog(null, "Wait");

		// new ImagePicture Object
		ImagePicture p1 = new ImagePicture(new ImageIcon("minion.png"), 100, 150);
		f.add(p1);
		f.setVisible(true); // repaint the whole window

		// wait
		JOptionPane.showMessageDialog(null, "Wait");

		p1.setImage(new ImageIcon("gru.png"));

		p1.repaint();

	}

}