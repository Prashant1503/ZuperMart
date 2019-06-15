package com.example.pintu.zupermart.Fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pintu.zupermart.Adapter.CategoryAdapter;
import com.example.pintu.zupermart.Adapter.HorizontalProductScrollAdapter;
import com.example.pintu.zupermart.Adapter.SliderAdapter;
import com.example.pintu.zupermart.Models.CategoryModel;
import com.example.pintu.zupermart.Models.HorizontalProductScrollModel;
import com.example.pintu.zupermart.Models.SliderModel;
import com.example.pintu.zupermart.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {




    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;

    private LinearLayoutManager linearLayoutManager;
    private Timer timer;


//    todo : viewpager ...

   public ViewPager bannerSliderViewPager;
   public SliderAdapter sliderAdapter;
    private int current_page_position = 2;

    private List<SliderModel> sliderModelList;

    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;


//    todo : end of viewpager..

//    todo : Strip ad Layout...

    private ImageView stripImage;
    private ConstraintLayout stripAdContainer;

//    todo : End of Strip AdLayout...

//    todo : horizontal productScroll layout..

    private TextView HorizontallayoutTitle;
    private Button HorizontallLayoutViewallButton;
    private RecyclerView horizontalRecyclerView;
    private HorizontalProductScrollAdapter horizontalProductScrollAdapter;
    private List<HorizontalProductScrollModel> horizontalProductScrollModelList;
//    todo ; End of horizontal productScroll layout




    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);





// todo : horizontal recycler view
        categoryRecyclerView = view.findViewById(R.id.category_recycler_view);
        Context context;
        LinearLayoutManager LayoutManager = new LinearLayoutManager(getActivity());
        LayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(LayoutManager);

        List<CategoryModel> categoryModelList = new ArrayList<>();
        categoryModelList.add(new CategoryModel("link","Home"));
        categoryModelList.add(new CategoryModel("link","Electronics"));
        categoryModelList.add(new CategoryModel("link","Appliances"));
        categoryModelList.add(new CategoryModel("link","Furniture"));
        categoryModelList.add(new CategoryModel("link","Fashion"));
        categoryModelList.add(new CategoryModel("link","Toys"));
        categoryModelList.add(new CategoryModel("link","Sports"));
        categoryModelList.add(new CategoryModel("link","Wall Arts"));
        categoryModelList.add(new CategoryModel("link","Books"));
        categoryModelList.add(new CategoryModel("link","Shoes"));

         categoryAdapter = new CategoryAdapter(categoryModelList);
         categoryRecyclerView.setAdapter(categoryAdapter);
         categoryAdapter.notifyDataSetChanged();



//  todo : end of horizontal recycler view...

//        todo : Banner slider ViewPager....
//


        bannerSliderViewPager = view.findViewById(R.id.banner_slider_viewPager);


        sliderModelList = new ArrayList<>();

        sliderModelList.add(new SliderModel(R.drawable.seepassword,"#D3D3D3"));
        sliderModelList.add(new SliderModel(R.drawable.bannerslider,"#D3D3D3"));


        sliderModelList.add(new SliderModel(R.drawable.bannerslider,"#D3D3D3"));
        sliderModelList.add(new SliderModel(R.drawable.redemailicon,"#D3D3D3"));
        sliderModelList.add(new SliderModel(R.drawable.homeicon,"#D3D3D3"));
        sliderModelList.add(new SliderModel(R.drawable.greenemail,"#D3D3D3"));
        sliderModelList.add(new SliderModel(R.drawable.shoppingcart,"#D3D3D3"));
        sliderModelList.add(new SliderModel(R.drawable.searchicon,"#D3D3D3"));
        sliderModelList.add(new SliderModel(R.drawable.seepassword,"#D3D3D3"));
        sliderModelList.add(new SliderModel(R.drawable.bannerslider,"#D3D3D3"));

        sliderModelList.add(new SliderModel(R.drawable.bannerslider,"#D3D3D3"));


        sliderAdapter = new SliderAdapter(sliderModelList);
        bannerSliderViewPager.setAdapter(sliderAdapter);

        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);
        bannerSliderViewPager.setCurrentItem(current_page_position);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int i, float v, int i1) {



            }

            @Override
            public void onPageSelected(int i) {

                current_page_position = i;

            }

            @Override
            public void onPageScrollStateChanged(int i) {

                if (i == ViewPager.SCROLL_STATE_IDLE){

                    PageLooper();
                }

            }
        };
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

        StartBannerSlideShow();

        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                PageLooper();
                StopBannerSlideShow();

                if (event.getAction() == MotionEvent.ACTION_UP){

                    StartBannerSlideShow();
                }
                return false;
            }
        });


        //        todo : end of slider ViewPager.....

//        todo : strip AdLayout...

        stripImage = view.findViewById(R.id.strip_ad_image);
        stripAdContainer = view.findViewById(R.id.strip_ad_container);

        stripImage.setImageResource(R.drawable.bannerslider);
        stripImage.setBackgroundColor(Color.parseColor("#ffffff"));
//        todo : end of strip AdLayout...

//        todo : horizontal product scroll view ...

        HorizontallayoutTitle = view.findViewById(R.id.horizontal_scroll_layout_title);
        HorizontallLayoutViewallButton = view.findViewById(R.id.horizontal_scroll_viewAll_button);
        horizontalRecyclerView = view.findViewById(R.id.horizontal_scroll_layout_recycler_view);


        horizontalProductScrollModelList = new ArrayList<>();


        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobilephone,"Redmi 5A","SD 625 processor","Rs.5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.greenemail,"Redmi 5A","SD 625 processor","Rs.5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.redemailicon,"Redmi 5A","SD 625 processor","Rs.5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.closebtn,"Redmi 5A","SD 625 processor","Rs.5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.addbutton,"Redmi 5A","SD 625 processor","Rs.5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.homeicon,"Redmi 5A","SD 625 processor","Rs.5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobilephone,"Redmi 5A","SD 625 processor","Rs.5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobilephone,"Redmi 5A","SD 625 processor","Rs.5999/-"));

        horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
      // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
    //   linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
       horizontalRecyclerView.setLayoutManager(linearLayoutManager);

        horizontalRecyclerView.setAdapter(horizontalProductScrollAdapter);
        horizontalProductScrollAdapter.notifyDataSetChanged();

//        todo : End of horizontal product Scroll View.

//        todo : Grid ProductLayout ..



//        todo : Grid ProductLayout...







          return view;
    }

//    todo : method to create slider..
    private void PageLooper(){

        if (current_page_position ==sliderModelList.size()-2){
            current_page_position = 2;

            bannerSliderViewPager.setCurrentItem(current_page_position,false);
        }


        if (current_page_position ==1){
            current_page_position = sliderModelList.size()-3;

            bannerSliderViewPager.setCurrentItem(current_page_position,false);
        }







    }
    private void StartBannerSlideShow(){

        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (current_page_position >=sliderModelList.size()){
                    current_page_position =1;

                }

                bannerSliderViewPager.setCurrentItem(current_page_position++,true);

            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(update);

            }
        },DELAY_TIME,PERIOD_TIME);


    }
    private void StopBannerSlideShow(){
        timer.cancel();
    }





}
