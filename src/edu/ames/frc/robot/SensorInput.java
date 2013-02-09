/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * This class polls robot sensors and switches, such as gyros, buttons, and accelerometers.
 */
/*
 * This is a test to teach programming team.
 */
package edu.ames.frc.robot;
  
import edu.wpi.first.wpilibj.Gyro;

public class SensorInput {
    static RobotMap rm = new RobotMap();
    static Gyro gy;
    
//    /* Initialize sensor values & variables */
    void init() {
        gy = new Gyro(rm.gyroport); 
        gy.reset();
    }
    
    /* Get the absolute angle of the robot */
    double getGyroAngle() {
        double a = gy.getAngle();
        return a;
    }
    
    /* useful to have, just in case our robot gyro drifts */
    void resetGyroAngle() {
        gy.reset();
    }
    
}
