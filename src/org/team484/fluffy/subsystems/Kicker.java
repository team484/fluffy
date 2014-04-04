/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team484.fluffy.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team484.fluffy.RobotMap;
import org.team484.fluffy.commands.KickerIn;

/**
 *
 * @author Team484
 */
public class Kicker extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    Solenoid kickerOn = new Solenoid(RobotMap.kickerOn);
    Solenoid kickerOff = new Solenoid(RobotMap.kickerOff);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new KickerIn());
    }
    public void kickerOut() {
        kickerOn.set(true);
        kickerOff.set(false);
    }
    public void kickerIn() {
        kickerOn.set(false);
        kickerOff.set(true);
    }
    public void kickerChecked() {
        if (!SmartDashboard.getBoolean("Ball Good", false) && !SmartDashboard.getBoolean("No Ball", false)) {
            kickerOn.set(true);
            kickerOff.set(false);
        } else {
            kickerOn.set(false);
            kickerOff.set(true);
        }
    }
}