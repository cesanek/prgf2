package rendererOperation;

import model.Vertex;
import transforms.Col;

public interface Shader {

    Col getColor(Vertex vertex);
}
