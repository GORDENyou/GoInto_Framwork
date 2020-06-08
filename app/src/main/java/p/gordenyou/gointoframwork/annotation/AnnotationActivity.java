package p.gordenyou.gointoframwork.annotation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import p.gordenyou.gointoframwork.R;

public class AnnotationActivity extends AppCompatActivity {

    @InjectIntent(Value = "testString")
    private String strTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        InjectIntentUtils.injectIntent(this);
    }
}