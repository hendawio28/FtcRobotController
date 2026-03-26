package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Spindexer {
    private DcMotor spindexerMotor;
    private double ticksPerRev;
    private int targetPosition = 0;
    boolean lastModeChange, lastPositionChange;
    public void init (HardwareMap hwMap) {
        spindexerMotor = hwMap.get(DcMotor.class, "Indexer");

        ticksPerRev = spindexerMotor.getMotorType().getTicksPerRev();

        spindexerMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        spindexerMotor.setTargetPosition(0);

        spindexerMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        spindexerMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void spin (boolean modeChange, boolean posiitonChange) {
        int currentPosition = spindexerMotor.getCurrentPosition();
        int nextMode = (int) (ticksPerRev/6);
        int nextPosition = (int) (ticksPerRev/3);

        if (modeChange && !lastModeChange) {
            targetPosition += nextMode;
        }
        else if (posiitonChange && !lastPositionChange) {
            targetPosition += nextPosition;
        }
        spindexerMotor.setTargetPosition(targetPosition);

        spindexerMotor.setPower(0.3);
        lastModeChange = modeChange;
        lastPositionChange = posiitonChange;
    }

}
