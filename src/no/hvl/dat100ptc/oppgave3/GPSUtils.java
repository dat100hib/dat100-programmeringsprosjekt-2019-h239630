package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] tab) {

		double max; 
		
		max = tab[0];
		for (double pos : tab) {
			if (pos > max) {
				max = pos;
			}
		}
		return max;
	}

	public static double findMin(double[] tab) {

		double min;

		// TODO - START
		min = tab[0];
		for (int pos = 1; pos < tab.length; pos++) {
			if (min > tab[pos]) {
				min = tab[pos];
			}
		}
		//Utvidet for løkke
		/*for (double i : tab) {
			if (i < min) {
				min = i;
			}
		}*/
		return min; 
		// TODO - SLUT

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		double[] latitudes = new double[gpspoints.length];
		for (int i = 0; i < gpspoints.length; i++) {
			double latitude = gpspoints[i].getLatitude();
			latitudes[i] = latitude;
		}
		return latitudes;
		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START
		double[] longitude = new double[gpspoints.length];
		for (int i = 0; i < gpspoints.length; i++) {
			longitude[i] = gpspoints[i].getLongitude();
		}
		return longitude;
		// TODO - SLUTT

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		// TODO - START
		latitude1 = gpspoint1.getLatitude();
		longitude1 = gpspoint1.getLongitude();
		latitude2 = gpspoint2.getLatitude();
		longitude2 = gpspoint2.getLongitude();
		
		double ø1 = toRadians(latitude1);
		double ø2 = toRadians(latitude2);
		double deltaØ = ø2 - ø1; 
		double deltaL = toRadians(longitude2) - toRadians(longitude1);
		double a = pow((sin(deltaØ/2)), 2) + (cos(ø1) * cos(ø2) * (pow(sin(deltaL / 2), 2)));
		double c = 2 * atan2(sqrt(a), sqrt(1-a));
		d = R * c;
		return d;
		// TODO - SLUTT
	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		// TODO - START
		secs = gpspoint2.getTime() - gpspoint1.getTime();
		double distance = distance(gpspoint1, gpspoint2);
		speed = ((distance / secs) * 3600 ) / 1000;
		
		return speed;
		// TODO - SLUTT

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		// TODO - START
		int hr = secs / 3600;
		int rest = secs % 3600; 
		int min = rest / 60; 
		int sec = rest % 60; 
		
		String hrStr = String.format("%02d", hr);
		String minStr = String.format("%02d", min);
		String secStr = String.format("%02d", sec);
		
		timestr = "  " + hrStr + TIMESEP + minStr + TIMESEP + secStr;
		
		return timestr;
		// TODO - SLUTT

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		// TODO - START
		str = String.format("%10.2f", d);
		return str; 
		// TODO - SLUTT
		
	}
}
