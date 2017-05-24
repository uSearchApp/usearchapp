package com.usearch.dialogs;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.usearch.R;


public class DialogProductConfirm extends DialogFragment {

    private TextInputEditText edtName;
    private TextInputEditText edtPrice;
    private Button btnSave;
    private Button btnAgain;

    private String txtName;
    private String txtPrice;

    public static DialogProductConfirm newInstance(String name, String price){
        DialogProductConfirm fragment = new DialogProductConfirm();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("price", price);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);

        Bundle args = getArguments();

        txtName = args.getString("name");
        txtPrice = args.getString("price");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.dialog_product_confirm, container, false);

        edtName = (TextInputEditText) v.findViewById(R.id.edtName);
        edtPrice = (TextInputEditText) v.findViewById(R.id.edtPrice);
        btnSave = (Button) v.findViewById(R.id.btnSave);
        btnAgain = (Button) v.findViewById(R.id.btnAgain);

        edtName.setText(txtName);
        edtPrice.setText(txtPrice);

        getDialog().setTitle("Confirmar produto");

        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return v;
    }

}
