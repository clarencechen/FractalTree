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
} 
public void draw() 
{   
	background(0);
	stroke(0,255,0);   
	line(0, 394, 0, 256, 394, 0);   
	drawBranches(256.0f, 394.0f, 0.0f, 128.0f, 0.0f, PI/2, PI/2);  //will add later 
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
	double cy = branchLength*Math.sin(9*PI/6)*Math.sin(branchAngle);
	double cz = branchLength*Math.cos(9*PI/6)*Math.sin(branchAngle);
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
		rotateX(PI/2 -(float)xAngle);
		rotateY(PI/2 -(float)yAngle);
		rotateZ(PI/2 -(float)zAngle);
		line(0.f, 0.f, 0.f, (float)(ax), (float)(ay), (float)(az));
		line(0.f, 0.f, 0.f, (float)(bx), (float)(by), (float)(bz));
		line(0.f, 0.f, 0.f, (float)(cx), (float)(cy), (float)(cz));
		popMatrix();
	}
	else
	{
		pushMatrix();
		translate((float)x, (float)y, (float)z);
		rotateX(PI/2 -(float)xAngle);
		rotateY(PI/2 -(float)yAngle);
		rotateZ(PI/2 -(float)zAngle);
		line(0.f, 0.f, 0.f, (float)(ax), (float)(ay), (float)(az));
		line(0.f, 0.f, 0.f, (float)(bx), (float)(by), (float)(bz));
		line(0.f, 0.f, 0.f, (float)(cx), (float)(cy), (float)(cz));
		drawBranches(ax, ay, az, branchLength*fractionLength, Math.acos(ai), Math.acos(aj), Math.acos(ak));
		drawBranches(bx, by, bz, branchLength*fractionLength, Math.acos(bi), Math.acos(bj), Math.acos(bk));
		drawBranches(cx, cy, cz, branchLength*fractionLength, Math.acos(ci), Math.acos(cj), Math.acos(ck));
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
}
public void keyPressed()
{
	switch (keyCode)
	{
		case UP:
		{
			branchAngle += PI/48;
			break;
		}
		case DOWN:
		{
			branchAngle -= PI/48;
			break;
		}
		case LEFT:
		{
			smallestBranch /= .75f;
			break;
		}
		case RIGHT:
		{
			smallestBranch *= .75f;
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
