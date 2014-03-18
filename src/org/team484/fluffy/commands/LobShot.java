/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team484.fluffy.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.team484.fluffy.RobotMap;

/**
 *
 * @author kevin
 */
public class LobShot extends CommandGroup {
    boolean wait = false;
    long time = 0;
    public LobShot() {
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
            addSequential(new WaitCommand(0.5), 0.5);
        }
        addSequential(new ShooterUp(), 0.29);
        addSequential(new ShooterDown(), 0.1);
        addSequential(new WaitCommand(1), 1);
        addSequential(new PickupArmUp(), 0.1);
    }
}
