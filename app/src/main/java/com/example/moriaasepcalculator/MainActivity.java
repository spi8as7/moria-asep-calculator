package com.example.moriaasepcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    public void openResultActivity(int result) {
        Intent intent = new Intent(this,ResultActivity.class);
        intent.putExtra("result",String.valueOf(result));
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        String[] descriptions = getResources().getStringArray(R.array.descpritions);
        findViewById(R.id.Result_Layout).setVisibility(View.GONE);

        int screen_width = metrics.widthPixels - (int)(0.1*metrics.widthPixels);
        int screen_height = metrics.heightPixels - (int)(0.2*metrics.heightPixels);

        LinearLayout description_layout = (LinearLayout) findViewById(R.id.descriptions_layout);
        LinearLayout answers_layout = (LinearLayout) findViewById(R.id.answers_layout);

        ViewGroup.LayoutParams description_layout_params = description_layout.getLayoutParams();
        description_layout_params.width = (int)(0.6 * screen_width);
        description_layout_params.height = screen_height;
        description_layout.setLayoutParams(description_layout_params);

        ViewGroup.LayoutParams layout_params = description_layout.getChildAt(0).getLayoutParams();
        layout_params.height = description_layout_params.height / description_layout.getChildCount();
        layout_params.width = description_layout_params.width;

        for (int i=0 ; i < description_layout.getChildCount(); i++) {
            description_layout.getChildAt(i).setLayoutParams(layout_params);
            TextView label = (TextView)description_layout.getChildAt(i);
            label.setText(descriptions[i]);
        }

        ViewGroup.LayoutParams answers_layout_params = answers_layout.getLayoutParams();
        answers_layout_params.width = (int)(0.3 * screen_width);
        answers_layout_params.height = screen_height;
        answers_layout.setLayoutParams(answers_layout_params);

        for( int i=0 ; i < answers_layout.getChildCount(); i++) {
            answers_layout.getChildAt(i).setLayoutParams(layout_params);
            if ( (i > 6) && (i < 11)) {
                EditText answer = (EditText)answers_layout.getChildAt(i);
                answer.setTransformationMethod(null);
            }
        }

        final Button button = (Button) findViewById(R.id.moria_calculation_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(R.id.Calculator_Layout).setVisibility(View.GONE);
                openResultActivity(20);
            }
        });
    }

}