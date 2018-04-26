package com.seb.calculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends Fragment {

    /**
     * Interface transmitter to CalculatorPresenter
     */
    private CalculatorContract.ForwardInputInteractionToPresenter forwardInputInteraction;

    public void setPresenter (CalculatorContract.ForwardInputInteractionToPresenter forwardInputInteraction) {
        this.forwardInputInteraction = forwardInputInteraction;
    }

    @OnClick({R.id.btn_inputfrag_no_one, R.id.btn_inputfrag_no_two, R.id.btn_inputfrag_no_three,
            R.id.btn_inputfrag_no_four, R.id.btn_inputfrag_no_five, R.id.btn_inputfrag_no_six,
            R.id.btn_inputfrag_no_seven, R.id.btn_inputfrag_no_eight, R.id.btn_inputfrag_no_nine,
            R.id.btn_inputfrag_no_zero})
    public void onNumberClick(Button view) {
        /**
         * Interface transmitter to Presenter
         */
        forwardInputInteraction.onNumberClickInterfaceMethod(Integer.parseInt(view.getText().toString()));
    }

    @OnClick(R.id.btn_inputfrag_op_evaluate)
    public void onEvaluateClick(Button button) {
        forwardInputInteraction.onEvaluateClickInterfaceMethod();
    }

    @OnClick(R.id.btn_inputfrag_dec)
    public void onDecimalClick(Button button) {
        forwardInputInteraction.onDecimalClickInterfaceMethod();
    }

    public InputFragment() {
        // Required empty public constructor
    }

    public static InputFragment newInstance() {
        return  new InputFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

}
