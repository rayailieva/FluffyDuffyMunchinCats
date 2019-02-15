package fdmc.repository;

import fdmc.domain.entities.Cat;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class CatRepositoryImpl implements CatRepository {

    private final EntityManager entityManager;

    @Inject
    public CatRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Cat save(Cat cat) {
       this.entityManager.getTransaction().begin();
       this.entityManager.persist(cat);
       this.entityManager.getTransaction().commit();

       return cat;
    }

    @Override
    public List<Cat> findAll() {
        this.entityManager.getTransaction().begin();
        List<Cat> cats = this.entityManager
                .createQuery("" +
                        "SELECT c " +
                        "FROM cats c " +
                        "", Cat.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return cats;
    }

    @Override
    public Cat findById(String s) {
        this.entityManager.getTransaction().begin();
        Cat cat = this.entityManager.createQuery("" +
                "SELECT c " +
                "FROM cats c " +
                "WHERE c.id = :id ", Cat.class)
                .setParameter("id", s)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return cat;
    }

    @Override
    public void remove(String s) {
        this.entityManager.getTransaction().begin();
        this.entityManager.createQuery("" +
                "DELETE " +
                "FROM cats c " +
                "WHERE c.id = :id ")
                .setParameter("id", s).executeUpdate();
        this.entityManager.getTransaction().commit();
    }
}
