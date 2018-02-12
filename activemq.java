package com.nys.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

//import statement

import java.io.IOException;

import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.activemq.ActiveMQConnectionFactory;

//import close


import org.apache.activemq.Message;

import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.dbcp.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microtripit.mandrillapp.lutung.controller.MandrillMessagesApi;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.MergeVar;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.MessageContent;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;
import com.mysql.jdbc.Connection;
import com.nys.message.dao.NSCClientDAO;
import com.nys.message.data.NSCClient;

@RestController
@CrossOrigin
public class MailChimpController {

	 @Autowired
	 private NSCClientDAO clientdataDao; 
	
	    @RequestMapping(value = "/clientdata/getalldata")
	    @CrossOrigin
	    public @ResponseBody List<NSCClient> getallclientdata(){
	    	List<NSCClient> lst = clientdataDao.getalldata("select * from clients");
	        return lst;
	    }
	
	 @RequestMapping(value="/sendTemplate")
	 @CrossOrigin
	 public @ResponseBody String sendTemplate(){
		 try{
			 MandrillMessagesApi api = new MandrillMessagesApi("i6K_85fcdHNZDxP9bxFa3g");
			  Map<String, String> templateContent = new HashMap<String, String>();
			
			List<MessageContent> attachments = null;
			
			MandrillMessage m=new MandrillMessage();
			m.setAttachments(attachments);
			String email="sathish@nayamsoft.com";
			m.setFromEmail(email);
			String name="sathish";
			m.setFromName(name);
			String html="<p>Example HTML content</p>";
			m.setHtml(html);
			List<MergeVar> globalMergeVars = new ArrayList<MergeVar>() ;
			
			
			globalMergeVars.add(new MergeVar("field9", "M.O.B.A 1976 CENTENARY YEAR GROUP"));
			globalMergeVars.add(new MergeVar("field24", "credited "));
			globalMergeVars.add(new MergeVar("field12", "140*******162"));
			globalMergeVars.add(new MergeVar("field16", "TT1802600037"));
			globalMergeVars.add(new MergeVar("field18", "30 JAN 2018"));
			globalMergeVars.add(new MergeVar("field21", "GHS 1,500.00"));
			globalMergeVars.add(new MergeVar("field28", "1"));
			globalMergeVars.add(new MergeVar("field17", "Cheque Deposit."));
			globalMergeVars.add(new MergeVar("field25", "Independence Avenue"));
			globalMergeVars.add(new MergeVar("field27", "2,468.00"));
			globalMergeVars.add(new MergeVar("field26", "3,968.00"));
			
			m.setGlobalMergeVars(globalMergeVars);
			
			List<Recipient> to = new ArrayList<Recipient>() ;
			Recipient tore = new Recipient();
			tore.setEmail("sathishvp2495@gmail.com");
			to.add(tore);
			m.setTo(to);
//			String signingDomain="nayamsoft.com";
//			m.setSigningDomain(signingDomain);
			String subject="test";
			m.setSubject(subject);
			Boolean async=false;
			api.sendTemplate("AC0004EMAIL", templateContent, m, async);
			System.out.println("success");
			 return "success";
		 }
		 catch(Exception ex){
			 
		 }
		return "failed";		 
	 }
	 
	 //send message to queue
	 
	@RequestMapping(value="/sendToQueue", method = RequestMethod.POST)
	 @CrossOrigin
	 protected String EnQueue(@RequestParam("xmldata") String xmlData) throws IOException {
	        try {
				System.out.println("enqueue try");
	            //created ConnectionFactory object for creating connection 
	            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
	            //Establish the connection
	            javax.jms.Connection connection = factory.createConnection();
	            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	            javax.jms.Queue queue = session.createQueue("Test");

	            //Added as a producer
	            javax.jms.MessageProducer producer = session.createProducer(queue);
	            // Create and send the message
	            TextMessage msg = session.createTextMessage();
	            msg.setText(xmlData);
	            producer.send(msg);
	            System.out.println("msg data::::::"+msg.getText());
	            return "message queued";
	        } catch (Exception e) {
	        	System.out.println("exception occur");
	            // TODO: handle exception
	        }
			return "not send";
	    }
		
	
	@RequestMapping(value="/dequeueMessage")
	@CrossOrigin
	protected String DeQueue(HttpServletRequest arg0,HttpServletResponse arg1) throws ServletException, IOException {
		try {
			System.out.println("dequeue try");
            //created ConnectionFactory object for creating connection 
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
            //Establish the connection
            javax.jms.Connection connection = factory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            javax.jms.Queue queue = session.createQueue("Test");
            //javax.jms.Queue queue = "queue:Test";
            MessageConsumer consumer = session.createConsumer(queue);
            javax.jms.Message msg = consumer.receive();
//            msg.acknowledge();
            session.commit(); 
            return "message dequeued";
		} catch (Exception e){
			System.out.println("dequeue catch");
			
		}
		return "failed";
	}
	 
}
