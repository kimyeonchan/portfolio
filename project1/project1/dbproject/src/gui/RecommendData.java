package gui;

import java.net.URL;

public class RecommendData {
	private int C_ID;
	private int R_ID;
	private URL Image;
	private String Text;
	private String Name;
	private String Description;
	private String Distance;
	private String Time;
	public RecommendData(){
		this.C_ID = -1;
		this.setR_ID(-1);
		this.setImage(null);
		this.setText("-");
		this.setName("-");
		this.setDescription("-");
		this.setDistance("-");
		this.setTime("-");
	}
	public RecommendData(int C_ID, int R_ID, URL Image, String Text, String Name, String Description, String Distance, String Time){
		this.C_ID = C_ID;
		this.setR_ID(R_ID);
		this.setImage(Image);
		this.setText(Text);
		this.setName(Name);
		this.setDescription(Description);
		this.setDistance(Distance);
		this.setTime(Time);
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
	public void setName(String Name) {
		this.Name = Name;
	}
	public String getName() {
		return this.Name;
	}
	public void setDescription(String Description) {
		this.Description = Description;
	}
	public String getDescription() {
		return this.Description;
	}
	public void setDistance(String Distance) {
		this.Distance = Distance;
	}
	public String getDistance() {
		return this.Distance;
	}
	public void setTime(String Time) {
		this.Time = Time;
	}
	public String getTime() {
		return this.Time;
	}

}
