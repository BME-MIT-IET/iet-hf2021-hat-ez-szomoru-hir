package cucumber;

import com.complexible.common.openrdf.model.ModelIO;
import com.complexible.pinto.RDFMapper;
import com.complexible.pinto.RDFMapperTests;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openrdf.model.Model;

import java.io.File;
import static org.junit.Assert.assertEquals;

public class ReadStepDefinitions {
    private RDFMapperTests.ClassWithPrimitives result;
    private String actualAnswer;

    @Given("graph file is primitives.nt")
    public void graphFileIsPrimitivesNt() throws Exception {
        Model graph = ModelIO.read(new File(getClass().getResource("/data/primitives.nt").toURI()).toPath());
        RDFMapper mapper = RDFMapper.create();
        result = mapper.readValue(graph, RDFMapperTests.ClassWithPrimitives.class);
    }

    @When("I ask the the {string} argument")
    public void iAskTheTheArgument(String arg) {
        switch (arg){
            case "int":
                actualAnswer = String.valueOf(result.getInt());
                break;
            case "float":
                actualAnswer = String.valueOf(result.getFloat());
                break;
            case "char":
                actualAnswer = String.valueOf(result.getChar());
                break;

            case "double":
                actualAnswer = String.valueOf(result.getDouble());
                break;
            case "string":
                actualAnswer = result.getString();
                break;
        }
    }

    @Then("I should be told {string}")
    public void iShouldBeTold(String expected) {
        assertEquals(expected, actualAnswer);
    }
}
