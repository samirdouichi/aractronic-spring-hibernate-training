package com.aractron.spring.training.junit.dao;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aractron.spring.training.dao.CourseSessionDao;
import com.aractron.spring.training.dao.StudentDao;
import com.aractron.spring.training.domain.CourseSession;
import com.aractron.spring.training.domain.Student;
import com.aractron.spring.training.domain.StudentClassification;

/**
 * Test class for the StudentDao interface. The implementation being tested is the one found in the 
 * Core project: StudentDaoJDBCImpl
 * 
 * @author vanessa.ortiz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:com/aractron/spring/training/dao/impl/dao-bean-config.xml")
public class StudentDaoTest {

	private static Log log =  LogFactory.getLog((StudentDaoTest.class));
	
	@Autowired
	private CourseSessionDao courseSessionDao;
	
	@Autowired 
	private StudentDao studentDao;
	
	private Student student1;
	private Student student2;
	private CourseSession courseSession;
	private List<Student> studentList;
	

	@Before
	public void runBefore(){
		student1 = studentDao.findById(2l);
		student2 = studentDao.findById(4l);
	}
	
	/**
	 * Verifies that the register student functionality works correctly.
	 */
	@Test
	public void testRegisterStudent(){
		courseSession = courseSessionDao.findCourseSessionBySession("English 422", "SPRING");
		Assert.assertNotNull(courseSession);
		Assert.assertEquals("English 422", courseSession.getCourse().getName());
		Assert.assertEquals("SPRING", courseSession.getTerm());
		Assert.assertNotNull("Student set null", courseSession.getStudents());
				
		for(CourseSession cs : student1.getRegisteredCourses()){
			log.info("Student is registered to: " + cs.getCourse().getName() + "/" + cs.getTerm() + "/" + cs.getFaculty().getUserName());
		}
		
		courseSession.getStudents().add(student1);
		student1.getRegisteredCourses().add(courseSession);
		
		for(CourseSession cs : student1.getRegisteredCourses()){
			log.info("New rergistered courses unsaved: " + cs.getCourse().getName() + "/" + cs.getTerm() + "/" + cs.getFaculty().getUserName());
		}
		
		studentDao.registerStudent(student1, courseSession);
		
		Student localStudent = studentDao.findById(student1.getId());
		Assert.assertNotNull("Student null!",localStudent);
		
		for(CourseSession cs : student1.getRegisteredCourses()){
			log.info("Registered courses after save: " + cs.getCourse().getName() + "/" + cs.getTerm() + "/" + cs.getFaculty().getUserName());
		}
	}
	
	/**
	 * Verifies that the unregister student functionality works correctly.
	 */
	@Test
	public void testUnregisterStudent(){
		courseSession = courseSessionDao.findCourseSessionBySession("Spring 101", "FALL");
		Assert.assertNotNull(courseSession);
		Assert.assertEquals("Spring 101", courseSession.getCourse().getName());
		Assert.assertEquals("FALL", courseSession.getTerm());
		Assert.assertNotNull("ERROR - Student set is null", courseSession.getStudents());
				
		for(Student stu : courseSession.getStudents()){
			if(student2.getUserName().equals(stu.getUserName())){
				log.info("The student to unregister is registered in the course/session: " 
						+ courseSession.getCourse().getName() + "/" + courseSession.getTerm());
			}
		}
		
		student2.getRegisteredCourses().remove(courseSession);
		courseSession.getStudents().remove(student2);
		studentDao.unregisterStudent(student2, courseSession);
		
		CourseSession localSsession = courseSessionDao.findCourseSessionBySession("Spring 101", "FALL");
		Assert.assertNotNull("CourseSesson is null!", localSsession);
		
		for(Student stu : localSsession.getStudents()){
			if(!student2.getUserName().equals(stu.getUserName())){
				Assert.assertFalse(student2.getUserName().equals(stu.getUserName()));
				log.info("The current registered student is not the one unregistered. This is good.");
			}
			else{
				log.info("ERROR - The student was not unregistered");
			}
		}
	}
	
	@Test
	public void testFindAll(){
		studentList = studentDao.findAll();
		Assert.assertNotNull(studentList);
	}
	
	/**
	 * Student #4 (ninjaTurt) is registered to CourseSession #3 (English 101/Spring) which has 3 students enrolled:
	 * 		- #3: drac2010
	 * 		- #4: ninjaTurt
	 * 		- #5: monty
	 */
	@Test
	public void testFindById(){
		Student localStudent2 = studentDao.findById(4l);
		Assert.assertNotNull(localStudent2);
		Assert.assertSame(4l,localStudent2.getId());
		Assert.assertSame(student2.getUserName(), localStudent2.getUserName());
		
		Set<CourseSession> cs = localStudent2.getRegisteredCourses();
		Assert.assertNotNull(cs);
		Assert.assertFalse(cs.isEmpty());
		
		int courseSessCount = 0;
		int studentCount = 0;
		
		for(CourseSession courseSess : cs){
			if(courseSess.getCourse().getName().equals("English 101")){
				System.out.println(courseSess.getCourse().getName() + "/" + courseSess.getTerm() + " id = " + courseSess.getId());
				courseSessCount++;
				
				for(Student s : courseSess.getStudents()){
					if("drac2010".equals(s.getUserName()) || "ninjaTurt".equals(s.getUserName()) || ("monty".equals(s.getUserName()))){
						studentCount++;
						System.out.println(s.getUserName());
					}
				}
			}
		}
		Assert.assertEquals("The courseSession count should be 1",1, courseSessCount);
		Assert.assertEquals("The number of students enrolled shouldbe 3",3, studentCount);
		
	}
	
	@Test
	public void testByUsernameAndPassword(){
		
		Student localStudent = studentDao.findByUsernameAndPassword(student1.getUserName(), student1.getPassword());
		Assert.assertNotNull(localStudent);
		Assert.assertTrue(student1.getUserName().equals(localStudent.getUserName()));
		Assert.assertEquals(student1.getId(), localStudent.getId());
	}

	@Test
	public void testInsert(){
		student2 = new Student();
		student2.setClassification(StudentClassification.GRADUATE);
		student2.setEmail("myEmail@xyz.com");
		student2.setFirstName("Cobra");
		student2.setLastName("Commander");
		student2.setPassword("hissss");
		student2.setPhoneNumber("3434345");
		student2.setUserName("kingCobra");
		
		studentDao.insert(student2);
		
		Student localStudent = studentDao.findByUsernameAndPassword("kingCobra", "hissss");
		Assert.assertNotNull(localStudent);
		Assert.assertTrue(student2.getUserName().equals(localStudent.getUserName()));
	}
}
