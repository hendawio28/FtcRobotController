package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
@TeleOp
public class OdoTest extends OpMode {
    private GoBildaPinpointDriver odo;

    MecanumDrive mecanumDrive = new MecanumDrive();
    public void init () {
        mecanumDrive.init(hardwareMap);
        odo = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");

        odo.setOffsets(-160.725, 1.570, DistanceUnit.MM);

        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);

        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);

        odo.resetPosAndIMU();

        Pose2D startingPosition = new Pose2D(DistanceUnit.MM, -609, -1617.8, AngleUnit.DEGREES, 90);
        odo.setPosition(startingPosition);

    }

    @Override
    public void init_loop() {
        odo.update();
    }

    @Override
    public void loop() {
        odo.update();

        double forward = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double rotate = gamepad1.right_stick_x;

        mecanumDrive.drive(forward, strafe, rotate);

        double xPos = odo.getPosX(DistanceUnit.MM);
        double yPos = odo.getPosY(DistanceUnit.MM);
        double xDistance = 1828.8-xPos;
        double yDistance = 1828.8-yPos;
        double distance = Math.hypot(xDistance, yDistance);
        telemetry.addData("X Position", odo.getPosX(DistanceUnit.MM));
        telemetry.addData("Y Position", odo.getPosY(DistanceUnit.MM));
        telemetry.addData("IMU", odo.getHeading(AngleUnit.DEGREES));
        telemetry.addData("Distance to Goal", distance);

        telemetry.update();
    }
}
