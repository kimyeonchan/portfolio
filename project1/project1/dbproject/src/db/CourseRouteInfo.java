package db;

import java.net.URL;

public class CourseRouteInfo {
	private int R_ID;
	private int C_ID;
	private String Name;
	private String Description;
	private String Tag;
	private URL Image;
	private URL Link;
	private String Distance;
	private String Time;
	private String Text;
	
	public CourseRouteInfo(int R_ID, int C_ID, String Name, String Description, String Tag, URL Image, URL Link, String Distance, String Time, String Text){
		this.R_ID = R_ID;
		this.C_ID = C_ID;
		this.Name = Name;
		this.Description = Description;
		this.Tag = Tag;
		this.Image = Image;
		this.Link = Link;
		this.Distance = Distance;
		this.Time = Time;
		this.Text = Text;
	}
	public CourseRouteInfo(){
		this.R_ID = -1;
		this.C_ID = -1;
		this.Name = "-";
		this.Description = "-";
		this.Tag = "-";
		this.Image = null;
		this.Link = null;
		this.Distance = "-";
		this.Time = "-";
		this.Text = "-";
	}
	
	public int getR_ID() {
		return R_ID;
	}
	public void setR_ID(int R_ID) {
		this.R_ID = R_ID;
	}
	public int getC_ID() {
		return C_ID;
	}
	public void setC_ID(int C_ID) {
		this.C_ID = C_ID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String Description) {
		this.Description = Description;
	}
	public String getTag() {
		return Tag;
	}
	public void setTag(String Tag) {
		this.Tag = Tag;
	}
	public URL getImage() {
		return Image;
	}
	public void setImage(URL Image) {
		this.Image = Image;
	}
	public URL getLink() {
		return Link;
	}
	public void setLink(URL Link) {
		this.Link = Link;
	}
	public String getDistance() {
		return Distance;
	}
	public void setDistance(String Distance) {
		this.Distance = Distance;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String Time) {
		this.Time = Time;
	}
	public void setText(String Text){
		this.Text = Text;
	}
	public String getText(){
		return Text;
	}
}
