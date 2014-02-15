/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team484.fluffy.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team484.fluffy.RobotMap;

/**
 *
 * @author kevin
 */
public class Autonomous extends CommandGroup {

    int blobs = 0;
    int[] boxcoord = new int[17];

    public Autonomous() {
        boolean target = false;
        boolean tryAgain = true;
        addSequential(new WaitCommand(0.1)); //Wait for vision to update
        //Check for target and set target to true if found
        while (tryAgain) {
            try {
                this.blobs = Integer.parseInt(SmartDashboard.getString("BLOB_COUNT"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (this.blobs == 2) {
                String strCoords = SmartDashboard.getString("MEQ_COORDINATES");
                int start = 0;
                int count = 0;
                while (count < 17) {
                    int comma = strCoords.indexOf(',', start);
                    if (comma == -1) {
                        break;
                    }
                    String str1 = strCoords.substring(start, comma);

                    boxcoord[count++] = Integer.parseInt(str1.trim());
                    start = comma + 1;
                }
                if (boxcoord[9] + 20 > boxcoord[3] && boxcoord[15] < boxcoord[3] + 20) {
                    target = true;
                    tryAgain = false;
                } else {
                    tryAgain = true;
                }
            } else {
                target = false;
                tryAgain = false;
            }
        }
        if (target) {
            addSequential(new DriveFromWall(SmartDashboard.getNumber("DriveFromWall", RobotMap.defaultDriveFromWall)));
            //addSequential(new DriveDistance(30)); //inches to drive forward
        } else {
            addSequential(new WaitCommand(5));
            addSequential(new DriveFromWall(SmartDashboard.getNumber("DriveFromWall", RobotMap.defaultDriveFromWall)));
            //addSequential(new DriveDistance(30)); //inches to drive forward
        }
        addSequential(new HighShot());
    }
}
