package co.edu.konradlorenz.a506132023.abuelitosservices;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION_CODES.M;

public class AgregarEventoActivity extends AppCompatActivity {
    private static String APP_DIRECTORY = "MyPictureApp/";
    private static String MEDIA_DIRECTORY = APP_DIRECTORY + "PictureApp";
    private Context context;
    private final int MY_PERMISSIONS = 100;
    private final int PHOTO_CODE = 200;
    private final int SELECT_PICTURE = 300;

    private ImageView mSetImage;
    private ImageButton mOptionButton;
    private RelativeLayout mRlView;

    private String mPath;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_evento);
        Button boton = (Button)findViewById(R.id.buttonAgregarEvento);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgregarEventoActivity.this, MenuActivity.class);
                startActivity(intent);

            }
        });

        mSetImage = (ImageView) findViewById(R.id.set_picture);
        mOptionButton = (ImageButton) findViewById(R.id.FotoButton);
        mRlView = (RelativeLayout) findViewById(R.id.activity_agregar_evento);

        if(mayRequestStoragePermission())
            mOptionButton.setEnabled(true);
        else
            mOptionButton.setEnabled(true);


        mOptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptions();
            }
        });

    }
    private boolean mayRequestStoragePermission() {

        if(Build.VERSION.SDK_INT < M)
            return true;

        if((checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED))
            return true;

        if((shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE))){
            Snackbar.make(mRlView, "Los permisos son necesarios para poder usar la aplicación",
                    Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok, new View.OnClickListener() {
                @TargetApi(M)
                @Override
                public void onClick(View v) {
                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS);
                }
            });
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS);
        }

        return false;
    }

    private void showOptions() {
        final CharSequence[] option = {"Elegir de galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(AgregarEventoActivity.this);
        builder.setTitle("Eleige una opción");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(option[which] == "Elegir de galeria"){
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent, "Selecciona app de imagen"), SELECT_PICTURE);
                }else {
                    dialog.dismiss();
                }
            }
        });

        builder.show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("file_path", mPath);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mPath = savedInstanceState.getString("file_path");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            switch (requestCode){
                case SELECT_PICTURE:
                    Uri path = data.getData();
                    mSetImage.setImageURI(path);
                    break;

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_PERMISSIONS){
            if(grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(AgregarEventoActivity.this, "Permisos aceptados", Toast.LENGTH_SHORT).show();
                mOptionButton.setEnabled(true);
            }
        }else{
            showExplanation();
        }
    }

    private void showExplanation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AgregarEventoActivity.this);
        builder.setTitle("Permisos denegados");
        builder.setMessage("Para usar las funciones de la app necesitas aceptar los permisos");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });

        builder.show();
    }

}
