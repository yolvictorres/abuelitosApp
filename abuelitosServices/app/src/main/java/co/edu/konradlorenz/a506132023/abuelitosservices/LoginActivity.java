package co.edu.konradlorenz.a506132023.abuelitosservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn1 = (Button) findViewById(R.id.btnRegistro);
        btn2 = (Button) findViewById(R.id.btnIngresar);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }

        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                Intent intent2 = new Intent(LoginActivity.this,EventosActivity.class);
=======
                Intent intent2 = new Intent(LoginActivity.this,MenuActivity.class);
>>>>>>> 14887f5504d989a4b1b4d606772f4c877f51baf0
                startActivity(intent2);
            }

        });
    }
}
