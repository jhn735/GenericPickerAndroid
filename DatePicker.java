
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import java.text.DateFormatSymbols;
import java.util.Calendar;

/** Allows a user to pick a date. If the day is on the maximum day of the month and either the month
 * or year changes, the displayed day is updated to ensure an invalid date does not occur.
 */
public class DatePicker extends PickerGroup {
    private static final int picker_slots = 3;

    private static final int month_index = 0;
    private static final int day_index = 1;
    private static final int year_index = 2;

    public static final int minimum_year = 2000;
    public static final int maximum_year = 2100;

    private static final Calendar calendar = Calendar.getInstance();

    public DatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int picker_slots_number() {
        return picker_slots;
    }

    @Override
    protected void setup_pickers() {
        DateFormatSymbols dfs = DateFormatSymbols.getInstance();
        picker_list.set(month_index, new DateSinglePicker(this.getContext()));
            picker_list.get(month_index).set_elements(dfs.getShortMonths());
        picker_list.set(year_index, new DateNumberPicker(this.getContext()));
            ((DateNumberPicker)picker_list.get(year_index)).set_elements(minimum_year, maximum_year);
        picker_list.set(day_index, new DateNumberPicker(this.getContext()));
        refresh_days();
    }


    /** Sets the displayed date to that specified in the arguments.
     *
     * @param month The number of the month in the year starting with 1.
     * @param day The day of the month.
     * @param year The year of the date between minimum_year and maximum_year. If the year doesn't land between those
     *             the year is set to the minimum_year
     */
    public void set_date(int month, int day, int year){
        //check for valid month value
        if(month < 1 || month > 12) month = 1;
        picker_list.get(month_index).set_displayed_element(month-1);

        //check for valid year value
        if(year < minimum_year || year > maximum_year) year = minimum_year;
        picker_list.get(year_index).set_displayed_element(year-1);

        //check if the day is actually in the month
            calendar.set(year, month, 1);
            int max_day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if(day < 0 || day <= max_day) day = 1;
        picker_list.get(day_index).set_displayed_element(day-1);
    }

    /** Refreshes the SinglePicker for the day number to hold the days that are contained specifically
     * in the month and year displayed.
     */
    private void refresh_days(){
        //get the currently displayed month and year
        int year = Integer.parseInt(picker_list.get(year_index).toString());
        int month = picker_list.get(month_index).get_element_index();

        //set the calendar and get the maximum number of days for that month and year
        calendar.set(year, month, 1);
        int max_day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        //if the number of days have changed then change it to the max of current month and year
        NumberPicker day_picker = (NumberPicker)picker_list.get(day_index);
        if(day_picker.get_maximum_number() != max_day)
            day_picker.set_elements(1, max_day);

    }


    /* The following two classes makes sure that the days of the month are updated when the either
        the month or the year are changed.
     */
    private class DateNumberPicker extends NumberPicker{

        public DateNumberPicker(Context context) {
            super(context);
            up_arrow.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    view_next_element();
                    refresh_days();
                }
            });
            down_arrow.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    view_prev_element();
                    refresh_days();
                }
            });
        }
    }

    private class DateSinglePicker extends SinglePicker{

        public DateSinglePicker(Context context) {
            super(context);
            up_arrow.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    view_next_element();
                    refresh_days();
                }
            });
            down_arrow.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    view_prev_element();
                    refresh_days();
                }
            });
        }
    }

}
