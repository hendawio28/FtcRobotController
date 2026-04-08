package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Indexer {
    private DcMotor indexerMotor;
    private double ticksPerRev = 537.7;
    private int targetPosition = 0;
    //boolean lastModeChange, lastPositionChange;
    private int currentSlot = 0;
    private boolean even;
    public void init(HardwareMap hwMap) {
        indexerMotor = hwMap.get(DcMotor.class, "Indexer");

        indexerMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        indexerMotor.setTargetPosition(targetPosition);

        indexerMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        indexerMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void autoSpin (boolean shoot, boolean intake) {
        if (currentSlot % 2 == 0) {
            even = true;
        }
        else {
            even = false;
        }
        if (shoot && even) {
            currentSlot++;
        } else if (shoot && !even) {
            currentSlot += 2;
        } else if (intake && even) {
            currentSlot +=2;
        } else if (intake && !even) {
            currentSlot+=1;
        }
        targetPosition = (int) Math.round((ticksPerRev/6) * currentSlot);
        indexerMotor.setTargetPosition(targetPosition);
        indexerMotor.setPower(0.3);
    }



































































































//    public void autoSpin (boolean now) {
//        int currentPosition = indexerMotor.getCurrentPosition();
//        if (now == true && currentSlot != 6) {
//            currentSlot++;
//        } else if (currentPosition == 538 && now == true) {
//            indexerMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            indexerMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            currentSlot = 1;
//        }
//        else if (currentPosition == 538 && now == false) {
//            indexerMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            indexerMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            currentSlot = 0;
//
//        }
//        int targetPosition = (int) Math.round((ticksPerRev / 6)*currentSlot);
//        indexerMotor.setTargetPosition(targetPosition);
//        indexerMotor.setPower(1.0);
//    }
//
//    // Add these to the bottom of your indexer class
//    public int getCurrentPosition() {
//        if (indexerMotor != null) {
//            int currentPosition = indexerMotor.getCurrentPosition();
//            return currentPosition;
//        }
//        return 0;
//    }
//
//    public int getTargetPosition() {
//        return targetPosition;
//    }





















































    /*
    public void spin (boolean modeChange, boolean positionChange) {
        int currentPosition = indexerMotor.getCurrentPosition();
        double nextMode = (ticksPerRev/6);
        double nextPosition = (ticksPerRev/3);

        if (modeChange && !lastModeChange) {
            targetPosition += nextMode;
        }
        else if (positionChange && !lastPositionChange) {
            targetPosition += nextPosition;
        }
        intTargetPosition = (int) Math.round(targetPosition);
        indexerMotor.setTargetPosition(intTargetPosition);
        indexerMotor.setPower(1.0);

        lastModeChange = modeChange;
        lastPositionChange = positionChange;
    }

     */
}
