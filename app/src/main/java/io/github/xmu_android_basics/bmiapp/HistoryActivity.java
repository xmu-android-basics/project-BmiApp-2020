package io.github.xmu_android_basics.bmiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private static final String TAG = HistoryActivity.class.getSimpleName();

    TextView historyView;

    double[] bmiHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyView = findViewById(R.id.history);

        Log.d(TAG, getIntent().toString());

        bmiHistory = getIntent().getDoubleArrayExtra("bmiHistory");

        if (bmiHistory != null) {
            renderHistory();
        } else {
            Toast.makeText(this, "还没有历史记录", Toast.LENGTH_SHORT).show();
        }
    }

    private void renderHistory() {
        StringBuffer sb = new StringBuffer();

        for (Double bmiValue : bmiHistory) {
            sb.append(bmiValue);
            sb.append("\n");
        }

        historyView.setText(sb);
    }
}
