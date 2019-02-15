public class NBody{
	public static double readRadius(String PlanetTxtPath) {
		In in = new In(PlanetTxtPath);
		int NumPlanets = in.readInt();
		double RadUniverse = in.readDouble();
		return(RadUniverse);
	}

	public static Planet[] readPlanets(String PlanetTxtPath) {
		int index = 0;
		In in = new In(PlanetTxtPath);
		int NumPlanets = in.readInt();
		Planet[] Planets = new Planet[NumPlanets];
		double RadUniverse = in.readDouble();
		while(index < NumPlanets) {
			Planets[index] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), 
				                      in.readDouble(), in.readDouble(), in.readString());
			index = index + 1;
		}
		return(Planets);
	}

	private static void drawBackground() {
		String BackImgToDraw = "images/starfield.jpg";
		StdDraw.picture(0, 0, BackImgToDraw);
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Double RadUniverse = NBody.readRadius(filename);
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(- RadUniverse, RadUniverse);
		Planet[] Planets = NBody.readPlanets(filename);
        double[] xForces = new double[Planets.length];
        double[] yForces = new double[Planets.length];
		for(double time = 0; time <= T; time += dt) { 
			for (int j = 0; j < Planets.length; j++) {
				xForces[j] = Planets[j].calcNetForceExertedByX(Planets);
				yForces[j] = Planets[j].calcNetForceExertedByY(Planets);
			}
			for (int k = 0; k < Planets.length; k++) {
				Planets[k].update(dt, xForces[k], yForces[k]);
			}
            drawBackground();
			for (int i = 0; i < Planets.length; i++) {
				Planets[i].draw();
		    }
		    /*StdDraw.clear();*/
		    StdDraw.show();
		    StdDraw.pause(10);
		}
		StdOut.printf("%d\n", Planets.length);
		StdOut.printf("%.2e\n", RadUniverse);
		for (int m = 0; m < Planets.length; m++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                   Planets[m].xxPos, Planets[m].yyPos, Planets[m].xxVel,
                  Planets[m].yyVel, Planets[m].mass, Planets[m].imgFileName);   
		}
	}
}
