package restws;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import restws.AppUser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-09T12:13:02")
@StaticMetamodel(Credential.class)
public class Credential_ { 

    public static volatile SingularAttribute<Credential, Date> signupdate;
    public static volatile SingularAttribute<Credential, String> passwordhash;
    public static volatile SingularAttribute<Credential, AppUser> userid;
    public static volatile SingularAttribute<Credential, String> username;

}