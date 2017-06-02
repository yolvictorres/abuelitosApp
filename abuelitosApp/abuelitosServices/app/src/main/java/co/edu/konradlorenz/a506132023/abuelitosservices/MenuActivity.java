package co.edu.konradlorenz.a506132023.abuelitosservices;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.ncapdevi.fragnav.FragNavController;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Fragment fragment;
    private FragNavController fragNavController;

    //indices to fragments
    private final int TAB_FIRST = FragNavController.TAB1;
    private final int TAB_SECOND = FragNavController.TAB2;
    private final int TAB_THIRD = FragNavController.TAB3;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragNavController.switchTab(TAB_FIRST);
                    return true;
                case R.id.navigation_dashboard:
                    fragNavController.switchTab(TAB_SECOND);
                    return true;
                case R.id.navigation_notifications:
                    fragNavController.switchTab(TAB_THIRD);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        List<Fragment> fragments = new ArrayList<>();

        //add fragments to list

        fragments.add(new EventosActivity.PlaceholderFragment());
        fragments.add(new foroActivity.PlaceholderFragment());
        fragments.add(new MapsActivity.PlaceholderFragment());

        //link fragments to container
        fragNavController = new FragNavController(getSupportFragmentManager(),R.id.container,fragments);
        //End of FragNav

        //mTextMessage = (TextView) findViewById(R.id.text12);
        fragNavController.switchTab(TAB_FIRST);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
