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
public class Goals {


BigDecimal totalcaloriegoal;


public BigDecimal getTotalcaloriegoal() {
return totalcaloriegoal;
}
public void setTotalcaloriegoal(BigDecimal totalcaloriegoal) {
this.totalcaloriegoal = totalcaloriegoal;
}



public Goals() {
}

public Goals( BigDecimal totalcaloriegoal) {

this.totalcaloriegoal=totalcaloriegoal;
}

}