package restws;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import restws.AppUser;
import restws.Food;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-09T12:13:02")
@StaticMetamodel(Consumption.class)
public class Consumption_ { 

    public static volatile SingularAttribute<Consumption, Date> date;
    public static volatile SingularAttribute<Consumption, Food> fid;
    public static volatile SingularAttribute<Consumption, Integer> quantity;
    public static volatile SingularAttribute<Consumption, Integer> consumptionid;
    public static volatile SingularAttribute<Consumption, AppUser> userid;

}