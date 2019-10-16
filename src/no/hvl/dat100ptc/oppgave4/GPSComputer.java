package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO - START
		int i = 0;
		int stopp = gpspoints.length - 1; 
		while (i < gpspoints.length && i != stopp) {
			distance = distance + GPSUtils.distance(gpspoints[i], gpspoints[i+1]); 
			i++;
		} 
		return distance;
		// TODO - SLUTT

	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO - START
		int i = 0; 
		int stopp = gpspoints.length - 1; 
		while (i < gpspoints.length && i != stopp) {
			if (gpspoints[i].getElevation() < gpspoints[i+1].getElevation()) {
				elevation = elevation + (gpspoints[i+1].getElevation() - gpspoints[i].getElevation());
			} 
		i++;	
		}
		return elevation; 
		// TODO - SLUTT

	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		
		// TODO - START
		int time = 0;
		int i = 0;
		int stopp = gpspoints.length - 1;
		while (i < gpspoints.length && i != stopp) {
			time = time + (gpspoints[i+1].getTime() - gpspoints[i].getTime());
			i++;
		} 
		return time; 
		//TODO - SLUTT
	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {
		
		// TODO - START		// OPPGAVE - START
		double[] speeds = new double[gpspoints.length - 1];
		int i = 0; 
		int stopp = gpspoints.length - 1; 
		while(i < gpspoints.length && i != stopp) {
			speeds[i] = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
			i++;
		}
		return speeds;
		// TODO - SLUTT

	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		// TODO - START
		maxspeed = GPSUtils.findMax(speeds()); 
		return maxspeed;
		// TODO - SLUTT
		
	}

	public double averageSpeed() {

		double average = 0;
		
		// TODO - START
		average = ((totalDistance() / totalTime()) * 3600.0 ) / 1000.0;
		return average; 
		// TODO - SLUTT
		
	}
	
	/*  <10 mph		4.0
		10-12 mph	6.0
		12-14 mph	8.0
		14-16 mph	10.0
		16-20 mph	12.0
		>20 mph		16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt vekt og tid det kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal * kg^-1 * h^-1)
		// kcal = MET / kg^-1 * h^-1
		//kg = weigth og h = secs(Må endres til time)
		
		double met = 0;		
		double speedmph = speed * MS;
		
		// TODO - START
		if (speedmph < 10) {
			met = 4.0;
		} else if (speedmph >= 10 && speedmph < 12) {
			met = 6.0;
		} else if (speedmph >= 12 && speedmph < 14) {
			met = 8.0;
		} else if (speedmph >= 14 && speedmph < 16) {
			met = 10.0;
		} else if (speedmph >= 16 && speedmph < 20) {
			met = 12.0;
		} else if (speedmph >= 20) {
			met = 16.0;
		}
		kcal = met * weight * secs / 3600.0;
		return kcal;
		// TODO - SLUTT
		
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO - START
	
		totalkcal = kcal(weight, totalTime(), averageSpeed());
		
		return totalkcal;
		// TODO - SLUTT
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		System.out.println("==============================================");

		// TODO - START
		double tD = totalDistance() / 1000; //Konverterer fra meter til Km
		String totalTime = GPSUtils.formatTime(totalTime());
		String totalDistance = GPSUtils.formatDouble(tD);
		String totalElevation = GPSUtils.formatDouble(totalElevation());
		String maxSpeed = GPSUtils.formatDouble(maxSpeed());
		String averageSpeed = GPSUtils.formatDouble(averageSpeed());
		String energy = GPSUtils.formatDouble(totalKcal(WEIGHT));
		
		System.out.println("Total time     :" + totalTime);
		System.out.println("Total distance :" + totalDistance + " km");
		System.out.println("Total elevation:" + totalElevation + " m");
		System.out.println("Max speed      :" + maxSpeed + " km/t");
		System.out.println("Average speed  :" + averageSpeed + " km/t");
		System.out.println("Energy         :" + energy + " kcal");
		
		
		// TODO - SLUTT
		
	}
	public double[] climbs() {
		
		/*Fremgangsmåte
		 * Må ta inn en tabell over alle punkter
		 * henter ut elevation for hvert punkt 
		 * Trekke ut differansen mellom punktene 
		 * Deretter gjøre denne differanse om til prosent stigning
		 */
		double[] stigning = new double[gpspoints.length - 1];
		
		for (int i = 1; i < gpspoints.length; i++) {
			if (gpspoints[i].getElevation() > gpspoints[i-1].getElevation()) {
				double høydeDiff = gpspoints[i].getElevation() - gpspoints[i - 1].getElevation();
				stigning[i] = (høydeDiff / GPSUtils.distance(gpspoints[i], gpspoints[i-1])) * 100;
			} else {
				stigning[i] = 0; 
			}
		}
		return stigning;
	}
	
	public double maxClimb() {
		double maxClimb = GPSUtils.findMax(climbs());
		return maxClimb;
		
	}

}
