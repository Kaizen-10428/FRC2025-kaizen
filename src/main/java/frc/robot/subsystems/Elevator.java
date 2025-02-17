package frc.robot.subsystems;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Configs;

public class Elevator extends SubsystemBase{
     private final SparkMax LElevator;
     private final SparkMax RElevator;

     public final RelativeEncoder encoder;
     public double enc;
     public double aenc;
    

     private final SparkClosedLoopController LElevatorPID;
     private final SparkClosedLoopController RElevatorPID;

     private final TrapezoidProfile m_Profile = new TrapezoidProfile(new TrapezoidProfile.Constraints(5,3));
     private TrapezoidProfile.State m_goal = new TrapezoidProfile.State();
     private TrapezoidProfile.State m_setpoint = new TrapezoidProfile.State();
     private TrapezoidProfile.State m_currState = new TrapezoidProfile.State();
     public static DigitalInput Input;



     public Elevator(){
        LElevator = new SparkMax(9,MotorType.kBrushless);
        encoder = LElevator.getEncoder();
        RElevator = new SparkMax(10,MotorType.kBrushless);
        LElevatorPID = LElevator.getClosedLoopController();
        RElevatorPID = RElevator.getClosedLoopController();
        LElevator.configure(Configs.ElevatorModule.LelevatorConfig,null, null);
        RElevator.configure(Configs.ElevatorModule.RelevatorConfig, null, null);
        encoder.setPosition(0.0);
        LElevator.set(0);
        RElevator.set(0);
        Input = new DigitalInput(0);


        

     }

    public void ElevatorUP(double R2){
        enc = encoder.getPosition();
        SmartDashboard.putNumber("encoder", enc);
        if (enc<78){
            LElevator.set( R2*0.2);
            RElevator.set(R2*0.2);
        }else{
            LElevator.set(0);
            RElevator.set(0);
        }
    }   
    public void ElevatorDown(double L2){
        double enc = encoder.getPosition();
        if(Input.get() == false){
            LElevator.set(0);
            RElevator.set(0);
            SmartDashboard.putBoolean("Limit Switch", true);
            encoder.setPosition(0.0);

        }
       else if (enc>3){
            LElevator.set(-(L2*0.2));
            RElevator.set(-(L2*0.2));
            SmartDashboard.putBoolean("Limit Switch", false);

        }
    } 
    
    public void setzero(){
        LElevator.set(0);
        RElevator.set(0);
    }

    public void degele(double value){
        double calcdegree = (1000 * value)/17.66;
        
        LElevatorPID.setReference(Math.toRadians(calcdegree), ControlType.kPosition); 

    }

    public void L2Control(double deg){
        m_currState = new TrapezoidProfile.State(encoder.getPosition(), encoder.getVelocity());
        double calcdegree = (1000 * deg)/17.66;
        m_goal = new TrapezoidProfile.State(Math.toRadians(calcdegree), 0);
        m_setpoint = m_Profile.calculate(0.02, m_setpoint, m_goal);
        LElevatorPID.setReference(m_setpoint.position, ControlType.kPosition);
    }
    

    }
