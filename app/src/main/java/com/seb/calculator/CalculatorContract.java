package com.seb.calculator;

/**
 * Created by sebastianuchwat on 06/03/2018.
 */

public interface CalculatorContract {

    /**
     * Channel CalculatorPresenter -> DisplayFragment
     */
    interface PublishToView {

        void showResultInterfaceMethod(String result);

        void showErrorInterfaceMethod(String message);
    }


    /**
     * Channel DisplayFragment -> CalculatorPresenter
     */
    interface ForwardDisplayInteractionToPresenter {

        void onDeleteClickInterfaceMethod();

        void onClearClickInterfaceMethod();

    }

    /**
     * Channel InputFragment -> CalculatorPresenter
     */
    interface ForwardInputInteractionToPresenter {

        void onNumberClickInterfaceMethod(int number);

        void onDecimalClickInterfaceMethod();

        void onEvaluateClickInterfaceMethod();

        void onOperatorClickInterfaceMethod(String operator);

    }

}
