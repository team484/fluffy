package org.team484.fluffy;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
    public static final int driveStick = 1;
    public static final int shootStick = 2;
    
    public static final int driveMechanumButton = 1; //Drive Stick
    public static final int lobButton = 3;
    public static final int shootButton = 1; //Shoot Stick
    
    public static final int frontLeftMotor = 1; //PWM
    public static final int backLeftMotor = 2; //PWM
    public static final int frontRightMotor = 3; //PWM
    public static final int backRightMotor = 4; //PWM
    public static final boolean frontLeftInvert = false;
    public static final boolean backLeftInvert = false;
    public static final boolean frontRightInvert = false;
    public static final boolean backRightInvert = false;
    
    public static final int solenoidOn = 1; //Solenoid Breakout
    public static final int solenoidOff = 2; //Solenoid Breakout
    
    public static final int pressureSwitch = 1; //Digital IO
    public static final int compressorRelay = 1; // Relay
}
