import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Main {// class

	private static final EV3LargeRegulatedMotor LEFT_MOTOR = new EV3LargeRegulatedMotor(
			MotorPort.A);

	private static final EV3LargeRegulatedMotor RIGHT_MOTOR = new

	EV3LargeRegulatedMotor(MotorPort.C);

	public static void main(String[] args) throws Exception {// main

		// get EV3 brick
		EV3 ev3brick = (EV3) BrickFinder.getLocal();

		// instantized LCD class for displaying and Keys class for buttons
		Keys buttons = ev3brick.getKeys();

		// print a message to prompt the user to press a button
		LCD.drawString("Waiting for a button press", 0, 1);

		// block the thread until a button is pressed
		buttons.waitForAnyPress();

		String message = "MOTOR Testing: ";

		// set up the motor A speed, i.e. 100 degrees per second
		LEFT_MOTOR.setSpeed(100);

		// set up the motor C speed, i.e. 100 degrees per second
		RIGHT_MOTOR.setSpeed(500);

		// Creating a repeating loop until an ESCAPE button is pressed!

		while (buttons.getButtons() != Keys.ID_ESCAPE) {// while

			// refresh screen and then write value stored in the variable
			// message
			LCD.clear();
			LCD.drawString(message, 0, 1);

			// motor A moving forward
			LEFT_MOTOR.forward(); // if the left button is pressed
			if (buttons.getButtons() == 16) {// if

				// stop right motor
				RIGHT_MOTOR.stop();

				// motor A moving forward
				LEFT_MOTOR.forward();
				LCD.drawInt(LEFT_MOTOR.getTachoCount(), 0, 2);
			}// if
			else if (buttons.getButtons() == 8) {// else if

				// stop left motor
				LEFT_MOTOR.stop();

				// motor C moving forward
				RIGHT_MOTOR.forward();
				LCD.drawInt(RIGHT_MOTOR.getTachoCount(), 0, 3);
			}// else if

			else {// else

				// print the value of the key being pressed
				LCD.drawString("Button #" + buttons.getButtons(), 0, 4);

				RIGHT_MOTOR.stop();
				LEFT_MOTOR.stop();

			}// else

			// delay or pause the program for 1 second
			Thread.sleep(1000);

			// clear screen for rewriting
			LCD.refresh();
		}// while
	}// main
}// class
