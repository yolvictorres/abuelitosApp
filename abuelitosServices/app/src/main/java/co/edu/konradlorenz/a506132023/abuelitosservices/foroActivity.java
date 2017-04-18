package co.edu.konradlorenz.a506132023.abuelitosservices;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
=======
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class foroActivity extends AppCompatActivity {
    private Intent intent;
    private BottomNavigationView mBottomBar;
    private BottomNavigationView bottomNavigationView;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foro);
        //bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        //  @Override
        //public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //  if(item.getItemId() == R.id.itemEvento){
        //    Intent intent1 = new Intent(EventosActivity.this, EventosActivity.class);
        //  startActivity(intent1);
        //}else if (item.getItemId() == R.id.itemMapa){
        //  Intent intent2 = new Intent(EventosActivity.this, MapsActivity.class);
        //startActivity(intent2);
        //}else if (item.getItemId() == R.id.itemForo){
        //  Intent intent3 = new Intent(EventosActivity.this, foroActivity.class);
        //startActivity(intent3);
        //}
        //return false;
        //}
        //});

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.containerForo, new foroActivity.PlaceholderFragment())
                    .commit();
        }
    }

    public static class PlaceholderFragment extends Fragment {

        ArrayAdapter<String> foroAdapter;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            // Es necesario definir una información dummy para la prueba, por favor agregue
            String[] data = {
                    "comentarios y dudas de los usuarios",
                    "comentarios y dudas de los usuarios",
                    "comentarios y dudas de los usuarios",
                    "comentarios y dudas de los usuarios",
                    "comentarios y dudas de los usuarios",
                    "comentarios y dudas de los usuarios",
                    "comentarios y dudas de los usuarios",
                    "comentarios y dudas de los usuarios",
                    "comentarios y dudas de los usuarios"

            };
            List<String> foroLista = new ArrayList<String>(Arrays.asList(data));


            // Now that we have some dummy forecast data, create an ArrayAdapter.
            // The ArrayAdapter will take data from a source (like our dummy forecast) and
            // use it to populate the ListView it's attached to.
            foroAdapter =
                    new ArrayAdapter<String>(
                            getActivity(), // The current context (this activity)
                            R.layout.list_item_foro, // The name of the layout ID.
                            R.id.list_item_foro_textview, // The ID of the textview to populate.
                            foroLista);

            View rootView = inflater.inflate(R.layout.fragment_foro, container, false);

            // Get a reference to the ListView, and attach this adapter to it.
            ListView listView = (ListView) rootView.findViewById(R.id.listview_foro);
            listView.setAdapter(foroAdapter);

            return rootView;
        }
    }
    /**
     * Perform action when any item is selected.
     *
     * @param item Item that is selected.
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    protected void selectFragment(MenuItem item) {

        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.itemForo:
                pushFragment(new PlaceholderFragment());
                break;
            case R.id.itemEvento:
                // Action to perform when Bag Menu item is selected.
                pushFragment(new EventosActivity.PlaceholderFragment());
                break;
        }
    }

    /**
     * Method to push any fragment into given id.
     *
     * @param fragment An instance of Fragment to show into the given id.
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    protected void pushFragment(Fragment fragment) {
        if (fragment == null)
            return;

        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            android.app.FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.commit();
            }
        }
    }
}
