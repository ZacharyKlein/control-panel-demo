package com.objectcomputing.controller;

import com.objectcomputing.entity.BrandEntity;
import com.objectcomputing.entity.BrandRepository;
import io.micronaut.data.exceptions.DataAccessException;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/brands")
public class BrandController {

  protected final BrandRepository brandRepository;

  public BrandController(BrandRepository brandRepository) {
    this.brandRepository = brandRepository;
  }

  @Get("/{id}")
  public Optional<BrandEntity> show(Long id) {
    return brandRepository
        .findById(id);
  }

  @Put
  public HttpResponse update(@Body @Valid BrandEntity command) {
    brandRepository.update(command.getId(), command.getName());
    return HttpResponse
        .noContent()
        .header(HttpHeaders.LOCATION, location(command.getId()).getPath());
  }

  @Get("/list")
  public List<BrandEntity> list(@Valid Pageable pageable) {
    return brandRepository.findAll(pageable).getContent();
  }

  @Post
  public HttpResponse<BrandEntity> save(@Body("name") @NotBlank String name) {
    BrandEntity brand = brandRepository.save(name);

    return HttpResponse
        .created(brand)
        .headers(headers -> headers.location(location(brand.getId())));
  }

  @Post("/ex")
  public HttpResponse<BrandEntity> saveExceptions(@Body @NotBlank String name) {
    try {
      BrandEntity brand = brandRepository.saveWithException(name);
      return HttpResponse
          .created(brand)
          .headers(headers -> headers.location(location(brand.getId())));
    } catch(DataAccessException e) {
      return HttpResponse.noContent();
    }
  }

  @Delete("/{id}")
  @Status(HttpStatus.NO_CONTENT)
  public void delete(Long id) {
    brandRepository.deleteById(id);
  }

  protected URI location(Long id) {
    return URI.create("/brands/" + id);
  }

  protected URI location(BrandEntity brand) {
    return location(brand.getId());
  }
}
