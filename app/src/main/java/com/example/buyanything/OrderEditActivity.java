package com.example.buyanything;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.buyanything.Database.OrderDAO;
import com.example.buyanything.models.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class OrderEditActivity extends AppCompatActivity {
    private EditText nameField;
    private EditText contactField;
    private EditText placeField;
    private EditText quantityField;
    private EditText sizeField;
    private EditText priceField;
    private Spinner itemSpinner;
    private Spinner paymentSpinner;
    private Button editButton;
    private Button deleteButton;

    private Order orderForSave;
    private boolean isEdit = false;
    private boolean isAdd = false;
    private OrderDAO orderDAO;

    String[] items = {"Select an Item", "Frock", "Trouser", "Saree", "Socks", "Shirt", "Ladies Shirt"};
    String[] payments = {"Select a Payment Method", "Cash", "Cheque"};
    List<String> itemList = new ArrayList<>();
    List<String> paymentList = new ArrayList<>();
    ArrayAdapter<String> itemAdapter;
    ArrayAdapter<String> paymentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_edit);

        nameField = findViewById(R.id.orderEditName);
        contactField = findViewById(R.id.orderEditContact);
        placeField = findViewById(R.id.orderEditPlace);
        quantityField = findViewById(R.id.orderEditQuantity);
        sizeField = findViewById(R.id.orderEditSize);
        priceField = findViewById(R.id.orderEditPrice);
        itemSpinner = findViewById(R.id.orderEditItemSpinner);
        paymentSpinner = findViewById(R.id.orderEditPaymentSpinner);
        editButton = findViewById(R.id.orderEditButton);
        deleteButton = findViewById(R.id.orderDeleteButton);

        orderDAO = new OrderDAO(this);
        orderForSave = new Order();
        itemList = Arrays.asList(items);
        paymentList = Arrays.asList(payments);

        itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itemList);
        itemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemSpinner.setAdapter(itemAdapter);

        paymentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paymentList);
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentSpinner.setAdapter(paymentAdapter);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        if (id == 0) {
            isAdd = true;
            editButton.setText("Add");
            deleteButton.setVisibility(View.GONE);
        } else {
            String itemName = intent.getStringExtra("itemName");
            String name = intent.getStringExtra("name");
            String contact = intent.getStringExtra("contact");
            String place = intent.getStringExtra("place");
            int qty = intent.getIntExtra("qty", 0);
            int size = intent.getIntExtra("size", 0);
            String paymentMethod = intent.getStringExtra("paymentMethod");
            double price = intent.getDoubleExtra("price", 0);

            orderForSave.setId(id);
            orderForSave.setItemName(itemName);
            orderForSave.setName(name);
            orderForSave.setContact(contact);
            orderForSave.setPlace(place);
            orderForSave.setQty(qty);
            orderForSave.setSize(size);
            orderForSave.setPaymentMethod(paymentMethod);
            orderForSave.setPrice(price);

            updateUI();

            nameField.setEnabled(false);
            contactField.setEnabled(false);
            placeField.setEnabled(false);
            quantityField.setEnabled(false);
            sizeField.setEnabled(false);
            priceField.setEnabled(false);
            itemSpinner.setEnabled(false);
            paymentSpinner.setEnabled(false);
        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemSpinner.getSelectedItemPosition() == 0) {
                    Toasty.warning(OrderEditActivity.this, "Please select an item.", Toasty.LENGTH_SHORT).show();
                } else if (paymentSpinner.getSelectedItemPosition() == 0) {
                    Toasty.warning(OrderEditActivity.this, "Please select a payment method.", Toasty.LENGTH_SHORT).show();
                } else if (nameField.getText().toString().equals("")) {
                    Toasty.warning(OrderEditActivity.this, "Please enter your name.", Toasty.LENGTH_SHORT).show();
                } else if (contactField.getText().toString().equals("") || placeField.getText().toString().equals("")) {
                    Toasty.warning(OrderEditActivity.this, "Please enter your contact details and destination.", Toasty.LENGTH_SHORT).show();
                } else if (quantityField.getText().toString().equals("") || sizeField.getText().toString().equals("")) {
                    Toasty.warning(OrderEditActivity.this, "Please enter quantity and price.", Toasty.LENGTH_SHORT).show();
                } else {
                    orderForSave.setName(nameField.getText().toString());
                    orderForSave.setContact(contactField.getText().toString());
                    orderForSave.setPlace(placeField.getText().toString());
                    orderForSave.setQty(Integer.parseInt(quantityField.getText().toString()));
                    orderForSave.setSize(Integer.parseInt(sizeField.getText().toString()));
                    orderForSave.setPrice(Double.parseDouble(priceField.getText().toString()));

                    if (itemSpinner.getSelectedItemPosition() != 0) {
                        orderForSave.setItemName(itemList.get(itemSpinner.getSelectedItemPosition()));
                    }

                    if (paymentSpinner.getSelectedItemPosition() != 0) {
                        orderForSave.setPaymentMethod(paymentList.get(paymentSpinner.getSelectedItemPosition()));
                    }

                    if (isAdd) {
                        orderDAO.add(orderForSave);

                        Toasty.success(OrderEditActivity.this, "Order added successfully.", Toasty.LENGTH_SHORT).show();

                        Intent backIntent = new Intent(OrderEditActivity.this, MainActivity.class);
                        startActivity(backIntent);
                    } else {
                        if (isEdit) {
                            boolean result = orderDAO.updateOrder(orderForSave);

                            if (result) {
                                Toasty.success(OrderEditActivity.this, "Order updated successfully.", Toasty.LENGTH_SHORT).show();
                                Intent backIntent = new Intent(OrderEditActivity.this, MainActivity.class);
                                startActivity(backIntent);
                            }

                        } else {
                            isEdit = true;
                            editButton.setText("Save");
                            deleteButton.setVisibility(View.GONE);

                            nameField.setEnabled(true);
                            contactField.setEnabled(true);
                            placeField.setEnabled(true);
                            quantityField.setEnabled(true);
                            sizeField.setEnabled(true);
                            priceField.setEnabled(true);
                            itemSpinner.setEnabled(true);
                            paymentSpinner.setEnabled(true);
                        }
                    }
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = orderDAO.deleteByID(orderForSave.getId());

                if (result) {
                    Toasty.success(OrderEditActivity.this, "Order deleted successfully.", Toasty.LENGTH_SHORT).show();
                    Intent backIntent = new Intent(OrderEditActivity.this, MainActivity.class);
                    startActivity(backIntent);
                    finish();
                }
            }
        });
    }

    private void updateUI() {
        nameField.setText(orderForSave.getName());
        contactField.setText(orderForSave.getContact());
        placeField.setText(orderForSave.getPlace());
        quantityField.setText(Integer.toString(orderForSave.getQty()));
        sizeField.setText(Integer.toString(orderForSave.getSize()));
        priceField.setText(Double.toString(orderForSave.getPrice()));
        itemSpinner.setSelection(itemList.indexOf(orderForSave.getItemName()));
        paymentSpinner.setSelection(paymentList.indexOf(orderForSave.getPaymentMethod()));
    }
}