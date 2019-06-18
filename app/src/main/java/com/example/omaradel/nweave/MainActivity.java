package com.example.omaradel.nweave;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.net.URI;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String PRODUCT_ID = "PRODUCT_ID ";//a public ID to control productid from detailActivity.
    public CoordinatorLayout coordinatorLayout;
    private static String webUrl = "https://www.facebook.com/H-Sport-1388674971422183/";//facebook of h+ sport
    private static String email = "info@hplussport.com"; //the email adress of h+ sport to send to
    private List<product>products=Dataprovider.productList;//object of the product list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //making the snakepar to send an email to h+ sport..
                String[] adresses={email};
                Intent emiIntent=new Intent(Intent.ACTION_SENDTO);
                emiIntent.setData(Uri.parse("mailto"));
                emiIntent.putExtra(Intent.EXTRA_EMAIL,adresses);
                emiIntent.putExtra(Intent.EXTRA_SUBJECT,"Information request");
                emiIntent.putExtra(Intent.EXTRA_TEXT,"Please send some information!");
                if(emiIntent.resolveActivity(getPackageManager())!=null)
                startActivity(emiIntent);
            }


        });


       // String[] items=getResources().getStringArray(R.array.clothing);//Array of string has the items //it is ubdated
       // ArrayAdapter<String> adabtor=
          //      new ArrayAdapter<String>
            //            (this,android.R.layout.simple_expandable_list_item_1,android.R.id.text1,items);

        //object of a class (productListAdabtor)
        productListAdabtor adabtor=new productListAdabtor(this,R.layout.list_item,products);
        ListView lv=findViewById(R.id.listview);
        lv.setAdapter(adabtor);
        //onclick to go detaials
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,detailActivity.class);//to goo to detailActivity
                product product=products.get(position);
                //what putextra do!!
                intent.putExtra(PRODUCT_ID,product.getProductid());
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_settings:
                Snackbar.make(coordinatorLayout, "You selected settings",
                        Snackbar.LENGTH_LONG).setAction("Action", null).show();
                return true;
            case R.id.action_about:
                  //  Snackbar.make(coordinatorLayout,"You selected About",
                    //Snackbar.LENGTH_LONG).setAction("Action",null).show();
                Intent intent=new Intent(this,AboutActivity.class);
                  startActivity(intent);
            return true;
            case R.id.action_web:
                Intent webIntent1=new Intent(Intent.ACTION_WEB_SEARCH);
             if (webIntent1.resolveActivity(getPackageManager())!=null)
                    startActivity(webIntent1);

                return true;
            case R.id.action_face:
                Intent facIntent=new Intent(Intent.ACTION_VIEW,Uri.parse(webUrl));
                if (facIntent.resolveActivity(getPackageManager())!=null)
                    startActivity(facIntent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
