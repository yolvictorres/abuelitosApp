package com.example.Diego.myapplication.backend;

import com.example.Diego.myapplication.backend.data.Event;
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

/**
 * Created by astro on 29/05/2017.
 */
@Api(
        name = "eventoApi",
        version = "v1",
        resource = "evento",
        namespace = @ApiNamespace(
                ownerDomain = "data.backend.myapplication.Diego.example.com",
                ownerName = "data.backend.myapplication.Diego.example.com",
                packagePath = ""
        )
)
public class EventoEnddpoint {
    private static final Logger logger = Logger.getLogger(UserEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    /**
     * Returns the {@link Event} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Event} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "evento/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Event get(@Named("id") String id) throws NotFoundException {
        logger.info("Getting event with ID: " + id);
        Event event = ofy().load().type(Event.class).id(id).now();
        if (event == null) {
            throw new NotFoundException("Could not find event with ID: " + id);
        }
        return event;
    }

    /**
     * Inserts a new {@code Event}.
     */
    @ApiMethod(
            name = "insert",
            path = "event",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Event insert(Event event) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that user.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(event).now();
        logger.info("Created event with ID: " + event.getId());

        return ofy().load().entity(event).now();
    }

    /**
     * Updates an existing {@code event}.
     *
     * @param id   the ID of the entity to be updated
     * @param event the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Event}
     */
    @ApiMethod(
            name = "update",
            path = "event/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Event update(@Named("id") String id, Event event) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entity(event).now();
        logger.info("Updated Event: " + event);
        return ofy().load().entity(event).now();
    }

    /**
     * Deletes the specified {@code Event}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Event}
     */
    @ApiMethod(
            name = "remove",
            path = "event/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") String id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(Event.class).id(id).now();
        logger.info("Deleted Event with ID: " + id);
    }

    /**
     * List all entities.
     *
     * @param cursor used for pagination to determine which page to return
     * @param limit  the maximum number of entries to return
     * @return a response that encapsulates the result list and the next page token/cursor
     */
    @ApiMethod(
            name = "list",
            path = "event",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Event> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Event> query = ofy().load().type(Event.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Event> queryIterator = query.iterator();
        List<Event> eventList = new ArrayList<Event>(limit);
        while (queryIterator.hasNext()) {
            eventList.add(queryIterator.next());
        }
        return CollectionResponse.<Event>builder().setItems(eventList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }
    private void checkExists(String id) throws NotFoundException {
        try {
            ofy().load().type(Event.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Event with ID: " + id);
        }
    }

}
