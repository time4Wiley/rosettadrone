package sq.rogue.rosettadrone.fragment.actuator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import dji.common.mission.waypointv2.Action.ActionTypes;
import dji.common.mission.waypointv2.Action.WaypointActuator;
import dji.common.mission.waypointv2.Action.WaypointAircraftControlParam;
import dji.common.mission.waypointv2.Action.WaypointAircraftControlRotateYawParam;
import dji.common.mission.waypointv2.Action.WaypointAircraftControlStartStopFlyParam;
import dji.common.mission.waypointv2.WaypointV2MissionTypes;
import sq.rogue.rosettadrone.settings.Tools;
import wiley.sq.rogue.rosettadrone.R;
import wiley.sq.rogue.rosettadrone.databinding.FragmentAircraftActuatorBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AircraftActuatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AircraftActuatorFragment extends Fragment implements IActuatorCallback {


    private FragmentAircraftActuatorBinding binding;


    private ActionTypes.AircraftControlType type = ActionTypes.AircraftControlType.UNKNOWN;

    public static AircraftActuatorFragment newInstance() {
        AircraftActuatorFragment fragment = new AircraftActuatorFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAircraftActuatorBinding.inflate(inflater, container, false);
        setupRadioGroup();
        return binding.getRoot();
    }

    private void setupRadioGroup() {
        binding.radioType.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_rotate_yaw:
                    binding.clYaw.setVisibility(View.VISIBLE);
                    binding.clStartStop.setVisibility(View.GONE);
                    type = ActionTypes.AircraftControlType.ROTATE_YAW;
                    break;
                case R.id.rb_start_stop_fly:
                    binding.clStartStop.setVisibility(View.VISIBLE);
                    binding.clYaw.setVisibility(View.GONE);
                    type = ActionTypes.AircraftControlType.START_STOP_FLY;
                    break;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public WaypointActuator getActuator() {
        float yaw = Tools.getFloat(binding.etYawAngle.getText().toString(), 0);
        WaypointAircraftControlRotateYawParam yawParam = new WaypointAircraftControlRotateYawParam.Builder()
                .setDirection(binding.boxYawClockwise.isChecked() ? WaypointV2MissionTypes.WaypointV2TurnMode.CLOCKWISE : WaypointV2MissionTypes.WaypointV2TurnMode.COUNTER_CLOCKWISE)
                .setRelative(binding.boxYawRelative.isChecked())
                .setYawAngle(yaw)
                .build();
        WaypointAircraftControlStartStopFlyParam startParam = new WaypointAircraftControlStartStopFlyParam.Builder()
                .setStartFly(binding.boxStartStopFly.isChecked())
                .build();
        WaypointAircraftControlParam controlParam = new WaypointAircraftControlParam.Builder()
                .setAircraftControlType(type)
                .setFlyControlParam(startParam)
                .setRotateYawParam(yawParam)
                .build();

        return new WaypointActuator.Builder()
                .setActuatorType(ActionTypes.ActionActuatorType.AIRCRAFT_CONTROL)
                .setAircraftControlActuatorParam(controlParam)
                .build();
    }
}
