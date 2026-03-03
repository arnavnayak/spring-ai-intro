package guru.springframework.springaiintro.ragboatexpert.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.List;


@Configuration
@ConfigurationProperties(prefix = "sfg.aiapp")
public class RagBoatExpertVectorStoreProperties {

    private String vectorStoreOfTowVehiclePath;
    private List<Resource> documentsOfTowVehicleToLoad;

    public String getVectorStoreOfTowVehiclePath() {
        return vectorStoreOfTowVehiclePath;
    }

    public void setVectorStoreOfTowVehiclePath(String vectorStoreOfTowVehiclePath) {
        this.vectorStoreOfTowVehiclePath = vectorStoreOfTowVehiclePath;
    }

    public List<Resource> getDocumentsOfTowVehicleToLoad() {
        return documentsOfTowVehicleToLoad;
    }

    public void setDocumentsOfTowVehicleToLoad(List<Resource> documentsOfTowVehicleToLoad) {
        this.documentsOfTowVehicleToLoad = documentsOfTowVehicleToLoad;
    }
}
