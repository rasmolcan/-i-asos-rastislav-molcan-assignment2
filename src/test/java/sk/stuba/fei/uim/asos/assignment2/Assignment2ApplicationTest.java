package sk.stuba.fei.uim.asos.assignment2;

import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@Log
public class Assignment2ApplicationTest {

    @BeforeEach
    public void testSetup() {

    }

    @Test
    public void validXsdSchemesShouldExist() throws IOException, SAXException {
        File xsdFolder = new ClassPathResource("xsd", this.getClass().getClassLoader()).getFile();

        assertTrue(xsdFolder.isDirectory());
        assertNotNull(xsdFolder.list());
        File[] files = xsdFolder.listFiles();
        assertTrue(Arrays.stream(Objects.requireNonNull(files)).anyMatch(file -> file.getName().endsWith(".xsd")));

        for (File file : files) {
            if (!file.getName().endsWith(".xsd")) {
                return;
            }
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(file);
        }
    }

    @Test
    public void wsdlSchemeShouldExists() throws IOException {
        File xsdFolder = new ClassPathResource("wsdl", this.getClass().getClassLoader()).getFile();
        assertTrue(xsdFolder.isDirectory());
        assertNotNull(xsdFolder.list());
        assertTrue(Arrays.stream(Objects.requireNonNull(xsdFolder.list())).anyMatch(file -> file.endsWith(".wsdl")));
    }

    @Test
    public void checkForGeneratedClasses() throws IOException {
        File generatedFolder = new ClassPathResource("sk/stuba/fei/uim/asos/assignment2/ws", this.getClass().getClassLoader()).getFile();
        assertTrue(generatedFolder.isDirectory());
        assertNotNull(generatedFolder.list());
        assertNotEquals(0, Objects.requireNonNull(generatedFolder.list()).length);
    }

    @Test
    public void shouldRun() {
        Assignment2Application.main(new String[]{});
    }

}
