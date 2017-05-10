package com.example.Diego.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Diego on 08/05/2017.
 */

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p/>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "userApi",
        version = "v1",
        resource = "user",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Diego.example.com",
                ownerName = "backend.myapplication.Diego.example.com",
                packagePath = ""
        )
)
public class UserEndpoint {

    private static final Logger logger = Logger.getLogger(UserEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(User.class);
    }


    /**
     * Returns the {@link User} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code User} with the provided ID.
     */
    @ApiMethod(
            name = "hola",
            path = "sayhello/{documento}/{mensaje}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public User digaHola(@Named("mensaje") String message,@Named("documento") String id) throws NotFoundException {
        User user = get(id);
        user.setMessage(message);

        return user;
    }

    /**
     * Returns the {@link User} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code User} with the provided ID.
     */
    @ApiMethod(
            name = "getTIUN",
            path = "unal/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public User getTIUN(@Named("id") String id) throws NotFoundException {
        User user = new User();

        try{
            org.jsoup.nodes.Document doc = Jsoup.connect("http://tarjetainteligente.unal.edu.co/tiun/mostrar/valide-aqui-datos-de-usuario.html")
                    .data("tx_tarjetas_tarjetas[identi]", id)
                    .post();

            for (Element table : doc.select("table.tx-tarjetas")) {
                int count = 0;
                for (Element row : table.select("td")) {
                    String campo = row.toString().split(">")[1].split("<")[0];
                    switch (count){
                        case 0:
                            user.setName(campo);
                            break;
                        case 1:
                            user.setLastName(campo);
                            break;
                        case 2:
                            user.setId(campo);
                            break;
                        case 3:
                            user.setTypePeople(campo);
                            break;
                        case 4:
                            user.setTypeUser(campo);
                            break;
                        case 5:
                            user.setTypePosition(campo);
                            break;
                        case 6:
                            user.setDepartment(campo);
                            break;
                        case 7:
                            user.setStatus(campo);
                            break;
                    }
                    count++;

                }
            }

        }catch (Exception e){
            System.out.println(e.toString());

        }


        return user;
    }

    /**
     * Returns the {@link User} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code User} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "user/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public User get(@Named("id") String id) throws NotFoundException {
        logger.info("Getting User with ID: " + id);
        User user = ofy().load().type(User.class).id(id).now();
        if (user == null) {
            throw new NotFoundException("Could not find User with ID: " + id);
        }
        return user;
    }

    /**
     * Inserts a new {@code User}.
     */
    @ApiMethod(
            name = "insert",
            path = "user",
            httpMethod = ApiMethod.HttpMethod.POST)
    public User insert(User user) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that user.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(user).now();
        logger.info("Created User with ID: " + user.getId());

        return ofy().load().entity(user).now();
    }

    /**
     * Updates an existing {@code User}.
     *
     * @param id   the ID of the entity to be updated
     * @param user the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code User}
     */
    @ApiMethod(
            name = "update",
            path = "user/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public User update(@Named("id") String id, User user) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entity(user).now();
        logger.info("Updated User: " + user);
        return ofy().load().entity(user).now();
    }

    /**
     * Deletes the specified {@code User}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code User}
     */
    @ApiMethod(
            name = "remove",
            path = "user/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") String id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(User.class).id(id).now();
        logger.info("Deleted User with ID: " + id);
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
            path = "user",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<User> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<User> query = ofy().load().type(User.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<User> queryIterator = query.iterator();
        List<User> userList = new ArrayList<User>(limit);
        while (queryIterator.hasNext()) {
            userList.add(queryIterator.next());
        }
        return CollectionResponse.<User>builder().setItems(userList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(String id) throws NotFoundException {
        try {
            ofy().load().type(User.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find User with ID: " + id);
        }
    }
}