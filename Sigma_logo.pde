import processing.serial.*;

Serial myport;

final float GUAGE_ANGLE_MAX = 0.32 * PI;

float SigmaMouthAngle = PI/4;
float SigmaGuageAngle = 0;
float SigmaTurnAngle = 0;
float SigmaXpos = 100;
float SigmaYpos = 100;
float increment_mouth = 0.04;
float increment_guage = 0.007;
float increment_Xpos = 0;
float increment_Ypos = 0;

void setup() {

  size(1280, 720);
}

void draw() {
  background(255, 255, 255);
  //arc(255, 255, 200, 200, -PI, +0.7*PI);
  //strokeWeight(10);

  drawSigmaIcon(SigmaXpos, SigmaYpos, 30, SigmaMouthAngle, SigmaGuageAngle, SigmaTurnAngle);
  animationEnableSigmaIcon(PI/2, PI/6, PI/8, -PI/8);
}

void keyPressed() {
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
  } else {
  }
}

void drawSigmaIcon(float x, float y, float rad, float mouthAngle, float meterAngle, float turnAngle) {
  pushMatrix();
  translate(x, y);
  scale(rad);
  rotate(turnAngle);
  drawUnitSigmaIcon(mouthAngle, meterAngle);
  popMatrix();
}

void drawUnitSigmaIcon(float mouthAngle, float meterAngle) {
  final float UNIT_RADIUS = 1.0f;
  final float UNIT_DIAMETER = UNIT_RADIUS * 2;
  noStroke();
  strokeWeight(0);
  fill(0, 0, 0);
  arc(0, 0, UNIT_DIAMETER, UNIT_DIAMETER, -PI + mouthAngle/2, -GUAGE_ANGLE_MAX/2);
  arc(0, 0, UNIT_DIAMETER, UNIT_DIAMETER, GUAGE_ANGLE_MAX/2, PI - mouthAngle/2);

  fill(255, 255, 255);
  arc(0, 0, UNIT_DIAMETER * 0.8f, UNIT_DIAMETER * 0.8f, -PI + mouthAngle/2, PI - mouthAngle/2);
  fill(0, 0, 0);
  ellipse(0, -0.45 * UNIT_RADIUS, 0.25*UNIT_RADIUS, 0.25*UNIT_RADIUS);

  for (int i = 0; i < 10; ++i) {
    if (i%3 == 0) {
      fill(0, 0, 0);
      arc(0, 0, UNIT_DIAMETER, UNIT_DIAMETER, -0.13*PI + 0.08667*PI*i/3 - 0.01*PI, -0.13*PI + 0.08667*PI*i/3 + 0.01*PI);
      noStroke();
      fill(255, 255, 255);
      arc(0, 0, UNIT_DIAMETER * 0.8, UNIT_DIAMETER * 0.8, -0.13*PI + 0.08667*PI*i/3 - 0.02*PI, -0.13*PI + 0.08667*PI*i/3 + 0.02*PI);
      noStroke();
    } else {
      fill(0, 0, 0);
      arc(0, 0, UNIT_DIAMETER, UNIT_DIAMETER, -0.13*PI + 0.08667*PI*i/3 - 0.005*PI, -0.13*PI + 0.08667*PI*i/3 + 0.005*PI);
      noStroke();
      fill(255, 255, 255);
      arc(0, 0, UNIT_DIAMETER * 0.9, UNIT_DIAMETER * 0.9, -0.13*PI + 0.08667*PI*i/3 - 0.009*PI, -0.13*PI + 0.08667*PI*i/3 + 0.009*PI);
      noStroke();
    }
  }

  stroke(0, 0, 0);
  strokeWeight(0);
  fill(255, 0, 0);
  triangle(0.7*UNIT_RADIUS*cos(meterAngle), 0.7*UNIT_RADIUS*sin(meterAngle), 
           0.1*UNIT_RADIUS*cos(meterAngle-PI/2), 0.1*UNIT_RADIUS*sin(meterAngle-PI/2), 
           0.1*UNIT_RADIUS*cos(meterAngle+PI/2), 0.1*UNIT_RADIUS*sin(meterAngle+PI/2));

  strokeWeight(0.2*UNIT_RADIUS);

  line(0.9*UNIT_RADIUS*cos(-PI + mouthAngle/2), UNIT_RADIUS*0.9*sin(-PI + mouthAngle/2), 0, 0);  
  line(0.9*UNIT_RADIUS*cos(PI - mouthAngle/2), UNIT_RADIUS*0.9*sin(PI - mouthAngle/2), 0, 0);
}

void animationEnableSigmaIcon(float mouthMaxAngle, float mouthMinAngle, float guageMaxAngle, float guageMinAngle) {
  SigmaMouthAngle += increment_mouth;
  SigmaGuageAngle += increment_guage;
  SigmaXpos += increment_Xpos;
  SigmaYpos += increment_Ypos;
  if (SigmaMouthAngle < mouthMinAngle || SigmaMouthAngle > mouthMaxAngle) {
    increment_mouth = -increment_mouth;
  }
  if (SigmaGuageAngle < guageMinAngle || SigmaGuageAngle > guageMaxAngle) {
    increment_guage = - increment_guage;
  }
}
