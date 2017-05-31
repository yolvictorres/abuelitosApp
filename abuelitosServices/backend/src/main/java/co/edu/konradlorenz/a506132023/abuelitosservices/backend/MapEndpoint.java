package co.edu.konradlorenz.a506132023.abuelitosservices.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "mapApi",
        version = "v1",
        resource = "map",
        namespace = @ApiNamespace(
                ownerDomain = "backend.abuelitosservices.a506132023.konradlorenz.edu.co",
                ownerName = "backend.abuelitosservices.a506132023.konradlorenz.edu.co",
                packagePath = ""
        )
)
public class MapEndpoint {

    private static final Logger logger = Logger.getLogger(MapEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Map.class);
    }

    /**
     * Returns the {@link Map} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Map} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "map/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Map get(@Named("id") String id) throws NotFoundException {
        logger.info("Getting Map with ID: " + id);
        Map map = ofy().load().type(Map.class).id(id).now();
        if (map == null) {
            throw new NotFoundException("Could not find Map with ID: " + id);
        }
        return map;
    }

    /**
     * Inserts a new {@code Map}.
     */
    @ApiMethod(
            name = "insert",
            path = "map",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Map insert(Map map) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that map.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(map).now();
        logger.info("Created Map with ID: " + map.getId());

        return ofy().load().entity(map).now();
    }

    /**
     * Updates an existing {@code Map}.
     *
     * @param id  the ID of the entity to be updated
     * @param map the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Map}
     */
    @ApiMethod(
            name = "update",
            path = "map/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Map update(@Named("id") String id, Map map) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entity(map).now();
        logger.info("Updated Map: " + map);
        return ofy().load().entity(map).now();
    }

    /**
     * Deletes the specified {@code Map}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Map}
     */
    @ApiMethod(
            name = "remove",
            path = "map/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") String id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(Map.class).id(id).now();
        logger.info("Deleted Map with ID: " + id);
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
            path = "map",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Map> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Map> query = ofy().load().type(Map.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Map> queryIterator = query.iterator();
        List<Map> mapList = new ArrayList<Map>(limit);
        while (queryIterator.hasNext()) {
            mapList.add(queryIterator.next());
        }
        return CollectionResponse.<Map>builder().setItems(mapList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(String id) throws NotFoundException {
        try {
            ofy().load().type(Map.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Map with ID: " + id);
        }
    }
}