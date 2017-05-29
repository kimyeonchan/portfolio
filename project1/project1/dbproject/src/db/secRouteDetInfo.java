package db;

import java.net.URL;

public class secRouteDetInfo {
	private int R_ID;
	private int C_ID;
	private URL Image;

	public secRouteDetInfo(int R_ID,int C_ID, URL Image){
		this.R_ID = R_ID;
		this.C_ID = C_ID;
		this.Image = Image;
	}
	public secRouteDetInfo(){
		this.R_ID = -1;
		this.C_ID = -1;
		this.Image = null;
	}
	
	public void setR_ID(int R_ID){
		this.R_ID = R_ID;
	}
	public int getR_ID(){
		return R_ID;
	}
	public void setC_ID(int C_ID){
		this.C_ID = C_ID;
	}
	public int getC_ID(){
		return C_ID;
	}
	public void setImage(URL Image){
		this.Image = Image;
	}
	public URL getImage(){
		return Image;
	}
}
