package com.seb.calculator;

/**
 * Created by sebastianuchwat on 06/03/2018.
 */

public class CalculatorPresenter implements CalculatorContract.ForwardInputInteractionToPresenter,
    CalculatorContract.ForwardDisplayInteractionToPresenter, CalculationModel.CalculationResult {

    private CalculatorContract.PublishToView publishResult;

    private CalculationModel calculationModel;


    /**
     *
     * @param publishResult
     */
    public CalculatorPresenter (CalculatorContract.PublishToView publishResult) {
        this.publishResult = publishResult;
        this.calculationModel = new CalculationModel();

        /**
         * this, because CalculationPresenter implements CalculationListener
         */
        calculationModel.setCalculationResultListener(this);
    }


    /**
     * Interface receiver from DisplayFragment
     */
    @Override
    public void onDeleteClickInterfaceMethod() {
        calculationModel.deleteChar();
    }

    /**
     * Interface receiver from DisplayFragment
     */
    @Override
    public void onClearClickInterfaceMethod() {
        calculationModel.deleteExpression();
    }

    /**
     * Interface receiver from InputFragment
     * @param number
     */
    @Override
    public void onNumberClickInterfaceMethod(int number) {
        calculationModel.appendNumber(Integer.toString(number));
    }

    /**
     * Interface receiver from InputFragment
     */
    @Override
    public void onDecimalClickInterfaceMethod() {
        calculationModel.appendDecimal();
    }

    /**
     * Interface receiver from InputFragment
     */
    @Override
    public void onEvaluateClickInterfaceMethod() {
        calculationModel.performEvaluation();
    }

    /**
     * Interface receiver from InputFragment
     */
    @Override
    public void onOperatorClickInterfaceMethod(String operator) {
        calculationModel.appendOperator(operator);
    }

    /**
     * Interface receiver from CalculationModel
     * @param result
     * @param isSuccessful
     */
    @Override
    public void onExpressionChangedLocalInterfaceMethod(String result, boolean isSuccessful) {
        if (isSuccessful) {

            /**
             * Interface transmitter to DisplayFragment
             */
            publishResult.showResultInterfaceMethod(result);
        } else {
            /**
             * Interface transmitter to DisplayFragment
             */
            publishResult.showErrorInterfaceMethod(result);
        }
    }
}
