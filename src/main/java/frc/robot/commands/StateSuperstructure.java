package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Elevator;
import frc.FSLib2025.state_machine.StateMachine;
import frc.FSLib2025.state_machine.SuperstructureState;
import frc.robot.subsystems.Grabber;
import frc.robot.subsystems.Waltson;

public class StateSuperstructure extends Command {

    private final Elevator elevator;
    private final Grabber intaker;
    private final Waltson waltson;

    private SuperstructureState state;

    public StateSuperstructure(Elevator elevator, Grabber intaker, Waltson waltson) {
        this.elevator = elevator;
        this.intaker = intaker;
        this.waltson = waltson;
        addRequirements(elevator, intaker, waltson);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        state = StateMachine.getInstance().getSuperstructureState();
        elevator.setDesiredState(state.getElevatorState());
        intaker.setDesiredState(state.getIntakerState());
        waltson.setDesiredState(state.getWaltsonState());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
    }
}
