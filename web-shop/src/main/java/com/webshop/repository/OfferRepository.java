package com.webshop.repository;

import com.webshop.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer,Long>
{

}
