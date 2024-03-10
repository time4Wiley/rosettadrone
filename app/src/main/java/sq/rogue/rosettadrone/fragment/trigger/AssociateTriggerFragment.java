package sq.rogue.rosettadrone.fragment.trigger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import dji.common.mission.waypointv2.Action.ActionTypes;
import dji.common.mission.waypointv2.Action.WaypointTrigger;
import dji.common.mission.waypointv2.Action.WaypointV2AssociateTriggerParam;
import sq.rogue.rosettadrone.settings.Tools;
import wiley.sq.rogue.rosettadrone.databinding.FragmentAssociateTriggerBinding;

public class AssociateTriggerFragment extends BaseTriggerFragment implements ITriggerCallback {

    private FragmentAssociateTriggerBinding binding;

    public AssociateTriggerFragment() {
    }

    public static AssociateTriggerFragment newInstance() {
        AssociateTriggerFragment fragment = new AssociateTriggerFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAssociateTriggerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public WaypointTrigger getTrigger() {
        float waitTime = Tools.getFloat(binding.etWaitTime.getText().toString(), 1);
        ActionTypes.AssociatedTimingType type = binding.rbSync.isChecked()
                ? ActionTypes.AssociatedTimingType.SIMULTANEOUSLY : ActionTypes.AssociatedTimingType.AFTER_FINISHED;
        int actionId = Tools.getInt(binding.etActionId.getText().toString(), 1);

        if (actionId > size) {
            Tools.showToast(getActivity(), "actionId can`t bigger existed action size, size=" + size);
            return null;
        }

        WaypointV2AssociateTriggerParam param = new WaypointV2AssociateTriggerParam.Builder()
                .setAssociateType(type)
                .setWaitingTime(waitTime)
                .setAssociateActionID(actionId)
                .build();
        return new WaypointTrigger.Builder()
                .setTriggerType(ActionTypes.ActionTriggerType.ASSOCIATE)
                .setAssociateParam(param)
                .build();
    }
}
