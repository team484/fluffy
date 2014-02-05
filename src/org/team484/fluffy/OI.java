
package org.team484.fluffy;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.team484.fluffy.commands.DriveMechanum;
import org.team484.fluffy.commands.HighShot;
import org.team484.fluffy.commands.LobShot;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    Joystick driveStick = new Joystick(RobotMap.driveStick);
    Joystick shootStick = new Joystick(RobotMap.shootStick);
    
    Button mechanum = new JoystickButton(driveStick, RobotMap.driveMechanumButton);
    Button lobButton = new JoystickButton(shootStick, RobotMap.lobButton);
    Button shootButton = new JoystickButton(shootStick, RobotMap.shootButton);
    public OI() {
        mechanum.whileHeld(new DriveMechanum());
        shootButton.whenPressed(new HighShot());
        lobButton.whenPressed(new LobShot());
    }
}

