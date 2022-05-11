package imagenes;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FondoPanel extends JPanel{
	private ImageIcon imagen;
	@Override
	public void paint(Graphics g) {
		Dimension dimension=this.getSize();
		imagen=new ImageIcon(getClass().getResource("/imagenes/imagen.jpg"));
		g.drawImage(imagen.getImage(), 0, 0, dimension.width, dimension.height, null);
		setOpaque(false);
super.paintChildren(g);

}
}
