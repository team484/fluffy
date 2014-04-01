
package org.team484.fluffy;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team484.fluffy.commands.Autonomous;
import org.team484.fluffy.commands.DriveFromWall;
import org.team484.fluffy.commands.HighShot;
import org.team484.fluffy.commands.KickerIn;
import org.team484.fluffy.commands.KickerOut;
import org.team484.fluffy.commands.LobShot;
import org.team484.fluffy.commands.PickupArmDown;
import org.team484.fluffy.commands.PickupArmUp;
import org.team484.fluffy.commands.PickupWheelBack;
import org.team484.fluffy.commands.PickupWheelHalf;
import org.team484.fluffy.commands.PickupWheelOff;
import org.team484.fluffy.commands.PickupWheelOn;
import org.team484.fluffy.commands.ZeroGyro;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    Joystick driveStick = new Joystick(RobotMap.driveStick);
    Joystick shootStick = new Joystick(RobotMap.shootStick);
    
    Button mechanum = new JoystickButton(driveStick, RobotMap.driveMechanumButton);
    Button highShot = new JoystickButton(shootStick, RobotMap.shootHigh);
    Button lobShot = new JoystickButton(shootStick, RobotMap.lobBall);
    Button lowerArm = new JoystickButton(shootStick, RobotMap.lowerArm);
    Button raiseArm = new JoystickButton(shootStick, RobotMap.raiseArm);
    Button pickupBall = new JoystickButton(shootStick, RobotMap.pickupBall);
    Button driveFromWall = new JoystickButton(driveStick, 6);
    Button autonomous = new JoystickButton(driveStick, 7);
    Button magTest = new JoystickButton(shootStick, 8);
    Button kickerOut = new JoystickButton(driveStick, 2);
    Button pickupWheelBack = new JoystickButton(shootStick, RobotMap.pickupWheelBack);
    Button pickupWheelHalf = new JoystickButton(driveStick, 7);
    public double getDriveX() {
        return driveStick.getX();
    }
    public double getDriveZ() {
        return driveStick.getZ();
    }
    public double getDriveY() {
        return driveStick.getY();
    }
    public boolean getDriveTrigger() {
        return driveStick.getTrigger();
    }
    public OI() {
        //autonomous.whenPressed(new Autonomous());
        //highShot.whenPressed(new PickupArmDown()); //added
        //lobShot.whenPressed(new PickupArmDown()); //added
        //pickupBall.whenPressed(new PickupArmDown()); //added
        highShot.whenPressed(new HighShot());
        lobShot.whenPressed(new LobShot());
        lowerArm.whenPressed(new PickupArmDown());
        raiseArm.whenPressed(new PickupArmUp());
        pickupBall.whenPressed(new PickupArmDown());
        pickupBall.whileHeld(new PickupWheelOn());
        pickupBall.whenReleased(new PickupWheelOff());
        pickupBall.whenPressed(new PickupArmDown());
        kickerOut.whileHeld(new KickerOut());
        kickerOut.whenReleased(new KickerIn());
        pickupWheelBack.whileHeld(new PickupWheelBack());
        //driveFromWall.whenPressed(new ZeroGyro());
        //driveFromWall.whileHeld(new DriveFromWall(SmartDashboard.getNumber("DriveFromWall", RobotMap.defaultDriveFromWall)));
    }
}

