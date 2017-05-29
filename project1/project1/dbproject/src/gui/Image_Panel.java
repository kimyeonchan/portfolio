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
	//�����ڿ� url�� �Ѱ��༭ ���� ������ Ŭ���� �ȸ��� ��� ������ �� ����.
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
