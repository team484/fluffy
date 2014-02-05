/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team484.fluffy.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author user
 */
public class LobShot extends CommandGroup {
    
    public LobShot() {
        addSequential(new ShooterUp(), 0.1);
        addSequential(new ShooterDown(), 0.01);
    }
}
