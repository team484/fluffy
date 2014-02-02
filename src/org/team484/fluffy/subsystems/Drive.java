/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team484.fluffy.subsystems;

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
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, RobotMap.frontLeftInvert);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, RobotMap.backLeftInvert);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, RobotMap.frontRightInvert);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, RobotMap.backRightInvert);
        setDefaultCommand(new DriveJoysticks());
    }
    
    public void driveJoysticks() {
        robotDrive.mecanumDrive_Cartesian(0, driveStick.getY(), driveStick.getX(), 0);
    }
    public void driveMechanum() {
        robotDrive.mecanumDrive_Cartesian(driveStick.getX(), driveStick.getY(), 0, 0);
    }
}
