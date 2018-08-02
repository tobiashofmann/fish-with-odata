/**
 * 
 */
package de.tobias.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Class representing the sensor entity.
 * A sensor can have many measurements assigned to it.
 * 
 * @author Tobias Hofmann
 * @since 1.0.0
 *
 */
@Entity(name = "Sensor")
@NamedQueries({ @NamedQuery(name = "GetListOfSensors", query = "select s from Sensor s") })
public class Sensor implements Serializable {

	/**
	 * serialization
	 */
	private static final long serialVersionUID = 809494036732697886L;

	public Sensor() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "ID")
	private long id;
	private String device;
	private String type;
	private String description;

	@OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL)
	private List<Measurement> employees = new ArrayList<Measurement>();


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String param) {
		this.device = param;
	}

	public String getType() {
		return type;
	}

	public void setType(String param) {
		this.type = param;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String param) {
		this.description = param;
	}

	/**
	 * @return the employees
	 */
	public List<Measurement> getEmployees() {
		return employees;
	}

	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(List<Measurement> employees) {
		this.employees = employees;
	}


}