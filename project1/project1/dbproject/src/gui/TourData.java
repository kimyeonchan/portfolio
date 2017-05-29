package gui;

import java.net.URL;

public class TourData {
	private int ID;
	private String Name;
	private String Address;
	private URL Image;
	private int LikeCnt;
	
	public TourData(int ID, String Name, String Address, URL Image, int LikeCnt){
		this.ID = ID;
		this.Name = Name;
		this.Address = Address;
		this.Image = Image;
		this.LikeCnt = LikeCnt;
	}
	
	public void setID(int ID){
		this.ID = ID;
	}
	public int getID(){
		return this.ID;
	}
	public void setName(String Name){
		this.Name = Name;
	}
	public String getName(){
		return this.Name;
	}
	public void setAddress(String Address){
		this.Address = Address;
	}
	public String getAddress(){
		return this.Address;
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
