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
    Solenoid On = new Solenoid(RobotMap.SolenoidOn);
    Solenoid Off = new Solenoid(RobotMap.SolenoidOff);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ShooterDown());
        
    }
    public void shooterDown() {
        On.set(false);
        Off.set(true);
        
    }
    public void shooterUp() {
        On.set(true);
        Off.set(false);
    }
    public void shooterOff() {
        On.set(false);
        Off.set(false);
    }
}
