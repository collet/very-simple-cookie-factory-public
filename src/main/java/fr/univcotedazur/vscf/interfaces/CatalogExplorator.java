package fr.univcotedazur.vscf.interfaces;

import fr.univcotedazur.vscf.entities.Cookies;

import java.util.Set;

public interface CatalogExplorator {

    Set<Cookies> listPreMadeRecipes();

    Set<Cookies> exploreCatalogue(String regexp);

}

