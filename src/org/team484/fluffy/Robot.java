/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team484.fluffy;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.team484.fluffy.commands.Autonomous;
import org.team484.fluffy.commands.CommandBase;


public class Robot extends IterativeRobot {

    Command autonomousCommand;

    public void robotInit() {
        Relay camLED = new Relay(RobotMap.camLED);
        camLED.set(Relay.Value.kReverse);
        autonomousCommand = new Autonomous();
        CommandBase.init();
        Compressor compressor = new Compressor(RobotMap.pressureSwitch,RobotMap.compressorRelay);
        compressor.start();
    }

    public void autonomousInit() {
        autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}
