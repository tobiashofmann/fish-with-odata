package de.tobias.jpa;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-04-06T13:07:43.120-0300")
@StaticMetamodel(Sensor.class)
public class Sensor_ {
	public static volatile SingularAttribute<Sensor, Long> id;
	public static volatile SingularAttribute<Sensor, String> device;
	public static volatile SingularAttribute<Sensor, String> type;
	public static volatile SingularAttribute<Sensor, String> description;
	public static volatile ListAttribute<Sensor, Measurement> employees;
}
