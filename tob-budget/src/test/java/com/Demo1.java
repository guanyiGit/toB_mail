package com;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Demo1 {
	public static void main(String[] args) {
		 double f = 111231.5585;
		double parseDouble = Double.parseDouble(String.format("%.2f", f)) ;
		System.out.println(parseDouble);
	}
}
