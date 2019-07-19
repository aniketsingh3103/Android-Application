package com.d_andaman;

public class Location_Manager {


		public static double[] latitude={11.635262,11.636114,11.634526,11.635177,11.634720,11.634395,11.634484,
		11.637274,11.365971,11.632782,11.633023,11.633232,11.633055,11.634483,11.634173,11.634630,11.634320,
		11.634252};
		public static double[] longitude={92.717994,92.717001,92.717275,92.715821,92.714662,92.718326,92.719678,
		92.720858,92.712897,92.713874,92.719791,92.720944,92.720896,92.720756,92.722757,92.718247,92.71805,
		92.716681};
		public static String[] place={"PDME Hostel","Dr.B.R.Ambedkar institute","SBI Dollygunj Branch","Hotel North Ref",
		"Sovtech","Regional medical research Center","Sahre-E-bangal","DBRAIT Boys Hostel",
		"Food Corporation of India","DRDO Guest House","Chevrolet Workshop","TSG Grand","Chaaroborty Hospital",
		"Andaman Blue Sea","dollygunj Junction",
		"HDFC ATM","North Reff Hotel","ISKON vally"};
		public static double[] sub={};

	public String checklocation(double lat,double lon){
		int near=0;
		double a=lat+lon;
		double gap=0;
		double min=distance(latitude[0],longitude[0],lat,lon);
		for(int i=0;i<latitude.length;i++){
			gap=distance(latitude[i],longitude[i],lat,lon);
			
			if(gap<=min){
				min=gap;
				near=i;
			}
			

		}

		

		return "Near to\t"+place[near];
	}
	
	
	private static double distance(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		
			return (dist);
	}
	
	
	
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}




}
