/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team484.fluffy.subsystems;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team484.fluffy.RobotMap;
import org.team484.fluffy.commands.DriveWithJoysticks;

/**
 *
 * @author Team484
 */
public class DriveTrain extends PIDSubsystem {

    private static final double Kp = 0.04;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;

    RobotDrive robotDrive = new RobotDrive(RobotMap.frontLeftMotor,RobotMap.backLeftMotor,RobotMap.frontRightMotor,RobotMap.backRightMotor);
    Joystick driveStick = new Joystick(RobotMap.driveStick);
    Ultrasonic sonic = new Ultrasonic(RobotMap.ping, RobotMap.echo);
    Gyro gyro = new Gyro(RobotMap.gyro);
    AnalogChannel leftIR = new AnalogChannel(RobotMap.ir1);
    AnalogChannel rightIR = new AnalogChannel(RobotMap.ir2);
    boolean wasMech = false;
    boolean startAuto = true;
    protected DriverStation ds;
    double old1 = 0;
    double old2 = 0;
    double old3 = 0;
    public DriveTrain() {
        super("DriveTrain", Kp, Ki, Kd);

        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        sonic.setAutomaticMode(true);
        ds = DriverStation.getInstance();
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, RobotMap.frontLeftInvert);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, RobotMap.backLeftInvert);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, RobotMap.frontRightInvert);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, RobotMap.backRightInvert);
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveWithJoysticks());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        if (sonic.getRangeInches() > 0) {
        if (old1 == 0) {
            old1 = sonic.getRangeInches();
            old2 = sonic.getRangeInches();
            old3 = sonic.getRangeInches();
        } else {
            old3 = old2;
            old2 = old1;
            old1 = sonic.getRangeInches();
        }
        if (old3 > old2 && old3 > old1) {
            return old3;
        } else if (old2 > old3 && old2 > old1) {
            return old2;
        } else {
            return old1;
        } 
        } else {
            return 100;
        }
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
        
        SmartDashboard.putNumber("Output", output);
        mechanumDrive(0, output / 2, 0, true, false);
    }
    public boolean zeroGyro() {
        gyro.reset();
        return true;
    }
    public boolean setStartAuto() {
        startAuto = true;
        return true;
    }
    public void autoMechDrive() {
        if (startAuto) {
            gyro.reset();
            startAuto = false;
        }
        double rotation = -(gyro.getAngle() / 160);
        //System.out.println("Gyro: "+gyro.getAngle() + " rotation: "+rotation);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, RobotMap.frontLeftInvert);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, RobotMap.backLeftInvert);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, RobotMap.frontRightInvert);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, RobotMap.backRightInvert);
        robotDrive.mecanumDrive_Cartesian(0, -0.5, rotation, 0);
    }
        public void autoMechDriveBack() {
        if (startAuto) {
            gyro.reset();
            startAuto = false;
        }
        double rotation = -(gyro.getAngle() / 160);
        //System.out.println("Gyro: "+gyro.getAngle() + " rotation: "+rotation);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, RobotMap.frontLeftInvert);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, RobotMap.backLeftInvert);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, RobotMap.frontRightInvert);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, RobotMap.backRightInvert);
        robotDrive.mecanumDrive_Cartesian(0, 0.5, rotation, 0);
    }
    public void followBall() {
        double rotation = -(gyro.getAngle() / 160);
        double speed = 0;
        int currentLeft = leftIR.getValue();
        int currentRight = rightIR.getValue();
        double leftIn = (MathUtils.pow(currentLeft, -1.22)*6627) / 0.533333333;
        double rightIn = (MathUtils.pow(currentRight, -1.22)*6627) / 0.533333333;
        System.out.println("Left: " + leftIn + " Right: " + rightIn);
        if ((leftIn < 64 || rightIn < 64) && SmartDashboard.getBoolean("No Ball", true)) {
            double mech = MathUtils.log(Math.abs(leftIn - rightIn)) / 20;
            if (leftIn - rightIn < 0) {
                mech = 0 - mech;
            }
            if (mech > 0.5) {
                mech = 0.5;
            } else if (mech < -0.5) {
                mech = -0.5;
            } else if (mech > -0.1 && mech < 0) {
                mech = 0;
            } else if (mech > 0 && mech < 0.1) {
                mech = 0;
            }
            if (leftIn < rightIn && leftIn > 12) {
                speed = (leftIn - 12) / 20;
            } else if (leftIn > rightIn && rightIn > 12) {
                speed = (rightIn - 12) / 20;
            } else {
                speed = 0;
            }
            robotDrive.mecanumDrive_Cartesian(mech, speed, rotation, 0);
        } /**else {
            double vector = ds.getAnalogIn(1);
            if (vector < 1.5) {
                robotDrive.mecanumDrive_Cartesian(0.5, 0, rotation, 0);
            } else if (vector > 3.5) {
                robotDrive.mecanumDrive_Cartesian(-0.5, 0, rotation, 0);
            }
        }**/
    }
    public void followBallTele() {
        double rotation = -(gyro.getAngle() / 160);
        double speed = 0;
        int currentLeft = leftIR.getValue();
        int currentRight = rightIR.getValue();
        double leftIn = (MathUtils.pow(currentLeft, -1.22)*6627) / 0.533333333;
        double rightIn = (MathUtils.pow(currentRight, -1.22)*6627) / 0.533333333;
        //System.out.println("Left: " + leftIn + " Right: " + rightIn);
        System.out.println("Left: " + leftIn + " Right: " +rightIn);
        if ((leftIn < 64 || rightIn < 64) && SmartDashboard.getBoolean("No Ball", true)) {
            double mech = MathUtils.log(Math.abs(leftIn - rightIn)) / 20;
            if (leftIn - rightIn < 0) {
                mech = 0 - mech;
            }
            if (mech > 0.5) {
                mech = 0.5;
            } else if (mech < -0.5) {
                mech = -0.5;
            } else if (mech > -0.1 && mech < 0) {
                mech = 0;
            } else if (mech > 0 && mech < 0.1) {
                mech = 0;
            }
            if (leftIn < rightIn && leftIn > 12) {
                speed = (leftIn - 12) / 20;
            } else if (leftIn > rightIn && rightIn > 12) {
                speed = (rightIn - 12) / 20;
            } else {
                speed = 0;
            }
            robotDrive.mecanumDrive_Cartesian(mech, speed, rotation, 0);
        } else {
            robotDrive.mecanumDrive_Cartesian(0, driveStick.getY(), MathUtils.pow(driveStick.getX(), 3), 0);
        }
        SmartDashboard.putNumber("Match Time", 230 - ds.getMatchTime());
    }
    public void mechanumDrive(double x, double y, double rotation, boolean autonomous, boolean mechanum) {
        int currentLeft = leftIR.getValue();
        int currentRight = rightIR.getValue();
        double leftIn = (MathUtils.pow(currentLeft, -1.22)*6627) / 0.53333333;
        double rightIn = (MathUtils.pow(currentRight, -1.22)*6627) / 0.533333333;
        //System.out.println("Left: " + leftIn + " Right: " + rightIn);
        if (mechanum && !this.wasMech) {
            this.wasMech = true;
            gyro.reset();
        }
        if (!mechanum) {
            this.wasMech = false;
        }
        if (autonomous || mechanum) {
            rotation = -(gyro.getAngle() / 200);
        } else {
            rotation = rotation * rotation * rotation;
            //System.out.println("Rotation: " + rotation);
        }
        if (Math.abs(x) > Math.abs(y)) {
            y = 0;
        } else {
            x = 0;
        }
        if (!(autonomous && sonic.getRangeInches() < 2)) {
            robotDrive.mecanumDrive_Cartesian(x, y, rotation, 0);
        } else {
            robotDrive.mecanumDrive_Cartesian(0, 0, 0, 0);
        }
        
        if (sonic.getRangeInches() > 30 && sonic.getRangeInches() < 70) {
            SmartDashboard.putBoolean("Shoot", true);
            SmartDashboard.putString("Can Shoot", "Ready To Shoot");
        } else {
            SmartDashboard.putBoolean("Shoot", false);
            SmartDashboard.putString("Can Shoot", "Don't Shoot");
        }
        SmartDashboard.putNumber("Match Time", 230 - ds.getMatchTime());
        SmartDashboard.putNumber("Gyro", gyro.getAngle());
        SmartDashboard.putNumber("UltraSonic", sonic.getRangeInches());
        //SmartDashboard.putNumber("voltage", ds.getBatteryVoltage());
        //System.out.println(SmartDashboard.getString("DriveFromWall"));
    }
    public void LeapDrive(double x, double y, double rot) {
        robotDrive.mecanumDrive_Cartesian(x, y, rot, 0);
        if (sonic.getRangeInches() > 30 && sonic.getRangeInches() < 70) {
            SmartDashboard.putBoolean("Shoot", true);
            SmartDashboard.putString("Can Shoot", "Ready To Shoot");
        } else {
            SmartDashboard.putBoolean("Shoot", false);
            SmartDashboard.putString("Can Shoot", "Don't Shoot");
        }
        SmartDashboard.putNumber("Match Time", 230 - ds.getMatchTime());
        SmartDashboard.putNumber("Gyro", gyro.getAngle());
        SmartDashboard.putNumber("UltraSonic", sonic.getRangeInches());
    }
}