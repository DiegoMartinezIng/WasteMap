package com.example.wastecontrol.fragmen.listafragment;

import com.example.wastecontrol.persistencia.model.PuntoRecoleccionModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample categoria for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ListasDePuntosPorCategorias {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<FiltroPuntosCategoria> ITEMS = new ArrayList<FiltroPuntosCategoria>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, FiltroPuntosCategoria> ITEM_MAP = new HashMap<String, FiltroPuntosCategoria>();

    private static int COUNT = 25;

    public static void cargar(String[] categorias){
        ITEMS.clear();
        ITEM_MAP.clear();
        COUNT = categorias.length;
        for (int i = 1; i <= COUNT; i++) {
            agregarFiltro(crearFiltroCategoria(i, categorias[i-1]));
        }
    }

    private static void agregarFiltro(FiltroPuntosCategoria item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static FiltroPuntosCategoria crearFiltroCategoria(int position, String categoria) {

        return new FiltroPuntosCategoria(String.valueOf(position), categoria, makeDetails(categoria));
    }

    private static PuntoRecoleccionModel makeDetails(String categoria) {
        PuntoRecoleccionModel model = new PuntoRecoleccionModel();
        model.setCategoriaResiduo(categoria);
        return model;
    }

    /**
     * A dummy item representing a piece of categoria.
     */
    public static class FiltroPuntosCategoria {
        public final String id;
        public final String categoria;
        public final PuntoRecoleccionModel filtro;

        public FiltroPuntosCategoria(String id, String content, PuntoRecoleccionModel filtro) {
            this.id = id;
            this.categoria = content;
            this.filtro = filtro;
        }

        @Override
        public String toString() {
            return categoria;
        }
    }
}
