/**** RobotMap.java
 * 
 * This holds all global constants and variables.
 * DO NOT PUT FUNCTIONS IN HERE.
 *****/
package edu.ames.frc.robot;

public class RobotMap {
    //Setting go here
    protected final static boolean debugmode = true;
    //Pin Assignments go here!
    
    /*Drive motor pins.
     * For managment reasons we will track each wheel motor based on the side of the traingle/hexagon it is located at
     * Each corner is labeled as either A,B, or C. A being the front wheel, B being the back right, and C being the back left.
     * This nameing trend is reflected in the pin assignments in this class and should continue to be reflected in other parts
     * of the overall robot system.
     */
    protected final static int Apin = 1; // Pin assignment for the front motor A
    protected final static int Bpin = 2;
    protected final static int Cpin = 3;

    protected final static int wheelpin = 4;
    protected final static int climbasstpin = 5;
    protected final static int climbpin = 6;
    protected final static int tiltpin = 7;
    
    // digital I/O pins for the spike relays
    protected final static int pushpin = 7;    
    
    protected final static int gyroport = 1; // gyro is on analog port 1
    
    //Joystick specific buttons below    
    // boolean for picking primary or secondary controller
    protected final static boolean primary = true;
    protected final static boolean secondary = false;
    
    //Playstation 2 controller buttons only
    protected final static int tiltupbutton = 4;   // Button 4 and 2 until we
    protected final static int tiltdownbutton = 2; // have a second joystick
    protected final static int realignpin = 9;
    protected final static int lockrotRight = 6;
    protected final static int lockrotLeft = 5;
    protected final static int autotarg = 10;
    //protected final static int clmpin = 8;
    protected final static int speedboost = 7;
    protected final static int speedunboost = 8;
    
    //Attack Joystick controller buttons
    protected final static int trigger = 1;
    protected final static int armactiv8 = 3;
    protected final static int shooterenable = 7;
    protected final static int feederforward = 11;
    protected final static int feederback = 10;

    protected final static String ok = "It's going to be ok now";
    
    //Values go here!
    protected final static int expo_ramp = 3;
    protected final static double compensatePivot = 0.07; // because the robot drift-pivots
    protected final static double speedcap = 0.5; // speed the robot should usually move
    protected final static double climberspeed = 1.0;
    protected final static double deadzone = .05;
} 
