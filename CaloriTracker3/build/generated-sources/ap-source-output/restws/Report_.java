package restws;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import restws.AppUser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-09T12:13:02")
@StaticMetamodel(Report.class)
public class Report_ { 

    public static volatile SingularAttribute<Report, Date> date;
    public static volatile SingularAttribute<Report, Integer> reportid;
    public static volatile SingularAttribute<Report, BigDecimal> totalcaloriesconsumed;
    public static volatile SingularAttribute<Report, BigDecimal> totalcaloriegoal;
    public static volatile SingularAttribute<Report, BigDecimal> totalcaloriesburned;
    public static volatile SingularAttribute<Report, Integer> totalstepstaken;
    public static volatile SingularAttribute<Report, AppUser> userid;

}