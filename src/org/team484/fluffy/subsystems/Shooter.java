/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team484.fluffy.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team484.fluffy.RobotMap;
import org.team484.fluffy.commands.ShooterDown;

/**
 *
 * @author kevin
 */
public class Shooter extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    Solenoid OnL = new Solenoid(RobotMap.solenoidOnL);
    Solenoid OffL = new Solenoid(RobotMap.solenoidOffL);
    Solenoid OnR = new Solenoid(RobotMap.solenoidOnR);
    Solenoid OffR = new Solenoid(RobotMap.solenoidOffR);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ShooterDown());
        
    }
    public void shooterDown() {
        OnL.set(false);
        OffL.set(true);
        OnR.set(false);
        OffR.set(true);
        
    }
    public void shooterUp() {
        OnL.set(true);
        OffL.set(false);
        OnR.set(true);
        OffR.set(false);
    }
    public void shooterOff() {
        OnL.set(false);
        OffL.set(false);
        OnR.set(false);
        OffR.set(false);
    }
}
