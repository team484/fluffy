package org.team484.fluffy.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.team484.fluffy.OI;
import org.team484.fluffy.subsystems.DriveTrain;
import org.team484.fluffy.subsystems.PickupArm;
import org.team484.fluffy.subsystems.PickupWheel;
import org.team484.fluffy.subsystems.Shooter;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems
    public static Shooter shooter = new Shooter();
    public static PickupWheel pickupWheel = new PickupWheel();
    public static PickupArm pickupArm = new PickupArm();
    public static DriveTrain drivetrain = new DriveTrain();
   

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();

        // Show what command your subsystem is running on the SmartDashboard
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
