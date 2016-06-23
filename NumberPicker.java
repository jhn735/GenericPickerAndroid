
import android.content.Context;

/**
 * Created by john on 6/21/16.
 */
public class NumberPicker extends SinglePicker {
    //the minimum number displayed
    private int minimum_number = 0;
    public void set_minimum_number(int new_minimum_number){
        this.minimum_number = new_minimum_number;
        if(displayed_number < minimum_number) set_displayed_element(minimum_number);
    }
    public int get_minimum_number(){ return minimum_number;}

    //the maximum number displayed
    private int maximum_number = 0;
    public void set_maximum_number(int new_maximum_number){
        this.maximum_number = new_maximum_number;
        if(displayed_number > maximum_number) set_displayed_element(maximum_number);
    }
    public int get_maximum_number(){ return maximum_number;}

    //the number to be displayed
    private int displayed_number = minimum_number;
    public void set_displayed_number(int number){
        //check if the number is within the range. Change it to the nearest extreme of the range if outside.
        if(number < minimum_number) number = minimum_number;
        if(number > maximum_number) number = maximum_number;
        //set the displayed number
        this.displayed_number = number;
        //set the current_element_index
        this.current_element_index = displayed_number - minimum_number;
    //set the displayed number
    this.element_display.setText(Integer.toString(this.displayed_number));
    }
    public int get_displayed_number(){ return displayed_number;}


    public NumberPicker(Context context) {
        super(context);
        this.current_element_index = minimum_number;
    }

    public NumberPicker(Context context, int minimum_number, int maximum_number){
        super(context);
        set_elements(minimum_number, maximum_number);

    }

    @Override
    public void view_next_element(){
        if(displayed_number < maximum_number) displayed_number++;
        else displayed_number = minimum_number;

    this.set_displayed_number(displayed_number);
    }

    @Override
    public void view_prev_element(){
        if(displayed_number > minimum_number) displayed_number--;
        else displayed_number = maximum_number;

    this.set_displayed_number(displayed_number);
    }

    @Override
    public void set_displayed_element(int element_index) {
        set_displayed_number(minimum_number + element_index);
    }

    public void set_elements(int minimum_number, int maximum_number){
        this.minimum_number = minimum_number;
        this.maximum_number = maximum_number;
        this.set_displayed_element(current_element_index);
    }

    @Override
    public int size(){
        return maximum_number - minimum_number;
    }
}
