package com.usearch.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.usearch.R;

import org.w3c.dom.Text;

public class DialogProductResult extends DialogFragment {

    private TextView txtName;
    private TextView txtPrice;
    private TextView txtAddress;
    private Button btnExit;

    public static DialogFragment newInstance(String name, String price, String address){
        DialogFragment dialogFragment = new DialogProductResult();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("price", price);
        bundle.putString("address", address);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.dialog_product_result, container);

        txtName = (TextView) v.findViewById(R.id.txtName);
        txtPrice = (TextView) v.findViewById(R.id.txtPrice);
        txtAddress = (TextView) v.findViewById(R.id.txtAddress);

        Bundle bundle = getArguments();

        txtName.setText(bundle.getString("name"));
        txtPrice.setText(bundle.getString("price"));
        txtAddress.setText(bundle.getString("address"));

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        getDialog().setCancelable(false);

        return v;
    }
}
