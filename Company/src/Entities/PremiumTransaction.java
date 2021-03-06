package Entities;

import java.time.LocalDateTime;
import java.io.*;
import java.util.*;
public class PremiumTransaction extends Transaction implements Serializable {
    private static double PremiumTransactionsProfit = 0.0;
    public PremiumTransaction(double es, String ec, String xc, int day, double conversionRate, double premCommission, double currParity, LocalDateTime local,int id) {
        super(es, ec, xc, day, local, id);
        double x = Transaction.roundAvoid(conversionRate, 4);
        this.TransactionProfit = Transaction.roundAvoid(premCommission * es * x, 4); //profit obtained in exit currency
        this.ExitSum = Transaction.roundAvoid(es * x - this.TransactionProfit, 4); //exit sum in exit currency
        if (xc != "dolars") {
            this.TransactionProfit = Transaction.roundAvoid(currParity * this.TransactionProfit, 4);
        }
        PremiumTransactionsProfit += this.TransactionProfit;
    }
    double getPremiumTransactionsProfit() {
        return PremiumTransactionsProfit;
    }
    void setPremiumTransactionsProfit(double val) {
        PremiumTransactionsProfit = val;
    }
    public String toString() {
        return super.toString();
    }
}