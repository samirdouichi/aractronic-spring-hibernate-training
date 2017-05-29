package com.aractron.spring.training.junit.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

/**
 * Test class for the CourseSessionDao interface. The implementation being tested is the one found in the 
 * Core project: CourseSessionDaoJDBCImpl
 * 
 * @author vanessa.ortiz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:com/aractron/spring/training/dao/impl/dao-bean-config.xml")
public class CourseSessionDaoTest {
	
	@Autowired
	private CourseSessionDao courseSessionDao;
	
	@Autowired 
	private StudentDao studentDao;
	
	private Student student1;
	private Student student2;
	private CourseSession courseSession;
	private Set<Student> studentSet;
	
	@Before
	public void runBefore(){
		student1 = studentDao.findById(2l);
		student2 = studentDao.findById(4l);
		
		studentSet = new HashSet<Student>();
		studentSet.add(student1);
		studentSet.add(student2);
	}

	@Test
	public void testFindCourseSessionById(){
		
		courseSession = courseSessionDao.findCourseSessionById(2l);
		
		Assert.assertNotNull(courseSession);
		Assert.assertEquals(2L, courseSession.getCourse().getId().longValue());
	}
	
	@Test
	public void testFindAllCourseSessions(){
		
		int studentCount = 0;
		
		List<CourseSession> csList = courseSessionDao.findAllCourseSessions();
		Assert.assertNotNull(csList);
		Assert.assertFalse(csList.isEmpty());
		
		for(CourseSession cs : csList){
			studentCount += cs.getStudents().size();
		}
		Assert.assertEquals("The total number of students enrolled in all of the CourseSessions should be 10", 10, studentCount);
	}
	
	
	/**
	 * This test method verifies that the CourseSession object are being returned correctly
	 * by using the Course name and CourseSession term to search. 
	 * 
	 * The CourseSession "Spring 101" & "FALL" will return:
	 * student_id = 3(drac2010) who is registered to 
	 * 		cs_id = 1 which has students 3(drac2010) & 6(test)
	 * 		cd_id = 3 which has students 3(drac2010), 4(ninjaTurt), & 5(monty)
	 * student_id = 6(test) who is registered to
	 * 		cs_id = 1 which has students 3(drac2010) & 6(test)
	 */
	@Test
	public void findCourseSesisonbySession(){
		String uName;
		int studentNum = 0;
		int courseSessNum = 0;
		
		courseSession = courseSessionDao.findCourseSessionBySession("Spring 101", "FALL");
		Assert.assertNotNull(courseSession);
		Assert.assertEquals("Spring 101", courseSession.getCourse().getName());
		
		for(Student s : courseSession.getStudents()){
			uName = s.getUserName();
			System.out.println(uName + " ID= " + s.getId());
			if(uName.equals("drac2010")){ 
				studentNum++;
				
				for(CourseSession cs : s.getRegisteredCourses()){
					System.out.println(cs.getId() + " Course name= " + cs.getCourse().getName());
					if(cs.getId() == 1L || cs.getId() == 3L){
						courseSessNum++;
					}
				}
			}
			else{
				if(uName.equals("test")){
					studentNum++;
					
					for(CourseSession cs : s.getRegisteredCourses()){
						System.out.println(cs.getId() + " Course name= " + cs.getCourse().getName());
						if(cs.getId() == 1L){
							courseSessNum++;
						}
					}
				}
			}
		}
		Assert.assertEquals(2, studentNum);
		Assert.assertEquals(3, courseSessNum);
	}
	
}
