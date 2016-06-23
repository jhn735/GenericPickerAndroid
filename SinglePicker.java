
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    public int get_element_index(){return current_element_index;}


    protected Button up_arrow;
    protected Button down_arrow;
    protected int arrow_visibility;

    protected TextView element_display;

    protected final int total_weight = 9;
    protected final int arrow_button_weight = 4;
    protected final int element_display_weight = 1;


    public SinglePicker(Context context){
        super(context);
        setup_layout();
    }

    public SinglePicker(Context context, AttributeSet attrs){
        super(context, attrs);
        setup_layout();
    }

    protected void setup_layout(){
        this.elements = new ArrayList<>();

        up_arrow = new Button(this.getContext());
        down_arrow = new Button(this.getContext());
        element_display = new TextView(this.getContext());

        //set the weightsum
        this.setWeightSum(this.total_weight);
        this.setOrientation(VERTICAL);

        this.setGravity(Gravity.CENTER_VERTICAL);
        ViewGroup.LayoutParams arrow_button_params =
            new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        //set the up arrow button
        up_arrow = new Button(this.getContext());
            up_arrow.setText("▲");
            up_arrow.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    view_next_element();
                }
            });
            up_arrow.setLayoutParams(arrow_button_params);
        this.addView(up_arrow);

        //set up the down arrow
        down_arrow = new Button(this.getContext());
            down_arrow.setText("▼");
            down_arrow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                view_prev_element();
            }
        });
            down_arrow.setLayoutParams(arrow_button_params);
        this.addView(down_arrow);

        //set up the element display
        element_display = new TextView(this.getContext());
            //this.set_displayed_element(this.current_element_index);
            element_display.setTextAlignment(TEXT_ALIGNMENT_CENTER);
            element_display.setTextSize(35f);
        element_display.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                element_display.setTextAlignment(TEXT_ALIGNMENT_CENTER);

        this.addView(element_display, 1);
    }

    public void set_arrow_visibility(int visibility){
        this.arrow_visibility = visibility;
        up_arrow.setVisibility(this.arrow_visibility);
        down_arrow.setVisibility(this.arrow_visibility);
    }

    /** Sets the displayed element the next element in the list.*/
    public void view_next_element(){
        int max_index = this.size() - 1;
        if(current_element_index < max_index) current_element_index++;
        else current_element_index = 0;
    set_displayed_element(current_element_index);
    }

    /** Sets the displayed element to the previous element in the list.*/
    public void view_prev_element(){
        if(current_element_index > 0) current_element_index--;
        else current_element_index = this.size()-1;

    set_displayed_element(current_element_index);
    }

    /** Sets the display element to the element with given index.
     *  @param element_index The index of the element to be displayed.
     */
    public void set_displayed_element(int element_index){
        this.element_display.setText(elements.get(element_index));
    }

    /** Sets the elements display by the picker. The order of elements is lowest-first.
     * @param new_elements the set of elements that will be displayed by the picker.
     */
    public void set_elements(String...new_elements){
        this.elements = new ArrayList<String>(Arrays.asList(new_elements));
        this.set_displayed_element(0);
    }

    public int size(){
        return elements.size();
    }

    /** Returns the string that is currently being displayed.
     * @return String that is currently being displayed.
     */
    @Override
    public String toString(){
        return (String)this.element_display.getText();
    }
}
