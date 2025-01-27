package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intaker;
import frc.robot.subsystems.Waltson;

public class FullTeleop extends Command {
    private final Elevator elevator;
    private final Waltson waltson;
    private final Intaker intaker;

    private final CommandXboxController controller;

    public FullTeleop(Elevator elevator, Intaker intaker, Waltson waltson, CommandXboxController controller) {
        this.elevator = elevator;
        this.waltson = waltson;
        this.intaker = intaker;
        this.controller = controller;
        addRequirements(elevator, intaker, waltson, intaker);
    }

    @Override
    public void execute() {
        elevator.setSpeed(-controller.getLeftY() * 0.3);
        waltson.setWaltsonSpeed(controller.getRightX() * 0.3);
        waltson.setRollerSpeed(controller.getRightY() * 0.3);
        if (controller.getLeftTriggerAxis() > 0.2) intaker.setSpeed(0.2);
        else if (controller.getRightTriggerAxis() > 0.2) intaker.setSpeed(-0.2);
        else intaker.setSpeed(0);
    }

    @Override
    public void end(boolean interrupted) {
        elevator.setSpeed(0);
        intaker.setSpeed(0);
        
    }

}
