package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 500;
	private static int MAPYSIZE = 500;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);

		playRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
		
		// TODO - START
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		
		double ystep = MAPYSIZE / (Math.abs(maxlat - minlat));
		
		return ystep;
		// TODO - SLUTT
	}

	public void showRouteMap(int ybase) {

		// TODO - START
		// fillCircle(senterX, senterY, radius) og drawLine(x,y,x1,y1) 
		
		for (int i = 0; i < gpspoints.length; i++) {
			int x = (int) (xstep() / gpspoints[i].getLongitude());
			int y = (int) (ystep() / gpspoints[i].getLatitude());
			setColor(0,255,0);
			fillCircle(x, y, 5);
			
			
		}
		
		double minLat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		
		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		// TODO - START
		/*double y = gpspoints[0].getLatitude();
		double x = gpspoints[0].getLongitude();
		double y1 = gpspoints[1].getLatitude();
		double x1 = gpspoints[1].getLongitude();
		double ys = ystep();
		double xs = xstep();
		System.out.println("ystep " + ys);
		System.out.println("Latitude: " + y);
		System.out.println("Delt på: " + ys / y);
		System.out.println("xstep " + xs);
		System.out.println("Longitude: " + x);
		System.out.println("Delt på: " + xs / x);
		System.out.println(ys / y1);
		System.out.println(xs / x1);
		*/
		// TODO - SLUTT;
	}

	public void playRoute(int ybase) {

		// TODO - START
		
		
		// TODO - SLUTT
	}

}
