/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team484.fluffy.commands;

/**
 *
 * @author Team484
 */
public class DontShoot extends CommandBase {
    boolean end = false;
    public DontShoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires (shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        this.end = shooter.dontShoot();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.end;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}