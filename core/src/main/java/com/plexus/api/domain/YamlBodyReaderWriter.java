package com.plexus.api.domain; // use your actual package

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Consumes({"application/x-yaml", "application/yaml", "text/yaml"})
@Produces({"application/x-yaml", "application/yaml", "text/yaml"})
public class YamlBodyReaderWriter implements MessageBodyReader<Object>, MessageBodyWriter<Object> {

  private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());

  // --- Reader ---
  @Override
  public boolean isReadable(
      Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
    return mediaType.getSubtype().contains("yaml");
  }

  @Override
  public Object readFrom(
      Class<Object> type,
      Type genericType,
      Annotation[] annotations,
      MediaType mediaType,
      jakarta.ws.rs.core.MultivaluedMap<String, String> httpHeaders,
      InputStream entityStream)
      throws IOException {
    return YAML_MAPPER.readValue(entityStream, type);
  }

  // --- Writer (optional, for returning YAML) ---
  @Override
  public boolean isWriteable(
      Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
    return mediaType.getSubtype().contains("yaml");
  }

  @Override
  public void writeTo(
      Object o,
      Class<?> type,
      Type genericType,
      Annotation[] annotations,
      MediaType mediaType,
      jakarta.ws.rs.core.MultivaluedMap<String, Object> httpHeaders,
      OutputStream entityStream)
      throws IOException {
    YAML_MAPPER.writeValue(entityStream, o);
  }

  @Override
  public long getSize(
      Object o, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
    return -1;
  }
}
