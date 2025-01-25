package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralPlacer;

public class IntakeCmd extends Command{
    public CoralPlacer Intake;
    public double speed;

    public IntakeCmd(CoralPlacer CoralIntake, double spd){
        this.Intake = CoralIntake;
        this.speed = spd;

    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        Intake.Take(speed);
    }

    @Override
    public void end(boolean interrupted) {
        Intake.Take(0);
    }

    @Override
    public boolean isFinished() {
    return false;
  }
}
