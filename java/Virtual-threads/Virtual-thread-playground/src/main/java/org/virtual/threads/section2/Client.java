package org.virtual.threads.section2;

import java.io.IOException;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {

  private static final String PRODUCT_REQUEST_FORMAT = "http://localhost:7070/sec01/product/%d";
  private static final String RATING_REQUEST_FORMAT = "http://localhost:7070/sec01/rating/%d";

  public static String getProduct(final int id) {
    return callExternalService(String.format(PRODUCT_REQUEST_FORMAT, id));
  }

  public static String getRating(final int id) {
    return callExternalService(String.format(RATING_REQUEST_FORMAT, id));
  }


  private static String callExternalService(final String url) {
    log.info("Calling external service: {}", url);
    try (var steam = URI.create(url).toURL().openStream()) {
      return new String(steam.readAllBytes());
    } catch (final IOException _) {
      throw new RuntimeException("Exception while opening stream");
    }
  }

}