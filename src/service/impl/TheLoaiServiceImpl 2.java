package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TheLoaiDao;
import entity.TheLoaiSach;
import entity.TheLoaiVanPhongPham;
import service.TheLoaiService;

public class TheLoaiServiceImpl implements TheLoaiService {
	TheLoaiDao theLoaiDao = new TheLoaiDao();

	@Override
	public List<TheLoaiSach> getListTheLoaiSach() throws Exception {
		try {
			return theLoaiDao.getListTheLoaiSach();
		} catch (Exception e) {
			throw new Exception("Error while fetching list of TheLoaiSach", e);
		}
	}

	@Override
	public List<TheLoaiVanPhongPham> getListTheLoaiVanPhongPham() throws Exception {
		return theLoaiDao.getListTheLoaiVanPhongPham();
	}

	@Override
	public boolean themTheLoaiSach(TheLoaiSach t) throws Exception {
		if(theLoaiDao.kiemTraTonTaiTheLoaiSach(t.getTenLoai())) {
			return false;
		}
		return theLoaiDao.themTheLoaiSach(t);
	}

	@Override
	public boolean themTheLoaiVanPhongPham(TheLoaiVanPhongPham t) throws Exception {
		if(theLoaiDao.kiemTraTonTaiTheLoaiVPP(t.getTenLoai())) {
			return false;
		}
		return theLoaiDao.themTheLoaiVanPhongPham(t);
	}

	@Override
	public TheLoaiSach timTheLoaiSach(String TheLoai) throws SQLException {
		return theLoaiDao.timTheLoaiSach(TheLoai);
	}

	@Override
	public TheLoaiVanPhongPham timTheLoaiVanPhongPham(String TheLoai) throws SQLException {
		return theLoaiDao.timTheLoaiVanPhongPham(TheLoai);
	}

	@Override
	public List<TheLoaiSach> getSachTheoTheLoai(String maTL) {
		try {
			return theLoaiDao.getSachTheoTheLoai(maTL);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
}