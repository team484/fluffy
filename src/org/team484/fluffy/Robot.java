/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package org.team484.fluffy;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team484.fluffy.commands.Autonomous;
import org.team484.fluffy.commands.Autonomous2;
import org.team484.fluffy.commands.CommandBase;

public class Robot extends IterativeRobot {

    Command autonomousCommand;
    Command autonomous2Command;
    Compressor compressor;
    protected DriverStation ds;
    Relay LEDs;
    Joystick drive = new Joystick(RobotMap.driveStick);
    double counter = 0;
    public void robotInit() {
        Relay camLED = new Relay(RobotMap.camLED);
        
        LEDs = new Relay(RobotMap.spikeLED);
        ds = DriverStation.getInstance();
        System.out.println(ds.getAlliance().value);
        if (ds.getAlliance().value == 0) {
            //Red Team
            System.out.println("Go Red!");
            try {
                LEDs.set(Relay.Value.kReverse);
            } catch (Exception e) {
            }
        } else {
            //Blue Team
            System.out.println("Go Blue!");
            try {
                LEDs.set(Relay.Value.kForward);
            } catch (Exception e) {
            }
        }
        camLED.set(Relay.Value.kReverse);
        autonomousCommand = new Autonomous();
        autonomous2Command = new Autonomous2();
        CommandBase.init();
        compressor = new Compressor(RobotMap.pressureSwitch, RobotMap.compressorRelay);
        compressor.start();
    }

    public void autonomousInit() {
        if (ds.getDigitalIn(1)) {
            autonomousCommand.start();
        } else {
            autonomous2Command.start();
        }
        if (ds.getAlliance().value == 0) {
            //Red Team
            System.out.println("Go Red!");
            try {
                LEDs.set(Relay.Value.kReverse);
            } catch (Exception e) {
            }
        } else {
            //Blue Team
            System.out.println("Go Blue!");
            try {
                LEDs.set(Relay.Value.kForward);
            } catch (Exception e) {
            }
        }
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        autonomous2Command.cancel();
        autonomousCommand.cancel();
        if (ds.getAlliance().value == 0) {
            //Red Team
            System.out.println("Go Red!");
            try {
                LEDs.set(Relay.Value.kReverse);
            } catch (Exception e) {
            }
        } else {
            //Blue Team
            System.out.println("Go Blue!");
            try {
                LEDs.set(Relay.Value.kForward);
            } catch (Exception e) {
            }
        }
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putBoolean("Pressure Gauge", compressor.getPressureSwitchValue());
        /**try {
            if (drive.getZ() + 1 < 0.1) {
                if (ds.getAlliance().value == 0) {
            //Red Team
            try {
                LEDs.set(Relay.Value.kReverse);
            } catch (Exception e) {
            }
        } else {
            //Blue Team
            try {
                LEDs.set(Relay.Value.kForward);
            } catch (Exception e) {
            }
        }
            } else {
                if (counter < 5) {
                    counter = counter + drive.getZ() + 1;
                } else {
                    counter = 0;
                    if (LEDs.get() == Relay.Value.kForward) {
                        LEDs.set(Relay.Value.kReverse);
                    } else {
                        LEDs.set(Relay.Value.kForward);
                    }
                }
            }
        } catch (Exception e) {
            
        }**/
    }

    public void testPeriodic() {

        LiveWindow.run();
    }
}
