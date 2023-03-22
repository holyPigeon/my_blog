package com.example.my_blog.domain.item.repository;

import com.example.my_blog.domain.item.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {

  private final EntityManager em;

  @Override
  public void save(Item item) {
    em.persist(item);
  }

  @Override
  public Item findById(Long id) {

    return em.find(Item.class, id);
  }

  @Override
  public Item findByName(String name) {

    return em.createQuery("SELECT i FROM Item i WHERE i.name = :name", Item.class)
        .setParameter("name", name)
        .getSingleResult();
  }

  @Override
  public List<Item> findByMemberId(Long memberId) {

    return em.createQuery("select distinct i from Item i" +
            " join fetch i.member m where m.id = :memberId", Item.class)
        .setParameter("memberId", memberId)
        .getResultList();
  }

  @Override
  public List<Item> findAll() {

    return em.createQuery("SELECT i FROM Item i", Item.class)
        .getResultList();
  }


  @Override
  public void deleteById(Long id) {
    Item findItem = em.find(Item.class, id);

    em.remove(findItem);
  }
}
