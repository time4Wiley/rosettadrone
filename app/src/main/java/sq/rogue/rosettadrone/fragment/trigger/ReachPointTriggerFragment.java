package sq.rogue.rosettadrone.fragment.trigger;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import dji.common.mission.waypointv2.Action.ActionTypes;
import dji.common.mission.waypointv2.Action.WaypointReachPointTriggerParam;
import dji.common.mission.waypointv2.Action.WaypointTrigger;
import sq.rogue.rosettadrone.settings.Tools;
import wiley.sq.rogue.rosettadrone.databinding.FragmentSimpleReachPointTriggerBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReachPointTriggerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReachPointTriggerFragment extends BaseTriggerFragment implements ITriggerCallback {
    private FragmentSimpleReachPointTriggerBinding binding;

    public static ReachPointTriggerFragment newInstance() {
        ReachPointTriggerFragment fragment = new ReachPointTriggerFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSimpleReachPointTriggerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public WaypointTrigger getTrigger() {
        int start = Tools.getInt(binding.etStartIndex.getText().toString(), 1);
        int count = Tools.getInt(binding.etAutoTerminateCount.getText().toString(), 1);

        if (start > size) {
            Tools.showToast(getActivity(), "start can`t bigger waypoint mission size, size=" + size);
            return null;
        }

        WaypointReachPointTriggerParam param = new WaypointReachPointTriggerParam.Builder()
                .setAutoTerminateCount(count)
                .setStartIndex(start)
                .build();
        return new WaypointTrigger.Builder()
                .setTriggerType(ActionTypes.ActionTriggerType.REACH_POINT)
                .setReachPointParam(param)
                .build();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
