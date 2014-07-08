/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team484.fluffy.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team484.fluffy.RobotMap;

/**
 *
 * @author kevin
 */
public class HighShot extends CommandGroup {
    boolean wait = false;
    long time = 0;
    public HighShot() {
        addParallel(new PickupArmDown());
        time = System.currentTimeMillis();
        addSequential(new DontShoot(), 0.1);
        if (time + 75 > System.currentTimeMillis()) {
            wait = true;
        } else {
            wait = false;
        }
        addSequential(new DontShoot());
        if (wait) {
            //addSequential(new WaitCommand(0.4), 0.4);
        }
        addSequential(new SetShootBall(), 0.1);
        addSequential(new ShooterUp(), 1);
        addSequential(new ShooterDown(), 0.1);
        //addSequential(new WaitCommand(1), 1);
        //addSequential(new PickupArmUp(), 0.1);
    }
}
