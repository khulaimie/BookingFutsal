package com.example.khulaimi.bookingfutsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewSchedulesActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY =
            "com.example.android.roomwordssample.REPLY";
    public static final String EXTRA_REPLY2 =
            "com.example.android.roomwordssample.REPLY2";

    private EditText mEditWordView;
    private EditText mEditJamView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_schedule);

        mEditWordView = findViewById(R.id.edit_word);
        mEditJamView = findViewById(R.id.edit_jam);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())) {
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.empty_not_saved,
                            Toast.LENGTH_LONG).show();
                    return;
                } else {
                    String word = mEditWordView.getText().toString();
                    String time = mEditJamView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    replyIntent.putExtra(EXTRA_REPLY2, time);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
