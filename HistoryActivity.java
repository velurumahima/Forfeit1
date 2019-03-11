package com.ocr.traffic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Vector;

public class HistoryActivity extends AppCompatActivity {

    List<Fine> fines;

    FineAdapter adapter;
    String vno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        vno=getIntent().getStringExtra("vno");
        fines=new Vector<Fine>();
        adapter=new FineAdapter(this,fines);
        ListView lv=(ListView)findViewById(R.id.lv);

        lv.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    loadFines();



    }

    private void loadFines() {
        String url=getResources().getString(R.string.host)+"getFines.jsp?vno="+vno;

        JsonArrayRequest req = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        fines.clear();

                        for(int i=0;i<response.length();i++)
                        {
                            try {
                                JSONObject ja = response.getJSONObject(i);
                                Fine it=new Fine();
                                it.setVno(ja.getString("vno"));
                                it.setDate(ja.getString("date"));
                                it.setReason(ja.getString("reason"));
                                it.setAmount(ja.getDouble("amount"));
                                it.setImage(ja.getString("image"));

                                fines.add(it);
                                adapter.notifyDataSetChanged();



                            }catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });


        Volley.newRequestQueue(this).add(req);



    }
}
