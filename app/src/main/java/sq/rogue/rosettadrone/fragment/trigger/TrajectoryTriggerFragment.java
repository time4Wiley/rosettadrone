package sq.rogue.rosettadrone.fragment.trigger;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import dji.common.mission.waypointv2.Action.ActionTypes;
import dji.common.mission.waypointv2.Action.WaypointTrajectoryTriggerParam;
import dji.common.mission.waypointv2.Action.WaypointTrigger;
import sq.rogue.rosettadrone.settings.Tools;
import wiley.sq.rogue.rosettadrone.databinding.FragmentTrajectoryTriggerBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrajectoryTriggerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrajectoryTriggerFragment extends BaseTriggerFragment implements ITriggerCallback {

    private FragmentTrajectoryTriggerBinding binding;

    public static TrajectoryTriggerFragment newInstance() {
        TrajectoryTriggerFragment fragment = new TrajectoryTriggerFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTrajectoryTriggerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public WaypointTrigger getTrigger() {
        int start = Tools.getInt(binding.etStartIndex.getText().toString(), 1);
        int end = Tools.getInt(binding.etEndIndex.getText().toString(), 1);

        WaypointTrajectoryTriggerParam param = new WaypointTrajectoryTriggerParam.Builder()
                .setEndIndex(end)
                .setStartIndex(start)
                .build();
        return new WaypointTrigger.Builder()
                .setTriggerType(ActionTypes.ActionTriggerType.TRAJECTORY)
                .setTrajectoryParam(param)
                .build();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
