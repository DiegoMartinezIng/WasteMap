package com.example.wastecontrol.fragmen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.wastecontrol.R;

public class TipoResiduoFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Spinner spnrTipoResiduo;

    private String selectedItem;

    public TipoResiduoFragment() {}

    public static TipoResiduoFragment newInstance() {
        TipoResiduoFragment fragment = new TipoResiduoFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tipo_residuo, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.spnrTipoResiduo = getActivity().findViewById(R.id.spnrTipoResiduo);
        spnrTipoResiduo.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),R.array.tipo_residuo,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrTipoResiduo.setAdapter(adapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        this.selectedItem = (String) adapterView.getSelectedItem();
        System.out.println(this.selectedItem);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        this.selectedItem = "N/A";
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public String getTipoResiduoSeleccionado(){
        return this.selectedItem;
    }
}
