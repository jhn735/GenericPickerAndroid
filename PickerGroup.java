
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * PickerGroup contains multiple instances of SinglePicker.
 */
public class PickerGroup extends LinearLayout{

    protected int element_weight = 1;

    public PickerGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void setup_layout(){
        this.setOrientation(HORIZONTAL);
    }

    @Override
    public String toString(){
        StringBuilder b = new StringBuilder();
        for(int cur_child = 0; cur_child < this.getChildCount(); cur_child++){
            //ppend();
        }
    return "group";
    }
}
