/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restws.service;

import entity.CalorisDurarion;
import entity.Goals;
import java.math.BigDecimal;
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
import javax.persistence.TemporalType;
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
import restws.Report;

/**
 *
 * @author sarah
 */
@Stateless
@Path("restws.report")
public class ReportFacadeREST extends AbstractFacade<Report> {

    @PersistenceContext(unitName = "CaloriTracker3PU")
    private EntityManager em;

    public ReportFacadeREST() {
        super(Report.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Report entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Report entity) {
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
    public Report find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("findByUser/{userid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findByUser(@PathParam("userid") Integer userid) {
        Query query = em.createNamedQuery("Report.findByUser");
        query.setParameter("userid", userid);
        return query.getResultList();
    }
    
     @GET
    @Path("findByDate/{date}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findByDate(@PathParam("date") Date date) {
        Query query = em.createNamedQuery("Report.findByDate");
        query.setParameter("date", date);
        return query.getResultList();
    }
    
    @GET
    @Path("findByTotalcaloriesconsumed/{totalcaloriesconsumed}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findByTotalcaloriesconsumed(@PathParam("totalcaloriesconsumed") BigDecimal totalcaloriesconsumed) {
        Query query = em.createNamedQuery("Report.findByTotalcaloriesconsumed");
        query.setParameter("totalcaloriesconsumed", totalcaloriesconsumed);
        return query.getResultList();
        
    }
    @GET
    @Path("findByUserIdandDate/{userid}/{date}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findByUserIdandDate(@PathParam("userid") Integer userid,@PathParam("date") Date date) {
        TypedQuery<Report> q = em.createQuery("SELECT r FROM Report r WHERE r.userid.userid = :userid AND r.date=:date",Report.class);
        q.setParameter("userid", userid);
        q.setParameter("date", date);
        return q.getResultList();
    }
     @GET
    @Path("findByUserDetails/{userid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findByUserDetails(@PathParam("userid") Integer userid) {
        TypedQuery<Report> q = em.createQuery("SELECT r FROM Report r WHERE r.userid.userid = :userid",Report.class);
        q.setParameter("userid", userid);
        return q.getResultList();
    }
   @GET
   @Path("findRemainingCaloriByUserIdandDate/{userid}/{date}") 
   @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
   public Object findRemainingCaloriByUserIdandDate(@PathParam("userid") Integer userid,@PathParam("date") Date date) {
       List<Object[]> queryList = em.createQuery("SELECT r.totalcaloriesconsumed ,r.totalcaloriesburned, ((r.totalcaloriesconsumed-r.totalcaloriesburned)-r.totalcaloriegoal)FROM Report AS r WHERE r.userid.userid = :userid AND r.date=:date", 
         Object[].class).setParameter("userid", (Integer) userid).setParameter("date",(Date) date).getResultList();
       JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
       for (Object[] row : queryList) {
           JsonObject personObject = Json.createObjectBuilder().
                   add("totalcaloriesconsumed", (BigDecimal)row[0])
                   .add("totalcaloriesburned", (BigDecimal)row[1])
                   .add("remainingCalorie",(BigDecimal)row[2]).build();
           arrayBuilder.add(personObject);
       }
       JsonArray jArray = arrayBuilder.build();
       return jArray;
   }
   
    @GET
   @Path("findCalorieGoalUserIdandDate/{userid}/{date}") 
   @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
   public Object findCalorieGoalUserIdandDate(@PathParam("userid") Integer userid,@PathParam("date") Date date) {
       List<Object[]> queryList = em.createQuery("SELECT r.totalcaloriegoal FROM Report AS r WHERE r.userid.userid = :userid AND r.date=:date", 
         Object[].class).setParameter("userid", (Integer) userid).setParameter("date",(Date) date).getResultList();
       JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
       for (Object[] row : queryList) {
           JsonObject personObject = Json.createObjectBuilder().
                   add("totalcaloriegoal", (BigDecimal)row[0])
                   .build();
           arrayBuilder.add(personObject);
       }
       JsonArray jArray = arrayBuilder.build();
       return jArray;
   }
    
    
    @GET
    @Path("findByUsernameAndDate/{name}/{date}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findByUsernameAndDate(@PathParam("name") String name, @PathParam("date") Date date) {
        TypedQuery<Report> q = em.createQuery("SELECT r FROM Report r WHERE r.userid.name=:name AND r.date=:date",Report.class);
        q.setParameter("name", name);
         q.setParameter("date", date);
        return q.getResultList();
    }
    
    
     /*@GET
    @Path("findtest/")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findtest() {
        TypedQuery<Report> q = em.createQuery("SELECT r.totalcaloriesburned FROM Report r",Report.class);
        return q.getResultList();
    }
    */
    
   /* @GET
    @Path("findByUserIdBetweenDate/{userid}/{startedate}/{enddate}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findByUserIdBetweenDate(@PathParam("userid") Integer userid,@PathParam("startedate") Date startedate,@PathParam("enddate") Date enddate) {
        TypedQuery<Report> q = em.createQuery("SELECT r FROM Report r WHERE r.userid.userid=:userid AND r.date BETWEEN :startedate AND :enddate ",Report.class);
        q.setParameter("userid", userid);
        q.setParameter("startedate", startedate,TemporalType.DATE);
        q.setParameter("enddate", enddate, TemporalType.DATE);
        return q.getResultList();
    }*/
    
    @GET
    @Path("findByUserIdBetweenDate/{userid}/{startedate}/{enddate}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CalorisDurarion> findByUserIdBetweenDate(@PathParam("userid") Integer userid,@PathParam("startedate") Date startedate,@PathParam("enddate") Date enddate){
        TypedQuery<CalorisDurarion> q = em.createQuery("SELECT new entity.CalorisDurarion(r.totalcaloriesconsumed, r.totalcaloriesburned,r.totalstepstaken, r.totalcaloriegoal) From Report As r WHERE r.userid.userid=:userid AND r.date BETWEEN :startedate AND :enddate",CalorisDurarion.class);
         q.setParameter("userid", userid);
        q.setParameter("startedate", startedate,TemporalType.DATE);
        q.setParameter("enddate", enddate, TemporalType.DATE);
        return q.getResultList();
    }
    
    
    @GET
    @Path("findRemainingCalorie/{userid}/{date}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CalorisDurarion> findRemainingCalorie(@PathParam("userid") Integer userid,@PathParam("date") Date date){
        TypedQuery<CalorisDurarion> q = em.createQuery("SELECT new entity.CalorisDurarion(r.totalcaloriesconsumed, r.totalcaloriesburned, r.totalcaloriegoal) From Report As r WHERE r.userid.userid=:userid AND r.date = :date",CalorisDurarion.class);
         q.setParameter("userid", userid);
        q.setParameter("date", date);
        return q.getResultList();
    }
  
    @GET
    @Path("findCalorieGoal/{userid}/{date}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Goals> findCalorieGoal(@PathParam("userid") Integer userid,@PathParam("date") Date date){
        TypedQuery<Goals> q = em.createQuery("SELECT new entity.Goals(r.totalcaloriegoal) From Report As r WHERE r.userid.userid=:userid AND r.date = :date",Goals.class);
         q.setParameter("userid", userid);
        q.setParameter("date", date);
        return q.getResultList();
    }
    /*
     @GET
    @Path("findCalorieGoal/{userid}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Goals> findCalorieGoal(@PathParam("userid") Integer userid){
        TypedQuery<Goals> q = em.createQuery("SELECT new entity.Goals(r.totalcaloriegoal) From Report As r WHERE r.userid.userid=:userid",Goals.class);
         q.setParameter("userid", userid);
      
        return q.getResultList();
    }
    */
    
    @GET
    @Path("findByTotalcaloriesburned/{totalcaloriesburned}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findByTotalcaloriesburned(@PathParam("totalcaloriesburned") BigDecimal totalcaloriesburned) {
        Query query = em.createNamedQuery("Report.findByTotalcaloriesburned");
        query.setParameter("totalcaloriesburned", totalcaloriesburned);
        return query.getResultList();
    }
    /*
    @GET
    @Path("findByReport1/{userid}/{date}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findByReport1(@PathParam("userid") Integer userid, @PathParam("date") Date date) {
        Query query = em.createNamedQuery("Report.findByReport1");
       query.setParameter("userid", userid);
        query.setParameter("date", date);
        return query.getResultList();
    }
    */
    @GET
    @Path("findByTotalcaloriegoal/{totalcaloriegoal}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findByTotalcaloriegoal(@PathParam("totalcaloriegoal") BigDecimal totalcaloriegoal) {
        Query query = em.createNamedQuery("Report.findByTotalcaloriegoal");
        query.setParameter("totalcaloriegoal", totalcaloriegoal);
        return query.getResultList();
    }
    
    @GET
    @Path("findByTotalstepstaken/{totalstepstaken}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findByTotalstepstaken(@PathParam("totalstepstaken") Integer totalstepstaken) {
        Query query = em.createNamedQuery("Report.findByTotalstepstaken");
        query.setParameter("totalstepstaken", totalstepstaken);
        return query.getResultList();
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
