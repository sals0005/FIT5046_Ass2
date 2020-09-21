/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restws.service;

import java.sql.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import restws.Credential;

/**
 *
 * @author sarah
 */
@Stateless
@Path("restws.credential")
public class CredentialFacadeREST extends AbstractFacade<Credential> {

    @PersistenceContext(unitName = "CaloriTracker3PU")
    private EntityManager em;

    public CredentialFacadeREST() {
        super(Credential.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Credential entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Credential entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Credential find(@PathParam("id") String id) {
        return super.find(id);
    }
    
    @GET
    @Path("findBySignupdate/{signupdate}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findBySignupdate(@PathParam("signupdate") Date signupdate) {
        Query query = em.createNamedQuery("Credential.findBySignupdate");
        query.setParameter("signupdate", signupdate);
        return query.getResultList();
    }
    
    @GET
    @Path("findByPasswordhash/{passwordhash}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findByPasswordhash(@PathParam("passwordhash") String passwordhash) {
        Query query = em.createNamedQuery("Credential.findByPasswordhash");
        query.setParameter("passwordhash", passwordhash);
        return query.getResultList();
    }
    
    @GET
    @Path("findByUser/{userid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findByUser(@PathParam("userid") Integer userid) {
        Query query = em.createNamedQuery("Credential.findByUser");
        query.setParameter("userid", userid);
        return query.getResultList();
    }
    
    @GET
    @Path("findByUsername/{username}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findByUsername(@PathParam("username") String username) {
        Query query = em.createNamedQuery("Credential.findByUsername");
        query.setParameter("username", username);
        return query.getResultList();
    }
@GET
    @Path("login/{username}/{passwordhash}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> login(@PathParam("username") String username, @PathParam("passwordhash") String passwordhash) {
        Query query = em.createNamedQuery("Credential.login");
        query.setParameter("username", username);
        query.setParameter("passwordhash", passwordhash);
        return query.getResultList();
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
