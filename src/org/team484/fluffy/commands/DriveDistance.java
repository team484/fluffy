/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team484.fluffy.commands;

/**
 *
 * @author kevin
 */
public class DriveDistance extends CommandBase {
    int inches = 0;
    boolean finished;
    public DriveDistance(int inches) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(drive);
        this.inches = inches;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        this.finished = drive.driveDistance(this.inches);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
