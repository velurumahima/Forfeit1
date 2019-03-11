package com.ocr.traffic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class FineActivity extends AppCompatActivity {

    Spinner reason;
    EditText amt;

    String fines[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fine);
        reason=(Spinner) findViewById(R.id.sp1);
        amt=(EditText)findViewById(R.id.et_amt);
        fines=getResources().getStringArray(R.array.fines);

        reason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                amt.setText(fines[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void doSave(View v)
    {
        final String reas=reason.getSelectedItem().toString();
        final String am=amt.getText().toString();

        Intent it=new Intent();
        it.putExtra("reas",reas);
        it.putExtra("amt",am);
        setResult(RESULT_OK,it);
        finish();
    }
}
