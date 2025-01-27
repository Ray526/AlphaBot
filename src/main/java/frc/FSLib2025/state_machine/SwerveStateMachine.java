package frc.FSLib2025.state_machine;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class SwerveStateMachine {
    private static SwerveStateMachine instance;

    private SwerveState swerveState = SwerveState.START;

    public SwerveStateMachine() {
        Shuffleboard.getTab("state machine")
                .add("swerve state", swerveState);
    }

    public static synchronized SwerveStateMachine getInstance() {
        if (instance == null) {
            instance = new SwerveStateMachine();
        }
        return instance;
    }

    public SwerveState getSwerveState() {
        return swerveState;
    }

    public synchronized void update(XboxController driver, XboxController operator) {
        switch (swerveState) {
            case AUTO:
                if (DriverStation.isTeleopEnabled())
                    swerveState = SwerveState.START;
                break;
            case START:
                if (driver.getLeftX() > 0.1 || driver.getLeftY() > 0.1
                        || driver.getRightX() > 0.1 || driver.getRightY() > 0.1)
                    swerveState = SwerveState.DRIVER_CONTROL;
                if (operator.getAButtonPressed())
                    swerveState = SwerveState.PATH_FOLLOWING;
                break;
            case DRIVER_CONTROL:
                if (operator.getAButtonPressed())
                    swerveState = SwerveState.PATH_FOLLOWING;
                break;
            case PATH_FOLLOWING:
                if (driver.getLeftBumperButtonPressed() && driver.getRightBumperButtonPressed())
                    swerveState = SwerveState.DRIVER_CONTROL;
                break;

            default:
                break;
        }

        if (DriverStation.isAutonomousEnabled()) {
            swerveState = SwerveState.AUTO;
        }
    }
}
