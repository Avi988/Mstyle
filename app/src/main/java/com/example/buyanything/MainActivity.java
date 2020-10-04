package com.example.buyanything;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.buyanything.Database.OrderDAO;
import com.example.buyanything.adapters.OrderAdapter;
import com.example.buyanything.models.Order;
import com.example.buyanything.utils.RecyclerTouchListener;
import java.util.ArrayList;
import java.util.List;

public class  MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;

    private List<Order> orderList = new ArrayList<>();
    private OrderAdapter orderAdapter;
    private OrderDAO orderDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.mainToolbar);
        recyclerView = findViewById(R.id.mainRecyclerView);

        setSupportActionBar(toolbar);

        orderAdapter = new OrderAdapter(orderList);
        orderDAO = new OrderDAO(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(orderAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Order order = orderList.get(position);

                Intent intent = new Intent(getApplicationContext(), OrderEditActivity.class);
                intent.putExtra("id", order.getId());
                intent.putExtra("itemName", order.getItemName());
                intent.putExtra("name", order.getName());
                intent.putExtra("contact", order.getContact());
                intent.putExtra("place", order.getPlace());
                intent.putExtra("qty", order.getQty());
                intent.putExtra("size", order.getSize());
                intent.putExtra("paymentMethod", order.getPaymentMethod());
                intent.putExtra("price", order.getPrice());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) { }
        }));

        orderData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.actionAdd) {
            Intent intent = new Intent(MainActivity.this, OrderEditActivity.class);
            intent.putExtra("id", 0);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void orderData() {
        for (Order item: orderDAO.GetAll()) {
            orderList.add(item);
        }
        orderAdapter.notifyDataSetChanged();
    }
}