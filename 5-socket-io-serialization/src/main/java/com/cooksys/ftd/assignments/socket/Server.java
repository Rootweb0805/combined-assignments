package com.cooksys.ftd.assignments.socket;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.LocalConfig;
import com.cooksys.ftd.assignments.socket.model.Student;

public class Server extends Utils {

    /**
     * Reads a {@link Student} object from the given file path
     *
     * @param studentFilePath the file path from which to read the student config file
     * @param jaxb the JAXB context to use during unmarshalling
     * @return a {@link Student} object unmarshalled from the given file path
     */
    public static Student loadStudent(String studentFilePath, JAXBContext jaxb) {
       File file = new File(studentFilePath);
    	
    	try {
    		Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
    		Student student = (Student) jaxbUnmarshaller.unmarshal(file);
    		System.out.println(student);
    		return student;

    	  } catch (JAXBException e) {
    		e.printStackTrace();
    	  }
    	return null;
    }

    /**
     * The server should load a {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
     * <project-root>/config/config.xml path, using the "port" property of the embedded
     * {@link com.cooksys.ftd.assignments.socket.model.LocalConfig} object to create a server socket that
     * listens for connections on the configured port.
     *
     * Upon receiving a connection, the server should unmarshal a {@link Student} object from a file location
     * specified by the config's "studentFilePath" property. It should then re-marshal the object to xml over the
     * socket's output stream, sending the object to the client.
     *
     * Following this transaction, the server may shut down or listen for more connections.
     * @throws JAXBException 
     */
    public static void main(String[] args) throws JAXBException {
    	JAXBContext jaxb = JAXBContext.newInstance(Student.class, Config.class, LocalConfig.class);
        loadStudent("config/student.xml", jaxb);
    }
}
