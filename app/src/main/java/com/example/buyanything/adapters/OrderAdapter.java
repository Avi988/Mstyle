package com.example.buyanything.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.buyanything.R;
import com.example.buyanything.models.Order;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    private List<Order> orderList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView qty;
        public TextView price;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.itemOrderName);
            qty = (TextView) view.findViewById(R.id.itemOrderQty);
            price = (TextView) view.findViewById(R.id.itemOrderPrice);
        }
    }

    public OrderAdapter(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public OrderAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_order, parent, false);

        return new OrderAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrderAdapter.MyViewHolder holder, int position) {
        Order order = orderList.get(position);

        holder.name.setText(order.getItemName());
        holder.qty.setText(Integer.toString(order.getQty()) + "pcs");
        holder.price.setText("Rs. "+ String.format("%.2f", order.getPrice() * order.getQty()) +"/=");
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
