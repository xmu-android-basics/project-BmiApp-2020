package io.github.xmu_android_basics.bmiapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText weightWidget;
    EditText heightWidget;
    TextView resultWidget;

    String bmiResult = "";

    List<Double> bmiHistory = new ArrayList<>();
    double bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightWidget = (EditText) findViewById(R.id.input_weight);
        heightWidget = (EditText) findViewById(R.id.input_height);
        resultWidget = (TextView) findViewById(R.id.output_result);
    }

    public void onComputeClick(View view) {
        String weightInput = weightWidget.getText().toString();
        String heightInput = heightWidget.getText().toString();

        if (weightInput.isEmpty()) {
            return;
        }

        if (heightInput.isEmpty()) {
            return;
        }

        Double height = Double.valueOf(heightInput);

        if (height == 0) {
            Toast.makeText(this, "Height cannot be 0", Toast.LENGTH_SHORT).show();
            return;
        }

        bmi = computeBmi(Double.valueOf(weightInput), height);

        bmiResult = generateResult(Double.valueOf(weightInput), bmi);

        resultWidget.setText(bmiResult);
    }

    public void onSaveClick(View view) {
//        if (!bmiResult.isEmpty()) {
            bmiHistory.add(bmi);
//        }
    }

    private double computeBmi(Double weight, Double height) {
        return weight / (height * height);
    }

    private String whatDateIsToday() {
        return SimpleDateFormat.getDateTimeInstance().format(new Date());
    }

    private String generateResult(Double weight, Double bmi) {
        StringBuffer sb = new StringBuffer();

        for (Double bmiValue : bmiHistory) {
            sb.append(bmiValue);
            sb.append("\n");
        }

        return sb.toString();

//        return whatDateIsToday() + "，体重是：" + weight + "，BMI 指数是：" + bmi;
    }
}
