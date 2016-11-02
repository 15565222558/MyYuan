package test.bawei.administrator.myyuan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void chukong(View v) {
        Intent in = new Intent(MainActivity.this, MyChuKong.class);
        startActivity(in);
    }
    public void Dchukong(View v) {
        Intent in = new Intent(MainActivity.this, MyDuoDianCK.class);
        startActivity(in);
    }
}
