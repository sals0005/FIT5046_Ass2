/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restws.service;

import java.math.BigDecimal;
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
import restws.Food;

/**
 *
 * @author sarah
 */
@Stateless
@Path("restws.food")
public class FoodFacadeREST extends AbstractFacade<Food> {

    @PersistenceContext(unitName = "CaloriTracker3PU")
    private EntityManager em;

    public FoodFacadeREST() {
        super(Food.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Food entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Food entity) {
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
    public Food find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("findByFname/{fname}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Food> findByFname(@PathParam("fname") String fname) {
        Query query = em.createNamedQuery("Food.findByFname");
        query.setParameter("fname", fname);
        return query.getResultList();
    }
    
    @GET
    @Path("findByCalorie/{calorie}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Food> findByCalorie(@PathParam("calorie") BigDecimal calorie) {
        Query query = em.createNamedQuery("Food.findByCalorie");
        query.setParameter("calorie", calorie);
        return query.getResultList();
    }
    
     @GET
    @Path("findByFcategory/{fcategory}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Food> findByFcategory(@PathParam("fcategory") String fcategory) {
        Query query = em.createNamedQuery("Food.findByFcategory");
        query.setParameter("fcategory", fcategory);
        return query.getResultList();
    }
      @GET
    @Path("findByServingunit/{servingunit}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Food> findByServingunit(@PathParam("servingunit") String servingunit) {
        Query query = em.createNamedQuery("Food.findByServingunit");
        query.setParameter("servingunit", servingunit);
        return query.getResultList();
    }
    
     @GET
    @Path("findByServingamount/{servingamount}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Food> findByServingamount(@PathParam("servingamount") BigDecimal servingamount) {
        Query query = em.createNamedQuery("Food.findByServingamount");
        query.setParameter("servingamount", servingamount);
        return query.getResultList();
    }
    
     @GET
    @Path("findByFat/{fat}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Food> findByFat(@PathParam("fat") BigDecimal fat) {
        Query query = em.createNamedQuery("Food.findByFat");
        query.setParameter("fat", fat);
        return query.getResultList();
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Food> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Food> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
