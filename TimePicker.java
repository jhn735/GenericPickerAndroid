
import android.content.Context;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * The time is either 24 hour or 12 with am or pm. Seven picker slots are needed as shown: 12:34 AM
 */
public class TimePicker extends PickerGroup {
    public enum mode{ Military, AM_PM}

    private static final int picker_slots = 5;

    @Override
    protected int picker_slots_number(){
        return picker_slots;
    }

    private mode current_mode;
    private static final mode default_mode = mode.Military;

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

    /**Returns a string array that contains string form of numbers from low to high.
     *
     * @param low The lowest number in the sequence
     * @param high The highest number in the sequence
     * @return String array that holds the sequence low, low+1...high-1, high
     */
    private String[] get_string_sequence(int low, int high){
        return get_string_sequence(low, high, "");
    }

    private String[] get_string_sequence(int low, int high, String other){
        String[] ret = new String[high-low+1];
        for(int i = low; i <= high; i++)
            ret[i - low] = Integer.toString(i) + other;
        return ret;
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

    private void setup_layout_military(){
        this.setWeightSum(4);
        //the layout should be H(0-23):m(0-5)m(0-9)**
        this.picker_list.get(0).set_elements(get_string_sequence(0,23));
        this.picker_list.get(1).set_elements(":");
        this.picker_list.get(2).set_elements(get_string_sequence(0,5));
        this.picker_list.get(3).set_elements(get_string_sequence(0, 9));
        this.picker_list.get(4).setVisibility(GONE);
    }

    private void setup_layout_am_pm(){
        this.setWeightSum(6);
        //the layout should be H(0-12):m(0-5)m(0-9) (AM|PM)
        this.picker_list.get(0).set_elements(get_string_sequence(1, 12));
        this.picker_list.get(1).set_elements(":");
        this.picker_list.get(2).set_elements(get_string_sequence(0, 5));
        this.picker_list.get(3).set_elements(get_string_sequence(0, 9));
        this.picker_list.get(4).set_elements("AM", "PM");

    }



}
