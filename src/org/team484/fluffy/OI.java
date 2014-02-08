
package org.team484.fluffy;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.team484.fluffy.commands.DriveMechanum;
import org.team484.fluffy.commands.HighShot;
import org.team484.fluffy.commands.LobShot;
import org.team484.fluffy.commands.PickupArmDown;
import org.team484.fluffy.commands.PickupArmUp;
import org.team484.fluffy.commands.PickupWheelOff;
import org.team484.fluffy.commands.PickupWheelOn;

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
    public OI() {
        mechanum.whileHeld(new DriveMechanum());
        highShot.whenPressed(new HighShot());
        lobShot.whenPressed(new LobShot());
        lowerArm.whenPressed(new PickupArmDown());
        raiseArm.whenPressed(new PickupArmUp());
        pickupBall.whenPressed(new PickupArmDown());
        pickupBall.whileHeld(new PickupWheelOn());
        pickupBall.whenReleased(new PickupWheelOff());
        pickupBall.whenReleased(new PickupArmUp());
    }
}

