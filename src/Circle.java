
import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class Circle {
   public static void main(String[] args) {
	   
	 // EV3LargeRegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A);
	   
	  EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
	  EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
      
      //leftMotor;
     

      leftMotor.setSpeed(360); // 360 degrees per second
      rightMotor.setSpeed(360);
      EV3 ev3brick = (EV3) BrickFinder.getLocal();
      Keys buttons = ev3brick.getKeys();
      
      while(buttons.getButtons() != Keys.ID_ESCAPE) {

      leftMotor.forward(); // start both motors moving forward
      rightMotor.forward();

      try {
         Thread.sleep(2000); // wait for 5 seconds
      } catch (InterruptedException e) {}
      
      }

      leftMotor.stop(); // stop both motors
      rightMotor.stop();
   }
}

