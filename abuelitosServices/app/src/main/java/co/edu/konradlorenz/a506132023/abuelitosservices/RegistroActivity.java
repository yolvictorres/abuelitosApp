package co.edu.konradlorenz.a506132023.abuelitosservices;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        new getByEscalafon(getApplicationContext()).execute(2);


        Button boton = (Button)findViewById(R.id.button);

        /*boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText nombreCampo = (EditText)findViewById(R.id.nombreRegistro);
                EditText cedulaCampo = (EditText)findViewById(R.id.cedulaRegistro);
                EditText contrasenaCampo = (EditText)findViewById(R.id.contrasenaRegistro);

                User unUsuario = new User();

                unUsuario.setName(String.valueOf(nombreCampo.getText()));
                unUsuario.setCedula(String.valueOf(cedulaCampo.getText()));
                unUsuario.setContrasena(String.valueOf(contrasenaCampo.getText()));
                new createUser(getApplicationContext()).execute((Runnable) unUsuario);
            }
        });*/
    }
}
