package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowSpeed extends EasyGraphics {
			
	private static int MARGIN = 50;
	private static int BARHEIGHT = 200; // assume no speed above 200 km/t

	private GPSComputer gpscomputer;
	private GPSPoint[] gpspoints;
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();
		
	}
	
	// read in the files and draw into using EasyGraphics
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length-1; // number of data points
		
		makeWindow("Speed profile", 2*MARGIN + 3 * N, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT,N);
	}
	
	public void showSpeedProfile(int ybase, int N) {
		
		//System.out.println("Angi tidsskalering i tegnevinduet ...");
		//int timescaling = Integer.parseInt(getText("Tidsskalering"));
				
		// TODO - START
		double[] speeds = new double[N];
		speeds = gpscomputer.speeds();
		int x = 10;
		setColor(0,0,255);
		drawRectangle(5, 150, 595, 105);
		for (int i = 0; i < speeds.length; i++) {
			if (i % 5 == 0) {
				x = x - 1;
				int yEnd = (int)(ybase - (2 * speeds[i]));
				drawLine(x, ybase, x, yEnd);
				x = x + 1;
			}
			setColor(100,100,255);
			int yEnd = (int)(ybase - (2 * speeds[i]));
			drawLine(x, ybase, x, yEnd);
			x = x + 3;
		}
		x = 9;
		int y = (int) (ybase - (2 * gpscomputer.averageSpeed()));
		setColor(0, 255, 0);
		drawLine(x, y, x + 587, y);
		drawLine(x, y + 1, x + 587, y + 1);
	
		// TODO - SLUTT
	}
}










