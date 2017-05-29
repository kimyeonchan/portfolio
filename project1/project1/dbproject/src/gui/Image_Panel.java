package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class Image_Panel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon icon;
	Image img;
	private int width, height;
	//생성자에 url을 넘겨줘서 쓰면 여러개 클래스 안만들어도 사용 가능할 것 같아.
	Image_Panel(URL url, int width, int height){
		this.width = width;
		this.height = height;
		try{
			icon = new ImageIcon(url);
		}
		catch(Exception e){}
		img = icon.getImage();
	}
	Image_Panel(String url, int width, int height){
		this.width = width;
		this.height = height;
		try{
			icon = new ImageIcon(url);
		}
		catch(Exception e){}
		img = icon.getImage();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img, 20, 20,this.width,this.height, this);
	}
}
