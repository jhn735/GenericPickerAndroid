
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * The time is either 24 hour or 12 with am or pm. Seven picker slots are needed as shown: 12:34 AM
 */
public class TimePicker extends PickerGroup {
    public enum mode{ Military, AM_PM}

    private static final int picker_slots = 5;

    private static final int hour_index = 0;
    private static final int colon_index= 1;
    private static final int minute_high_index = 2;
    private static final int minute_low_index = 3;
    private static final int am_pm_index = 4;

    @Override
    protected int picker_slots_number(){
        return picker_slots;
    }

    private mode current_mode;
    private static final mode default_mode = mode.AM_PM;

    public void set_mode(mode new_mode){
        this.current_mode = new_mode;
        switch (current_mode){
            case AM_PM: this.setup_layout_am_pm(); break;
            case Military: this.setup_layout_military(); break;
            default: this.setup_layout_am_pm();
        }
    }

    public TimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void setup_pickers(){

        this.current_mode = default_mode;

        //setup the layout of the time picker
        switch (current_mode){
            case AM_PM:this.setup_layout_am_pm(); break;
            case Military:this.setup_layout_military(); break;
            default: this.setup_layout_am_pm();
        }
    }

    /** Sets up the time picker to display U.S. Military time
     *
     */
    private void setup_layout_military(){
        this.setWeightSum(4);
        //the layout should be H(0-23):m(0-5)m(0-9)**
        picker_list.set(hour_index, new NumberPicker(this.getContext(), 0, 23));
        this.picker_list.get(colon_index).set_elements(":");
            this.picker_list.get(colon_index).set_arrow_visibility(View.GONE);
        picker_list.set(minute_high_index, new NumberPicker(this.getContext(), 0, 5));
        picker_list.set(minute_low_index, new NumberPicker(this.getContext(), 0, 9));

        this.picker_list.get(am_pm_index).setVisibility(GONE);
    }

    private void setup_layout_am_pm(){
        this.setWeightSum(6);
        //the layout should be H(0-12):m(0-5)m(0-9) (AM|PM)
        picker_list.set(hour_index, new NumberPicker(this.getContext(), 1, 12));
        this.picker_list.get(colon_index).set_elements(":");
            this.picker_list.get(colon_index).set_arrow_visibility(View.GONE);
        picker_list.set(minute_high_index, new NumberPicker(this.getContext(), 0, 5));
        picker_list.set(minute_low_index, new NumberPicker(this.getContext(), 0, 9));
        this.picker_list.get(am_pm_index).set_elements("AM", "PM");

    }



}
