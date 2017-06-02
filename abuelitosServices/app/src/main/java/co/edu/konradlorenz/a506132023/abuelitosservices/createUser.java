package co.edu.konradlorenz.a506132023.abuelitosservices;

/**
 * Created by jiacontrerasp on 9/2/15.
 */


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import co.edu.konradlorenz.a506132023.abuelitosservices.backend.userApi.UserApi;
import co.edu.konradlorenz.a506132023.abuelitosservices.backend.userApi.model.User;


public class createUser extends AsyncTask<User, String, Boolean> {

    private static final String TAG ="getByEscalafon";
    Context context;
    private static UserApi myUserEndpointService = null;
    private ProgressDialog pd;


    public createUser(Context context) {
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(User... usuarios) {

        Boolean ok = false;

        if (myUserEndpointService == null) { // Only do this once
            UserApi.Builder builder = new UserApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null);
            myUserEndpointService = builder.build();

        }

        try {

            for (User usuario: usuarios){
                myUserEndpointService.insert(usuario).execute();
            }
            ok=true;
        } catch (IOException e) {
            e.printStackTrace();
        }


             return ok;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setMessage("Leyendo información");
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected void onPostExecute(Boolean ok) {
        super.onPostExecute(ok);
        pd.dismiss();
        Toast.makeText(context,"El usuario registrado",Toast.LENGTH_LONG).show();
        Log.i(TAG,"Todo esta ok: "+ ok);
    }

}