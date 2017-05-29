package gui;

import java.net.URL;

public class TravelData {
	private int ID;
	private URL Image;
	private String Name;
	private String Local;
	private String Ask;
	private String Time;
	private String DayOff;
	private String During;
	private String Season;
	private String Spot;
	private String Post;
	private String ViewTime;
	private String Appoint;
	private String Heritage;
	private String Description;
	
	public TravelData(int ID, URL Image, String Name, String Local, String Ask, String Time, 
			String DayOff, String During, String Season, String Spot, String Post,
			String ViewTime, String Appoint, String Heritage, String Description){
		this.ID = ID;
		this.Image = Image;
		this.Name = Name;
		this.Local = Local;
		this.Ask = Ask;
		this.Time = Time;
		this.DayOff = DayOff;
		this.During = During;
		this.Season = Season;
		this.Spot = Spot;
		this.Post = Post;
		this.ViewTime = ViewTime;
		this.Appoint = Appoint;
		this.Heritage = Heritage;
		this.Description = Description;
	}
	

	public void setID(int ID){
		this.ID = ID;
	}
	public int getID(){
		return this.ID;
	}
	public void setImage(URL Image){
		this.Image = Image;
	}
	public URL getImage(){
		return this.Image;
	}
	public void setName(String Name){
		this.Name = Name;
	}
	public String getName(){
		return this.Name;
	}
	public void setTime(String Time){
		this.Time = Time;
	}
	public String getTime(){
		return this.Time;
	}
	public void setAsk(String Ask){
		this.Ask = Ask;
	}
	public String getAsk(){
		return this.Ask;
	}
	public void setLocal(String Local){
		this.Local = Local;
	}
	public String getLocal(){
		return this.Local;
	}
	public void setDayOff(String DayOff){
		this.DayOff = DayOff;
	}
	public String getDayOff(){
		return this.DayOff;
	}
	public void setDuring(String During){
		this.During = During;
	}
	public String getDuring(){
		return this.During;
	}
	public void setSeason(String Season){
		this.Season = Season;
	}
	public String getSeason(){
		return this.Season;
	}
	public void setSpot(String Spot){
		this.Spot = Spot;
	}
	public String getSpot(){
		return this.Spot;
	}
	public void setPost(String Post){
		this.Post = Post;
	}
	public String getPost(){
		return this.Post;
	}
	public void setViewTime(String ViewTime){
		this.ViewTime = ViewTime;
	}
	public String getViewTime(){
		return this.ViewTime;
	}
	public void setAppoint(String Appoint){
		this.Appoint = Appoint;
	}
	public String getAppoint(){
		return this.Appoint;
	}
	public void setHeritage(String Heritage){
		this.Heritage = Heritage;
	}
	public String getHeritage(){
		return this.Heritage;
	}
	public void setDescription(String Description){
		this.Description = Description;
	}
	public String getDescription(){
		return this.Description;
	}
}
