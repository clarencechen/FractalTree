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

public float branchAngle = PI/6;
private float fractionLength = .75f;
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
public void drawBranches(float x,float y, float z, float branchLength, float xAngle, float yAngle, float zAngle)
{
	float ax = branchLength*cos(branchAngle);
	float ay = branchLength*sin(PI/6)*sin(branchAngle);
	float az = branchLength*cos(PI/6)*sin(branchAngle);
	float bx = branchLength*cos(branchAngle);
	float by = branchLength*sin(5*PI/6)*sin(branchAngle);
	float bz = branchLength*cos(5*PI/6)*sin(branchAngle);
	float cx = branchLength*cos(branchAngle);
	float cy = branchLength*sin(-3*PI/6)*sin(branchAngle);
	float cz = branchLength*cos(-3*PI/6)*sin(branchAngle);
	float ar = sqrt((ax)*(ax) +(ay)*(ay) +(az)*(az));
	float br = sqrt((bx)*(bx) +(by)*(by) +(bz)*(bz));
	float cr = sqrt((cx)*(cx) +(cy)*(cy) +(cz)*(cz));
	float ai = (ax)/ar;	float bi = (bx)/br;	float ci = (cx)/cr;
	float aj = (ay)/ar;	float bj = (by)/br;	float cj = (cy)/cr;
	float ak = (az)/ar;	float bk = (bz)/br;	float ck = (cz)/cr;

	if(branchLength <= smallestBranch)
	{
		pushMatrix();
		translate(x, y, z);
		rotateX(-xAngle);
		rotateY(-yAngle);
		rotateZ(-zAngle);
		stroke(255,0,0);
		line(0.f, 0.f, 0.f, (ax), (ay), (az));
		stroke(0,255,0);
		line(0.f, 0.f, 0.f, (bx), (by), (bz));
		stroke(0,0,255);
		line(0.f, 0.f, 0.f, (cx), (cy), (cz));
		popMatrix();
	}
	else
	{
		pushMatrix();
		translate(x, y, z);
		rotateX(-xAngle);
		rotateY(-yAngle);
		rotateZ(-zAngle);
		System.out.println(xAngle/PI + " " + yAngle/PI + " " +zAngle/PI);
		stroke(255,0,0);
		line(0.f, 0.f, 0.f, (ax), (ay), (az));
		stroke(0,255,0);
		line(0.f, 0.f, 0.f, (bx), (by), (bz));
		stroke(0,0,255);
		line(0.f, 0.f, 0.f, (cx), (cy), (cz));
		drawBranches(ax, ay, az, branchLength*fractionLength, asin(ai) +xAngle, asin(aj) +yAngle, asin(ak) +zAngle);
		drawBranches(bx, by, bz, branchLength*fractionLength, asin(bi) +xAngle, asin(bj) +yAngle, asin(bk) +zAngle);
		drawBranches(cx, cy, cz, branchLength*fractionLength, asin(ci) +xAngle, asin(cj) +yAngle, asin(ck) +zAngle);
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
