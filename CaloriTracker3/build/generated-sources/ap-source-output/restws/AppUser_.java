package restws;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import restws.Consumption;
import restws.Credential;
import restws.Report;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-09T12:13:02")
@StaticMetamodel(AppUser.class)
public class AppUser_ { 

    public static volatile SingularAttribute<AppUser, String> address;
    public static volatile SingularAttribute<AppUser, String> gender;
    public static volatile CollectionAttribute<AppUser, Consumption> consumptionCollection;
    public static volatile SingularAttribute<AppUser, Integer> stepspermile;
    public static volatile SingularAttribute<AppUser, Integer> postcode;
    public static volatile SingularAttribute<AppUser, BigDecimal> weight;
    public static volatile SingularAttribute<AppUser, String> surename;
    public static volatile SingularAttribute<AppUser, Integer> userid;
    public static volatile CollectionAttribute<AppUser, Report> reportCollection;
    public static volatile SingularAttribute<AppUser, Date> dob;
    public static volatile CollectionAttribute<AppUser, Credential> credentialCollection;
    public static volatile SingularAttribute<AppUser, String> name;
    public static volatile SingularAttribute<AppUser, Integer> activitylevel;
    public static volatile SingularAttribute<AppUser, String> email;
    public static volatile SingularAttribute<AppUser, BigDecimal> height;

}