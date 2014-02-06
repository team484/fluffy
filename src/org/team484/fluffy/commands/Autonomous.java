/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team484.fluffy.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author kevin
 */
public class Autonomous extends CommandGroup {
    
    public Autonomous() {
        boolean target = false;
        addSequential(new WaitCommand(0.1)); //Wait for vision to update
        //Check for target and set target to true if found
        
        if (target) {
            addSequential(new DriveDistance(30)); //inches to drive forward
        } else {
            addSequential(new WaitCommand(5));
            addSequential(new DriveDistance(30)); //inches to drive forward
        }
        addSequential(new WaitCommand(0.5)); //Make sure robot is completely stopped
        addSequential(new HighShot());
    }
}
