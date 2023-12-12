package com.objectcomputing.controlpanel;

import io.micronaut.controlpanel.core.AbstractControlPanel;
import io.micronaut.controlpanel.core.config.ControlPanelConfiguration;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Singleton
public class CustomControlPanel extends AbstractControlPanel<String> {

  private static final String NAME = "custom";

  public CustomControlPanel(@Named(NAME) ControlPanelConfiguration configuration) {
    super(NAME, configuration);
  }

  @Override
  public String getBody() {
    return "This is a custom control panel. This text is coming from the body.";
  }

  @Override
  public Category getCategory() {
    return new Category(NAME, "Custom", "fa-user");
  }
}