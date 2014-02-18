/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team484.fluffy.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author kevin
 */
public class Autonomous extends CommandGroup {

    public Autonomous() {
        int blobs = -1;
        int[] boxcoord = new int[17];
        boolean target = true;
        boolean tryAgain = true;
        addSequential(new ZeroGyro(), 0.3);
        /*System.out.println("Zero-ing gyro");
        
        System.out.println("Finished Zero-ing");
        addSequential(new WaitCommand(0.3), 0.3);
        //Check for target and set target to true if found
        try {
        while (tryAgain) {
            System.out.println("Trying");
            while(blobs == -1){
                System.out.println("Unset Blob count");
            try {
                blobs = Integer.parseInt(SmartDashboard.getString("BLOB_COUNT"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            }
            
            /*
            if (blobs == 2) {
                System.out.println("2 blobs found");
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
                    System.out.println("Setting coords");
                }
                if (boxcoord[9] + 20 > boxcoord[3] && boxcoord[15] < boxcoord[3] + 20) {
                    target = true;
                    tryAgain = false;
                    System.out.println("GO!");
                } else {
                    tryAgain = true;
                    System.out.println("Not sure");
                }
            } else {
                target = false;
                tryAgain = false;
                System.out.println("No GO!");
            }
        }
        System.out.println("Decision Made");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
            addSequential(new isHot(), 4);
           System.out.println("Driving From Wall");
           addParallel(new PickupArmDown());
           addSequential(new DriveFromWall(0), 1.7);
            //addSequential(new DriveDistance(30)); //inches to drive forward
            //addSequential(new DriveDistance(30)); //inches to drive forward
        //addSequential(new DriveToWall(SmartDashboard.getNumber("DriveFromWall", RobotMap.defaultDriveFromWall)));
        //addSequential(new DriveFromWall(SmartDashboard.getNumber("DriveFromWall", RobotMap.defaultDriveFromWall)));
        addParallel(new HighShot());
       //addSequential(new DriveFromWall(0), 0.5);
    }
}
