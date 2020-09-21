/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restws.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import restws.AppUser;

/**
 *
 * @author sarah
 */
@Stateless
@Path("restws.appuser")
public class AppUserFacadeREST extends AbstractFacade<AppUser> {

    @PersistenceContext(unitName = "CaloriTracker3PU")
    private EntityManager em;

    public AppUserFacadeREST() {
        super(AppUser.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(AppUser entity) {
        super.create(entity);

    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, AppUser entity) {
        super.edit(entity);

    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AppUser find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Path("findByName/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AppUser> findByName(@PathParam("name") String name) {
        Query query = em.createNamedQuery("AppUser.findByName");
        query.setParameter("name", name);
        return query.getResultList();
    }

    @GET
    @Path("findByEmail/{email}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AppUser> findByEmail(@PathParam("email") String email) {
        Query query = em.createNamedQuery("AppUser.findByEmail");
        query.setParameter("email", email);
        return query.getResultList();
    }

    @GET
    @Path("findBySurename/{surename}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AppUser> findBySurename(@PathParam("surename") String surename) {
        Query query = em.createNamedQuery("AppUser.findBySurename");
        query.setParameter("surename", surename);
        return query.getResultList();
    }

    @GET
    @Path("findByDob/{dob}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AppUser> findByDob(@PathParam("dob") Date dob) {
        Query query = em.createNamedQuery("AppUser.findByDob");
        query.setParameter("dob", dob);
        return query.getResultList();
    }

    @GET
    @Path("findByHeight/{height}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AppUser> findByHeight(@PathParam("height") BigDecimal height) {
        Query query = em.createNamedQuery("AppUser.findByHeight");
        query.setParameter("height", height);
        return query.getResultList();
    }
    ///

    @GET
    @Path("findByIdCaloriesBurnedPerStep/{userid}")
   // @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public double findByIdCaloriesBurnedPerStep(@PathParam("userid") Integer userid) {
        TypedQuery<AppUser> q = em.createQuery("SELECT a FROM AppUser a WHERE a.userid=:userid", AppUser.class);
        q.setParameter("userid", userid);
        AppUser appUser=q.getSingleResult();
        double caloriburned = appUser.caloriesBurnedStep();
        return caloriburned;
    }
@GET
    @Path("findByIdBMRAmount/{userid}")
   @Produces({MediaType.TEXT_PLAIN, MediaType.TEXT_PLAIN})
    public double findByIdBMRAmount(@PathParam("userid") Integer userid) {
        TypedQuery<AppUser> q = em.createQuery("SELECT a FROM AppUser a WHERE a.userid=:userid", AppUser.class);
        q.setParameter("userid", userid);
        AppUser appUser=q.getSingleResult();
        double bmr = appUser.getBMRAmount();
        return bmr;
    }
    
    @GET
    @Path("findByIdTotalalorisBurndeRest/{userid}")
   //@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public double findByIdTotalalorisBurndeRest(@PathParam("userid") Integer userid) {
        TypedQuery<AppUser> q = em.createQuery("SELECT a FROM AppUser a WHERE a.userid=:userid", AppUser.class);
        q.setParameter("userid", userid);
        AppUser appUser=q.getSingleResult();
        int caloriRest = appUser.calorieBurnedRest();
        return caloriRest;
    }
//     @GET
//   @Path("test/{userid}") 
//   @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//   public Object test(@PathParam("userid") Integer userid) {
//       Double queryList = em.createQuery("SELECT ((2.20462*a.weight* 0.49)/a.stepspermile) FROM AppUser AS a Where a.userid = :userid",Double.class)
//               .setParameter("userid", (Integer) userid).getSingleResult();
//     JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
////       for (Object[] row : queryList) {
////           JsonObject personObject = Json.createObjectBuilder().
////                   add("caloriesBurnedPerStep", (Double)row[0]).build();
////           arrayBuilder.add(personObject);
////       }
//       JsonArray jArray = arrayBuilder.build();
//       return jArray;
//   }
    @GET
    @Path("findByWeight/{weight}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AppUser> findByWeight(@PathParam("weight") BigDecimal weight) {
        Query query = em.createNamedQuery("AppUser.findByWeight");
        query.setParameter("weight", weight);
        return query.getResultList();
    }

    @GET
    @Path("findByAddress/{address}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AppUser> findByAddress(@PathParam("address") String address) {
        Query query = em.createNamedQuery("AppUser.findByAddress");
        query.setParameter("address", address);
        return query.getResultList();
    }

    @GET
    @Path("findByPostcode/{postcode}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AppUser> findByPostcode(@PathParam("postcode") Integer postcode) {
        Query query = em.createNamedQuery("AppUser.findByPostcode");
        query.setParameter("postcode", postcode);
        return query.getResultList();
    }

    @GET
    @Path("findByActivitylevel/{activitylevel}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AppUser> findByActivitylevel(@PathParam("activitylevel") Integer activitylevel) {
        Query query = em.createNamedQuery("AppUser.findByActivitylevel");
        query.setParameter("activitylevel", activitylevel);
        return query.getResultList();
    }

    @GET
    @Path("findByStepspermile/{stepspermile}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AppUser> findByStepspermile(@PathParam("stepspermile") Integer stepspermile) {
        Query query = em.createNamedQuery("AppUser.findByStepspermile");
        query.setParameter("stepspermile", stepspermile);
        return query.getResultList();
    }

    @GET
    @Path("findByGender/{gender}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AppUser> findByGender(@PathParam("gender") String gender) {
        Query query = em.createNamedQuery("AppUser.findByGender");
        query.setParameter("gender", gender);
        return query.getResultList();
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AppUser> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AppUser> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
