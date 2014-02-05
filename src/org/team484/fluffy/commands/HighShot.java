/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team484.fluffy.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author kevin
 */
public class HighShot extends CommandGroup {
    
    public HighShot() {
        addSequential(new ShooterUp(), 1);
        addSequential(new ShooterDown());
    }
}
