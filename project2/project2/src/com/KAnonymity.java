package com;

import java.util.List;

import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.bson.Document;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;


class Pair{
	String data;
	String card;
	Pair(String data,String card){
		this.data=data;
		this.card=card;
	}
}

class BigSet{
	BigInteger trrn2;
	BigInteger trrn1;
	BigInteger tlate;
	String cardCorp;
	BigSet(BigInteger trrn2,BigInteger trrn1,BigInteger tlate,String cardCorp){
		this.trrn2=trrn2;
		this.trrn1=trrn1;
		this.tlate=tlate;
		this.cardCorp=cardCorp;
	}
}

public class KAnonymity {
	static int[][][] latt={{{0,0,0}},
			{{0,0,1},{0,1,0},{1,0,0}},
			{{0,0,2},{0,1,1},{0,2,0},{1,0,1},{1,1,0}},
			{{0,0,3},{0,1,2},{0,2,1},{1,0,2},{1,1,1},{1,2,0}},
			{{0,1,3},{0,2,2},{1,0,3},{1,1,2},{1,2,1}},
			{{0,2,3},{1,1,3},{1,2,2}},
			{{1,2,3}}};
	
	//성별: 남,여     나이: 18~107   최근거래연도:1976~2016
	static String[][] S={{"남","여"},{"*"}};      // i 번째 계층에서의 j값
	
	   // i번째 계층에서 j번째의 k범위
	static int[][][] A={{{18,18},{19,19},{20,20},{21,21},{22,22},{23,23},{24,24},{25,25},{26,26},{27,27},{28,28},{29,29}
	,{30,30},{31,31},{32,32},{33,33},{34,34},{35,35},{36,36},{37,37},{38,38},{39,39}
	,{40,40},{41,41},{42,42},{43,43},{44,44},{45,45},{46,46},{47,47},{48,48},{49,49},{50,50}
	,{51,51},{52,52},{53,53},{54,54},{55,55},{56,56},{57,57},{58,58},{59,59}
	,{60,60},{61,61},{62,62},{63,63},{64,64},{65,65},{66,66},{67,67},{68,68}
	,{69,69},{70,70},{71,71},{72,72},{73,73},{74,74},{75,75},{76,76},{77,77},{78,78},{79,79}
	,{80,80},{81,81},{82,82},{83,83},{84,84},{85,85},{86,86},{87,87},{88,88},{89,89},{90,90},{91,91},{92,92}
	,{93,93},{94,94},{95,95},{96,96},{97,97},{98,98},{99,99},{100,100},{101,101},{102,102},{103,103}
	,{104,104},{105,105},{106,106},{107,107}},
		{{10,19},{20,29},{30,39},{40,49},{50,59},{60,69},{70,79},{80,89},{90,99},{100,109}},
		{{10,109}}};
	
	static int[][][] Y={{{1976,1976},{1977,1977},{1978,1978},{1979,1979},{1980,1980},{1981,1981},{1982,1982}
	,{1983,1983},{1984,1984},{1985,1985},{1986,1986},{1987,1987},{1988,1988},{1989,1989},{1990,1990}
	,{1991,1991},{1992,1992},{1993,1993},{1994,1994},{1995,1995},{1996,1996},{1997,1997},{1998,1998},{1999,1999}
	,{2000,2000},{2001,2001},{2002,2002},{2003,2003},{2004,2004},{2005,2005},{2006,2006},{2007,2007},{2008,2008}
	,{2009,2009},{2010,2010},{2011,2011},{2012,2012},{2013,2013},{2014,2014},{2015,2015},{2016,2016}},
			{{1970,1974},{1975,1979},{1980,1984},{1985,1989},{1990,1994},{1995,1999},{2000,2004},{2005,2009},{2010,2014},{2015,2019}},
			{{1970,1979},{1980,1989},{1990,1999},{2000,2009},{2010,2019}},
			{{1970,2019}}};
	
	static String changeTemptoSex(int ts){
		return ts>2000000 ? "여" : "남";
	}
	
	static int changeTemptoAge(int ta){          //930913
		int birthYear=1900+ta/10000;
		return 2016-birthYear+1;
	}
	
	static int changeTempToYear(int ty){
		return ty/10000;
	}
	
