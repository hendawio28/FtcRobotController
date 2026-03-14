package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
@TeleOp
public class OdoTest extends OpMode {
    private GoBildaPinpointDriver odo;
    public void init () {
        odo = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");

        odo.setOffsets(-160.725, 1.570, DistanceUnit.MM);

        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);

        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);

        odo.resetPosAndIMU();

        Pose2D startingPosition = new Pose2D(DistanceUnit.MM, -923.925, 1601.47, AngleUnit.RADIANS, 0);
        odo.setPosition(startingPosition);

    }

    @Override
    public void init_loop() {
        odo.update();
    }

    @Override
    public void loop() {
        odo.update();

        telemetry.addData("X Offset", odo.getPosX(DistanceUnit.MM));
        telemetry.addData("Y Offset", odo.getPosY(DistanceUnit.MM));
        telemetry.addData("IMU", odo.getHeading(AngleUnit.DEGREES));

        telemetry.update();
    }
}
