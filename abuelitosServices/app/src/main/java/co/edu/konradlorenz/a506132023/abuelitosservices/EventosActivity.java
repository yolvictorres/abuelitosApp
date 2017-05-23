package co.edu.konradlorenz.a506132023.abuelitosservices;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventosActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_eventos);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.containerEvento, new EventosActivity.PlaceholderFragment())
                    .commit();
        }
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        selectFragment(item);
                        return true;
                    }
                });
    }

    public static class PlaceholderFragment extends Fragment {

        ArrayAdapter<String> eventoAdapter;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            // Es necesario definir una información dummy para la prueba, por favor agregue
            String[] data = {
                    "informacion de los eventos publicador",
                    "informacion de los eventos publicador",
                    "informacion de los eventos publicador",
                    "informacion de los eventos publicador",
                    "informacion de los eventos publicador",
                    "informacion de los eventos publicador",
                    "informacion de los eventos publicador",
                    "informacion de los eventos publicador",
                    "informacion de los eventos publicador",
                    "informacion de los eventos publicador",
                    "informacion de los eventos publicador"

            };
            List<String> eventoLista = new ArrayList<String>(Arrays.asList(data));


            // Now that we have some dummy forecast data, create an ArrayAdapter.
            // The ArrayAdapter will take data from a source (like our dummy forecast) and
            // use it to populate the ListView it's attached to.
            eventoAdapter =
                    new ArrayAdapter<String>(
                            getActivity(), // The current context (this activity)
                            R.layout.list_item_evento, // The name of the layout ID.
                            R.id.list_item_evento_textview, // The ID of the textview to populate.
                            eventoLista);

            View rootView = inflater.inflate(R.layout.fragment_evento, container, false);

            // Get a reference to the ListView, and attach this adapter to it.
            ListView listView = (ListView) rootView.findViewById(R.id.listview_evento);
            listView.setAdapter(eventoAdapter);

            return rootView;
        }
    }
    /**
     * Perform action when any item is selected.
     *
     * @param item Item that is selected.
     */
    protected void selectFragment(MenuItem item) {

        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.itemForo:
                pushFragment(new foroActivity.PlaceholderFragment());
                break;
            case R.id.itemEvento:
                // Action to perform when Bag Menu item is selected.
                pushFragment(new EventosActivity.PlaceholderFragment());
                break;
            case R.id.itemMapa:
                pushFragment(new MapsActivity.PlaceholderFragment());
                break;

        }
    }

    /**
     * Method to push any fragment into given id.
     *
     * @param fragment An instance of Fragment to show into the given id.
     */
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
