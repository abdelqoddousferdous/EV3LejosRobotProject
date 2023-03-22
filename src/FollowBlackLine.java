import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.EV3ColorSensor;

public class FollowBlackLine {
   public static void main(String[] args) {
      EV3LargeRegulatedMotor leftMotor =new EV3LargeRegulatedMotor(MotorPort.A);
      EV3LargeRegulatedMotor rightMotor =new EV3LargeRegulatedMotor(MotorPort.D);

      // initialize color sensor
      Port port = LocalEV3.get().getPort("S4");
      EV3ColorSensor colorSensor = new EV3ColorSensor(port);
      SampleProvider colorProvider = colorSensor.getRedMode();
      float[] colorSample = new float[colorProvider.sampleSize()];

     

      // start both motors moving forward
      LCD.drawString("Waiting for a button press", 0, 1);
      leftMotor.forward();
      rightMotor.forward();
      EV3 ev3brick = (EV3) BrickFinder.getLocal();
      Keys buttons = ev3brick.getKeys();

      while(buttons.getButtons() != Keys.ID_ESCAPE) {
    	  float[] sample = new float[1];
          colorSensor.getRedMode().fetchSample(sample, 0);
          float intensity = sample[0];

         if (intensity < 0.2) {
            // turn left if black line is detected
        	 leftMotor.setSpeed(100);
             rightMotor.setSpeed(200);
          
         } else {
             // continue straight
        	  leftMotor.setSpeed(100);
              rightMotor.setSpeed(200);
      }}

      // color sensor and motors should be closed after use
      colorSensor.close();
      leftMotor.close();
      rightMotor.close();
   }
}

