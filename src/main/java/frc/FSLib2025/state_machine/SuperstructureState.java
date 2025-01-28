package frc.FSLib2025.state_machine;

public enum SuperstructureState {
    DEFAULT(
            GrabberState.EMPTY,
            ElevatorState.DEFAULT,
            WaltsonState.DEFAULT),
    CORAL_STATION(
            GrabberState.INTAKING,
            ElevatorState.CORAL_STATION,
            WaltsonState.DEFAULT),
    L1(
            GrabberState.HOLD,
            ElevatorState.L1,
            WaltsonState.DEFAULT),
    L1_PLACEMENT(
            GrabberState.PALCE,
            ElevatorState.L1,
            WaltsonState.DEFAULT),
    L2(
            GrabberState.HOLD,
            ElevatorState.L2,
            WaltsonState.DEFAULT),
    L2_PLACEMENT(
            GrabberState.PALCE,
            ElevatorState.L2,
            WaltsonState.DEFAULT),
    L3(
            GrabberState.HOLD,
            ElevatorState.L3,
            WaltsonState.DEFAULT),
    L3_PLACEMENT(
            GrabberState.PALCE,
            ElevatorState.L3,
            WaltsonState.DEFAULT),
    L4(
            GrabberState.HOLD,
            ElevatorState.L4,
            WaltsonState.DEFAULT),
    L4_PLACEMENT(
            GrabberState.PALCE,
            ElevatorState.L4,
            WaltsonState.DEFAULT),
    PROCESSOR(
            GrabberState.EMPTY,
            ElevatorState.DEFAULT,
            WaltsonState.INTAKE),
    PROCESSOR_PLACEMECT(
            GrabberState.EMPTY,
            ElevatorState.DEFAULT,
            WaltsonState.PROCESSOR),
    CLIMB_READY(
            GrabberState.EMPTY,
            ElevatorState.DEFAULT,
            WaltsonState.CLIMB_READY),
    CLIMB(
        GrabberState.EMPTY,
        ElevatorState.DEFAULT,
        WaltsonState.CLIMB);

    private final GrabberState grabberState;
    private final ElevatorState elevatorState;
    private final WaltsonState waltsonState;


    private SuperstructureState(GrabberState grabberState, ElevatorState elevatorState, WaltsonState waltsonState) {
        this.grabberState = grabberState;
        this.elevatorState = elevatorState;
        this.waltsonState = waltsonState;
    }

    public GrabberState getGrabberState() {
        return grabberState;
    }

    public ElevatorState getElevatorState() {
        return elevatorState;
    }

    public WaltsonState getWaltsonState() {
        return waltsonState;
    }
}
