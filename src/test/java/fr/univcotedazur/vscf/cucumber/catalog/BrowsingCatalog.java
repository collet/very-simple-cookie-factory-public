package fr.univcotedazur.vscf.cucumber.catalog;

import fr.univcotedazur.vscf.entities.Cookies;
import fr.univcotedazur.vscf.interfaces.CatalogExplorator;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrowsingCatalog {

    @Autowired
    CatalogExplorator catalogExplorator;

    Set<Cookies> cookiesSet;

    @When("one check the catalog contents")
    public void oneCheckTheCatalogContents() {
       cookiesSet = catalogExplorator.listPreMadeRecipes();
    }

    @Then("^there (?:is|are) (\\d+) items? in it$")
    public void thereAreItemsInIt(int itemsNb) {
        assertEquals(itemsNb,cookiesSet.size());
    }

}
