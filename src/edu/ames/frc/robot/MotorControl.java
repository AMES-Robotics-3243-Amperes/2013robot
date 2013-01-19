/* Currently managed by Tarun Sunkaraneni, and Ben Rose
 * This class manages all motor writing and managment. May need to deal with complex Gyro
 * input. 
 */
package edu.ames.frc.robot;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

public class MotorControl {
    
    private static RobotMap rm = new RobotMap();
    
    private static Victor A = new Victor(rm.Apin);
    private static Victor B = new Victor(rm.Bpin);
    private static Victor C = new Victor(rm.Cpin);
    
    static void Drive(double[] mv){
        A.set(mv[0]);
        B.set(mv[1]);
        C.set(mv[2]);
    }
     
    /* This converts the direction we want to go (from 0 to 1, relative to the robot's base)
     * and speed (from 0 to 1) directly to values for the three omni-wheeled motors.
     */
    double[] convertHeadingToMotorCommands(double direction, double speed) {
        double[] motorvalue = new double[3];
        
        /* so, we'll define the direction we want to go as "forward". There are
         * 3 different points where only two motors will need to run (if the direction
         * is parallel to a motor's axle).
         */
        // 0 is what we define as the "front" motor - what we measure our heading angle from,
        // 1 is the motor one position clockwise from that, and
        // 2 is the motor one position counter-clockwise from 0.
        motorvalue[0] = speed * Math.sin(direction);
        motorvalue[1] = speed * Math.sin(direction - (2 * Math.PI / 3));
        motorvalue[2] = speed * Math.sin(direction + (2 * Math.PI / 3));
        
        return motorvalue;
    }
}
