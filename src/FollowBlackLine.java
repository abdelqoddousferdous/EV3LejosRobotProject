import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.EV3ColorSensor;

public class FollowBlackLine {
   public static void main(String[] args) {
      RegulatedMotor leftMotor = Motor.A;
      RegulatedMotor rightMotor = Motor.D;

      // initialize color sensor
      Port port = LocalEV3.get().getPort("S4");
      EV3ColorSensor colorSensor = new EV3ColorSensor(port);
      SampleProvider colorProvider = colorSensor.getRedMode();
      float[] colorSample = new float[colorProvider.sampleSize()];

      leftMotor.setSpeed(90); // 360 degrees per second
      rightMotor.setSpeed(90);

      // start both motors moving forward
      leftMotor.forward();
      rightMotor.forward();
      EV3 ev3brick = (EV3) BrickFinder.getLocal();
      Keys buttons = ev3brick.getKeys();

      while(buttons.getButtons() != Keys.ID_ESCAPE) {
         colorProvider.fetchSample(colorSample, 0);
         float intensity = colorSample[0];

         if (intensity < 0.2) {
            // turn left if black line is detected
            leftMotor.setSpeed(100);
            rightMotor.setSpeed(200);
         } else if (intensity > 0.5) {
             // turn left if white surface is detected
             leftMotor.setSpeed(200);
             rightMotor.setSpeed(100);
          } else {
             // continue straight
             leftMotor.setSpeed(200);
             rightMotor.setSpeed(200);
      }}

      // color sensor and motors should be closed after use
      colorSensor.close();
      leftMotor.close();
      rightMotor.close();
   }
}

