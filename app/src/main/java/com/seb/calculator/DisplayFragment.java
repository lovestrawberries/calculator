package com.seb.calculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;


public class DisplayFragment extends Fragment implements CalculatorContract.PublishToView {

    private CalculatorContract.ForwardDisplayInteractionToPresenter forwardDisplayInteractionToPresenter;

    public void setPresenter(CalculatorContract.ForwardDisplayInteractionToPresenter forwardInteraction) {
        this.forwardDisplayInteractionToPresenter = forwardInteraction;
    }

    @BindView(R.id.tv_display)
    TextView display;

    @OnClick(R.id.img_btn_delete)
    public void onDeleteClick() {
        forwardDisplayInteractionToPresenter.onDeleteClickInterfaceMethod();
    }

    @OnLongClick(R.id.img_btn_delete)
    public boolean onClearClick() {
        forwardDisplayInteractionToPresenter.onClearClickInterfaceMethod();

        return true;
    }

    public static DisplayFragment newInstance() {
        return new DisplayFragment();
    }


    public DisplayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void showResult(String result) {
        display.setText(result);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
