package com.ycm.cal;

import java.util.ArrayList;
import java.util.List;

public class CalDistance {

	/**
	 * 计算两点之间距离
	 * 
	 * @param start
	 * @param end
	 * @return 米
	 */
	public static String getDistance(LatLng start, LatLng end) {
		double lat1 = (Math.PI / 180) * start.getLatitude();
		double lat2 = (Math.PI / 180) * end.getLatitude();

		double lon1 = (Math.PI / 180) * start.getLongtitude();
		double lon2 = (Math.PI / 180) * end.getLongtitude();

		// 地球半径
		double R = 6371;

		// 两点间距离 km，如果想要米的话，结果*1000
		double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1))
				* R;
		if (d < 1)
			return (int) d * 1000 + "m";
		else
			return String.format("%.2f", d) + "km";
	}

	private static LatLng getCentPoint(List<LatLng> pointlist) {
		int total = pointlist.size();
		double X = 0, Y = 0, Z = 0;
		for (LatLng g : pointlist) {
			double lat, lon, x, y, z;
			lat = (g.getLatitude()) * Math.PI / 180; // 纬度
			lon = (g.getLongtitude()) * Math.PI / 180; // 经度
			x = Math.cos(lat) * Math.cos(lon);
			y = Math.cos(lat) * Math.sin(lon);
			z = Math.sin(lat);
			X += x;
			Y += y;
			Z += z;
		}
		X = X / total;
		Y = Y / total;
		Z = Z / total;
		double Lon = Math.atan2(Y, X);
		double Hyp = Math.sqrt(X * X + Y * Y);
		double Lat = Math.atan2(Z, Hyp);
		LatLng ql = new LatLng();
		ql.setLatitude((Lon * 180 / Math.PI));
		ql.setLongtitude((Lat * 180 / Math.PI));

		return ql;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LatLng P1 = new LatLng(39.864741, 116.450055);
		LatLng P2 = new LatLng(34.505097, 113.460483);

		System.out.println(getDistance(P1, P2));

		// ************************计算中心点
		List<LatLng> pointlist = new ArrayList<>();
		pointlist.add(P1);
		pointlist.add(P2);

		LatLng p = getCentPoint(pointlist);
		System.out.println(p);
	}

}

class LatLng {
	private double latitude;
	private double longtitude;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public LatLng(double lat, double lon) {
		this.latitude = lat;
		this.longtitude = lon;
	}

	public LatLng() {

	}

	@Override
	public String toString() {
		return "latitude:" + this.latitude + "****" + "longtitude:" + this.longtitude;
	}

}
