package br.ulbra.icook;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        link = (TextView) findViewById(R.id.txtClique);

        SpannableString ss = new SpannableString("Registre-se aqui");

        ss.setSpan(new CustomClickableSpan(),12 , 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        link.setText(ss);
        link.setMovementMethod(LinkMovementMethod.getInstance());
    }

    class CustomClickableSpan extends ClickableSpan {
        @Override
        public void onClick(View textView) {
            setContentView(R.layout.activity_fom_cadastro);

        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(Color.BLUE);
            ds.setUnderlineText(false);
        }
    }
}