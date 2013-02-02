/* Currently managed by: Levi Raby,Tarun Sunkaraneni, Adam Jessop
 * 
 * 
 * 
 */
package edu.ames.frc.robot;
//Non-explicit imports of io libraries. Once code is finished it should be changed into a set of explicit imports.
import java.io.*;
import javax.microedition.io.*;
import edu.wpi.first.wpilibj.DriverStation;
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

    public void MsgPrint() {
            // printMsg("update", true, 0);

    
     }
    
    
   /* public void MsgPrint( ){
        
    }
*/
    /***************************************************************************
     *               Raspberry Pi Protocol Information
     * 
     * The Raspberry Pi sends information about direction (height and 
     * angle) the goals are in, distance to the goals, and confidence 
     * level (how sure it is that the blobs it found are actual goals) 
     * This is sent in a long integer:
     * 
     * (example): 67455423497
     * 
     * The 11-digit number is split up as such:
     * 
     *      check numbers (to make sure the integer sent is valid)
     *            |          |          |
     *     67    4    55    4    23    4    97
     *      |          |          |          |
     *  angle(x)  height(y)   distance    confidence level
     * 
     * All values range from 0 to 99 - that is, these values use 
     * arbitrary units.
     **************************************************************************/
    public class PISocket {

        boolean active;
        SocketConnection psock = null;
        Long rcnum;
        public PISocket(boolean activated) throws Exception {
            active = activated;
            psock = (SocketConnection) Connector.open("socket://127.0.0.1:3243");
            InputStream is = psock.openInputStream();
            rcnum = new Long(is.read());
            is.close();
            psock.close();
        }
    }
}
//Figure out how to take a long number such as 67455423497 and seperate it into many individual numbers according to the layout shown above
