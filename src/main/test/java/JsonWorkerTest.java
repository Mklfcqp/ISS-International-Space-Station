import com.google.gson.JsonElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonWorkerTest {

    @Mock
    private JsonWorker jsonWorker;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        jsonWorker = new JsonWorker();
    }

    @Test
    public void testJsonParser() throws IOException {
        // Arrange
        String jsonFilePath = "src/iss.json";

        // Act
        JsonElement jsonElement = jsonWorker.jsonParser(jsonFilePath);

        // Assert
        assertNotNull(jsonElement);
        assertTrue(jsonElement.isJsonObject());
    }
}
