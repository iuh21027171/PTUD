package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.XuatXuDao;
import entity.XuatXu;
import service.XuatXuService;

public class XuatXuServiceImpl implements XuatXuService {
	XuatXuDao xuatXuDao = new XuatXuDao();

	@Override
<<<<<<< HEAD
	public ArrayList<XuatXu> getListXuatXu() throws Exception {
		// TODO Auto-generated method stub
=======
	public List<XuatXu> getListXuatXu() throws Exception {
>>>>>>> 18a2abe (Update DAO & Service)
		return xuatXuDao.getListXuatXu();
	}

	@Override
	public boolean themXuatXu(XuatXu x) throws Exception {
<<<<<<< HEAD
		if(xuatXuDao.kiemTraTonTaiXuatXu(x.getTenXuatXu()))
			return false;
=======
		if(xuatXuDao.kiemTraTonTaiXuatXu(x.getTenXuatXu())) {
			return false;
		}
>>>>>>> 18a2abe (Update DAO & Service)
		return xuatXuDao.themXuatXu(x);
	}

	@Override
	public boolean xoaXuatXu(String maXuatXu) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<XuatXu> getXuatXu(String maXuatXu) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		return xuatXuDao.getXuatXu(maXuatXu);
	}

	@Override
	public XuatXu timXuatXu(String XuatXu) throws SQLException {
		// TODO Auto-generated method stub
		return xuatXuDao.timXuatXu(XuatXu);
	}

}
=======
		try {
			return xuatXuDao.getXuatXu(maXuatXu);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public XuatXu timXuatXu(String XuatXu) {
		try {
			return xuatXuDao.timXuatXu(XuatXu);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
>>>>>>> 18a2abe (Update DAO & Service)
