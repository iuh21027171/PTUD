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
<<<<<<< HEAD
	public ArrayList<TheLoaiSach> getListTheLoaiSach() throws Exception {
		// TODO Auto-generated method stub
		return theLoaiDao.getListTheLoaiSach();
	}

	@Override
	public ArrayList<TheLoaiVanPhongPham> getListTheLoaiVanPhongPham() throws Exception {
		// TODO Auto-generated method stub
=======
	public List<TheLoaiSach> getListTheLoaiSach() throws Exception {
		try {
			return theLoaiDao.getListTheLoaiSach();
		} catch (Exception e) {
			throw new Exception("Error while fetching list of TheLoaiSach", e);
		}
	}

	@Override
	public List<TheLoaiVanPhongPham> getListTheLoaiVanPhongPham() throws Exception {
>>>>>>> 18a2abe (Update DAO & Service)
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
<<<<<<< HEAD
		// TODO Auto-generated method stub
=======
>>>>>>> 18a2abe (Update DAO & Service)
		return theLoaiDao.timTheLoaiSach(TheLoai);
	}

	@Override
	public TheLoaiVanPhongPham timTheLoaiVanPhongPham(String TheLoai) throws SQLException {
<<<<<<< HEAD
		// TODO Auto-generated method stub
=======
>>>>>>> 18a2abe (Update DAO & Service)
		return theLoaiDao.timTheLoaiVanPhongPham(TheLoai);
	}

	@Override
	public List<TheLoaiSach> getSachTheoTheLoai(String maTL) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		return theLoaiDao.getSachTheoTheLoai(maTL);
	}

}
=======
		try {
			return theLoaiDao.getSachTheoTheLoai(maTL);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
}
>>>>>>> 18a2abe (Update DAO & Service)
