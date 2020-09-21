/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restws.service;

import entity.FoodConsum;
import java.sql.Date;
import java.util.List;
import javax.ejb.Stateless;
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
import restws.Consumption;

/**
 *
 * @author sarah
 */
@Stateless
@Path("restws.consumption")
public class ConsumptionFacadeREST extends AbstractFacade<Consumption> {

    @PersistenceContext(unitName = "CaloriTracker3PU")
    private EntityManager em;

    public ConsumptionFacadeREST() {
        super(Consumption.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Consumption entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Consumption entity) {
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
    public Consumption find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("findByQuantity/{quantity}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findByQuantity(@PathParam("quantity") Integer quantity) {
        Query query = em.createNamedQuery("Consumption.findByQuantity");
        query.setParameter("quantity", quantity);
        return query.getResultList();
    }
    
     @GET
    @Path("findByDate/{date}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findByDate(@PathParam("date") Date date) {
        Query query = em.createNamedQuery("Consumption.findByDate");
        query.setParameter("date", date);
        return query.getResultList();
    }
    
    @GET
    @Path("findByUser/{userid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findByUser(@PathParam("userid") Integer userid) {
        Query query = em.createNamedQuery("Consumption.findByUser");
        query.setParameter("userid", userid);
        return query.getResultList();
    }
    @GET
    @Path("findByFoodNameAndUId/{fname}/{userid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findByFoodNameAndUId(@PathParam("fname") String fname,@PathParam("userid") Integer userid) {
        Query query = em.createNamedQuery("Consumption.findByFoodNameAndUId");
         query.setParameter("fname", fname);
        query.setParameter("userid", userid);
        return query.getResultList();
    }
    @GET
    @Path("findByFood/{fid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findByFood(@PathParam("fid") Integer fid) {
        Query query = em.createNamedQuery("Consumption.findByFood");
        query.setParameter("fid", fid);
        return query.getResultList();
    }


    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    
    @GET
    @Path("findByIdDateFoodConsum/{userid}/{date}")
    @Produces({MediaType.TEXT_PLAIN, MediaType.TEXT_PLAIN})
    public int findByIdDateFoodConsum(@PathParam("userid") Integer userid,@PathParam("date") Date date){
        TypedQuery<Consumption> q = em.createQuery("SELECT c From Consumption c WHERE c.userid.userid=:userid AND c.date = :date",Consumption.class);
        q.setParameter("userid", userid);
        q.setParameter("date", date);
        Consumption cons=q.getSingleResult();
        int foodConsum = cons.totalCaloriesConsumed();
       // System.out.println("Inside facade "+foodConsum);
        return foodConsum;
//return cons.totalCaloriesConsumed();
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
