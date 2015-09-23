package org.usfirst.frc.team2972.robot;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically it 
 * contains the code necessary to operate a robot with tank drive.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends SampleRobot {
    Joystick leftStick;  // set to ID 1 in DriverStation
    Joystick rightStick; // set to ID 2 in DriverStation
    
    Talon rightTalon, leftTalon;
    
    Compressor compressor = new Compressor(0);
    DoubleSolenoid shifter = new DoubleSolenoid(0,1);
    
    // Channels for the things
    final int rightTalonChannel = 0;
    final int leftTalonChannel = 1;
    
    public Robot() {
        leftStick = new Joystick(1);
        rightStick = new Joystick(0);
        
        leftTalon = new Talon(leftTalonChannel);
        rightTalon = new Talon(rightTalonChannel);
        
        compressor.setClosedLoopControl(true);
    }

    
    /**
     * Runs the motors with tank steering.
     */
    public void operatorControl() {
       
        while (isOperatorControl() && isEnabled()) {
        	rightTalon.set(rightStick.getY());
        	leftTalon.set(leftStick.getY()*-1);
        	
        	//Shifter Code
        	if (rightStick.getRawButton(1)){
        		shifter.set(DoubleSolenoid.Value.kForward);
        	}else if(leftStick.getRawButton(1)){
        		shifter.set(DoubleSolenoid.Value.kReverse);
        	}else{
        		shifter.set(DoubleSolenoid.Value.kOff);
        	}
        	
        	
            Timer.delay(0.005);		// wait for a motor update time
        }
    }

}
