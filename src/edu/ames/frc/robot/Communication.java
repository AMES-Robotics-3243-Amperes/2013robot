/* Currently managed by: Levi Raby
 * 
 * 
 * 
 */
package edu.ames.frc.robot;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
 import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.Communication;

/* import edu.wpi.first.wpilibj.command.Command;
 *
 * import edu.wpi.first.wpilibj.templates.OI;
 * 
 * 
 * 
 * 
 */


public class Communication {
    
    public static boolean isinit = false;   // make sure we're already initted
    public static boolean debugmode = false; // debugging symbols enabled/disabled?
    public static long time; 
    public static int step = 0;
    public static double voltage;
    public static boolean mainlcd = false;  // enable/disable main led
    public static boolean sensorlighton;
    public static String[] messages = new String[5];
    public static int cycle = 0;            // how many clock cycles the robot ran, divided by 500
    
    public void ConsoleMsg (String msg, int type)
    {                                                   
        messages[type]=msg;  //array hold all robot mesages

    }
    
    public void MsgPrint ()
    {       long newtime =System.currentTimeMillis();
            if (newtime - time > 500){
                
                if (!messages[0].equals(" ")) {      
                        SmartDashboard.putString("-Shooter Speed: ", messages[0]);
            } 
                
                if(!messages[1].equals(" ")){
                        SmartDashboard.putString("-Shooter Angle: ", messages[1]);
                }                
                
                if(!messages[2].equals(" ")){
                        SmartDashboard.putString("-Gyro Angle: ", messages[2]);
               }
                
                if(!messages[3].equals(" ")){
                        SmartDashboard.putString("-", messages[3]);       //allows us to put any string
                }
                
                if(!messages[4].equals(" ")){
                        SmartDashboard.putString("-", messages[4]);
                }
            
                if(!messages[5].equals(" ")){
                        SmartDashboard.putString("-", messages[5]);
                }
                
                if(!messages[6].equals(" ")){
                        SmartDashboard.putString("-", messages[6]);
                }
                
                
                
                
            } 
    }
    
}