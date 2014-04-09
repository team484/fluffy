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
public class Autonomous2 extends CommandGroup {
    
    public Autonomous2() {
        addSequential(new SetStartAuto());
        addParallel(new PickupArmDown());
        addSequential(new DriveForTime(), 1.6);
        addSequential(new WaitCommand(0.5), 0.5);
        addSequential(new ShooterUp(), 1);
        addParallel(new ShooterDown());
        addSequential(new DriveForTimeBack(), 1.6);
        addParallel(new PickupWheelOn());
        addSequential(new FollowBall(), 1.9);
        addParallel(new PickupWheelOn());
        addSequential(new KickerChecked(), 0.3);
        addParallel (new KickerIn());
        addParallel(new PickupWheelOff());
        addSequential(new DriveForTime(), 1.7);
        addSequential(new WaitCommand(0.5), 0.5);
        addSequential(new SetShootBall(), 0.05);
        addSequential(new ShooterUp(), 1);
        addParallel(new ShooterDown());
    }
}
