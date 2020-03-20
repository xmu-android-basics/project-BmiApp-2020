package io.github.xmu_android_basics.bmiapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    EditText weightWidget;
    EditText heightWidget;
    TextView resultWidget;

    ArrayList<Double> bmiHistory = new ArrayList<>();

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

        double bmi = computeBmi(Double.valueOf(weightInput), height);

        saveBmi(bmi);

        String bmiResult = generateResult();

        resultWidget.setText(bmiResult);
    }

    public void onHistoryClick(View view) {
        if (!bmiHistory.isEmpty()) {
            Intent intent = new Intent(this, HistoryActivity.class);
            intent.putExtra("bmiHistory", convertToArray(bmiHistory));

            startActivity(intent);
        }
    }

    private double computeBmi(Double weight, Double height) {
        if (height == 0) {
            return 0;
        }

        double bmi = weight / (height * height);
        bmi = Math.round(bmi * 10) / 10;

        return bmi;
    }

    private void saveBmi(double bmi) {
        bmiHistory.add(bmi);
    }

    private String generateResult() {
        StringBuffer sb = new StringBuffer();

        for (Double bmiValue : bmiHistory) {
            sb.append(bmiValue);
            sb.append("\n");
        }

        return sb.toString();
    }

    private double[] convertToArray(List<Double> doubleList) {
        int arraySize = doubleList.size();
        double[] array = new double[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = doubleList.get(i);
        }

        return array;
    }
}
