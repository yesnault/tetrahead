package gui;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*; 

/**
 * JPanel dans lequel on affiche une image 
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class JPanelImageBg extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @uml.property  name="texture"
	 */
	private TexturePaint texture; 
	/**
	 * @uml.property  name="bufferedImage"
	 */
	private BufferedImage bufferedImage; 

	/**
	 * Construit le panel avec une image dont on passe le nom
	 * @param fileName nom de l'image
	 */
	public JPanelImageBg( String fileName)
	{	
		setLayout(null);
		this.bufferedImage = this.toBufferedImage(Toolkit.getDefaultToolkit().getImage(fileName));
		this.texture = new TexturePaint(bufferedImage,new Rectangle(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight()));
		this.setSize(bufferedImage.getWidth(), bufferedImage.getHeight());
		this.setPreferredSize(getSize()); 
	} 

	/**
	 * Construit un panel à partir d'une image bufferisée.
	 * @param bufferedImage
	 */
	public JPanelImageBg(BufferedImage bufferedImage) 
	{
		setLayout(null);
		this.bufferedImage = bufferedImage;
		this.texture = new TexturePaint(bufferedImage,new Rectangle(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight()));
		this.setSize(bufferedImage.getWidth(), bufferedImage.getHeight());
		this.setPreferredSize(getSize());
	}
	
	@Override
	public void paintComponent(Graphics g)
	{	
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g; 
		g2d.setPaint(texture);
		g2d.fillRect(0, 0, getWidth(), getHeight() );
				
	}


	private BufferedImage toBufferedImage(Image image)
	{	image = new ImageIcon(image).getImage(); 

		BufferedImage bufferedImage = new BufferedImage( image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB); 
		Graphics g = bufferedImage.createGraphics(); 

		g.setColor(Color.white); 
		g.fillRect(0, 0, image.getWidth(null), 
		image.getHeight(null)); 
		g.drawImage(image, 0, 0, null); 
		g.dispose(); 
		return bufferedImage; 
	}

}

