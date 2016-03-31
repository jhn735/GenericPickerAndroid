
import android.content.Context;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 3/28/16.
 */
public class SinglePicker extends ScrollView {
    public List<String> elements = new ArrayList<>();


    public Selector(Context context) {
        super(context);
    }
}
