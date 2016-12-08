package com.example.perhe.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.perhe.myapplication.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final Button button = (Button) findViewById(R.id.btnGenerate);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                // Perform action on click
                final String allChars = getString(R.string.acceptable_chars);

                int pswdLen = 12;

                ViewGroup layout = (ViewGroup) findViewById(R.id.layoutPasswords);

                // Clear previous passwords
                layout.removeAllViews();

                new GeneratePasswords(allChars, pswdLen, layout).invoke();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            String message = "Foo";
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class GeneratePasswords {
        private String allChars;
        private int pswdLen;
        private ViewGroup layout;

        public GeneratePasswords(String allChars, int pswdLen, ViewGroup layout) {
            this.allChars = allChars;
            this.pswdLen = pswdLen;
            this.layout = layout;
        }

        public void invoke() {
            try {
                SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");

                for(int j=0; j<20; j++) {

                    StringBuffer buf = new StringBuffer();

                    for(int i=0; i<pswdLen; i++) {
                        Integer val = sr.nextInt(allChars.length());
                        buf.append(allChars.charAt(val));
                    }
                    String str = buf.toString();
                    TextView tv = new TextView(findViewById(R.id.layoutPasswords).getContext());
                    //tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                    tv.setText(str);
                    layout.addView(tv);
                }

            } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                // Use normal random or something
            }
        }
    }
}
