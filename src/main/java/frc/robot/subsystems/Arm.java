package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;

public class Arm extends SubsystemBase {
    
    SparkMax armt1;
    SparkMax armt2;

    public static AbsoluteEncoder armenc;
    public static SparkClosedLoopController armpid;
    public static double armpos;

    public Arm() {
    armt1 = new SparkMax(13, MotorType.kBrushless);
    // armt2 = new SparkMax(18, MotorType.kBrushless);
    armenc = armt1.getAbsoluteEncoder();
    armpid = armt1.getClosedLoopController();
    armt1.configure(Configs.ArmModule.ArmMotorConfig1,null, null);
    // armt2.configure(Configs.ArmModule.ArmMotorConfig2, null, null);
    armt1.set(0);
    armpos = Math.toDegrees(armenc.getPosition());


    }
    public void armpow(double value){
        armpos = Math.toDegrees(armenc.getPosition());

        // if(value<0 && armpos<180){
        //     armt1.set(value);
        // }else if(value>0 && armpos>23){
        //     armt1.set(value);
        // }else{
        //     armt1.set(0);

        // }
        armt1.set(value);

    }
    public void armdeg(double value){
        armpid.setReference(Math.toRadians(value), ControlType.kPosition);

    }

    public void armcontrol(double value){
    
    }

}
