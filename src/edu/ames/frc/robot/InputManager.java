/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ames.frc.robot;
//We need to test this.
import edu.wpi.first.wpilibj.Joystick;
import com.sun.squawk.util.MathUtils;
/* List of buttons/toggles needed
 * Manual pivot toggle: 2
 * Speed boost button: Active joystick push
 * Force shoot button: 4 
 * Force Realign button: 7
 * Stop auto-target toggle: 10
 * Activate frisbee grab button: 8
 * Launch climb procedure: Simultaneously &  5,6,7,8,9, and 10.
 *
 */

public class InputManager {
//Git is good
    protected static Joystick PS2Cont = new Joystick(1);
    protected static boolean dzactive  = false; // In case we want to check for deadzoneing being active
    protected static double[][] axisOC = new double[2][2]; // Stores the original copies of the axis reads, for use elsewhere.
    public static class directions{
        public directions(){
        }
    }
    
    public static double[] GetPureAxis() { // Gets, stores, and returns the status of the joysticks on the PS2 Controller
        /* We will use a double dimension arry to hold the joystick data so that everything can be sent to other functions.
         * Both of the first dimensions will hold 2 doulbes, the first is the x & y axis of the first (paning) joystick
         * The second dimension holds the x & y for the second (pivoting) joystick
         */
        double[][] axis = new double[2][2];// Variable for storing all that data
        double[] dir = new double [2];
        axis[0][0] = PS2Cont.getRawAxis(1);// X
        axis[0][1] = PS2Cont.getRawAxis(2);// Y
        axis[1][0] = PS2Cont.getRawAxis(3);// X
        axisOC[0][0] = axis[0][0];
        axisOC[0][1] = axis[0][1];
        axisOC[0][0] = axis[1][0];
        axisOC[0][0] = axis[1][1];
 //       axis[1][1] = PS2Cont.getRawAxis(4);// Y We dont actually need this value
        axis = deadzone(axis);
        axis = ramp(axis);
        dir = translate(axis);
        return (dir); // Returns axis data to the caller.
    }

    protected static double[][] deadzone(double[][] axis) {// Checks for deadzone
        //This is a skeleton of the deadzone funtion. Mark should fill this in.
        if (axis[0][0] <= RobotMap.deadzone && axis[0][0] >= rm.deadzone) { 
            axis[0][0] = 0;
        }
        if (axis[0][1] <= RobotMap.deadzone && axis[0][1] >= rm.deadzone) {
            axis[0][1] = 0;
        }
        if (axis[1][0] <= RobotMap.deadzone && axis[1][0] >= rm.deadzone) {
            axis[1][0] = 0;
        }
        if (axis[1][1] <= RobotMap.deadzone && axis[1][1] >= rm.deadzone) {
            axis[1][1] = 0;
        }
        return (axis);
    }
    protected static double[][] ramp(double[][] axis){
        axis[0][0] = MathUtils.pow(axis[0][0], rm.expo_ramp);
        return (axis);
    }
    protected static double[] translate(double[][] axis){// Ramps inputs so that they curve all happy like.
        //This is a skeleton of the ramp funtion. Mark should fill this in.
        double speed = 0;
        double angle = 0;
        double hypo = 0;
        double[] vect = new double[2];
        speed = Math.sqrt(MathUtils.pow(axis[0][0],2) + MathUtils.pow(axis[0][1], 2));
        angle = RobotArithmetic.arcTangent(axisOC[0][0], axisOC[0][1]);
        vect[0] = angle;
        vect[1] = speed;
        return (vect);
    }
    //protected static double[] translate(double[][] axis){// Translates deadzoned and scaled inputs into whatever exact type of input MotorControl needs/wants.
        
        //This is a skeleton of the translate funtion. Mark should fill this in.
        
        //return (ABC);
   // }
}