 	public static ArrayList<String> getIdentifier(FindIterable<Document> iterable) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{            //.find() 한 상태
		boolean result=true;
		Set<String> set=null;
		HashMap<String,Integer> map=null;
		ArrayList<String> kdata=new ArrayList<String>();
		ArrayList<Pair> pairData=null;
		ArrayList<BigSet> bigData=new ArrayList<BigSet>();
		
		OPE o=OPE.getOPE();
		AES256Util aes256=AES256Util.createAES256();
		for(Document document: iterable){
			BigInteger trrn2=o.decrypt(BigInteger.valueOf(document.getInteger("rrn2")));
			BigInteger trrn1=o.decrypt(BigInteger.valueOf(document.getInteger("rrn1")));
			BigInteger tlate=o.decrypt(BigInteger.valueOf(document.getInteger("late")));
			String tcardCorp=aes256.aesDecode(document.getString("cardCorp"));
			
			bigData.add(new BigSet(trrn2,trrn1,tlate,tcardCorp));
		}
		
		
		
		
		int K=3;                           // K=3 으로 설정.
		for(int i=0;i<latt.length;i++){                    
			for(int j=0;j<latt[i].length;j++)
			{	
				result=true;
				int SAY[]={0,0,0};
				
				for(int k=0;k<latt[i][j].length;k++){
					SAY[k]=latt[i][j][k];                         //1.어떤 계층을 사용할지를 결정한다.
				}
				
				map=new HashMap<String,Integer>();                 // k 익명성이 적용된 값이 map에 저장.
				pairData=new ArrayList<Pair>();
				for(BigSet document:bigData){
					String data=null;
					
					//BigInteger trrn2=o.decrypt(BigInteger.valueOf(document.getInteger("rrn2")));
					//BigInteger trrn1=o.decrypt(BigInteger.valueOf(document.getInteger("rrn1")));
					//BigInteger tlate=o.decrypt(BigInteger.valueOf(document.getInteger("late")));
					
					
					int ts=document.trrn2.intValue();
					int ta=document.trrn1.intValue();
					int ty=document.tlate.intValue();    //late의 리스트
					
					String sex= changeTemptoSex(ts);            //성별 
					int age=changeTemptoAge(ta);           //나이
					int year=changeTempToYear(ty);             // 거래일 목록
					
					int nS=SAY[0];
					if(nS == 0){
						for(int x=0;x<S[nS].length;x++){
							if(sex.equals(S[nS][x])){                     //sex : 현재 document의 성별
								//sex=S[nS][x];                     
								data=sex;
								break;
							}
						}
					}
					else{
						sex="*";
						data=sex;
					}
					
					////////////////
					int nA=SAY[1];
					for(int x=0;x<A[nA].length;x++){
						for(int y=0;y<A[nA][x].length;y++){
							if(age >= A[nA][x][0] && age <= A[nA][x][1]){
								/////////*******
								data+="             "+A[nA][x][0]+"~"+A[nA][x][1];
								break;
							}
						}
					}
					
					//////////////////
					
					int nY=SAY[2];
					for(int x=0;x<Y[nY].length;x++){
						for(int y=0;y<Y[nY][x].length;y++){
							if(year >= Y[nY][x][0] && year <= Y[nY][x][1]){
								data+="             "+Y[nY][x][0]+"~"+Y[nY][x][1];
								break;
							}
						}
					}
					
					
					             //map에 데이터를 추가하는 코드 넣기.
					
					if(map.get(data)==null){
						map.put(data,1);
					}
					else{
						map.put(data,map.get(data)+1);
					}
					
					pairData.add(new Pair(data,document.cardCorp));    //카드정보 추가하기.
				}
				

				set=map.keySet();
				for(String key:set){
					if(map.get(key) < K){
						result=false;          // false
						break;
					}
				}
				if(result)
					break;
				
			}
			
			if(result)
				break;
		}
				
				
		if(result){
			for(Pair key:pairData){
				kdata.add(key.data+"             "+key.card);	
			}
		}
		
		return kdata;
	}
 	
//	

//	void getIdentifierTest(){            //.find() 한 상태
//		
//		String Sarray[]={"남","남","여","남","여","남","여","남","남","여",};
//		int Aarray[]={890000,680000,740000,940000,770000,700000,720000,880000,760000,680000};
//		//int Aarray2[]={28,49,43,23,40,47,45,29,41,49};
//		int Yarray[]={2001,2016,2016,2001,2015,2015,2011,2003,2013,2013};
//		int K=3;
//		
//		boolean result=true;
//		Set<String> set=null;
//		ArrayList<String> kdata=new ArrayList<String>();
//		
//		for(int i=0;i<latt.length;i++){                    
//			for(int j=0;j<latt[i].length;j++)
//			{	
//				result=true;
//				int SAY[]={0,0,0};
//	
//				for(int k=0;k<latt[i][j].length;k++){
//					SAY[k]=latt[i][j][k];                         //1.어떤 계층을 사용할지를 결정한다.
//					//K+=SAY[k];
//				}
//				
//				
//				
//				HashMap<String,Integer> map=new HashMap<String,Integer>();                 // k 익명성이 적용된 값이 map에 저장.
//				int b=0;
//				while(b<10){                
//					String data=null;
//					   
//					String sex= Sarray[b];            //성별 
//					int age=changeTemptoAge(Aarray[b]);           //나이
//				
//					int ysize=1;
//					int q=0;
//					while(q<ysize)     //한 사람당 최근 거래일을 다 뒤져야 한다.
//					{
//						int year=Yarray[b];             // 거래일 목록
//				
//						int nS=SAY[0];
//						if(nS == 0){
//							for(int x=0;x<S[nS].length;x++){
//								if(sex.equals(S[nS][x])){                     //sex : 현재 document의 성별
//									//sex=S[nS][x];                     
//									data=sex;
//									break;
//								}
//							}
//						}
//						else{
//							sex="*";
//							data=sex;
//						}
//						
//						////////////////
//						int nA=SAY[1];
//						for(int x=0;x<A[nA].length;x++){
//							for(int y=0;y<A[nA][x].length;y++){
//								if(age >= A[nA][x][0] && age <= A[nA][x][1]){
//									/////////*******
//									data+=" "+A[nA][x][0]+"~"+A[nA][x][1];
//									break;
//								}
//							}
//						}
//						
//						//////////////////
//						
//						int nY=SAY[2];
//						for(int x=0;x<Y[nY].length;x++){
//							for(int y=0;y<Y[nY][x].length;y++){
//								if(year >= Y[nY][x][0] && year <= Y[nY][x][1]){
//									data+=" "+Y[nY][x][0]+"~"+Y[nY][x][1];
//									break;
//								}
//							}
//						}
//						
//						             //map에 데이터를 추가하는 코드 넣기.
//						
//						if(map.get(data)==null){
//							map.put(data,1);
//						}
//						else{
//							map.put(data,map.get(data)+1);
//						}
//						
//						q++;
//					}
//					
//					
//					b++;
//				}
//				
//
//				set=map.keySet();
//				
//				if(SAY[0]+SAY[1]+SAY[2]==3){
//					System.out.println(SAY[0]+" "+SAY[1]+" "+SAY[2]);
//					for(String key:set){
////						for(int m=0;m<map.get(key);m++){
////							System.out.println(key);
////						}
//						System.out.println(key+" "+map.get(key));
//					}
//					System.out.println("------------");
//				}
//				//Iterator<String> iterator=set.iterator();
//				for(String key:set){
//					if(map.get(key) < K){                    //여기 규칙 바꾸자.
//						result=false;          // false
//						break;
//					}
//				}
//				if(result)
//					break;
//				
//				
//			}
//			
//			if(result)
//				break;
//			
//		}
//		
//		if(result){
//			for(String data:set){
//				kdata.add(data);
//			}
//		}
//
//		
//		for(String data : kdata){
//			System.out.println(data);
//		}
//	}
	

	public static void main(String args[]){
		KAnonymity anoni=new KAnonymity();
		
//		MongoClient mongoClient = new MongoClient();
//		MongoDatabase db = mongoClient.getDatabase("test");
//		
//		FindIterable<Document> iterable = db.getCollection("information")
//				.find(new Document("pname", "이삭길"));//키워드포함 검색
		
		//anoni.getIdentifier();
	}
	
	
}
