package org.example.couponservice.repos;

import org.example.couponservice.model.Coupon;
import org.springframework.data.repository.CrudRepository;

public interface CouponRepository extends CrudRepository<org.example.couponservice.model.Coupon, Long> {
    Coupon findByCode(String code);
}
