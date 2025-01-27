package frc.FSLib2025.state_machine;

import static edu.wpi.first.units.Units.Meters;

import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import frc.robot.Constants.SuperstructureConstants;

public enum ElevatorState implements Sendable {
    DEFAULT(SuperstructureConstants.ELEVATOR_DEFAULT_HEIGHT),
    L1(SuperstructureConstants.ELEVATOR_L1_HEIGHT),
    L2(SuperstructureConstants.ELEVATOR_L2_HEIGHT),
    L3(SuperstructureConstants.ELEVATOR_L3_HEIGHT),
    L4(SuperstructureConstants.ELEVATOR_L4_HEIGHT),
    CORAL_STATION(SuperstructureConstants.ELEVATOR_CORAL_STATION_HEIGHT);

    private final Distance height;

    private ElevatorState(Distance height) {
        this.height = height;
    }

    public Distance getHeight() {
        return height;
    }

    public double getValue() {
        return height.in(Meters);
    }

    public String getStateName() {
        return this.name();
    }

    @Override
    public String toString() {
        return String.format("elevator state: %s, height: %.2f meters", this.name(), height.in(Meters));
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("elevator");
        builder.addStringProperty("state", this::getStateName, null);
        builder.addDoubleProperty("height", this::getValue, null);
    }
}
