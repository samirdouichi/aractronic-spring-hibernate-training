INSERT INTO COURSE values (1, 'Spring 101', 'The best class ever', 'Computer Science', 2.5, 'FRESHMAN');
INSERT INTO COURSE values (2, 'Spring 321', 'Advanced Spring', 'Computer Science', 3.0, 'JUNIOR');
INSERT INTO COURSE values (3, 'English 101', 'Learns ta spoke n rite', 'English', 2.0, 'FRESHMAN');
INSERT INTO COURSE values (4, 'English 422', 'The advancement of the knowledge in the English language', 'English', 3.5, 'SENIOR');
INSERT INTO COURSE values (5, 'Calculus 245', '3D Graphs', 'Math', 3.5, 'SOPHOMORE');

-- Using Faculty 0 to represent TBD instructors
INSERT INTO FACULTY values (0, 'TBD', null, 'TBD', '', '', '', 'All');
INSERT INTO FACULTY values (1, 'teach1', 'pass1', 'Edna', 'Crabapple', 'test@test.com', '5551234567', 'English');
INSERT INTO FACULTY values (2, 'CSProf', 'pass2', 'Spring', 'Source', 'springy@test.com', '5557654321', 'Computer Science');
INSERT INTO FACULTY values (3, 'onePlusOne', 'maths', 'Ms', 'Swan', 'mathTeach@test.com', '5551234567', 'Math');
INSERT INTO FACULTY values (4, 'teach2', 'pass2', 'Iseeq', 'Ewe', 'speech@test.com', '5657686', 'English');

INSERT INTO STUDENT(STUDENT_ID, USERNAME, Password, FirstName, LastName, Email, PhoneNumber, CLASSIFICATION) 
	values(1, 'testStudent', 'password', 'Homer', 'Simpson', 'test1.03.2009@gmail.com', '5551234567', 'SOPHOMORE');
INSERT INTO STUDENT(STUDENT_ID, USERNAME, Password, FirstName, LastName, Email, PhoneNumber, CLASSIFICATION) 
	values(2, 'blueHair', 'homey', 'Marge', 'Simpson', 'bluehair@xyz.com', '5551234567', 'JUNIOR');
INSERT INTO STUDENT(STUDENT_ID, USERNAME, Password, FirstName, LastName, Email, PhoneNumber, CLASSIFICATION) 
	values(3, 'drac2010', 'cereal', 'Count', 'Chocula', 'choco@xyt.com', '6666789087', 'FRESHMAN');
INSERT INTO STUDENT(STUDENT_ID, USERNAME, Password, FirstName, LastName, Email, PhoneNumber, CLASSIFICATION) 
	values(4, 'ninjaTurt', 'pizza', 'Donatello', 'Ninjaturtle', 'purple@dfd.com', '5551234567',	'JUNIOR');
INSERT INTO STUDENT(STUDENT_ID, USERNAME, Password, FirstName, LastName, Email, PhoneNumber, CLASSIFICATION) 
	values(5, 'monty', 'power', 'Montgomery', 'Burns', 'montyMoneyBags@dfd.com', '5551234567', 'GRADUATE');
INSERT INTO STUDENT(STUDENT_ID, USERNAME, Password, FirstName, LastName, Email, PhoneNumber, CLASSIFICATION) 
	values(6, 'test', 'test', 'Spring', 'Student', 'test@accenture.com', '2225551212', 'SENIOR');

	
INSERT INTO COURSESESSION (COURSE_SESSION_ID, COURSE_ID, FACULTY_ID, TERM) values(1, 1, 2, 'FALL');
INSERT INTO COURSESESSION (COURSE_SESSION_ID, COURSE_ID, FACULTY_ID, TERM) values(2, 2, 3, 'SPRING');
INSERT INTO COURSESESSION (COURSE_SESSION_ID, COURSE_ID, FACULTY_ID, TERM) values(3, 3, 1, 'SPRING');
INSERT INTO COURSESESSION (COURSE_SESSION_ID, COURSE_ID, FACULTY_ID, TERM) values(4, 4, 4, 'SPRING');
INSERT INTO COURSESESSION (COURSE_SESSION_ID, COURSE_ID, FACULTY_ID, TERM) values(5, 5, 3, 'SPRING');	
INSERT INTO COURSESESSION (COURSE_SESSION_ID, COURSE_ID, FACULTY_ID, TERM) values(6, 5, 3, 'FALL');
INSERT INTO COURSESESSION (COURSE_SESSION_ID, COURSE_ID, FACULTY_ID, TERM) values(7, 1, 2, 'FALL');
	
INSERT INTO CourseSession_Enrollment(COURSE_SESSION_ID, STUDENT_ID) values (1, 6);
INSERT INTO CourseSession_Enrollment(COURSE_SESSION_ID, STUDENT_ID) values (4, 5);
INSERT INTO CourseSession_Enrollment(COURSE_SESSION_ID, STUDENT_ID) values (3, 4);
INSERT INTO CourseSession_Enrollment(COURSE_SESSION_ID, STUDENT_ID) values (1, 3);
INSERT INTO CourseSession_Enrollment(COURSE_SESSION_ID, STUDENT_ID) values (5, 2);
INSERT INTO CourseSession_Enrollment(COURSE_SESSION_ID, STUDENT_ID) values (6, 1);
INSERT INTO CourseSession_Enrollment(COURSE_SESSION_ID, STUDENT_ID) values (7, 1);
INSERT INTO CourseSession_Enrollment(COURSE_SESSION_ID, STUDENT_ID) values (2, 2);
INSERT INTO CourseSession_Enrollment(COURSE_SESSION_ID, STUDENT_ID) values (3, 3);
INSERT INTO CourseSession_Enrollment(COURSE_SESSION_ID, STUDENT_ID) values (3, 4);