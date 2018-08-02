package de.tobias.jpa;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-04-06T09:47:58.930-0300")
@StaticMetamodel(Measurement.class)
public class Measurement_ {
	public static volatile SingularAttribute<Measurement, Long> id;
	public static volatile SingularAttribute<Measurement, String> unit;
	public static volatile SingularAttribute<Measurement, Date> createdAt;
	public static volatile SingularAttribute<Measurement, Date> updatedAt;
	public static volatile SingularAttribute<Measurement, Double> value;
	public static volatile SingularAttribute<Measurement, Sensor> sensor;
}
