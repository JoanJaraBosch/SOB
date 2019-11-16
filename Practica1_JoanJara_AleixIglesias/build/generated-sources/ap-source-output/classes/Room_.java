package classes;

import classes.Renter;
import classes.Tenant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-16T19:15:15")
@StaticMetamodel(Room.class)
public class Room_ { 

    public static volatile SingularAttribute<Room, Integer> zip;
    public static volatile SingularAttribute<Room, Boolean> furnished;
    public static volatile SingularAttribute<Room, String> city;
    public static volatile SingularAttribute<Room, Float> price;
    public static volatile SingularAttribute<Room, String> description;
    public static volatile SingularAttribute<Room, Boolean> indoor;
    public static volatile SingularAttribute<Room, Boolean> simple;
    public static volatile SingularAttribute<Room, String> adreça;
    public static volatile SingularAttribute<Room, Renter> renter;
    public static volatile SingularAttribute<Room, Integer> roomID;
    public static volatile SingularAttribute<Room, Tenant> tenant;

}