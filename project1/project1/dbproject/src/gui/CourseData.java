package gui;

import java.net.URL;

public class CourseData {
	private int ID;
	private String Text;
	private URL Image;
	private int LikeCnt;
	
	public CourseData(int ID,  String Text, URL Image, int LikeCnt){
		this.ID = ID;
		this.Text = Text;
		this.Image = Image;
		this.LikeCnt = LikeCnt;
	}
	public CourseData(){
		this.ID = -1;
		this.Text = "-";
		this.Image = null;
		this.LikeCnt = -1;
	}
	
	public void setID(int ID){
		this.ID = ID;
	}
	public int getID(){
		return this.ID;
	}
	public void setText(String Text){
		this.Text = Text;
	}
	public String getText(){
		return this.Text;
	}
	public void setImage(URL Image){
		this.Image = Image;
	}
	public URL getImage(){
		return this.Image;
	}
	public void setLikeCnt(int LikeCnt){
		this.LikeCnt = LikeCnt;;
	}
	public int getLikeCnt(){
		return this.LikeCnt;
	}
}
