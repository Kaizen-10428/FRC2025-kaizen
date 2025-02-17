// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.CoralPlacer;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Gimbal;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.elevatorcmd;
import frc.robot.limelight.Limelight3DDistance;
import frc.robot.commands.IntakeCmd;
import frc.robot.commands.alignStation;

public class RobotContainer {
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  public final Elevator elevator = new Elevator();
  // public final Grabber grabber = new Grabber();
  XboxController m_driverController = new XboxController(0);
  XboxController m_mechController = new XboxController(1);
  CommandXboxController mcomtroller = new CommandXboxController(2);
  Gimbal gimbal = new Gimbal();
  // public final LinearSlide slide = new LinearSlide();
  //public final gimbal gimbal = new gimbal();
  public final Limelight3DDistance aprilDistance = new Limelight3DDistance();
  public final CoralPlacer coral = new CoralPlacer();
  private final SendableChooser<Command> autoChooser;
  // public final Funnel funnel = new Funnel();
  // private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final alignStation align = new alignStation(coral, elevator, aprilDistance, m_robotDrive);

  public RobotContainer(){
    autoChooser = AutoBuilder.buildAutoChooser("fwd");
    SmartDashboard.putData("Auto Chooser", autoChooser);
    // CoralPlacer coral_intake = new CoralPlacer();
    // Elevator elevator_move = new Elevator();
    // NamedCommands.registerCommand("OpenIntake", new IntakeCmd(coral_intake, 0.6));
    // NamedCommands.registerCommand("Elevator Lowest", new elevatorcmd(elevator_move, 0));
    // NamedCommands.registerCommand("Elevator L1", new elevatorcmd(elevator_move, 18));
    // NamedCommands.registerCommand("Elevator L2", new elevatorcmd(elevator_move, 54));
    // NamedCommands.registerCommand("Elevator L3", new elevatorcmd(elevator_move, 108));
    // NamedCommands.registerCommand("Elevator L4", new elevatorcmd(elevator_move, 150));

        
    configureBindings();  
    m_robotDrive.setDefaultCommand(
      new RunCommand(
        () -> m_robotDrive.drive(
          -MathUtil.applyDeadband(m_driverController.getLeftY(),0.06),
          -MathUtil.applyDeadband(m_driverController.getLeftX(),0.06),
          -MathUtil.applyDeadband(m_driverController.getRightX(),0.06),
          true,false),
          m_robotDrive));


  }
   
  private void configureBindings() {
    // new CommandXboxController(0).a().whileTrue(new RunCommand(() -> coral.Take(0.4), coral));
    // new CommandXboxController(0).b().whileTrue(new RunCommand(() -> coral.Take(-0.4), coral));
    new JoystickButton(m_driverController, Button.kR1.value)
    .whileTrue(new RunCommand(
      () -> m_robotDrive.setX(),
      m_robotDrive));
    new JoystickButton(m_driverController, Button.kTriangle.value)
    .whileTrue(new RunCommand(
      () -> m_robotDrive.zeroHeading(),
      m_robotDrive));
  
 //Elevator stuff
 new Trigger(() -> m_mechController.getRightY()<-0.1)
 .whileTrue(new RunCommand(() -> gimbal.gimbalpowup(Math.abs(m_mechController.getRightY())), gimbal));

new Trigger(() -> m_mechController.getRightY()>0.1)
 .whileTrue(new RunCommand(() -> gimbal.gimbalpowdown(-m_mechController.getRightY()), gimbal));

  // new JoystickButton(m_mechController, Button.kTriangle.value) //80 max, // l4 preset
  //   .onTrue(new RunCommand(
  //     () -> elevator.degele(70),
  //     elevator));
  
  new JoystickButton(m_mechController, Button.kCross.value)//red right
  .onTrue(new RunCommand(
    () -> elevator.degele(20),
    elevator));

  new JoystickButton(m_mechController, Button.kTriangle.value)
  .whileTrue(new RunCommand(() -> aprilDistance.updateDistance(m_robotDrive, 0), aprilDistance));

  // new JoystickButton(m_mechController, Button.kCircle.value)//blue left
  // .onTrue(new RunCommand(
  //   () -> elevator.degele(40),
  //   elevator)); 

  // new JoystickButton(m_mechController, Button.kSquare.value)//green down
  // .onTrue(new RunCommand(
  //   () -> elevator.degele(4),
  //   elevator));

  // new Trigger(Math.abs(m_mechController.getRightTriggerAxis())>0.1)
  // .whileTrue(new RunCommand(() -> arm., null))

  // new POVButton(m_mechController, 90)
  // .onTrue( new RunCommand(()->arm.armdeg(0) ));

  new Trigger(() -> Math.abs(m_mechController.getLeftTriggerAxis())>0.1)
  .whileTrue(new RunCommand(() -> elevator.ElevatorDown(m_mechController.getLeftTriggerAxis()), elevator));
  
  new Trigger(() -> Math.abs(m_mechController.getRightTriggerAxis())>0.1)
  .whileTrue(new RunCommand(() -> elevator.ElevatorUP(m_mechController.getRightTriggerAxis()), elevator));
//   new JoystickButton(m_mechController, Button.kTriangle.value) //80 max, // l4 preset
//   .onTrue(new RunCommand(
//     () -> gimbal.gimbaldeg(100),
//     gimbal));
// new JoystickButton(m_mechController, Button.kSquare.value) //80 max, // l4 preset
//   .onTrue(new RunCommand(
//     () -> gimbal.gimbaldeg(20),
//     gimbal));  

  // new POVButton(m_mechController, 270)
  // .onTrue( new RunCommand(()->arm.armdeg(70) ));
  
  // new JoystickButton(m_driverController, Button.kCross.value)         //NEED TO DECIDE BUTTON TO TRIGGER THIS
  // .whileTrue(new RunCommand(
  //   () -> aprilDistance.updateDistance(m_robotDrive),
  //   m_robotDrive
  // ));


        }



  public Command getAutonomousCommand() {
   return new PathPlannerAuto("None");
  }
}
