package cucumber;

import com.complexible.common.openrdf.model.ModelIO;
import com.complexible.pinto.RDFMapper;
import com.complexible.pinto.RDFMapperTests;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openrdf.model.Model;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import com.complexible.pinto.RDFMapperTests.ClassWithPrimitives;

public class ReadStepDefinitions {
    private Model graph;
    private Object actualAnswer;

    @io.cucumber.java.DataTableType
    public ClassWithPrimitives classWithPrimitivesEntry(Map<String, String> entry) throws URISyntaxException {
        ClassWithPrimitives c = new ClassWithPrimitives();
        c.setChar(entry.get("mChar").charAt(0));
        c.setInt(Integer.parseInt(entry.get("mInt")));
        c.setDouble(Double.parseDouble(entry.get("mDouble")));
        c.setFloat(Float.parseFloat(entry.get("mFloat")));
        c.setString(entry.get("mString"));
        c.setURI(URI.create("urn:any"));
        return c;
    }

    @Given("graph is in {string}")
    public void graphFileIsPrimitivesNt(String file) throws Exception {
        graph = ModelIO.read(new File(getClass().getResource("/data/" + file).toURI()).toPath());
    }

    @When("I ask for the graph")
    public void iAskForTheGraph() {
        RDFMapper mapper = RDFMapper.create();
        actualAnswer = mapper.readValue(graph, RDFMapperTests.ClassWithPrimitives.class);
    }

    @Then("I should be told")
    public void iShouldBeTold(ClassWithPrimitives expected) {
        assertEquals(expected, actualAnswer);
    }
}
