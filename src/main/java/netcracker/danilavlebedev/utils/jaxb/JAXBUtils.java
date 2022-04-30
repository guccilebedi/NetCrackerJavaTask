package netcracker.danilavlebedev.utils.jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import netcracker.danilavlebedev.di.Singleton;
import netcracker.danilavlebedev.repository.Repository;

import java.io.File;

@Singleton
public class JAXBUtils {
    public Repository read(String path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Repository.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (Repository) unmarshaller.unmarshal(new File(path));
    }

    public void write(Repository repository, String path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Repository.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(repository, new File("scheme.xml"));
    }
}
