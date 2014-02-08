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
    
    public static final int driveStick = 1; //Joystick #
    public static final int shootStick = 2; //Joystick #
    
    public static final int driveMechanumButton = 1; //Drive Stick Button
    public static final int shootHigh = 1; //Shoot Stick Button
    public static final int lobBall = 2; //Shoot Stick Button
    public static final int lowerArm = 7; //Shoot Stick
    public static final int raiseArm = 6; //Shoot Stick
    public static final int pickupBall = 3; //Shoot Stick
    
    public static final int frontLeftMotor = 2; //PWM port
    public static final int backLeftMotor = 4; //PWM port
    public static final int frontRightMotor = 3; //PWM port
    public static final int backRightMotor = 1; //PWM port
    public static final boolean frontLeftInvert = false;
    public static final boolean backLeftInvert = false;
    public static final boolean frontRightInvert = true;
    public static final boolean backRightInvert = true;
    
    public static final int solenoidOn = 1; //Solenoid Breakout - Shooter
    public static final int solenoidOff = 2; //Solenoid Breakout - Shooter
    public static final int compressorRelay = 1; //Spike Relay
    public static final int pressureSwitch = 1; //Digital IO
    
    public static final int encoderA = 2; //Digital Source 1 for encoder
    public static final int encoderB = 3; //Digital Source 2 for encoder
    public static final int gyro = 1; //Gyro shold be slot 1 on analog input
    public static final int ir1 = 2; //Ball tracking IR analog input
    public static final int ir2 = 3; //Ball tracking IR analog input
    public static final int camLED = 2; //Spike relay
    
    public static final int pickupWheels = 3; //Spike for pickup wheel motor
    public static final int pickupArmUp = 3; //Solenoid Breakout to lift arm up
    public static final int pickupArmDown = 4; //Solenoid Breakout to let arm down
}
