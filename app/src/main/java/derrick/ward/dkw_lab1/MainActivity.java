package derrick.ward.dkw_lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    * Event Handler for AddNew Button
    * - Creates a new linear layout (horizontal)
    * - Adds a Edit Text View to the layout
    * - Adds a Button to the layout
    */
    public void AddNew(View view) {
        //Get Current Sibling EditText View
        EditText currentEditText = this.getEditViewFromViewParent(view.getParent());

        // Create Linear Layout
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(0,5,0,0);
        linearLayout.setWeightSum(3);

        // Create New Edit Text View
        EditText editText = new EditText(this);
        LinearLayout.LayoutParams layoutParamsForEditText = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 2f);
        editText.setLayoutParams(layoutParamsForEditText);

        // Create New Button View
        Button button = new Button(this);
        LinearLayout.LayoutParams layoutParamsForButton = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        button.setLayoutParams(layoutParamsForButton);
        if (currentEditText != null) {
            button.setText(currentEditText.getText());
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNew(view);
            }
        });

        // Add Views to Linear Layout
        linearLayout.addView(editText);
        linearLayout.addView(button);

        // Add Layout to main layout
        LinearLayout mainLayout = findViewById(R.id.rootContainer);
        mainLayout.addView(linearLayout);
    }

    /*
    * Get EditText view from View Parent
    */
    public EditText getEditViewFromViewParent(ViewParent viewParent) {
        EditText editText = null;

        if (viewParent != null) {
            ViewGroup viewGroup = (ViewGroup) viewParent;

            // Iterate through all the child view until the first edit text is found
            for (int childPosition = 0; childPosition < viewGroup.getChildCount(); childPosition++) {
                View view = viewGroup.getChildAt(childPosition);

                if (view instanceof EditText) {
                    editText = (EditText) view;
                    break;
                }
            }
        }

        return editText;
    }
}