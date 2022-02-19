package frc.robot.commands.auto;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;
import frc.robot.utils.*;

import java.util.List;

public class SideShootAuto extends SequentialCommandGroup {

    public SideShootAuto(Drivetrain drivetrain){
        new Rotation2d();
        new Rotation2d();
        CustomRamseteCommand goToBall =
            RamseteGenerator.getRamseteCommand(
            drivetrain,
            List.of(
                new Pose2d(Units.feetToMeters(29.273), Units.feetToMeters(19.107), Rotation2d.fromDegrees(70)),
                new Pose2d(Units.feetToMeters(39.351), Units.feetToMeters(21.330), Rotation2d.fromDegrees(0.00))
            ),
            Units.feetToMeters(12), Units.feetToMeters(8), true
        );
        CustomRamseteCommand parktoLine =
            RamseteGenerator.getRamseteCommand(
            drivetrain,
            List.of(
                new Pose2d(Units.feetToMeters(39.351), Units.feetToMeters(21.330), Rotation2d.fromDegrees(0.00)),
                new Pose2d(Units.feetToMeters(44.99), Units.feetToMeters(21.330), Rotation2d.fromDegrees(0.00))
            ),
            Units.feetToMeters(9), Units.feetToMeters(7), true
        );
                addCommands(
                    sequence(
                       // hopefully the code for shooter here new AutomaticShoot(shooter, conveyor, intake, 2620, false, 3), //was 2300
                       new InstantCommand(() -> drivetrain.resetOdometry(goToBall.getInitialPose())),
                        deadline(
                            goToBall
                           //(code for intaking the ball) AutoIntake(shooter,intake, AutoIntake.IntakeType.INTAKE)
                        ),
                        // hopefully the code for shooter here new AutomaticShoot(shooter, conveyor, intake, 2620, false, 3), //was 2300
                        parktoLine.andThen(() -> drivetrain.tankDriveVolts(0,0))
                    )
            
                );
                
    }
}
