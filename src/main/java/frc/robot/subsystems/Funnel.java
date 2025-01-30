package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Funnel extends SubsystemBase{
    private final SparkMax lIntakeMax;

    public Funnel(){
        lIntakeMax = new SparkMax(12,MotorType.kBrushless);
        lIntakeMax.set(0);
    }

    public void CoralAcceptor(double L){
        SmartDashboard.putNumber("Outake Value", L);
        if (L>0.05){
            lIntakeMax.set(L);
        }  
}

}
