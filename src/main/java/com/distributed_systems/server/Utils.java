/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.distributed_systems.server;
import com.distributed_systems.models.Person;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

/**
 *
 * @author diego
 */
public class Utils {
    
    public static Person parsePersonXML(String xml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(xml);
        return (Person) unmarshaller.unmarshal(reader);
    }    
    
    public static File getResultFile(Person person){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            
            // Opcional: Formatea el XML para que sea más fácil de leer
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            
            StringWriter writer = new StringWriter();
            marshaller.marshal(person, writer);
            
            String xmlContent = writer.toString();
            
            System.out.println("GENERATED FILE WITH RESULTS: " + xmlContent);
            
            Files.write(Paths.get("result.xml"), xmlContent.getBytes(), StandardOpenOption.CREATE);
            
            File result = new File("result.xml");
            
            return result; 
            
        } catch (PropertyException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        } catch(IOException ex){
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
