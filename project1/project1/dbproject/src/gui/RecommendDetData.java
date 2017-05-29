package gui;

import java.net.URL;

public class RecommendDetData {
	private int C_ID;
	private int R_ID;
	private URL Image;
	private String Text;
	private String Description;
	
	public RecommendDetData(int C_ID, int R_ID, URL Image, String Text, String Description){
		this.C_ID = C_ID;
		this.R_ID = R_ID;
		this.Image = Image;
		this.Text = Text;
		this.Description = Description;
	}
	public RecommendDetData(){
		this.C_ID = -1;
		this.R_ID = -1;
		this.Image = null;
		this.Text = "-";
		this.Description = "-";
	}
	public void setC_ID(int C_ID) {
		this.C_ID = C_ID;
	}
	public int getC_ID() {
		return this.C_ID;
	}
	public void setR_ID(int R_ID) {
		this.R_ID = R_ID;
	}
	public int getR_ID() {
		return this.R_ID;
	}
	public void setImage(URL Image) {
		this.Image = Image;
	}
	public URL getImage() {
		return this.Image;
	}
	public void setText(String Text) {
		this.Text = Text;
	}
	public String getText() {
		return this.Text;
	}
	public void setDescription(String Description) {
		this.Description = Description;
	}
	public String getDescription() {
		return this.Description;
	}
}
