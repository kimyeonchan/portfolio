package db;

import java.net.URL;

public class CourseInfo {
	private int C_ID;
	private URL Link;
	private String Text;
	private String Tag;
	private URL Image;
	private int LikeCnt;
	
	public CourseInfo(int C_ID, URL Link, String Text, String Tag, URL Image, int LikeCnt){
		this.C_ID = C_ID; 
		this.Link = Link;
		this.Text = Text;
		this.Tag = Tag;
		this.Image = Image;
		this.LikeCnt = LikeCnt;
	}
	public CourseInfo(){
		this.C_ID = -1;
		this.Link = null;
		this.Text = "-";
		this.Tag = "-";
		this.Image = null;
		this.LikeCnt = 0;	
	}
	public void setID(int C_ID) {
		this.C_ID = C_ID;
	}
	public int getID() {
		return C_ID;
	}
	public void setLink(URL Link){
		this.Link = Link;
	}
	public URL getLink(){
		return Link;
	}
	public void setText(String Text){
		this.Text = Text;
	}
	public String getText(){
		return Text;
	}
	public void setTag(String Tag){
		this.Tag = Tag;
	}
	public String getTag(){
		return Tag;
	}
	public void setImage(URL Image){
		this.Image = Image;
	}
	public URL getImage(){
		return Image;
	}
	public void setLikeCnt(int LikeCnt){
		this.LikeCnt = LikeCnt;
	}
	public int getLikeCnt(){
		return LikeCnt;
	}
}
