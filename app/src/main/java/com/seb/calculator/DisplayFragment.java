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


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment implements CalculatorContract.PublishToView {

    /**
     * Interface transmitter to CalculatorPresenter
     */
    private CalculatorContract.ForwardDisplayInteractionToPresenter forwardDisplayInteractionToPresenter;

    public void setPresenter(CalculatorContract.ForwardDisplayInteractionToPresenter forwardInteraction) {
        this.forwardDisplayInteractionToPresenter = forwardInteraction;
    }

    @BindView(R.id.tv_display)
    TextView display;

    @OnClick(R.id.img_btn_delete)
    public void onDeleteClick() {
        /**
         * Interface transmitter to CalculatorPresenter
         */
        forwardDisplayInteractionToPresenter.onDeleteClickInterfaceMethod();
    }

    @OnLongClick(R.id.img_btn_delete)
    public boolean onClearClick() {
        /**
         * Interface transmitter to CalculatorPresenter
         */
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

    /**
     * Interface receiver from CalculatorPresenter
     * @param result
     */
    @Override
    public void showResultInterfaceMethod(String result) {
        display.setText(result);
    }

    @Override
    public void showErrorInterfaceMethod(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
