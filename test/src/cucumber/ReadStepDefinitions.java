package cucumber;

import com.complexible.common.openrdf.model.ModelIO;
import com.complexible.pinto.RDFMapper;
import com.complexible.pinto.RDFMapperTests;
import com.google.common.collect.Sets;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openrdf.model.Model;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import static org.junit.Assert.assertEquals;
import com.complexible.pinto.RDFMapperTests.ClassWithPrimitives;
import com.complexible.pinto.RDFMapperTests.ClassWithPrimitiveLists;
import org.openrdf.model.impl.SimpleValueFactory;

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

    @io.cucumber.java.DataTableType
    public ClassWithPrimitiveLists classWithPrimitiveListsEntry(Map<String, String> entry) {
        ClassWithPrimitiveLists c = new ClassWithPrimitiveLists();
        String[] str_ints = entry.get("mInts").split(",");
        ArrayList<Integer> intList = new ArrayList<>();
        for(String s : str_ints) intList.add(Integer.valueOf(s));
        c.setInts(intList);

        String[] str_doubles = entry.get("mDoubles").split(",");
        Set<Double> doubleList = Sets.newLinkedHashSet();
        for(String s : str_doubles) doubleList.add(Double.valueOf(s));
        c.setDoubles(doubleList);

        String[] str_floats = entry.get("mFloats").split(",");
        Set<Float> floatList = Sets.newLinkedHashSet();
        for(String s : str_floats) floatList.add(Float.valueOf(s));
        c.setFloats(floatList);

        String[] str_bools = entry.get("mBools").split(",");
        SortedSet<Boolean> boolList = Sets.newTreeSet();
        for(String s : str_bools) boolList.add(Boolean.valueOf(s));
        c.setBools(boolList);
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

    @When("I ask for the graph with lists")
    public void iAskForTheGraphWithLists() {
        actualAnswer = RDFMapper.create().readValue(graph,
                RDFMapperTests.ClassWithPrimitiveLists.class,
                SimpleValueFactory.getInstance().createIRI("tag:complexible:pinto:b7d283d3a73c7b8a870087942b9a43b1"));

    }

    @Then("I should get these lists")
    public void iShouldGetTheseLists(ClassWithPrimitiveLists expected) {
        assertEquals(expected, actualAnswer);
    }
}
