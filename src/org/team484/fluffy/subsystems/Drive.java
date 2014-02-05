/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team484.fluffy.subsystems;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team484.fluffy.RobotMap;
import org.team484.fluffy.commands.DriveJoysticks;

/**
 *
 * @author kevin
 */
public class Drive extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    RobotDrive robotDrive = new RobotDrive(RobotMap.frontLeftMotor,RobotMap.backLeftMotor,RobotMap.frontRightMotor,RobotMap.backRightMotor);
    Joystick driveStick = new Joystick(RobotMap.driveStick);
    Encoder encoder = new Encoder(RobotMap.encoderA, RobotMap.encoderB);
    Gyro gyro = new Gyro(RobotMap.gyro);
    protected DriverStation ds;
    double encoderStart = 0;
    double wayThere = 0;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, RobotMap.frontLeftInvert);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, RobotMap.backLeftInvert);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, RobotMap.frontRightInvert);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, RobotMap.backRightInvert);
        setDefaultCommand(new DriveJoysticks());
        ds = DriverStation.getInstance();
        encoder.setDistancePerPulse(0.027);
        encoder.start();
        encoder.reset();
        gyro.reset();
    }
    
    public void driveJoysticks() {
        robotDrive.mecanumDrive_Cartesian(0, driveStick.getY(), driveStick.getX(), 0);
    }
    public void driveMechanum() {
        robotDrive.mecanumDrive_Cartesian(driveStick.getX(), driveStick.getY(), 0, 0);
    }
    public boolean driveDistance(int inches) {
        if (this.encoderStart == 0) {
            this.encoderStart = encoder.getDistance();
        }
        if (encoder.getDistance() - this.encoderStart  < inches && inches > 0) {
            this.wayThere = (encoder.getDistance() - this.encoderStart) / inches;
            robotDrive.mecanumDrive_Cartesian(0, (2*MathUtils.pow(this.wayThere, 2)-(2*this.wayThere)-0.3), 0, 0);
            return false;
        } else {
            this.encoderStart = 0;
            return true;
        }
    }
    public void resetEncoder() {
        encoder.reset();
    }
    public void resetGyro() {
        gyro.reset();
    }
}
