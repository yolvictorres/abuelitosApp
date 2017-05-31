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
        name = "foroApi",
        version = "v1",
        resource = "foro",
        namespace = @ApiNamespace(
                ownerDomain = "backend.abuelitosservices.a506132023.konradlorenz.edu.co",
                ownerName = "backend.abuelitosservices.a506132023.konradlorenz.edu.co",
                packagePath = ""
        )
)
public class ForoEndpoint {

    private static final Logger logger = Logger.getLogger(ForoEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Foro.class);
    }

    /**
     * Returns the {@link Foro} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Foro} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "foro/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Foro get(@Named("id") String id) throws NotFoundException {
        logger.info("Getting Foro with ID: " + id);
        Foro foro = ofy().load().type(Foro.class).id(id).now();
        if (foro == null) {
            throw new NotFoundException("Could not find Foro with ID: " + id);
        }
        return foro;
    }

    /**
     * Inserts a new {@code Foro}.
     */
    @ApiMethod(
            name = "insert",
            path = "foro",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Foro insert(Foro foro) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that foro.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(foro).now();
        logger.info("Created Foro with ID: " + foro.getId());

        return ofy().load().entity(foro).now();
    }

    /**
     * Updates an existing {@code Foro}.
     *
     * @param id   the ID of the entity to be updated
     * @param foro the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Foro}
     */
    @ApiMethod(
            name = "update",
            path = "foro/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Foro update(@Named("id") String id, Foro foro) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entity(foro).now();
        logger.info("Updated Foro: " + foro);
        return ofy().load().entity(foro).now();
    }

    /**
     * Deletes the specified {@code Foro}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Foro}
     */
    @ApiMethod(
            name = "remove",
            path = "foro/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") String id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(Foro.class).id(id).now();
        logger.info("Deleted Foro with ID: " + id);
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
            throw new NotFoundException("Could not find Foro with ID: " + id);
        }
    }
}