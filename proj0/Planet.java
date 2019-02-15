public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	private static double G = 6.67e-11;

	public Planet(double xP, double yP, double xV, 
		        double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos; 
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet a) {
        double xxDist = a.xxPos - this.xxPos;
        double yyDist = a.yyPos - this.yyPos;
		double Dist = Math.sqrt(xxDist * xxDist + yyDist * yyDist);
		return(Dist);
	}

	public double calcForceExertedBy(Planet a) {
		double SquareDist = this.calcDistance(a) * this.calcDistance(a);
		double Force = G * this.mass * a.mass / SquareDist;
		return(Force);
	}

	public double calcForceExertedByX(Planet a) {
		double xxDist = a.xxPos - this.xxPos;
		double xxForce = this.calcForceExertedBy(a) * xxDist / this.calcDistance(a);
		return(xxForce);
	}

	public double calcForceExertedByY(Planet a) {
		double yyDist = a.yyPos - this.yyPos;
		double yyForce = this.calcForceExertedBy(a) * yyDist / this.calcDistance(a);
		return(yyForce);
	}


	public double calcNetForceExertedByX(Planet[] allbodys) {
		double xxNetForce = 0;
		for(int i = 0; i < allbodys.length; i++) {
			if (this == allbodys[i]) continue;
			else xxNetForce = this.calcForceExertedByX(allbodys[i]) + xxNetForce;
		}
		return(xxNetForce);
	}

	public double calcNetForceExertedByY(Planet[] allbodys) {
		double yyNetForce = 0;

		for(int i = 0; i < allbodys.length; i++) {
			if (this == allbodys[i]) {
				continue;
			} else yyNetForce = this.calcForceExertedByY(allbodys[i]) + yyNetForce;
		}
		return(yyNetForce);
	}

	public void update(double time, double xxForce, double yyForce) {
		double xxNetAccer = xxForce / this.mass;
		double yyNetAccer = yyForce / this.mass;
		xxVel = this.xxVel + time * xxNetAccer;
		yyVel = this.yyVel + time * yyNetAccer;
		xxPos = this.xxPos + time * this.xxVel;
		yyPos = this.yyPos + time * this.yyVel;
	}

	public void draw() {
		String imgToDraw = "images/" + this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, imgToDraw);
	}
}


