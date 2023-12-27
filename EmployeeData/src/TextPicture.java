import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TextPicture extends Picture {

	private String text;    // Data for Text and Font
	private Font myFont;
	
	//default Constructor
	public TextPicture() {
		super();
		this.myFont = new Font("Monospaced", Font.PLAIN, 12);
		this.text = "";
	}
	
	// Constructor to for specific location and title
	public TextPicture(String title, int x, int y) {
		super();
		setxPos(x);
		setyPos(y);
		this.myFont = new Font("Monospaced", Font.PLAIN, 12);
		this.text = title;
	}
	
	/**
	 * Overloaded constructor 
	 */
	public TextPicture(Font font, String title, int xPos, int yPos, Color color) {
		// Call parent constructor
		super();

		// Initialize my default attributes 
		setFont(font);
		setText(title);
		setxPos(xPos);
		setyPos(yPos);
		setColor(color);
	}
	
	// setter for font
	public void setFont(Font f) {
		this.myFont = f;
	}
	
	// setter for text
	public void setText(String text) {
		this.text = text;
		//repaint();
	}
	
	// getter for text
		public String getText() {
			return this.text;
		}

	// paint method
	public void paint (Graphics g) {
		g.setColor (this.getColor());
		g.setFont(this.myFont);
		g.drawString(this.text, getxPos(), getyPos());
	}
	
	public static void main(String[] args) {
		// self testing main method;            
		JFrame f = new JFrame("Testing");
		
		String title = "My title";
		
		TextPicture p = new TextPicture(title, 10, 20);
		
		p.setFont(new Font("Serif", Font.ITALIC, 24));
		
		f.setSize(400,350);  // size for graphics
		f.add(p);
		f.setVisible(true);
		
		JOptionPane.showMessageDialog(null,"Wait");
		p.setColor(Color.BLUE);
		
		JOptionPane.showMessageDialog(null,"Wait");
		p.setColor(50,50,120);  
		
		JOptionPane.showMessageDialog(null,"Wait");

		p.setxPos(100);
		p.setyPos(100);
		p.setText("New Title");
		p.repaint();  // repaint picture
	}
}
