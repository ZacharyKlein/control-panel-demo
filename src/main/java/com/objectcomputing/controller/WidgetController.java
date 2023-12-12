package com.objectcomputing.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.runtime.context.scope.Refreshable;

import java.util.ArrayList;

import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExecuteOn(TaskExecutors.BLOCKING)
@Refreshable
@Controller("/widget")
public class WidgetController {

  Logger log = LoggerFactory.getLogger(WidgetController.class.getName());

  private ArrayList<String> widgets;

  public WidgetController() {
    log.debug("Initializing WidgetController");
    this.widgets = new ArrayList<>();
  }

  @Post
  public String createWidget(String widget) {
    log.info("Creating widget: " + widget);
    widgets.add(widget);
    return widget;
  }

  @Get("/{id}")
  public String getWidgetById(String id) {
    log.info("Getting widget by id: " + id);
    return widgets.stream().filter(widget -> widget.equals(id)).findFirst().orElse(null);
  }


}
