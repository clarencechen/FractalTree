import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class FractalTree extends PApplet {

public double branchAngle = PI/6;
private double fractionLength = .75f;
private int smallestBranch = 54; 
public void setup() 
{   
	
	background(0);
	size(1024, 768, P3D);
	noLoop();
} 
public void draw() 
{   
	background(0);
	stroke(0,255,0);   
	line(0, 394, 0, 256, 394, 0);   
	drawBranches(256.0f, 394.0f, 0.0f, 128.0f, 0.0f, 0.0f, 0.0f);  //will add later 
} 
public void drawBranches(double x,double y, double z, double branchLength, double xAngle, double yAngle, double zAngle)
{
	double ax = branchLength*Math.cos(branchAngle);
	double ay = branchLength*Math.sin(PI/6)*Math.sin(branchAngle);
	double az = branchLength*Math.cos(PI/6)*Math.sin(branchAngle);
	double bx = branchLength*Math.cos(branchAngle);
	double by = branchLength*Math.sin(5*PI/6)*Math.sin(branchAngle);
	double bz = branchLength*Math.cos(5*PI/6)*Math.sin(branchAngle);
	double cx = branchLength*Math.cos(branchAngle);
	double cy = branchLength*Math.sin(-3*PI/6)*Math.sin(branchAngle);
	double cz = branchLength*Math.cos(-3*PI/6)*Math.sin(branchAngle);
	double ar = Math.sqrt((ax)*(ax) +(ay)*(ay) +(az)*(az));
	double br = Math.sqrt((bx)*(bx) +(by)*(by) +(bz)*(bz));
	double cr = Math.sqrt((cx)*(cx) +(cy)*(cy) +(cz)*(cz));
	double ai = (ax)/ar;	double bi = (bx)/br;	double ci = (cx)/cr;
	double aj = (ay)/ar;	double bj = (by)/br;	double cj = (cy)/cr;
	double ak = (az)/ar;	double bk = (bz)/br;	double ck = (cz)/cr;

	if(branchLength <= smallestBranch)
	{
		pushMatrix();
		translate((float)x, (float)y, (float)z);
		rotateX(-(float)xAngle);
		rotateY(-(float)yAngle);
		rotateZ(-(float)zAngle);
		stroke(255,0,0);
		line(0.f, 0.f, 0.f, (float)(ax), (float)(ay), (float)(az));
		stroke(0,255,0);
		line(0.f, 0.f, 0.f, (float)(bx), (float)(by), (float)(bz));
		stroke(0,0,255);
		line(0.f, 0.f, 0.f, (float)(cx), (float)(cy), (float)(cz));
		popMatrix();
	}
	else
	{
		pushMatrix();
		translate((float)x, (float)y, (float)z);
		rotateX(-(float)xAngle);
		rotateY(-(float)yAngle);
		rotateZ(-(float)zAngle);
		System.out.println(xAngle/PI + " " + yAngle/PI + " " +zAngle/PI);
		stroke(255,0,0);
		line(0.f, 0.f, 0.f, (float)(ax), (float)(ay), (float)(az));
		stroke(0,255,0);
		line(0.f, 0.f, 0.f, (float)(bx), (float)(by), (float)(bz));
		stroke(0,0,255);
		line(0.f, 0.f, 0.f, (float)(cx), (float)(cy), (float)(cz));
		drawBranches(ax, ay, az, branchLength*fractionLength, Math.asin(ai) +xAngle, Math.asin(aj) +yAngle, Math.asin(ak) +zAngle);
		drawBranches(bx, by, bz, branchLength*fractionLength, Math.asin(bi) +xAngle, Math.asin(bj) +yAngle, Math.asin(bk) +zAngle);
		drawBranches(cx, cy, cz, branchLength*fractionLength, Math.asin(ci) +xAngle, Math.asin(cj) +yAngle, Math.asin(ck) +zAngle);
		popMatrix();
	}
} 
public void mouseMoved()
{
	
	beginCamera();
	camera();
	translate(512, 394, 0);
	rotateX((394 -mouseY)/(48*PI));
	rotateY((mouseX -512)/(48*PI));
	translate(-512, -394, 0);
	endCamera();
	redraw();
}
public void keyPressed()
{
	switch (keyCode)
	{
		case UP:
		{
			branchAngle += PI/48;
			redraw();
			break;
		}
		case DOWN:
		{
			branchAngle -= PI/48;
			redraw();
			break;
		}
		case LEFT:
		{
			smallestBranch /= .75f;
			redraw();
			break;
		}
		case RIGHT:
		{
			smallestBranch *= .75f;
			redraw();
			break;
		}
		default:
			break;	
	}
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "FractalTree" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
