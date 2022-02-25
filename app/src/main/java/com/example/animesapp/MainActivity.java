package com.example.animesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.animesapp.Models.animes;
import com.example.animesapp.Models.animesList;

import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.xml.transform.Transformer;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ArrayList<animes> mListAnime;
    private ArrayList<animes> mListAnimeFilter;
    private RequestQueue mRequest;
    private ViewPager2 mRecyclerView;
    private RecyclerView mRecyclerViewListAnimes;
    private com.example.animesapp.Adapter mAdapter;
    private com.example.animesapp.AdapterListAnimes mAdapterListAnimes;
    private SearchView searchView;

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            TextView mBanner = findViewById(R.id.banner);
            mBanner.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Anton-Regular.ttf"));
            mRecyclerView = findViewById(R.id.reciclerView1);
            mRecyclerViewListAnimes = findViewById(R.id.animeList);
            searchView = findViewById(R.id.navSearch);
            mRecyclerViewListAnimes.setHasFixedSize(true);
            mRecyclerViewListAnimes.setLayoutManager(new LinearLayoutManager(this));
            mListAnime = new ArrayList<>();
            mListAnimeFilter = new ArrayList<>();
            mRequest = Volley.newRequestQueue(this);
            getAnimes();
            initListener();
        }

        private void getAnimes () {
            // Request a string response from the provided URL.
            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, Constants.getAPIJikan, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                JSONArray jsonArray = response.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject result = jsonArray.getJSONObject(i);

                                    String mal_id = result.getString("mal_id");
                                    String title = result.getString("title");
                                    String rating = result.getString("rating");
                                    String popularity = result.getString("popularity");
                                    String status = result.getString("status");
                                    String airing = result.getString("airing");
                                    String season = result.getString("season");
                                    String year = result.getString("year");
                                    String synopsis = result.getString("synopsis");
                                    synopsis = synopsis.replaceAll("'", "\\'");
                                    String image = result.getJSONObject("images").getJSONObject("jpg").getString("large_image_url");

                                    mListAnime.add(new animes(mal_id, title, rating, popularity, status, synopsis, airing, image, season, year));
                                    mListAnimeFilter.add(new animes(mal_id, title, rating, popularity, status, synopsis, airing, image, season, year));

                                    Collections.sort(mListAnimeFilter);//Envia el ArrayList para ordenar por popularidad

                                }

                                //CARGAR EL reciclerView1

                                mAdapter = new Adapter(MainActivity.this, mListAnimeFilter);
                                //mAdapter.setOnItemClickListener(MainActivity.this);
                                mRecyclerView.setClipToPadding(false);
                                mRecyclerView.setClipChildren(false);
                                mRecyclerView.setOffscreenPageLimit(3);
                                mRecyclerView.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
                                mRecyclerView.setAdapter(mAdapter);
                                CompositePageTransformer transformer = new CompositePageTransformer();
                                transformer.addTransformer(new MarginPageTransformer(80));
                                transformer.addTransformer(new ViewPager2.PageTransformer() {
                                    @Override
                                    public void transformPage(@NonNull View page, float position) {
                                        float v = 1 - Math.abs(position);

                                        page.setScaleY(0.85f + v * 0.15f);
                                    }
                                });
                                //RECYCLERVIEW CARRUSEL
                                mRecyclerView.setPageTransformer(transformer);

                                //CARGA EL RECYCLER VIEW VERTICAL
                                mAdapterListAnimes = new AdapterListAnimes(MainActivity.this, mListAnime);
                                mRecyclerViewListAnimes.setAdapter(mAdapterListAnimes);


                                // mAdapter.setOnItemClickListener(MainActivity.this);

                            } catch (JSONException e) {
                                e.printStackTrace();
                                System.out.println("ERROR ENN: " + e);
                            }
                            System.out.println(mListAnime);
                            //text.setText(mListAnime.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("ANIMES FALLO :( ", "That didn't work! " + error);
                }
            });

            mRequest.add(stringRequest)

            ;
        }


    private void initListener(){
            searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {//SE EJECUTA OPRIMIENDO EL ICONO SEACH DEL TECLADO
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {//SE EJEECUTA CADA QUE ESCRIBAMOS EMPIEZA A BUSCAR
            mAdapterListAnimes.filter(newText);
        return false;
    }
}