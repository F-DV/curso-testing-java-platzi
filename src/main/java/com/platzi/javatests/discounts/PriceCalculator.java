package com.platzi.javatests.discounts;

import java.util.ArrayList;
import java.util.List;

public class PriceCalculator {
    List<Double> prices = new ArrayList<>();
    double discount;

    public double getTotal(){
        double result = 0.0;
        if (discount >0.0){
            for(Double price: prices){
                result += price;
            }
            return result - (result * discount/100) ;
        }else{
            for(Double price: prices){
                result += price;
            }
        }
        return result;
    }

    public void addPrice(double price) {
        prices.add(price);
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
