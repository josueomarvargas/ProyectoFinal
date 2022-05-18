package vistas.ventanas.custom.containers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import javax.swing.JPanel;

public class CustomPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	/** Tamaño de la línea del border */
	protected int strokeSize = 0;
	/** Color de la sombra */
	protected Color shadowColor = Color.DARK_GRAY;
	/** Establece si el panel crea sombra */
	protected boolean shady = true;
	/** Establece si es de alta resolucion */
	protected boolean highQuality = true;
	/** Valores para el radio de la curva de las esquinas */
	protected Dimension arcs = new Dimension(25, 25);
	/** Distancia entre el borde de la sombra y el borde del panel */
	protected int shadowGap = 5;
	/** Desplazamiento de la sombra */
	protected int shadowOffset = 10;
	/** Valor de la transparencia de la sombra. ( 0 - 255) */
	protected int shadowAlpha = 150;

	public CustomPanel() {
		super();
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.WHITE);
		int width = getWidth();
		int height = getHeight();
		int shadowGap = this.shadowGap;
		Color shadowColorA = new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(),
				shadowAlpha);
		Graphics2D graphics = (Graphics2D) g;

		// Sets antialiasing if HQ.
		if (highQuality) {
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}

		// Draws shadow borders if any.
		if (shady) {
			graphics.setColor(shadowColorA);
			graphics.fillRoundRect(shadowOffset, // X position
					shadowOffset, // Y position
					width - strokeSize - shadowOffset, // width
					height - strokeSize - shadowOffset, // height
					arcs.width, arcs.height);// arc Dimension
		} else {
			shadowGap = 1;
		}

		// Draws the rounded opaque panel with borders.
		graphics.setColor(getBackground());
		graphics.fillRoundRect(0, 0, width - shadowGap, height - shadowGap, arcs.width, arcs.height);
		graphics.setColor(getForeground());
		graphics.setStroke(new BasicStroke(strokeSize));
		graphics.drawRoundRect(0, 0, width - shadowGap, height - shadowGap, arcs.width, arcs.height);

		// Sets strokes to default, is better.
		graphics.setStroke(new BasicStroke());
	}
}
