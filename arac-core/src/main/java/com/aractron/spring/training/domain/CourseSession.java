package com.aractron.spring.training.domain;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Entity representing a particular session of a {@link Course}.
 */
public class CourseSession extends BaseEntity implements
		Comparable<CourseSession> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4327322843770133418L;

	/**
	 * 
	 */
	private Course course = new Course();

	/**
	 * 
	 */
	private Faculty faculty;

	/**
	 * 
	 */
	private Set<Student> students = new HashSet<Student>();

	/**
	 * 
	 */
	private String term;

	/**
	 * {@inheritDoc}
	 */
	public int compareTo(CourseSession that) {
		int sig = this.getCourse().compareTo(that.getCourse());
		if (sig == 0) {
			sig = this.getTerm().compareTo(that.getTerm());
			if (sig == 0) {
				sig = this.getId().compareTo(that.getId());
			}
		}
		return sig;
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).append(getCourse())
				.append(getTerm()).toHashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("CourseSession [");
		sb.append("id=").append(getId());
		sb.append(", courseName=").append(course.getName());
		sb.append(", term=").append(getTerm());
		sb.append(", faculty=").append(
				getFaculty() == null ? "" : getFaculty().getUserName());
		sb.append(", enrollment=").append(getStudents().size());
		return sb.append("]").toString();
	}

	/**
	 * Retrieve the {@link Course} that this session pertains to.
	 * 
	 * @return null by default
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * Retrieve the {@link Faculty} who will be delivering this session.
	 * 
	 * @return empty collection by default
	 */
	public Faculty getFaculty() {
		return faculty;
	}

	/**
	 * Retrieve the {@link Student}s enrolled in this session.
	 * 
	 * @return empty collection by default
	 */
	public Set<Student> getStudents() {
		return students;
	}

	/**
	 * @return the term
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * 
	 * @param course
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * 
	 * @param faculty
	 */
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	/**
	 * 
	 * @param students
	 */
	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	/**
	 * @param term
	 *            the term to set
	 */
	public void setTerm(String term) {
		this.term = term;
	}
}
