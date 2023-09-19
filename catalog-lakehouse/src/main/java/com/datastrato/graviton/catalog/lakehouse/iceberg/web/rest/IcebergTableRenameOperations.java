/*
 * Copyright 2023 Datastrato.
 * This software is licensed under the Apache License version 2.
 */
package com.datastrato.graviton.catalog.lakehouse.iceberg.web.rest;

import com.datastrato.graviton.catalog.lakehouse.iceberg.ops.IcebergTableOps;
import com.datastrato.graviton.catalog.lakehouse.iceberg.web.IcebergRestUtils;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.iceberg.rest.requests.RenameTableRequest;

@Path("/v1/{prefix:([^/]*/)?}tables/rename")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IcebergTableRenameOperations {

  @Context private HttpServletRequest httpRequest;

  private IcebergTableOps icebergTableOps;

  @Inject
  public IcebergTableRenameOperations(IcebergTableOps icebergTableOps) {
    this.icebergTableOps = icebergTableOps;
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Response renameTable(RenameTableRequest renameTableRequest) {
    icebergTableOps.renameTable(renameTableRequest);
    return IcebergRestUtils.okWithoutContent();
  }
}