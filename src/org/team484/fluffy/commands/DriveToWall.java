/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team484.fluffy.commands;
/**
 *
 * @author Team484
 */
public class DriveToWall extends CommandBase {
    double setpoint;
    public DriveToWall(double setpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(drivetrain);
        this.setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drivetrain.setSetpoint(setpoint);
        drivetrain.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return drivetrain.getPosition() - setpoint > 0;
    }

    // Called once after isFinished returns true
    protected void end() {
        drivetrain.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        drivetrain.disable();
    }
}