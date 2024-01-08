package com.phone.handler;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class BooleanToStringSerializer extends JsonSerializer<Boolean> {
  @Override
  public void serialize(Boolean value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    if (value != null) {
      gen.writeString(value ? "Yes" : "No");
    }
  }
}
