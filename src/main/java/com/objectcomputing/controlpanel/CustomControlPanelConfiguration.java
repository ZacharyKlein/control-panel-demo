package com.objectcomputing.controlpanel;

import io.micronaut.controlpanel.core.config.ControlPanelConfiguration;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Singleton
@Named("custom")
public class CustomControlPanelConfiguration extends ControlPanelConfiguration {

  public CustomControlPanelConfiguration() {
    super("custom");
  }
}
