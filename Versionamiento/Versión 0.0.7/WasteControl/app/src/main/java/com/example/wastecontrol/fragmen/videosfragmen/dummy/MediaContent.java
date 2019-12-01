package com.example.wastecontrol.fragmen.videosfragmen.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MediaContent {

    public static final List<MediaItem> ITEMS = new ArrayList<MediaItem>();
    public static final Map<String, MediaItem> ITEM_MAP = new HashMap<String, MediaItem>();

    private static final int COUNT = 25;

    static {
        addItem(createDummyItem("¿Qué es el reciclaje?", "https://www.youtube.com/watch?v=0JaIOos0vcs"));
        addItem(createDummyItem("¿por qué es IMPORTANTE?", "https://www.youtube.com/watch?v=uaI3PLmAJyM"));
        addItem(createDummyItem("¿Por qué tenemos que reciclar?", "https://www.youtube.com/watch?v=Kef1Dr3rHDY"));
        addItem(createDummyItem("¿Como reciclar?", "https://www.youtube.com/watch?v=YiHTNfKJwAw"));
        addItem(createDummyItem("17 IDEAS GENIALES DE RECICLAJE", "https://www.youtube.com/watch?v=-AHMQTA8MgI"));
        addItem(createDummyItem("Noticias del reciclaje (Revista Semana)", "https://www.semana.com/noticias/reciclaje/104078"));
        addItem(createDummyItem("Noticias del reciclaje (Periodico El TIEMPO)", "https://www.eltiempo.com/noticias/reciclaje"));
        addItem(createDummyItem("Noticias del reciclaje (Revista DINERO)", "https://www.dinero.com/noticias/reciclaje/1306"));
        addItem(createDummyItem("Noticias del reciclaje (Periodico El Colombiano)", "https://www.elcolombiano.com/cronologia/noticias/meta/reciclaje"));
        addItem(createDummyItem("Noticias del reciclaje (Periodico El Pais)", "https://elpais.com/tag/reciclaje/a"));

    }

    private static void addItem(MediaItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static MediaItem createDummyItem(String titulo, String url) {
        return new MediaItem(String.valueOf(ITEMS.size() + 1),titulo, url);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static class MediaItem {
        public final String id;
        public final String content;
        public final String details;

        public MediaItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
