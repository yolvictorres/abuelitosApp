package com.example.Diego.myapplication.backend;

/**
 * Created by astro on 29/05/2017.
 */


import com.example.Diego.myapplication.backend.data.Foro;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Api(
        name = "foroApi",
        version = "v1",
        resource = "foro",
        namespace = @ApiNamespace(
                ownerDomain = "data.backend.myapplication.Diego.example.com",
                ownerName = "data.backend.myapplication.Diego.example.com",
                packagePath = ""
        )
)
public class ForoEndpoint {
        private static final Logger logger = Logger.getLogger(UserEndpoint.class.getName());

        private static final int DEFAULT_LIST_LIMIT = 20;
    /**
     * Inserts a new {@code foro}.
     */
    @ApiMethod(
            name = "insert",
            path = "foro",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Foro insert(Foro foro) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that user.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(foro).now();
        logger.info("Created foro with ID: " + foro.getId());

        return ofy().load().entity(foro).now();
    }
    @ApiMethod(
            name = "list",
            path = "foro",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Foro> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Foro> query = ofy().load().type(Foro.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Foro> queryIterator = query.iterator();
        List<Foro> foroList = new ArrayList<Foro>(limit);
        while (queryIterator.hasNext()) {
            foroList.add(queryIterator.next());
        }
        return CollectionResponse.<Foro>builder().setItems(foroList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }
    private void checkExists(String id) throws NotFoundException {
        try {
            ofy().load().type(Foro.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find foro with ID: " + id);
        }
    }


}
