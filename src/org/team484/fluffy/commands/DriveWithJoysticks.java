/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team484.fluffy.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Team484
 */
public class DriveWithJoysticks extends CommandBase {
    
    public DriveWithJoysticks() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (SmartDashboard.getNumber("Status", 0) > 0) {
            if (SmartDashboard.getNumber("Status", 0) == 1) {
                if (SmartDashboard.getBoolean("AllowRot", false)) {
                    //Rotation only
                    drivetrain.LeapDrive(0, 0, SmartDashboard.getNumber("Rot", 0));
                } else {
                    if (oi.getDriveTrigger()) {
                        drivetrain.mechanumDrive(oi.getDriveX(), oi.getDriveY(), 0, false, true);
                    } else {
                        drivetrain.mechanumDrive(0, oi.getDriveY(), oi.getDriveX(), false, false);
                    }
                }
            } else if (SmartDashboard.getNumber("Status", 0) == 2) {
                //Rotation and Drive
                drivetrain.LeapDrive(SmartDashboard.getNumber("DriveX", 0), SmartDashboard.getNumber("DriveY", 0), SmartDashboard.getNumber("Rot", 0));
            } else {
               if (oi.getDriveTrigger()) {
                    drivetrain.mechanumDrive(oi.getDriveX(), oi.getDriveY(), 0, false, true);
                } else {
                    drivetrain.mechanumDrive(0, oi.getDriveY(), oi.getDriveX(), false, false);
                } 
            }
        } else {
            if (oi.getDriveTrigger()) {
                drivetrain.mechanumDrive(oi.getDriveX(), oi.getDriveY(), 0, false, true);
            } else {
                drivetrain.mechanumDrive(0, oi.getDriveY(), oi.getDriveX(), false, false);
            }
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}