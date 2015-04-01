import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.serial.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class sketch_150327a extends PApplet {



Serial myport;

float SigmaMouthAngle = PI/4;
float SigmaGuageAngle = 0;
float SigmaTurnAngle = 0;
float SigmaXpos = 100;
float SigmaYpos = 100;
float increment_mouth = 0.04f;
float increment_guage = 0.007f;
float increment_Xpos = 0;
float increment_Ypos = 0;

public void setup() {

	size(1280, 720);	

}

public void draw() {
	background(255, 255, 255);
	//arc(255, 255, 200, 200, -PI, +0.7*PI);
	//strokeWeight(10);

	drawSigmaIcon(SigmaXpos,SigmaYpos,30,SigmaMouthAngle,SigmaGuageAngle,SigmaTurnAngle);
	animationEnableSigmaIcon(PI/2,PI/6,PI/8,-PI/8);
	
}
public void keyPressed() {
	if (key == CODED) 
	{
	    switch (keyCode) {
	    	case UP :
	    		SigmaTurnAngle = PI/2;
	    		increment_Xpos = 0;
	    		increment_Ypos = -2;
	    	break;
	    	case DOWN:
	    		SigmaTurnAngle = -PI/2;
	    		increment_Xpos = 0;
	    		increment_Ypos = 2;
	    	break;
	    	case RIGHT:
	    		SigmaTurnAngle = PI;
	    		increment_Xpos = 2;
	    		increment_Ypos = 0;
	    	break;
	    	case LEFT:
	    		SigmaTurnAngle = 0;
	    		increment_Xpos = -2;
	    		increment_Ypos = 0;
	    	break;
	    	case ENTER:
	    		increment_Xpos = 0;
	    		increment_Ypos = 0;
	    	break;
	    }
  	} 
  	else {
  	}
}

public void drawSigmaIcon(float x, float y, float rad, float mouthAngle, float meterAngle, float turnAngle){
	
	noStroke();
	strokeWeight(0);
	fill(0, 0, 0);
	arc(x, y, rad*2, rad*2, -PI + mouthAngle/2+turnAngle, -0.16f*PI+turnAngle);
	arc(x, y, rad*2, rad*2, 0.16f*PI+turnAngle, PI - mouthAngle/2+turnAngle );

	fill(255, 255, 255);
	arc(x, y, rad*1.6f, rad*1.6f, -PI + mouthAngle/2+turnAngle, PI - mouthAngle/2+turnAngle);
	fill(0, 0, 0);
	ellipse(x+0.45f*rad*cos(turnAngle-PI/2), y+0.45f*rad*sin(turnAngle-PI/2), 0.25f*rad, 0.25f*rad);
	
	for (int i = 0; i < 10; ++i) {
		if(i%3 == 0){
			fill(0, 0, 0);
			arc(x, y, rad*2, rad*2, -0.13f*PI + 0.08667f*PI*i/3 - 0.01f*PI+turnAngle, -0.13f*PI + 0.08667f*PI*i/3 + 0.01f*PI+turnAngle);
			noStroke();
			fill(255, 255, 255);
			arc(x, y, rad*1.6f, rad*1.6f, -0.13f*PI + 0.08667f*PI*i/3 - 0.02f*PI+turnAngle, -0.13f*PI + 0.08667f*PI*i/3 + 0.02f*PI+turnAngle);
			noStroke();
		}
		else{
			fill(0, 0, 0);
			arc(x, y, rad*2, rad*2, -0.13f*PI + 0.08667f*PI*i/3 - 0.005f*PI+turnAngle, -0.13f*PI + 0.08667f*PI*i/3 + 0.005f*PI+turnAngle);
			noStroke();
			fill(255, 255, 255);
			arc(x, y, rad*1.8f, rad*1.8f, -0.13f*PI + 0.08667f*PI*i/3 - 0.009f*PI+turnAngle, -0.13f*PI + 0.08667f*PI*i/3 + 0.009f*PI+turnAngle);
			noStroke();
		}
	}

	stroke(0, 0, 0);
	strokeWeight(0);
	fill(255, 0, 0);
	triangle(x+0.7f*rad*cos(meterAngle+turnAngle), y+0.7f*rad*sin(meterAngle+turnAngle),x+0.1f*rad*cos(meterAngle-PI/2+turnAngle), y+0.1f*rad*sin(meterAngle-PI/2+turnAngle), x+0.1f*rad*cos(meterAngle+PI/2+turnAngle), y+0.1f*rad*sin(meterAngle+PI/2+turnAngle));

	strokeWeight(0.2f*rad);

	line(x+0.9f*rad*cos(-PI + mouthAngle/2+turnAngle), y+rad*0.9f*sin(-PI + mouthAngle/2+turnAngle), x, y);	
	line(x+0.9f*rad*cos(PI - mouthAngle/2+turnAngle), y+rad*0.9f*sin(PI - mouthAngle/2+turnAngle), x, y);	
}
public void animationEnableSigmaIcon(float mouthMaxAngle, float mouthMinAngle, float guageMaxAngle, float guageMinAngle){
	SigmaMouthAngle += increment_mouth;
	SigmaGuageAngle += increment_guage;
	SigmaXpos += increment_Xpos;
	SigmaYpos += increment_Ypos;
	if(SigmaMouthAngle < mouthMinAngle || SigmaMouthAngle > mouthMaxAngle){
		increment_mouth = -increment_mouth;
	}
	if(SigmaGuageAngle < guageMinAngle || SigmaGuageAngle > guageMaxAngle){
		increment_guage = - increment_guage;
	}
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_150327a" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
