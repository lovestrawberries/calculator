package com.seb.calculator;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;

/**
 * Created by sebastianuchwat on 09/03/2018.
 */

public class CalculationModel {

    private final Symbols symbols;
    private CalculationResult calculationResult;
    private static String currentExpression;

    /**
     * Interface transmitter to CalculatorPresenter
     */
    interface CalculationResult {
        void onExpressionChangedLocalInterfaceMethod(String result, boolean isSuccessful);
    }

    /**
     * This setter doesn't care what parameter is passed as long as it implements
     * CalculationResult interface
     * @param calculationResult
     */
    public void setCalculationResultListener (CalculationResult calculationResult) {
        this.calculationResult = calculationResult;
        currentExpression = "";
    }

    public CalculationModel() {
        this.symbols = new Symbols();
    }

    /**
     * Delete single char from currentExpression, unless empty
     * "" - invalid
     * 543 - valid
     * 45*55 - valid
     *
     */
    public void deleteChar() {
        if (currentExpression.length() > 0) {
            currentExpression = currentExpression.substring(0, currentExpression.length() -1);
            /**
             * Interface transmitter to CalculatorPresenter
             */
            calculationResult.onExpressionChangedLocalInterfaceMethod(currentExpression, true);
        } else {
            /**
             * Interface transmitter to CalculatorPresenter
             */
            calculationResult.onExpressionChangedLocalInterfaceMethod("Invalid input", false);
        }
    }

    /**
     * Delete entire expression unless empty
     * "" - invalid
     */
    public void deleteExpression() {
        if (currentExpression.length() > 0) {
            currentExpression = "";
            /**
             * Interface transmitter to CalculatorPresenter
             */
            calculationResult.onExpressionChangedLocalInterfaceMethod(currentExpression, true);
        } else {
            return;
        }
    }

    /**
     * Append number to currentExpression if valid
     * "0" & number is 0 - invalid
     * "17 digits and more" - invalid
     * @param number
     */
    public void appendNumber(String number) {
        if (currentExpression.startsWith("0") && number.equals("0")) {
            /**
             * Interface transmitter to CalculatorPresenter
             */
            calculationResult.onExpressionChangedLocalInterfaceMethod("Invalid input", false);
        } else {
            if (currentExpression.length() <= 16) {
                currentExpression += number;
                /**
                 * Interface transmitter to CalculatorPresenter
                 */
                calculationResult.onExpressionChangedLocalInterfaceMethod(currentExpression, true);
            } else {
                /**
                 * Interface transmitter to CalculatorPresenter
                 */
                calculationResult.onExpressionChangedLocalInterfaceMethod("Expression too long",false);
            }
        }
    }

    /**
     * Append operator to currentExpression if valid
     * 56 - valid
     * 56* - invalid
     * 56*2 - valid
     * "" - invalid
     * @param operator one of:
     * - "*"
     * - "/"
     * - "-"
     * - "+"
     */
    public void appendOperator(String operator) {
        if (isExpressionValid(currentExpression)) {
            currentExpression += operator;
            /**
             * Interface transmitter to CalculatorPresenter
             */
            calculationResult.onExpressionChangedLocalInterfaceMethod(currentExpression, true);
        }
    }

    public void appendDecimal() {
        if (isExpressionValid(currentExpression)) {
            currentExpression += ".";
        }
    }

    /**
     * If currentExpression passes checks, pass currentExpression to symbols,
     * then return result.
     */
    public void performEvaluation() {
        if (isExpressionValid(currentExpression)) {
            try {
                Double result = symbols.eval(currentExpression);
                currentExpression = Double.toString(result);
                /**
                 * Interface transmitter to CalculatorPresenter
                 */
                calculationResult.onExpressionChangedLocalInterfaceMethod(currentExpression, true);
            } catch (SyntaxException e) {
                /**
                 * Interface transmitter to CalculatorPresenter
                 */
                calculationResult.onExpressionChangedLocalInterfaceMethod("Invalid input", false);
                e.printStackTrace();
            }
        }
    }




    /**
     * Helper function to validate expression against the following checks
     * @param expression
     * @return
     */
    public boolean isExpressionValid(String expression) {
        if (expression.endsWith("*") ||
                expression.endsWith("/") ||
                expression.endsWith("-") ||
                expression.endsWith("+")) {
            calculationResult.onExpressionChangedLocalInterfaceMethod("Invalid expression", false);
            return false;
        } else if (expression.equals("")) {
            calculationResult.onExpressionChangedLocalInterfaceMethod("Empty expression", false);
            return false;
        } else if (expression.length() > 16) {
            calculationResult.onExpressionChangedLocalInterfaceMethod("Expression too long", false);
            return false;
        } else {
            return true;
        }
    }

}
