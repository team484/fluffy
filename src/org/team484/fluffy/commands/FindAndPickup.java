/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team484.fluffy.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author Team484
 */
public class FindAndPickup extends CommandGroup {
    
    public FindAndPickup() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
        addSequential(new ZeroGyro(), 0.1);
        addParallel(new PickupArmDown());
        addParallel(new PickupWheelOn());
        addSequential(new WaitCommand(0.8), 0.8);
        addSequential(new FollowBall());
        addParallel(new PickupWheelOn());
        addSequential (new WaitCommand(0.5), 0.5);
        addSequential(new KickerChecked(), 0.3);
        addParallel (new KickerIn());
    }
}