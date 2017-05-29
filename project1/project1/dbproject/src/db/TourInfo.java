package db;

import java.net.URL;

public class TourInfo {

	private int T_ID;
	private String Name;
	private String Ddo;
	private String Tag;
	private URL Image;
	private int LikeCnt;
	private URL Link;
	private String Local, Ask, During, Time, DayOff, Season, Spot, Post, ViewTime, Appoint, Heritage, Description;
	
	public TourInfo(int T_ID, String Name, String Ddo, String Tag, URL Image, int LikeCnt, URL Link, 
			String Local, String Ask, String During, String Time, String DayOff, String Season, 
			String Spot, String Post, String ViewTime, String Appoint, String Heritage, String Description){
		this.T_ID = T_ID;
		this.Name = Name;
		this.Ddo = Ddo;
		this.Tag = Tag;
		this.Image = Image;
		this.LikeCnt = LikeCnt;
		this.Link = Link;
		this.T_ID = T_ID;
		this.Local = Local;
		this.Ask = Ask;
		this.During = During;
		this.Time = Time;
		this.DayOff = DayOff;
		this.Season = Season;
		this.Spot = Spot;
		this.Post = Post;
		this.ViewTime = ViewTime;
		this.Appoint = Appoint;
		this.Heritage = Heritage;
		this.Description = Description;
	}
	public TourInfo(){
		this.T_ID = -1;
		this.Name = "-";
		this.Ddo = "-";
		this.Tag = "-";
		this.Image = null;
		this.LikeCnt = 0;
		this.Link = null;
		this.Local = "-";
		this.Ask = "-";
		this.During = "-";
		this.Time = "-";
		this.DayOff = "-";
		this.Season = "-";
		this.Spot = "-";
		this.Post = "-";
		this.ViewTime = "-";
		this.Appoint = "-";
		this.Heritage = "-";
		this.Description = "-";
	}

	public void setID(int T_ID){
		this.T_ID = T_ID;
	}
	public int getID(){
		return T_ID;
	}
	public void setName(String Name){
		this.Name = Name;
	}
	public String getName(){
		return Name;
	}	
	public void setDdo(String Ddo){
		this.Ddo = Ddo;
	}
	public String getDdo(){
		return Ddo;
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
	public void setLink(URL Link){
		this.Link = Link;
	}
	public URL getLink(){
		return Link;
	}	
	public void setLocal(String Local) {
		this.Local = Local;
	}
	public String getLocal() {
		return Local;
	}
	public void setAsk(String Ask) {
		this.Ask = Ask;
	}
	public String getAsk() {
		return Ask;
	}
	public void setDuring(String During) {
		this.During = During;
	}
	public String getDuring() {
		return During;
	}
	public void setTime(String Time) {
		this.Time = Time;
	}
	public String getTime() {
		return Time;
	}
	public void setDayOff(String DayOff) {
		this.DayOff = DayOff;
	}
	public String getDayOff() {
		return DayOff;
	}
	public void setSeason(String Season) {
		this.Season = Season;
	}
	public String getSeason() {
		return Season;
	}
	public void setSpot(String Spot) {
		this.Spot = Spot;
	}
	public String getSpot() {
		return Spot;
	}
	public void setPost(String Post) {
		this.Post = Post;
	}
	public String getPost() {
		return Post;
	}
	public void setViewTime(String ViewTime) {
		this.ViewTime = ViewTime;
	}
	public String getViewTime() {
		return ViewTime;
	}
	public void setAppoint(String Appoint) {
		this.Appoint = Appoint;
	}
	public String getAppoint() {
		return Appoint;
	}
	public void setHeritage(String Heritage) {
		this.Heritage = Heritage;
	}
	public String getHeritage() {
		return Heritage;
	}
	public void setDescription(String Description) {
		this.Description = Description;
	}
	public String getDescription() {
		return Description;
	}
}
