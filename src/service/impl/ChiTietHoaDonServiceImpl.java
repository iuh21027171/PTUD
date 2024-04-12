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
	public ArrayList<ChiTietHoaDon> getCTHoaDonTheoMaHoaDon(String maHD) {
		try {
			return new ArrayList<>(chiTietHoaDonDao.getCTHoaDonTheoMaHoaDon(maHD));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<ChiTietHoaDon> getCTHDTheoHoaDon(HoaDon hoaDon) {
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
	}

	@Override
	public int addChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		try {
			return chiTietHoaDonDao.addChiTietHoaDon(chiTietHoaDon);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public long getTien(String maHD) {
		try {
			return chiTietHoaDonDao.getTien(maHD);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}