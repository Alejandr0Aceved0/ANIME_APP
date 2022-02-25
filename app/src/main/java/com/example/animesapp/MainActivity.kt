package com.example.animesapp

import androidx.appcompat.app.AppCompatActivity
import com.example.animesapp.Models.animes
import com.example.animesapp.Models.animesList
import com.android.volley.RequestQueue
import androidx.viewpager2.widget.ViewPager2
import androidx.recyclerview.widget.RecyclerView
import com.example.animesapp.AdapterListAnimes
import android.os.Bundle
import com.example.animesapp.R
import android.widget.TextView
import android.graphics.Typeface
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.Volley
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import org.json.JSONArray
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import org.json.JSONException
import com.android.volley.VolleyError
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import com.android.volley.Request
import com.example.animesapp.DetailActivity
import java.util.*

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private var mListAnime: ArrayList<animes>? = null
    private var mListAnimeFilter: ArrayList<animesList>? = null
    private var mRequest: RequestQueue? = null
    private var mRecyclerView: ViewPager2? = null
    private var mRecyclerViewListAnimes: RecyclerView? = null
    private var mAdapter: Adapter? = null
    private var mAdapterListAnimes: AdapterListAnimes? = null
    private var searchView: SearchView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mBanner = findViewById<TextView>(R.id.banner)
        mBanner.setTypeface(Typeface.createFromAsset(assets, "fonts/Anton-Regular.ttf"))
        mRecyclerView = findViewById(R.id.reciclerView1)
        searchView = findViewById(R.id.navSearch)
        mRecyclerViewListAnimes = findViewById(R.id.animeList)
        mRecyclerViewListAnimes!!.setHasFixedSize(true)
        mRecyclerViewListAnimes!!.setLayoutManager(LinearLayoutManager(this))
        mListAnime = ArrayList()
        mListAnimeFilter = ArrayList()
        mRequest = Volley.newRequestQueue(this)
        animes
        initListener()
    }//Envia el ArrayList para ordenar por popularidad

    //CARGAR EL reciclerView1
    //RECYCLERVIEW CARRUSEL

    //CARGA EL RECYCLER VIEW VERTICAL


    // mAdapter.setOnItemClickListener(MainActivity.this);
    //text.setText(mListAnime.toString());
    // Request a string response from the provided URL.
    private val animes: Unit
        private get() {
            // Request a string response from the provided URL.
            val stringRequest = JsonObjectRequest(Request.Method.GET, Constants.getAPIJikan, null,
                    { response ->
                        try {
                            val jsonArray = response.getJSONArray("data")
                            for (i in 0 until jsonArray.length()) {
                                val result = jsonArray.getJSONObject(i)
                                val mal_id = result.getString("mal_id")
                                val title = result.getString("title")
                                val rating = result.getString("rating")
                                val popularity = result.getString("popularity")
                                val status = result.getString("status")
                                val airing = result.getString("airing")
                                val season = result.getString("season")
                                val year = result.getString("year")
                                var synopsis = result.getString("synopsis")
                                synopsis = synopsis.replace("'".toRegex(), "\\'")
                                val image = result.getJSONObject("images").getJSONObject("jpg").getString("large_image_url")
                                mListAnime!!.add(animes(mal_id, title, rating, popularity, status, synopsis, airing, image, season, year))
                                mListAnimeFilter!!.add(animesList(mal_id, title, rating, popularity, status, synopsis, airing, image, season, year))
                                Collections.sort(mListAnime) //Envia el ArrayList para ordenar por popularidad
                            }

                            //CARGAR EL reciclerView1
                            mAdapter = Adapter(this@MainActivity, mListAnime!!)
                            mRecyclerView!!.clipToPadding = false
                            mRecyclerView!!.clipChildren = false
                            mRecyclerView!!.offscreenPageLimit = 3
                            mRecyclerView!!.getChildAt(0).overScrollMode = View.OVER_SCROLL_NEVER
                            mRecyclerView!!.adapter = mAdapter
                            val transformer = CompositePageTransformer()
                            transformer.addTransformer(MarginPageTransformer(80))
                            transformer.addTransformer { page, position ->
                                val v = 1 - Math.abs(position)
                                page.scaleY = 0.85f + v * 0.15f
                            }
                            //RECYCLERVIEW CARRUSEL
                            mRecyclerView!!.setPageTransformer(transformer)

                            //CARGA EL RECYCLER VIEW VERTICAL
                            mAdapterListAnimes = AdapterListAnimes(this@MainActivity, mListAnimeFilter!!)
                            mRecyclerViewListAnimes!!.adapter = mAdapterListAnimes


                            // mAdapter.setOnItemClickListener(MainActivity.this);
                        } catch (e: JSONException) {
                            e.printStackTrace()
                            println("ERROR ENN: $e")
                        }
                        println(mListAnime)
                        //text.setText(mListAnime.toString());
                    }) { error -> Log.e("ANIMES FALLO :( ", "That didn't work! $error") }
            mRequest!!.add(stringRequest)
        }

    private fun initListener() {
        searchView!!.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String): Boolean { //SE EJECUTA OPRIMIENDO EL ICONO SEACH DEL TECLADO
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean { //SE EJEECUTA CADA QUE ESCRIBAMOS EMPIEZA A BUSCAR
        mAdapterListAnimes!!.filter(newText)
        return false
    }

    fun ryAnimeClick(animesItem: animes) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("itemDetail", animesItem.toString())
        startActivity(intent)
    }
}