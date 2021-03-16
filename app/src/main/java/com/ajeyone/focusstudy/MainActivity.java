package com.ajeyone.focusstudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private boolean mCustomMoveEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button3).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // 修改自身的属性
                v.setScaleX(hasFocus ? 1.15f : 1.0f);
                v.setScaleY(hasFocus ? 1.15f : 1.0f);
                // 联动其他 View 的变化
                TextView textView = MainActivity.this.findViewById(R.id.label_for_button3);
                textView.setText(hasFocus ? R.string.result_focused : R.string.result_unfocused);
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomMoveEnabled = !mCustomMoveEnabled;
                updateCustomFocusMoveEnabled();
            }
        });
        updateCustomFocusMoveEnabled();

        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }

    private void updateCustomFocusMoveEnabled() {
        Button button = findViewById(R.id.button4);
        TextView label = findViewById(R.id.label_for_button4);
        if (mCustomMoveEnabled) {
            label.setText(R.string.enabled);
            // 自定义获焦时按右键移动到哪个 View
            button.setNextFocusRightId(R.id.image1);
            // 自定义获焦时按下键移动到哪个 View
            button.setNextFocusDownId(R.id.button1);
        } else {
            label.setText(R.string.disabled);
            // 将 id 设置为 View.NO_ID 可以取消自定义
            button.setNextFocusRightId(View.NO_ID);
            button.setNextFocusDownId(View.NO_ID);
        }
    }
}