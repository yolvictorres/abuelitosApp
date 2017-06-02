package co.edu.konradlorenz.a506132023.abuelitosservices;

/**
 * Created by jiacontrerasp on 9/2/15.
 */


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.diego.myapplication.backend.userApi.UserApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.util.ArrayList;
import java.util.List;

import co.edu.konradlorenz.a506132023.abuelitosservices.backend.userApi.model.User;

public class getByEscalafon extends AsyncTask<Integer, String, List<User>> {

    private static final String TAG ="getByEscalafon";
    Context context;
    private static UserApi myUserEndpointService = null;
    private ProgressDialog pd;


    public getByEscalafon(Context context) {
        this.context = context;
    }

    @Override
    protected List<User> doInBackground(Integer... escalafones) {

        List<User> users = new ArrayList<>();

        if (myUserEndpointService == null) { // Only do this once
            UserApi.Builder builder = new UserApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null);
            myUserEndpointService = builder.build();

        }

        /*try {

            for (Integer escalafon: escalafones){
                users = myUserEndpointService.clients(escalafones.toString()).execute().getItems();
                Log.i(TAG,"Size: "+users.size());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }*/


        return users;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setMessage("Leyendo información");
        pd.setCancelable(false);
        //pd.show();
    }

    @Override
    protected void onPostExecute(List<User> users) {
        super.onPostExecute(users);
        pd.dismiss();

        for (User usuario: users){
            Log.i(TAG,"Nombre:"+usuario.getName()+" Cedula: "+usuario.getCedula() + " Tipo de Usuario: "+ usuario.getTipoUser());
            Toast.makeText(context,"Nombre:"+usuario.getName()+" Cedula: "+usuario.getCedula() + " Tipo de Usuario: "+ usuario.getTipoUser(), Toast.LENGTH_LONG);
        }
    }

}