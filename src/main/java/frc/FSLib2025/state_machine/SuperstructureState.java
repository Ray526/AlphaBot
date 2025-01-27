package frc.FSLib2025.state_machine;

public enum SuperstructureState {
    DEFAULT(
            IntakerState.EMPTY,
            ElevatorState.DEFAULT,
            WaltsonState.DEFAULT),
    CORAL_STATION(
            IntakerState.INTAKING,
            ElevatorState.CORAL_STATION,
            WaltsonState.DEFAULT),
    L1(
            IntakerState.HOLD,
            ElevatorState.L1,
            WaltsonState.DEFAULT),
    L1_PLACEMENT(
            IntakerState.PALCE,
            ElevatorState.L1,
            WaltsonState.DEFAULT),
    L2(
            IntakerState.HOLD,
            ElevatorState.L2,
            WaltsonState.DEFAULT),
    L2_PLACEMENT(
            IntakerState.PALCE,
            ElevatorState.L2,
            WaltsonState.DEFAULT),
    L3(
            IntakerState.HOLD,
            ElevatorState.L3,
            WaltsonState.DEFAULT),
    L3_PLACEMENT(
            IntakerState.PALCE,
            ElevatorState.L3,
            WaltsonState.DEFAULT),
    L4(
            IntakerState.HOLD,
            ElevatorState.L4,
            WaltsonState.DEFAULT),
    L4_PLACEMENT(
            IntakerState.PALCE,
            ElevatorState.L4,
            WaltsonState.DEFAULT),
    PROCESSOR(
            IntakerState.EMPTY,
            ElevatorState.DEFAULT,
            WaltsonState.INTAKE),
    PROCESSOR_PLACEMECT(
            IntakerState.EMPTY,
            ElevatorState.DEFAULT,
            WaltsonState.PROCESSOR),
    CLIMB_READY(
            IntakerState.EMPTY,
            ElevatorState.DEFAULT,
            WaltsonState.CLIMB_READY),
    CLIMB(
        IntakerState.EMPTY,
        ElevatorState.DEFAULT,
        WaltsonState.CLIMB);

    private final IntakerState intakerState;
    private final ElevatorState elevatorState;
    private final WaltsonState waltsonState;


    private SuperstructureState(IntakerState intakerState, ElevatorState elevatorState, WaltsonState waltsonState) {
        this.intakerState = intakerState;
        this.elevatorState = elevatorState;
        this.waltsonState = waltsonState;
    }

    public IntakerState getIntakerState() {
        return intakerState;
    }

    public ElevatorState getElevatorState() {
        return elevatorState;
    }

    public WaltsonState getWaltsonState() {
        return waltsonState;
    }
}
