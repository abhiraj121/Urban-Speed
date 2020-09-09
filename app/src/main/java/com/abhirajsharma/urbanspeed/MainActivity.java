package com.abhirajsharma.urbanspeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abhirajsharma.urbanspeed.Adapter.HomeAdapter;
import com.abhirajsharma.urbanspeed.Adapter.HomeCategoryAdapter;
import com.abhirajsharma.urbanspeed.model.HomeCategoryModels;
import com.abhirajsharma.urbanspeed.model.HomeModel;
import com.abhirajsharma.urbanspeed.model.dealsofthedayModel;
import com.abhirajsharma.urbanspeed.model.sliderModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView homecategoryRecycler;
    private RecyclerView homecycler;

    private Toolbar toolbar;
    private List<HomeModel> homeModelList;


    private NestedScrollView scrollView;
    private AppBarLayout appBarLayout;
    private FloatingActionButton search_Fab;
    public static CircleImageView imageProfileHome;

    private HomeCategoryAdapter homeCategoryAdapter;
    private List<HomeCategoryModels> homeCategoryModelsList;
    public static List<dealsofthedayModel> dealsofthedayModelList=new ArrayList<>(  );
    public static List<dealsofthedayModel> gridList=new ArrayList<>(  );
    private List<sliderModel> sliderModelList;
    private List<HomeCategoryModels> circularHorizontallist;

    private LinearLayout search_bar_LL, profileLL;
    private LinearLayout adminLayout;
    public static TextView OutOfStock, Orders;
    String image = "", name = "", mail = "";
    private Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        toolbar = findViewById( R.id.toolbar );
        appBarLayout = findViewById( R.id.appBarLayout );
        search_Fab = findViewById( R.id.home_search_product_FAB );
        imageProfileHome = findViewById( R.id.profile_image_home );
        homecategoryRecycler = findViewById( R.id.home_category_recycler );
        homecycler = findViewById( R.id.home_recycler );
        scrollView = findViewById( R.id.home_scroll );
        search_bar_LL = findViewById( R.id.search_bar_LL );
        profileLL = findViewById( R.id.profileImageHomeLL );





        homeCategoryModelsList=new ArrayList<>(  );
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));
        homeCategoryModelsList.add( new HomeCategoryModels( "jhbhsda","helloworld","kajdsfg" ));





        ////scroll_movement
        scrollView.setOnScrollChangeListener( new NestedScrollView.OnScrollChangeListener( ) {
            @SuppressLint("RestrictedApi")
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int movement = v.getScrollY();
                int maxDistance=toolbar.getHeight();
                float alphaFactor = ((movement * 1.0f) / (maxDistance));
                int height= appBarLayout.getScrollY();
                if ( height>= 0 &&
                        height<= maxDistance) {
                    /*for image parallax with scroll */
                   /* set visibility */
                    search_bar_LL.setAlpha(alphaFactor);
                    search_Fab.setAlpha( 1-alphaFactor );
                    if(alphaFactor>0.8){
                        appBarLayout.setBackgroundColor( Color.parseColor( "#252525" ) );

                    }else {
                        appBarLayout.setBackgroundResource( R.drawable.background );

                    }
                }
                if (oldScrollY > scrollY) {
                    search_Fab.setAlpha( 1f );
                    search_Fab.setVisibility( View.VISIBLE );
                    appBarLayout.setBackgroundResource( R.drawable.background );
                    search_bar_LL.setAlpha( 0 );
                } else {
                    search_Fab.setVisibility( View.GONE );

                }
            }
        } );

        ////scroll_movement


        ArrayList<String> ids=new ArrayList<>(  );
        homeModelList=new ArrayList<>(  );


        ///lists
        sliderModelList=new ArrayList<>(   );
        sliderModelList.add( new sliderModel( "abdbakl","hsdfay","#FFFFFF"));
        sliderModelList.add( new sliderModel( "abdbakl","hsdfay","#FFFFFF"));
        sliderModelList.add( new sliderModel( "abdbakl","hsdfay","#FFFFFF"));
        sliderModelList.add( new sliderModel( "abdbakl","hsdfay","#FFFFFF"));
        sliderModelList.add( new sliderModel( "abdbakl","hsdfay","#FFFFFF"));
        sliderModelList.add( new sliderModel( "abdbakl","hsdfay","#FFFFFF"));
        sliderModelList.add( new sliderModel( "abdbakl","hsdfay","#FFFFFF"));
        sliderModelList.add( new sliderModel( "abdbakl","hsdfay","#FFFFFF"));
        sliderModelList.add( new sliderModel( "abdbakl","hsdfay","#FFFFFF"));
        sliderModelList.add( new sliderModel( "abdbakl","hsdfay","#FFFFFF"));
        sliderModelList.add( new sliderModel( "abdbakl","hsdfay","#FFFFFF"));
        sliderModelList.add( new sliderModel( "abdbakl","hsdfay","#FFFFFF"));
        sliderModelList.add( new sliderModel( "abdbakl","hsdfay","#FFFFFF"));
        sliderModelList.add( new sliderModel( "abdbakl","hsdfay","#FFFFFF"));
        sliderModelList.add( new sliderModel( "abdbakl","hsdfay","#FFFFFF"));
        sliderModelList.add( new sliderModel( "abdbakl","hsdfay","#FFFFFF"));
        sliderModelList.add( new sliderModel( "abdbakl","hsdfay","#FFFFFF"));
        sliderModelList.add( new sliderModel( "abdbakl","hsdfay","#FFFFFF"));





        dealsofthedayModelList.add(new  dealsofthedayModel("image","title","ahsdbf","jhfdba","jfdnsa","jdfhajd"));
        dealsofthedayModelList.add(new  dealsofthedayModel("image","title","ahsdbf","jhfdba","jfdnsa","jdfhajd"));
        dealsofthedayModelList.add(new  dealsofthedayModel("image","title","ahsdbf","jhfdba","jfdnsa","jdfhajd"));
        dealsofthedayModelList.add(new  dealsofthedayModel("image","title","ahsdbf","jhfdba","jfdnsa","jdfhajd"));
        dealsofthedayModelList.add(new  dealsofthedayModel("image","title","ahsdbf","jhfdba","jfdnsa","jdfhajd"));
        dealsofthedayModelList.add(new  dealsofthedayModel("image","title","ahsdbf","jhfdba","jfdnsa","jdfhajd"));
        dealsofthedayModelList.add(new  dealsofthedayModel("image","title","ahsdbf","jhfdba","jfdnsa","jdfhajd"));
        dealsofthedayModelList.add(new  dealsofthedayModel("image","title","ahsdbf","jhfdba","jfdnsa","jdfhajd"));



        gridList.add(new  dealsofthedayModel("image","title","ahsdbf","jhfdba","jfdnsa","jdfhajd"));
        gridList.add(new  dealsofthedayModel("image","title","ahsdbf","jhfdba","jfdnsa","jdfhajd"));
        gridList.add(new  dealsofthedayModel("image","title","ahsdbf","jhfdba","jfdnsa","jdfhajd"));
        gridList.add(new  dealsofthedayModel("image","title","ahsdbf","jhfdba","jfdnsa","jdfhajd"));

        circularHorizontallist=new ArrayList<>(  );
        circularHorizontallist.add(new  HomeCategoryModels("image","title","afda") );
        circularHorizontallist.add(new  HomeCategoryModels("image","title","afda") );
        circularHorizontallist.add(new  HomeCategoryModels("image","title","afda") );
        circularHorizontallist.add(new  HomeCategoryModels("image","title","afda") );
        circularHorizontallist.add(new  HomeCategoryModels("image","title","afda") );
        circularHorizontallist.add(new  HomeCategoryModels("image","title","afda") );
        circularHorizontallist.add(new  HomeCategoryModels("image","title","afda") );
        circularHorizontallist.add(new  HomeCategoryModels("image","title","afda") );
        circularHorizontallist.add(new  HomeCategoryModels("image","title","afda") );
        circularHorizontallist.add(new  HomeCategoryModels("image","title","afda") );


        homeModelList.add( new HomeModel( 1,sliderModelList ) );
        homeModelList.add( new HomeModel( 2,"title",dealsofthedayModelList,ids,"#000000" ) );
        homeModelList.add( new HomeModel( 3,"grid_title",gridList,ids,"#FFFFFF" ) );
        homeModelList.add( new HomeModel( 4,0,"Top Brands",circularHorizontallist ) );
        homeModelList.add( new HomeModel( 5,"name_1","name_1","name_1","name_1","name_1",
                "name_1","name_1","name_1",
                "name_1","name_1","name_1","name_1","name_1"

        ) );
        ///lists

        homeCategoryAdapter=new HomeCategoryAdapter( homeCategoryModelsList );
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager( this );
        linearLayoutManager.setOrientation( RecyclerView.HORIZONTAL );
        homecategoryRecycler.setLayoutManager( linearLayoutManager );
        homecategoryRecycler.setAdapter( homeCategoryAdapter );
        homeCategoryAdapter.notifyDataSetChanged();


        final HomeAdapter homeAdapter=new HomeAdapter(homeModelList  );
        LinearLayoutManager grocerymain=new LinearLayoutManager( this );
        grocerymain.setOrientation( RecyclerView.VERTICAL );
        homecycler.setLayoutManager( grocerymain );
        homecycler.setAdapter( homeAdapter );










    }
}