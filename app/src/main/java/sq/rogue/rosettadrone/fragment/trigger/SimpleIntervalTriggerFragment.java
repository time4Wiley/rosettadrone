package sq.rogue.rosettadrone.fragment.trigger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import dji.common.mission.waypointv2.Action.ActionTypes;
import dji.common.mission.waypointv2.Action.WaypointIntervalTriggerParam;
import dji.common.mission.waypointv2.Action.WaypointTrigger;
import sq.rogue.rosettadrone.settings.Tools;
import wiley.sq.rogue.rosettadrone.databinding.FragmentSimpleIntervalTriggerBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SimpleIntervalTriggerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SimpleIntervalTriggerFragment extends BaseTriggerFragment implements ITriggerCallback {

    private FragmentSimpleIntervalTriggerBinding binding;

    public static SimpleIntervalTriggerFragment newInstance() {
        SimpleIntervalTriggerFragment fragment = new SimpleIntervalTriggerFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSimpleIntervalTriggerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public WaypointTrigger getTrigger() {
        float value = Tools.getFloat(binding.etValue.getText().toString(), 1.1f);
        int start = Tools.getInt(binding.etStartIndex.getText().toString(), 1);

        if (start > size) {
            Tools.showToast(getActivity(), "start can`t bigger waypoint mission size, size=" + size);
            return null;
        }

        ActionTypes.ActionIntervalType type = binding.rbDistance.isChecked()
                ? ActionTypes.ActionIntervalType.DISTANCE : ActionTypes.ActionIntervalType.TIME;
        WaypointIntervalTriggerParam param = new WaypointIntervalTriggerParam.Builder()
                .setStartIndex(start)
                .setInterval(value)
                .setType(type)
                .build();
        return new WaypointTrigger.Builder()
                .setTriggerType(ActionTypes.ActionTriggerType.SIMPLE_INTERVAL)
                .setIntervalTriggerParam(param)
                .build();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
