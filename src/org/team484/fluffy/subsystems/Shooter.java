/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team484.fluffy.subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team484.fluffy.RobotMap;
import org.team484.fluffy.commands.ShooterDown;

/**
 *
 * @author kevin
 */
public class Shooter extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    Solenoid OnL = new Solenoid(RobotMap.solenoidOnL);
    Solenoid OffL = new Solenoid(RobotMap.solenoidOffL);
    Solenoid OnR = new Solenoid(RobotMap.solenoidOnR);
    Solenoid OffR = new Solenoid(RobotMap.solenoidOffR);
    DigitalInput allowShot = new DigitalInput(RobotMap.contactSwitch);
    AnalogChannel ballIR = new AnalogChannel(2);
    Joystick shootStick = new Joystick(RobotMap.shootStick);
    JoystickButton safetyOverride = new JoystickButton(shootStick, 11);

    boolean shooterBall = true;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ShooterDown());
        
    }
    public void setShootBall() {
        if (ballIR.getValue() > 190) {
            shooterBall = true;
        } else {
            shooterBall = false;
        }
    }
    public void shooterDown() {
        OnL.set(false);
        OffL.set(true);
        OnR.set(false);
        OffR.set(true);
        if (ballIR.getValue() > 190) {
            SmartDashboard.putBoolean("No Ball", false);
            SmartDashboard.putBoolean("Ball Good", true);
            SmartDashboard.putString("Ball Status", "Good To Go");
        } else if (ballIR.getValue() > 110) {
            SmartDashboard.putBoolean("No Ball", false);
            SmartDashboard.putBoolean("Ball Good", false);
            SmartDashboard.putString("Ball Status", "Kick It, Son!");
        } else {
            SmartDashboard.putBoolean("No Ball", true);
            SmartDashboard.putBoolean("Ball Good", false);
            SmartDashboard.putString("Ball Status", "Grab a Ball");
        }
        
    }
    public void shooterUp() {
        if ((allowShot.get() && shooterBall) || safetyOverride.get()) {
            OnL.set(true);
            OffL.set(false);
            OnR.set(true);
            OffR.set(false);
        }
    }
    public void shooterOff() {
        OnL.set(false);
        OffL.set(false);
        OnR.set(false);
        OffR.set(false);
    }
    public boolean dontShoot() {
        if (safetyOverride.get()) {
            return true;
        } else {
            return allowShot.get();
        }
    }
    public boolean isHot() {
        try {
        if (Integer.parseInt(SmartDashboard.getString("BLOB_COUNT", "0")) < 2) {
            return false;
        } else {
            return true;
        }
        } catch (Exception e) {
            return false;
        }
    }
}
