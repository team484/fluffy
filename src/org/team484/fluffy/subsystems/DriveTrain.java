/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team484.fluffy.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team484.fluffy.RobotMap;
import org.team484.fluffy.commands.DriveWithJoysticks;

/**
 *
 * @author Team484
 */
public class DriveTrain extends PIDSubsystem {

    private static final double Kp = 0.04;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;

    RobotDrive robotDrive = new RobotDrive(RobotMap.frontLeftMotor,RobotMap.backLeftMotor,RobotMap.frontRightMotor,RobotMap.backRightMotor);
    Joystick driveStick = new Joystick(RobotMap.driveStick);
    Ultrasonic sonic = new Ultrasonic(RobotMap.ping, RobotMap.echo);
    Gyro gyro = new Gyro(RobotMap.gyro);
    boolean wasMech = false;
    protected DriverStation ds;
    double old1 = 0;
    double old2 = 0;
    double old3 = 0;
    public DriveTrain() {
        super("DriveTrain", Kp, Ki, Kd);

        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        sonic.setAutomaticMode(true);
        ds = DriverStation.getInstance();
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, RobotMap.frontLeftInvert);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, RobotMap.backLeftInvert);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, RobotMap.frontRightInvert);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, RobotMap.backRightInvert);
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveWithJoysticks());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        if (sonic.getRangeInches() > 0) {
        if (old1 == 0) {
            old1 = sonic.getRangeInches();
            old2 = sonic.getRangeInches();
            old3 = sonic.getRangeInches();
        } else {
            old3 = old2;
            old2 = old1;
            old1 = sonic.getRangeInches();
        }
        if (old3 > old2 && old3 > old1) {
            return old3;
        } else if (old2 > old3 && old2 > old1) {
            return old2;
        } else {
            return old1;
        } 
        } else {
            return 100;
        }
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
        
        SmartDashboard.putNumber("Output", output);
        mechanumDrive(0, output / 2, 0, true, false);
    }
    public void zeroGyro() {
        gyro.reset();
    }
    public void autoMechDrive() {
        double rotation = -(gyro.getAngle() / 200);
        //System.out.println("Gyro: "+gyro.getAngle() + " rotation: "+rotation);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, RobotMap.frontLeftInvert);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, RobotMap.backLeftInvert);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, RobotMap.frontRightInvert);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, RobotMap.backRightInvert);
        robotDrive.mecanumDrive_Cartesian(0, -0.5, rotation, 0);
    }
    public void mechanumDrive(double x, double y, double rotation, boolean autonomous, boolean mechanum) {
        if (mechanum && !this.wasMech) {
            this.wasMech = true;
            gyro.reset();
        }
        if (!mechanum) {
            this.wasMech = false;
        }
        if (autonomous || mechanum) {
            rotation = -(gyro.getAngle() / 200);
        } else {
            rotation = rotation * rotation * rotation;
            //System.out.println("Rotation: " + rotation);
        }
        if (Math.abs(x) > Math.abs(y)) {
            y = 0;
        } else {
            x = 0;
        }
        if (!(autonomous && sonic.getRangeInches() < 2)) {
            robotDrive.mecanumDrive_Cartesian(x, y, rotation, 0);
        }
        
        if (sonic.getRangeInches() > 30 && sonic.getRangeInches() < 70) {
            SmartDashboard.putBoolean("Shoot", true);
        } else {
            SmartDashboard.putBoolean("Shoot", false);
        }
        SmartDashboard.putNumber("Gyro", gyro.getAngle());
        SmartDashboard.putNumber("UltraSonic", sonic.getRangeInches());
        //SmartDashboard.putNumber("voltage", ds.getBatteryVoltage());
        //System.out.println(SmartDashboard.getString("DriveFromWall"));
    }
}