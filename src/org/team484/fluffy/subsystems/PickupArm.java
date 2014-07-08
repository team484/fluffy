/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team484.fluffy.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team484.fluffy.RobotMap;
import org.team484.fluffy.commands.PickupArmUp;

/**
 *
 * @author Team484
 */
public class PickupArm extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    Solenoid armUp = new Solenoid(RobotMap.pickupArmUp);
    Solenoid armDown = new Solenoid(RobotMap.pickupArmDown);
    boolean go = false;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new PickupArmUp());
        
    }
    public void pickupArmDown() {
        armUp.set(false);
        armDown.set(true);
        
    }
    public void pickupArmUp() {
       // if (this.go) {
            armUp.set(true);
            armDown.set(false);
       // } else {
            //armUp.set(false);
            //armDown.set(true);
        //this.go = !this.go;
    }
    public void pickupArmUpChecked() {
        if (SmartDashboard.getBoolean("No Ball", true)) {
            armUp.set(true);
            armDown.set(false);
        }
    }
}