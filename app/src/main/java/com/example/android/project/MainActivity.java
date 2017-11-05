package com.example.android.project;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.M;


public class MainActivity extends AppCompatActivity /**implements View.OnClickListener*/{
    private RecyclerView rvCategory;
    private ArrayList<Penemu> list;
    private void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvCategory = (RecyclerView) findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);
        getSupportActionBar().setTitle("Founder List");


        list = new ArrayList<>();
        list.addAll(PenemuData.getListData());

        showRecyclerList();
    }



    private void showRecyclerList() {
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        ListPenemuAdapter listPenemuAdapter = new ListPenemuAdapter(this);
        listPenemuAdapter.setListPenemu(list);
        rvCategory.setAdapter(listPenemuAdapter);
        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedPenemu(list.get(position));
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void showRecyclerGrid(){
        rvCategory.setLayoutManager(new GridLayoutManager(this, 2));
        GridPenemuAdapter gridPenemuAdapter = new GridPenemuAdapter(this);
        gridPenemuAdapter.setListPenemu(list);
        rvCategory.setAdapter(gridPenemuAdapter);
        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedPenemu(list.get(position));
            }
        });

    }


    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardViewPenemuAdapter cardViewPenemuAdapter = new CardViewPenemuAdapter(this);
        cardViewPenemuAdapter.setListPenemu(list);
        rvCategory.setAdapter(cardViewPenemuAdapter);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_list:
                showRecyclerList();
                break;

            case R.id.action_grid:
                showRecyclerGrid();
                break;

            case R.id.action_cardview:
                showRecyclerCardView();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void showSelectedPenemu(Penemu Penemu){
        Toast.makeText(this, "Kamu memilih "+Penemu.getName(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, MoveActivity.class);
        intent.putExtra("Name",Penemu.getName());
        intent.putExtra("Photo",Penemu.getPhoto());
        intent.putExtra("Remarks",Penemu.getRemarks());
        intent.putExtra("Cont",Penemu.getPeran());
        intent.putExtra("Tgl",Penemu.getTgl());
        intent.putExtra("Bio",Penemu.getBio());
        MainActivity.this.startActivity(intent);
    }


}

