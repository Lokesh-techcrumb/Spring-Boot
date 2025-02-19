package com.example.hibernateInheritance;

import com.example.hibernateInheritance.entities.Check;
import com.example.hibernateInheritance.entities.CreditCard;
import com.example.hibernateInheritance.entities.Payment;
import com.example.hibernateInheritance.repos.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HibernateInheritanceApplicationTests {

	@Autowired
	private PaymentRepository paymentRepository;

	@Test
	public void createCredit(){
		CreditCard cc = new CreditCard();
		cc.setId(1);
		cc.setAmount(1000d);
		cc.setCardnumber("123456789");
		paymentRepository.save(cc);
	}

	@Test
	public void createCheck(){
		Check ch = new Check();
		ch.setId(2);
		ch.setAmount(1000d);
		ch.setChecknumber("123456789");
		paymentRepository.save(ch);
	}

}
