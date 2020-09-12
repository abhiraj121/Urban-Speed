package com.abhirajsharma.urbanspeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class profile extends AppCompatActivity {
    private Toolbar toolbar;
    private LinearLayout myOrderLayout,ChangeAddressLayout, myAccountbottomLayout, myCart, myOffers, myAccoutnFav,ordrOnDemand,help_ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile );

        toolbar = findViewById( R.id.toolbar_profile );
        ChangeAddressLayout = findViewById( R.id.myAccountChangeAddressLayout );
        myCart = findViewById( R.id.myAccountCartLL );
        myOrderLayout = findViewById( R.id.myOrderLayout );

        myOffers = findViewById( R.id.myAccountOfferLayout );
        myAccoutnFav = findViewById( R.id.myAccountFavouriteLayout );


        myAccoutnFav.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {

            }
        } );
        myOrderLayout.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent( profile.this,myOrder.class );
                startActivity( intent );
            }
        } );
        myOffers.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {

            }
        } );

        myCart.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent( profile.this,MyCart.class );
                startActivity( intent );
            }
        } );

        ChangeAddressLayout.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( profile.this, MyAddress.class );
                startActivity( intent );
            }
        } );



        toolbar.setTitle( "My Profile" );
        setSupportActionBar( toolbar );
        toolbar.setTitleTextColor( Color.parseColor( "#FFFFFF" ) );
        getSupportActionBar( ).setDisplayShowHomeEnabled( true );
        getSupportActionBar( ).setDisplayHomeAsUpEnabled( true );






    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId( ) == android.R.id.home) {
            finish( );
        }

        return super.onOptionsItemSelected( item );
    }

}