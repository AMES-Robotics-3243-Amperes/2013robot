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
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//ServerSocket Javadoc: http://docs.oracle.com/javase/1.4.2/docs/api/java/net/ServerSocket.html
//http://www.wbrobotics.com/javadoc/javax/microedition/io/SocketConnection.html

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

    public void ConsoleMsg(String msg, int type) {//What is this? Levi explain please.
        messages[type] = msg;
    }

    public void MsgPrint() {
    }

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
        long rcnum;
        public PISocket(boolean activated) throws Exception {
            active = activated;
            psock = (SocketConnection) Connector.open("socket://127.0.0.1:3243");
            InputStream is = psock.openInputStream();
            rcnum = is.read();
            is.close();
            psock.close();
            int i;                          //Rename this, if you want.  It's just there for the for loop.
            long Paca=rcnum;                //There might be a better way to do this, but this is what I ended up with.  The variable "Paca" might be unnecessary. 
            long Array[]= new long[4];      //Here's the array of the four values.  This definitely needs to be renamed, and it might not need to be "longs".
            for(i=0;i<4;i++)     
            {
                Array[i]=Paca%100;          //This modulus SHOULD store the last two numbers of Paca.  At the time of writing, it hasn't been tested.
                Paca =- Array[i];           //This makes the last two digits of paca 0.
                Paca = Paca / 100;          //This knocks the zeroes off of Paca, so when it loops again the program will take the next two numbers.
            }                               //Again, I don't actually know if this will work, because we haven't tested it.  Some things definitely need to be changed, such as the variable names.
                                            //It should be noted that the values will be stored from the last two digits to the first two.  This shouldn't be too much of a problem, but it might cause some mistakes. 
                                            //  And here ends our work.     -Tarun & Noah 
            /*      long newRcnum;          //This is what we had before  I came up with a efficient way. 
                newRcnum= rcnum/100;
                if(rcnum/newRcnum >100 )
                {
                    newRcnum=newRcnum-1;
                }
                long newRcnum2;
                newRcnum2= newRcnum/1000;
                if(newRcnum/newRcnum2 >1000 )
                {
                    newRcnum2=newRcnum2-1;
                }
                long newRcnum3;
                newRcnum3= newRcnum2/1000;
                if(newRcnum2/newRcnum3 >1000 )
                {
                    newRcnum3=newRcnum3-1;
                }
                long newRcnum4;
                newRcnum4= newRcnum3/1000;
                if(newRcnum3/newRcnum4 >1000 )
                {
                    newRcnum4=newRcnum4-1;
                }
             */   // Is this right Kole?
        }
    }
}
//Figure out how to take a long number such as 67455423497 and seperate it into many individual numbers according to the layout shown above
