package org.example.couponservice.controller;

import org.example.couponservice.model.Coupon;
import org.example.couponservice.repos.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon")
public class CouponRestController {

    @Autowired
    CouponRepository couponRepository;

    @PostMapping
    public Coupon add(@RequestBody Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @GetMapping("/{code}")
    public Coupon getCoupon(@PathVariable String code) {
        return couponRepository.findByCode(code);
    }
}
