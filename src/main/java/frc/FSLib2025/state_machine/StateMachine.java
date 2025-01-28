package frc.FSLib2025.state_machine;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class StateMachine {
    private static StateMachine instance;

    private SuperstructureState superstructureState = SuperstructureState.DEFAULT;

    public StateMachine() {
        Shuffleboard.getTab("state machine")
                .add("elevator state", getSuperstructureState().getElevatorState());
        Shuffleboard.getTab("state machine")
                .add("intaker state", getSuperstructureState().getGrabberState());
                Shuffleboard.getTab("state machine")
                .add("waltson state", getSuperstructureState().getWaltsonState());
    }

    public static synchronized StateMachine getInstance() {
        if (instance == null) {
            instance = new StateMachine();
        }
        return instance;
    }

    public SuperstructureState getSuperstructureState() {
        return superstructureState;
    }


    public synchronized void update(XboxController controller) {
        // normal state change
        switch (superstructureState) {
            case DEFAULT:
                if (controller.getLeftBumperButtonPressed())
                    superstructureState = SuperstructureState.CORAL_STATION;
                if (controller.getRightBumperButtonPressed())
                    superstructureState = SuperstructureState.DEFAULT;
                if (controller.getAButtonPressed())
                    superstructureState = SuperstructureState.L1;
                if (controller.getXButtonPressed())
                    superstructureState = SuperstructureState.L2;
                if (controller.getBButtonPressed())
                    superstructureState = SuperstructureState.L3;
                if (controller.getYButtonPressed())
                    superstructureState = SuperstructureState.L4;
                break;

            case CORAL_STATION:
                if (controller.getLeftBumperButtonPressed())
                    superstructureState = SuperstructureState.DEFAULT;
                break;

            case L1:
                if (controller.getRightTriggerAxis() > 0.2)
                    superstructureState = SuperstructureState.L1_PLACEMENT;
                if (controller.getAButtonPressed())
                    superstructureState = SuperstructureState.DEFAULT;
                if (controller.getXButtonPressed())
                    superstructureState = SuperstructureState.L2;
                if (controller.getBButtonPressed())
                    superstructureState = SuperstructureState.L3;
                if (controller.getYButtonPressed())
                    superstructureState = SuperstructureState.L4;
                break;
            case L1_PLACEMENT:
                if (controller.getRightTriggerAxis() < 0.2)
                    superstructureState = SuperstructureState.DEFAULT;
                break;

            case L2:
                if (controller.getRightTriggerAxis() > 0.2)
                    superstructureState = SuperstructureState.L2_PLACEMENT;
                if (controller.getAButtonPressed())
                    superstructureState = SuperstructureState.L1;
                if (controller.getXButtonPressed())
                    superstructureState = SuperstructureState.DEFAULT;
                if (controller.getBButtonPressed())
                    superstructureState = SuperstructureState.L3;
                if (controller.getYButtonPressed())
                    superstructureState = SuperstructureState.L4;
                break;
            case L2_PLACEMENT:
                if (controller.getRightTriggerAxis() < 0.2)
                    superstructureState = SuperstructureState.DEFAULT;
                break;

            case L3:
                if (controller.getRightTriggerAxis() > 0.2)
                    superstructureState = SuperstructureState.L3_PLACEMENT;
                if (controller.getAButtonPressed())
                    superstructureState = SuperstructureState.L1;
                if (controller.getXButtonPressed())
                    superstructureState = SuperstructureState.L2;
                if (controller.getBButtonPressed())
                    superstructureState = SuperstructureState.DEFAULT;
                if (controller.getYButtonPressed())
                    superstructureState = SuperstructureState.L4;
                break;
            case L3_PLACEMENT:
                if (controller.getRightTriggerAxis() < 0.2)
                    superstructureState = SuperstructureState.DEFAULT;
                break;

            case L4:
                if (controller.getRightTriggerAxis() > 0.2)
                    superstructureState = SuperstructureState.L4_PLACEMENT;
                if (controller.getAButtonPressed())
                    superstructureState = SuperstructureState.L1;
                if (controller.getXButtonPressed())
                    superstructureState = SuperstructureState.L2;
                if (controller.getBButtonPressed())
                    superstructureState = SuperstructureState.L3;
                if (controller.getYButtonPressed())
                    superstructureState = SuperstructureState.DEFAULT;
                break;
            case L4_PLACEMENT:
                if (controller.getRightTriggerAxis() < 0.2)
                    superstructureState = SuperstructureState.DEFAULT;
                break;
            case PROCESSOR:
                if (controller.getRightTriggerAxis() > 0.2)
                    superstructureState = SuperstructureState.PROCESSOR_PLACEMECT;
                if (controller.getAButtonPressed())
                    superstructureState = SuperstructureState.L1;
                if (controller.getXButtonPressed())
                    superstructureState = SuperstructureState.L2;
                if (controller.getBButtonPressed())
                    superstructureState = SuperstructureState.L3;
                if (controller.getYButtonPressed())
                    superstructureState = SuperstructureState.L4;
                break;
            case PROCESSOR_PLACEMECT:
                if (controller.getRightTriggerAxis() < 0.2)
                    superstructureState = SuperstructureState.DEFAULT;
                break;
            case CLIMB_READY:
                if (controller.getRightTriggerAxis() > 0.2)
                    superstructureState = SuperstructureState.CLIMB;
                if (controller.getRightBumperButtonPressed())
                    superstructureState = SuperstructureState.DEFAULT;
                if (controller.getAButtonPressed())
                    superstructureState = SuperstructureState.L1;
                if (controller.getXButtonPressed())
                    superstructureState = SuperstructureState.L2;
                if (controller.getBButtonPressed())
                    superstructureState = SuperstructureState.L3;
                if (controller.getYButtonPressed())
                    superstructureState = SuperstructureState.L4;
                break;
            case CLIMB:
                if (controller.getRightBumperButtonPressed())
                    superstructureState = SuperstructureState.DEFAULT;
                if (controller.getAButtonPressed())
                    superstructureState = SuperstructureState.L1;
                if (controller.getXButtonPressed())
                    superstructureState = SuperstructureState.L2;
                if (controller.getBButtonPressed())
                    superstructureState = SuperstructureState.L3;
                if (controller.getYButtonPressed())
                    superstructureState = SuperstructureState.L4;
                break;

            default:
                break;
        }

        // force set to default
        if (controller.getLeftBumperButton() && controller.getRightBumperButton()) {
            superstructureState = SuperstructureState.DEFAULT;
        }

    }
}
