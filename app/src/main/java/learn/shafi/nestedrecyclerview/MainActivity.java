package learn.shafi.nestedrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import adapter.InstitututionAdapter;
import pojo.Institution;

public class MainActivity extends AppCompatActivity {

    RecyclerView institutionRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        institutionRV = findViewById(R.id.userInstituteRV);

        Institution institution1 = new Institution("Murarichad College Sylhet","Science",
                "HSC","B (3.00)","2011-2013");
        Institution institution2 = new Institution("Metropolitan University Sylhet","Computer Science Ans Engineering",
                "Bachelor Of Science","B+ Second Class","2015-2019");
        Institution institution = new Institution("Police Line High School Sylhet","Science",
                "SSC","A (4.00)","2009-2011");


        ArrayList<Institution> institutions = new ArrayList<>();
        institutions.add(institution);
        institutions.add(institution1);
        institutions.add(institution2);

        // sorting institution based on passing year
        Collections.sort(institutions);

        Log.d("Institutions ",institutions.toString());

        InstitututionAdapter institututionAdapter = new InstitututionAdapter(this,institutions);
        institutionRV.setLayoutManager(new LinearLayoutManager(this));
        institutionRV.setAdapter(institututionAdapter);
    }
}
