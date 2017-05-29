package com.aractron.spring.training.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Base domain entity model. The 'Domain Supertype' pattern is used here to
 * provide a base set of state and behavior for all entities in the system.
 * 
 * @author aaron.levensailor 
 */
public abstract class BaseEntity implements Serializable {
	
	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -2505251167352871070L;

	/**
	 * Entity Id, unique identifier
	 */
	private long id;

	/**
	 * Getter for the Entity's Id.
	 * 
	 * @return Entity's Id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for the Entity's Id.
	 * 
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		// Default hashCode.  Should be overridden for HashSet usage
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object that) {
		return this == that || this.hashCode() == that.hashCode();
	}
}
