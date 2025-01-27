package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.RobotConstants;
import frc.robot.commands.FullTeleop;
import frc.robot.commands.StateSuperstructure;
import frc.robot.commands.TeleopSwerve;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intaker;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.Waltson;

public class RobotContainer {

    public final CommandXboxController driver = new CommandXboxController(RobotConstants.DRIVE_CONTROLLER_PORT);
    public final CommandXboxController operator = new CommandXboxController(RobotConstants.OPERATOR_BOARD_PORT);

    public final Elevator elevator = Elevator.getInstance();
    public final Waltson waltson = Waltson.getInstance();
    public final Intaker intaker = Intaker.getInstance();
    public final Swerve swerve = Swerve.getInstance();

    // private final FullTeleop fullTeleop = new FullTeleop(elevator, intaker, waltson, operator);
    private final StateSuperstructure stateSuperstructure = new StateSuperstructure(elevator, intaker, waltson);
    private final TeleopSwerve teleopSwerve = new TeleopSwerve(swerve, driver);
    
    private final SendableChooser<Command> autoChooser;
    
    public RobotContainer() {

        configureDefaultCommands();

        autoChooser = AutoBuilder.buildAutoChooser();
        autoChooser.setDefaultOption("null", null);
        autoChooser.addOption("FullAuto", new PathPlannerAuto("FullAuto"));

        SmartDashboard.putData("Auto Chooser", autoChooser);
    }

    private void configureDefaultCommands() {
        elevator.setDefaultCommand(stateSuperstructure);
        intaker.setDefaultCommand(stateSuperstructure);
        waltson.setDefaultCommand(stateSuperstructure);
        swerve.setDefaultCommand(teleopSwerve);
    }

    public Command getAutonomousCommand() {
        return autoChooser.getSelected();
    }
}
