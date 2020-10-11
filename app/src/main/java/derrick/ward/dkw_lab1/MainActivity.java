package derrick.ward.dkw_lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    * Event Handler for when Add New Button is clicked in landscape mode
    * - Will add SU Logo to the existing linear layout
    */
    public void AddNewInLandscape(View view) {
        LinearLayout linearLayout = findViewById(R.id.rootContainer);

        // Create Image View
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.su_logo);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(600, 600, 1f);
        imageView.setLayoutParams(layoutParams);

        // Add Image View to Linear Layout
        linearLayout.addView(imageView);
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

        switch (currentEditText.getText().toString().trim().toUpperCase()) {
            case "H":
                this.AddNewHorizontalView();
                break;
            default:
                this.AddNewVerticalView(view);
                break;
        }
    }

    /*
     * Adds new Vertical Layout Control
     * - Creates a new linear layout (horizontal)
     * - Adds a Edit Text View to the layout
     * - Adds a Button to the layout
     */
    public void AddNewVerticalView(View view) {
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

    /*
    * Adds new Horizontal Layout Control
    */
    public void AddNewHorizontalView() {
        // Create Horizontal View
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this);
        LinearLayout.LayoutParams scrollViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        horizontalScrollView.setLayoutParams(scrollViewParams);

        // Create Linear Layout
        final LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(0,5,0,0);
        horizontalScrollView.addView(linearLayout);

        // Create Button
        this.AddNewButton(linearLayout);

        // Add Horizontal View to main layout
        LinearLayout mainLayout = findViewById(R.id.rootContainer);
        mainLayout.addView(horizontalScrollView);
    }

    /*
    * Adds Button to Linear Layout
    */
    public void AddNewButton(final LinearLayout linearLayout) {
        Button button = new Button(this);
        Integer numOfChildren = linearLayout.getChildCount() + 1;
        button.setText(numOfChildren.toString());
        linearLayout.addView(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewButton(linearLayout);
            }
        });
    }
}