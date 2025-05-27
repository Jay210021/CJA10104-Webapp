package com.coupon.model;

import java.util.*;

public interface CouponDAO_interface {
          public void insert(CouponVO couponVO);
          public void update(CouponVO couponVO);
          public void delete(Integer couponno);
          public CouponVO findByPrimaryKey(Integer couponno);
          public List<CouponVO> getAll();
}
