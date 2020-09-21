/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
/**
 *
 * @author sarah
 */
public class CalorisDurarion {

BigDecimal totalcaloriesconsumed;
BigDecimal totalcaloriesburned;
int totalstepstaken;
BigDecimal totalcaloriegoal;

public BigDecimal getTotalcaloriesconsumed() {
return totalcaloriesconsumed;
}
public void setTotalcaloriesconsumed(BigDecimal totalcaloriesconsumed) {
this.totalcaloriesconsumed = totalcaloriesconsumed;
}

public BigDecimal getTotalcaloriegoal() {
return totalcaloriegoal;
}
public void setTotalcaloriegoal(BigDecimal totalcaloriegoal) {
this.totalcaloriegoal = totalcaloriegoal;
}
public BigDecimal getTotalcaloriesburned() {
return totalcaloriesburned;
}
public void setTotalcaloriesburned(BigDecimal totalcaloriesburned) {
this.totalcaloriesburned = totalcaloriesburned;
}

public int getTotalstepstaken() {
return totalstepstaken;
}
public void setTotalstepstaken(int totalstepstaken) {
this.totalstepstaken = totalstepstaken;
}
public CalorisDurarion() {
}

public CalorisDurarion(BigDecimal totalcaloriesconsumed, BigDecimal totalcaloriesburned, int totalstepstaken, BigDecimal totalcaloriegoal) {
this.totalcaloriesconsumed = totalcaloriesconsumed;
this.totalcaloriesburned = totalcaloriesburned;
this.totalstepstaken = totalstepstaken;
this.totalcaloriegoal=totalcaloriegoal;
}

}