package kz.hts.ce.util;

import java.math.BigDecimal;

public class CalculateHelper {

    public static BigDecimal calculateCost(long itemQuantity, BigDecimal itemPrice) {
        BigDecimal totalCost = BigDecimal.ZERO;
        BigDecimal itemCost = itemPrice.multiply(new BigDecimal(itemQuantity));
        totalCost = totalCost.add(itemCost);
        return totalCost;
    }
}
