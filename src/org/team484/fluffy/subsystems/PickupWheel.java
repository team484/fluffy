/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team484.fluffy.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team484.fluffy.RobotMap;
import org.team484.fluffy.commands.PickupWheelOff;

/**
 *
 * @author Team484
 */
public class PickupWheel extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    Relay wheels = new Relay(RobotMap.pickupWheels);
    int isSpin = 0;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new PickupWheelOff());
    }
    public void pickupWheelOff() {
        wheels.set(Relay.Value.kOff);
    }
    public void pickupWheelOn() {
        wheels.set(Relay.Value.kReverse);
    }
    public void pickupWheelBack() {
        wheels.set(Relay.Value.kForward);
    }
    public void pickupWheelHalf() {
        if (isSpin < 2) {
            isSpin++;
            wheels.set(Relay.Value.kReverse);
        } else if (isSpin < 4){
            isSpin++;
            wheels.set(Relay.Value.kOff);
        } else {
            isSpin = 0;
        }
            
    }
}