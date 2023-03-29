package gov.iti.jets.util;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public JsonElement serialize(LocalDate date, Type type, JsonSerializationContext context) {
        return new JsonPrimitive(date.format(DATE_FORMAT));
    }

    @Override
    public LocalDate deserialize(JsonElement element, Type type, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDate.parse(element.getAsString(), DATE_FORMAT);
    }
}

