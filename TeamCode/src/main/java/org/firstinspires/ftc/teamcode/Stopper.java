package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Stopper {
    private final Servo Stopper;
    public boolean isTargetReached = false;
    public State state = State.REST;
    public static Stopper instance;
    private final double rest = 0.0;
    private final double open = 0.3;
    private final double close = 1;

    public enum State {
        OPEN,
        CLOSE,
        REST
    }

    public Stopper(HardwareMap hardwareMap) {
        Stopper = hardwareMap.get(Servo.class, "stopper");

        instance = this;
    }

    public void update() {
        switch (state) {
            case OPEN:
                Stopper.setPosition(open);
                break;
            case CLOSE:
                Stopper.setPosition(close);
                break;
            case REST:
                Stopper.setPosition(rest);
                break;
        }

        if (state == State.OPEN && Stopper.getPosition() == open) {
            isTargetReached = true;
        } else if (state == State.CLOSE && Stopper.getPosition() == close) {
            isTargetReached = true;
        } else if (state == State.REST && Stopper.getPosition() == rest) {
            isTargetReached = true;
        } else {
            isTargetReached = false;
        }
    }

    public String getTurretGateTelemetry() {
        String telemetry = "";
        telemetry = telemetry + "\n Stopper for turret pos = " + Stopper.getPosition();
        telemetry = telemetry + "\n ";
        return telemetry;
    }
}
