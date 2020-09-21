/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.math.BigDecimal;


/**
 *
 * @author sarah
 */
public class FoodConsum {
    
     BigDecimal calorie;
    
public BigDecimal getCalorie() {
return calorie;
}
public void setCalorie(BigDecimal calorie) {
this.calorie = calorie.add(calorie);
}


public FoodConsum(BigDecimal calorie) {
this.calorie = calorie;

}
}
