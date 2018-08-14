/**
 * 
 */
package de.tobias.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class representing the measurement entity.
 * A measurement can have at most one senser assigned to it.
 * 
 * @author Tobias Hofmann
 * @since 1.0.0
 *
 */
@Entity(name = "Measurement")
@NamedQueries({ @NamedQuery(name = "AllMeasurements", query = "select m from Measurement m") })
public class Measurement implements Serializable {

	/**
	 * serial
	 */
	private static final long serialVersionUID = -1563538389979670830L;

	public Measurement() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "ID")
	private long id;
	
	private String unit;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = true, updatable = false)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = true)
	private Date updatedAt;

	private Double value;

	@ManyToOne
	@JoinColumn(name = "SID", referencedColumnName = "ID")
	private Sensor sensor;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return the value
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(Double value) {
		this.value = value;
	}

	/**
	 * @return the sensor
	 */
	public Sensor getSensor() {
		return sensor;
	}

	/**
	 * @param sensor
	 *            the sensor to set
	 */
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt
	 *            the updatedAt to set
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@PrePersist
	void createdAt() {
		this.createdAt = this.updatedAt = new Date();
	}

	@PreUpdate
	void updatedAt() {
		this.updatedAt = new Date();
	}

}