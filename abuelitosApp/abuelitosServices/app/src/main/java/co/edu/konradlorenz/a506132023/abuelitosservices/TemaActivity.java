package co.edu.konradlorenz.a506132023.abuelitosservices;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TemaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.containerTema, new TemaActivity.PlaceholderFragment())
                    .commit();
        }
    }
    public static class PlaceholderFragment extends Fragment {

        ArrayAdapter<String> temaAdapter;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            // Es necesario definir una información dummy para la prueba, por favor agregue
            String[] data = {
                    "comentario 1",
                    "comentario 2",
                    "comentario 3",
                    "comentario 4",
                    "comentario 5",
                    "comentario 6",
                    "comentario 7",
            };
            List<String> temaLista = new ArrayList<String>(Arrays.asList(data));


            // Now that we have some dummy forecast data, create an ArrayAdapter.
            // The ArrayAdapter will take data from a source (like our dummy forecast) and
            // use it to populate the ListView it's attached to.
            temaAdapter =
                    new ArrayAdapter<String>(
                            getActivity(), // The current context (this activity)
                            R.layout.list_item_tema, // The name of the layout ID.
                            R.id.list_item_tema_textview, // The ID of the textview to populate.
                            temaLista);

            View rootView = inflater.inflate(R.layout.fragment_tema, container, false);

            // Get a reference to the ListView, and attach this adapter to it.
            ListView listView = (ListView) rootView.findViewById(R.id.listview_tema);
            listView.setAdapter(temaAdapter);

            return rootView;
        }
    }
}
