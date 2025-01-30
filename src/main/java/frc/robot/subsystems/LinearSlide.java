package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LinearSlide extends SubsystemBase{
    public final SparkMax lIntakeMax;

    public LinearSlide(){
        lIntakeMax = new SparkMax(20,MotorType.kBrushless);
        lIntakeMax.set(0);
    }

    public void LinearSlider(double L){
        SmartDashboard.putNumber("Outake Value", L);
        if (L>0.05){
            lIntakeMax.set(L);
        }  
}
}
