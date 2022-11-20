package fr.univcotedazur.vscf.components;

import fr.univcotedazur.vscf.entities.Cookies;
import fr.univcotedazur.vscf.interfaces.CatalogExplorator;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Catalog implements CatalogExplorator {

    @Override
    public Set<Cookies> listPreMadeRecipes() {
        return EnumSet.allOf(Cookies.class);
    }

    @Override
    public Set<Cookies> exploreCatalogue(String regexp) {
        return EnumSet.allOf(Cookies.class).stream().filter(cookie -> cookie.name().matches(regexp)).collect(Collectors.toSet());
    }

}
