package com.g4s8.rxkeyboardexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.g4s8.rxkeyboard.RxAndroidSoftKeyboard;
import com.g4s8.rxkeyboard.RxSoftKeyboard;
import io.reactivex.functions.Action;

public class MainActivity extends AppCompatActivity {

    private TextView statusView;
    private EditText targetView;
    private Button showButton;
    private Button hideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.statusView = TextView.class.cast(findViewById(R.id.status));
        this.targetView = EditText.class.cast(findViewById(R.id.target));
        this.showButton = Button.class.cast(findViewById(R.id.btn_show));
        this.hideButton = Button.class.cast(findViewById(R.id.btn_hide));

        this.showButton.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    MainActivity.this.statusView.setText("show: started");
                    MainActivity.this.showButton.setEnabled(false);
                    MainActivity.this.hideButton.setEnabled(false);
                    new RxAndroidSoftKeyboard(MainActivity.this).show(
                        MainActivity.this.targetView,
                        RxSoftKeyboard.ShowFlags.IMPLICIT
                    ).subscribe(
                        new Action() {
                            @Override
                            public void run() throws Exception {
                                MainActivity.this.showButton.setEnabled(true);
                                MainActivity.this.hideButton.setEnabled(true);
                                MainActivity.this.statusView.setText("show: complete");
                            }
                        }
                    );
                }
            }
        );

        this.hideButton.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    MainActivity.this.statusView.setText("hide: started");
                    MainActivity.this.showButton.setEnabled(false);
                    MainActivity.this.hideButton.setEnabled(false);
                    new RxAndroidSoftKeyboard(MainActivity.this).hide(
                        MainActivity.this.targetView.getWindowToken(),
                        RxSoftKeyboard.HideFlags.NONE
                    ).subscribe(
                        new Action() {
                            @Override
                            public void run() throws Exception {
                                MainActivity.this.showButton.setEnabled(true);
                                MainActivity.this.hideButton.setEnabled(true);
                                MainActivity.this.statusView.setText("hide: complete");
                            }
                        }
                    );
                }
            }
        );
    }
}
