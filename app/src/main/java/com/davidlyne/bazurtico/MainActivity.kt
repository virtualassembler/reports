package com.davidlyne.bazurtico

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.davidlyne.bazurtico.data.local.ClientDataType
import com.davidlyne.bazurtico.data.local.TotalizerDatabase
import com.davidlyne.bazurtico.data.local.VegetableDao
import com.davidlyne.bazurtico.ui.client.CreateClientActivity
import com.davidlyne.bazurtico.ui.recipient.NewRecipientActivity
import com.davidlyne.bazurtico.ui.recipient.SelectVegetableActivity
import kotlinx.android.synthetic.main.activity_create_client.*

//import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    //private val movieDatabase: VegetableDao get() = TotalizerDatabase.getInstance(this)!!.getVegetableDAO()
    //private val instance = TotalizerDatabase.getInstance(applicationContext)
    //private val insertDefaultData = TotalizerDatabase.PopulateDbAsyncTask(instance)

    //private val clientList: ClientDao get() = TotalizerDatabase.getInstance(this)!!.getClientDAO()
    //private val clientList: List<ClientDataType>  get() = TotalizerDatabase.getInstance(this)!!.getClientDAO().getClientList()
    //private val clientList2: List<ClientDataType>  get() = TotalizerDatabase.getInstance(this)!!.getClientDAO().getClientList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            this@MainActivity.startActivity(Intent(this@MainActivity, NewRecipientActivity::class.java))
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //ADDED
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        //Log.e("#000",""+TotalizerDatabase.PopulateDbAsyncTask(TotalizerDatabase.getTotalizerDatabase(applicationContext)))
        Log.e("#001",""+TotalizerDatabase.getInstance(this)!!.getVegetableDAO().getVegetableList())
        Log.e("#002","eee"+TotalizerDatabase.getInstance(this)!!.getClientDAO().getClientList())
        Log.e("#003","test")

        //Log.e("#002","eee"+clientList2)

    }

    //ADDED
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> this@MainActivity.startActivity(Intent(this@MainActivity, CreateClientActivity::class.java))
            R.id.nav_gallery -> this@MainActivity.startActivity(Intent(this@MainActivity, SelectVegetableActivity::class.java))
            R.id.nav_slideshow -> Toast.makeText(this, "Clicked item three", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}