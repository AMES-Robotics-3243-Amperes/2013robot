/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.      
 /*----------------------------------------------------------------------------*/
package edu.ames.frc.robot;
//___
// | |_  o  _     o  _    _|_|_  _    __  _  o __     _  |  _  _  _
// | | | | _>     | _>     |_| |(/_   |||(_| | | |   (_  | (_|_> _>
//The main class is under control of Kolton Yager and Danial Ebling. DO NOT EDIT
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Watchdog;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotProject extends IterativeRobot {

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    protected static MotorControl MC = new MotorControl();
    protected static InputManager IM = new InputManager();
    protected static ImageProcessor IP = new ImageProcessor();
    protected static FrisbeeSimulator FS = new FrisbeeSimulator();
    protected static Communication Com = new Communication();
    protected static SensorInput SI = new SensorInput();
    protected static RobotMap RM = new RobotMap();
    protected static Watchdog wd;
    double pivotval;
    double[] drivemotorvalues;
    double[] joystickangleandspeed;
    double climbval;

    public void robotInit() {
        wd = Watchdog.getInstance();
        wd.setExpiration(1);
        SI.init();
        IM.init();
        MC.init();
        wd.feed();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        //Tarun example
       /* double[] example = new double[3];
         example[0] = 0;
         example[1] = 1;
         example[2] = 1;
         MC.drive(example);
         */
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        while (isOperatorControl() && isEnabled()) {
            wd.feed();
            MC.shooter(true);
            IM.updateAllButtons();
            if (!IM.climber.getState()) {
                joystickangleandspeed = IM.getPureAxis();
                pivotval = IM.getPivot();
                drivemotorvalues = MC.convertHeadingToMotorCommands(joystickangleandspeed[0], joystickangleandspeed[1]);
                drivemotorvalues = MC.setSpeedCap(drivemotorvalues, IM.speedBoost.getState());
                drivemotorvalues = MC.addPivot(drivemotorvalues, pivotval);
                wd.feed();
                System.out.println("motors: " + drivemotorvalues[0] + ",\t" + drivemotorvalues[1] + ",\t" + drivemotorvalues[2]);
                MC.drive(drivemotorvalues);
                MC.climb(0);

                if(IM.activ8tilt.getState()){
                    MC.shootertilt(IM.getSecondaryAxis());
                }
                // this is only temporary, should be moved to second joystick
              /*  if (IM.tiltdown.getState()) {
                    MC.shootertilt(1);
                }
                if (IM.tiltup.getState()){ //Not entirely sure what is going on here
                    MC.shootertilt(-1);
                }
                if (!IM.tiltup.getState() && !IM.tiltdown.getState()) {
                    MC.shootertilt(0);
                }
                  MC.pusher(IM.fireButton.getState());
        */

            } else {
                climbval = IM.getClimb();
                climbval = MC.Climblimit(climbval);
                MC.climb(climbval);
            }
        }
    }
}
