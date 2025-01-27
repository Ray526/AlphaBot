package frc.robot.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.SwerveConstants;
import frc.robot.subsystems.Swerve;

public class TeleopSwerve extends Command {

    private final Swerve swerve;

    private SlewRateLimiter xLimiter = new SlewRateLimiter(3.0);
    private SlewRateLimiter yLimiter = new SlewRateLimiter(3.0);
    private SlewRateLimiter rotLimiter = new SlewRateLimiter(3.0);
    private SlewRateLimiter xSlowLimiter = new SlewRateLimiter(0.75);
    private SlewRateLimiter ySlowLimiter = new SlewRateLimiter(0.75);
    private SlewRateLimiter rotSlowLimiter = new SlewRateLimiter(0.75);


    private double xSpeed = 0.0;
    private double ySpeed = 0.0;
    private double rotSpeed = 0.0;

    private CommandXboxController driver;

    public TeleopSwerve(Swerve swerve, CommandXboxController driver) {
        this.swerve = swerve;
        this.driver = driver;
        addRequirements(swerve);
    }

    @Override
    public void execute() {

        // reset odometry
        if (driver.getHID().getBackButtonPressed()) {
            swerve.setOdometryPosition(new Pose2d());
            swerve.setGyroYaw(new Rotation2d());
        }

        if (driver.getHID().getLeftBumperButtonPressed()) {
            // slow mode
            xSpeed = xSlowLimiter.calculate(-driver.getLeftY());
            ySpeed = ySlowLimiter.calculate(-driver.getLeftX());
            rotSpeed = rotSlowLimiter.calculate(-driver.getRightX());
            swerve.drive(
                    new Translation2d(xSpeed, ySpeed).times(SwerveConstants.MAX_MODULE_SPEED),
                    rotSpeed * SwerveConstants.MAX_MODULE_ROTATIONAL_SPEED,
                    true);
        } else {
            // normal drive
            xSpeed = xLimiter.calculate(-driver.getLeftY());
            ySpeed = yLimiter.calculate(-driver.getLeftX());
            rotSpeed = rotLimiter.calculate(-driver.getRightX());
            swerve.drive(
                    new Translation2d(xSpeed, ySpeed).times(SwerveConstants.MAX_MODULE_SPEED),
                    rotSpeed * SwerveConstants.MAX_MODULE_ROTATIONAL_SPEED,
                    true);
        }
    }

    @Override
    public void end(boolean interrupted) {
        swerve.drive(new Translation2d(), 0, false);
    }
}
