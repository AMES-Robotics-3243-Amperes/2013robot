/* Currently managed by Derick Anderson, Michael Chueng, and Hunter Schmidt
 * This class uses exact physics to simulate the flight of the frissbee and ultimately returns
 * The angle of attack for shooting the frisbee from the turret. 
 * 
 */
package edu.ames.frc.robot;
import com.sun.squawk.util.MathUtils;
public class FrisbeeSimulator {

    public static double BasicTrig(double distance, double height) {
        double AoA = 0; // AoA is notation for Angle of Attack
        AoA = MathUtils.atan2(height, distance);
        return (AoA);
    }
}
