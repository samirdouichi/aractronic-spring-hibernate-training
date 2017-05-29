package com.aractron.spring.training.labs.transactions;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
// We are explicitly declaring the context configurations in this example
@ContextConfiguration(locations = {
		"classpath:/com/aractron/spring/training/util/example-db-config.xml",
		"classpath:/com/aractron/spring/training/labs/transactions/XmlTransactionalTests-context.xml", })
public class XmlTransactionalTests extends AnnotationTransactionalTest {

}
