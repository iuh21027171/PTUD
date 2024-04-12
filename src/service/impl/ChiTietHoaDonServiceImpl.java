package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ChiTietHoaDonDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import service.ChiTietHoaDonService;

public class ChiTietHoaDonServiceImpl implements ChiTietHoaDonService {
	ChiTietHoaDonDao chiTietHoaDonDao = new ChiTietHoaDonDao();

	@Override
<<<<<<< HEAD
	public List<ChiTietHoaDon> getCTHoaDonTheoMaHoaDon(String maHD) {
		// TODO Auto-generated method stub
		return chiTietHoaDonDao.getCTHoaDonTheoMaHoaDon(maHD);
=======
	public ArrayList<ChiTietHoaDon> getCTHoaDonTheoMaHoaDon(String maHD) {
		try {
			return new ArrayList<>(chiTietHoaDonDao.getCTHoaDonTheoMaHoaDon(maHD));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
>>>>>>> 18a2abe (Update DAO & Service)
	}

	@Override
	public ArrayList<ChiTietHoaDon> getCTHDTheoHoaDon(HoaDon hoaDon) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		return chiTietHoaDonDao.getCTHDTheoHoaDon(hoaDon);
	}

	@Override
	public List<ChiTietHoaDon> getAllCTHD() throws SQLException {
		// TODO Auto-generated method stub
		return chiTietHoaDonDao.getAllCTHD();
=======
		try {
			return new ArrayList<>(chiTietHoaDonDao.getCTHDTheoHoaDon(hoaDon));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<ChiTietHoaDon> getAllCTHD() {
		try {
			return new ArrayList<>(chiTietHoaDonDao.getAllCTHD());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
>>>>>>> 18a2abe (Update DAO & Service)
	}

	@Override
	public int addChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		return chiTietHoaDonDao.addChiTietHoaDon(chiTietHoaDon);
=======
		try {
			return chiTietHoaDonDao.addChiTietHoaDon(chiTietHoaDon);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
>>>>>>> 18a2abe (Update DAO & Service)
	}

	@Override
	public long getTien(String maHD) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		return chiTietHoaDonDao.getTien(maHD);
	}

}
=======
		try {
			return chiTietHoaDonDao.getTien(maHD);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
>>>>>>> 18a2abe (Update DAO & Service)
