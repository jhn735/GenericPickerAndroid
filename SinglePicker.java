
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Displays one element at a time. Arrow buttons on top and bottom change the element displayed.
 * The elements are set via either xml constructor or internally set.
 *
 */
public class SinglePicker extends LinearLayout {
    protected List<String> elements;
    protected int current_element_index = 0;

    protected Button up_arrow;
    protected Button down_arrow;

    protected TextView element_display;

    protected final int total_weight = 20;
    protected final int arrow_button_weight = 7;
    protected final int element_display_weight = 6;


    public SinglePicker(Context context, AttributeSet attrs, List<String> elements) {
        super(context, attrs);
        this.elements = elements;
        setup_layout();
    }

    public SinglePicker(Context context, AttributeSet attrs){
        super(context, attrs);
        this.elements = new ArrayList<>();
        setup_layout();
    }

    protected void setup_layout(){
        this.setWeightSum(this.total_weight);
        //set the up arrow button
        up_arrow = new Button(this.getContext());
            up_arrow.setText("▲");
            up_arrow.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    view_prev_element();
                }
            });
        this.addView(up_arrow);


        //set up the element display
        element_display = new TextView(this.getContext());
            this.set_displayed_element(this.current_element_index);
        this.addView(element_display);

        //set up the down arrow
        down_arrow = new Button(this.getContext());
            down_arrow.setText("▼");
            down_arrow.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    view_next_element();
                }
            });
        this.addView(down_arrow);

    }

    /** Sets the displayed element the next element in the list.*/
    public void view_next_element(){
        current_element_index++;
    set_displayed_element(current_element_index);
    }

    /** Sets the displayed element to the previous element in the list.*/
    public void view_prev_element(){
        current_element_index--;
    set_displayed_element(current_element_index);
    }

    /** Sets the display element to the element with given index.
     *  @param element_index The index of the element to be displayed.
     */
    public void set_displayed_element(int element_index){

    }

    /** Sets the elements display by the picker. The order of elements is lowest-first.
     * @param new_elements the set of elements that will be displayed by the picker.
     */
    public void set_elements(String...new_elements){
        this.elements = new ArrayList<String>(Arrays.asList(new_elements));
    }

}
