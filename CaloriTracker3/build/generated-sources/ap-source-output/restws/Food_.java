package restws;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import restws.Consumption;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-09T12:13:02")
@StaticMetamodel(Food.class)
public class Food_ { 

    public static volatile SingularAttribute<Food, Integer> fid;
    public static volatile SingularAttribute<Food, String> fname;
    public static volatile SingularAttribute<Food, String> fcategory;
    public static volatile CollectionAttribute<Food, Consumption> consumptionCollection;
    public static volatile SingularAttribute<Food, BigDecimal> servingamount;
    public static volatile SingularAttribute<Food, BigDecimal> calorie;
    public static volatile SingularAttribute<Food, BigDecimal> fat;
    public static volatile SingularAttribute<Food, String> servingunit;

}