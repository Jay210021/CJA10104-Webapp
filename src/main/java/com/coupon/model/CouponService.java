package com.coupon.model;

import java.util.List;

public class CouponService {

	private CouponDAO_interface dao;

	public CouponService() {
		dao = new CouponJDBCDAO();
	}

	public CouponVO addCoupon(String couponCode, Integer discountValue, Integer minSpend,
			java.sql.Date startDate, java.sql.Date endDate) {

		CouponVO couponVO = new CouponVO();

		couponVO.setCouponCode(couponCode);
		couponVO.setDiscountValue(discountValue);
		couponVO.setMinSpend(minSpend);
		couponVO.setStartDate(startDate);
		couponVO.setEndDate(endDate);
		dao.insert(couponVO);

		return couponVO;
	}

	public CouponVO updateCoupon(Integer couponId, String couponCode, Integer discountValue, Integer minSpend,
			java.sql.Date startDate, java.sql.Date endDate) {

		CouponVO couponVO = new CouponVO();

		couponVO.setCouponId(couponId);
		couponVO.setCouponCode(couponCode);
		couponVO.setDiscountValue(discountValue);
		couponVO.setMinSpend(minSpend);
		couponVO.setStartDate(startDate);
		couponVO.setEndDate(endDate);
		dao.insert(couponVO);

		return couponVO;
	}

	public void deleteCoupon(Integer couponId) {
		dao.delete(couponId);
	}

	public CouponVO getOneCoupon(Integer couponId) {
		return dao.findByPrimaryKey(couponId);
	}

	public List<CouponVO> getAll() {
		return dao.getAll();
	}
}
