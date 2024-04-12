package service.impl;

import java.sql.SQLException;
import java.util.List;

import dao.HoaDonDoiTraDao;
import entity.HoaDonDoiTra;
import service.HoaDonDoiTraService;

public class HoaDonDoiTraServiceImpl implements HoaDonDoiTraService {
	HoaDonDoiTraDao hoaDonDoiTraDao = new HoaDonDoiTraDao();

	@Override
	public int themHoaDonDoiTra(HoaDonDoiTra hddt) throws SQLException {
		// TODO Auto-generated method stub
		return hoaDonDoiTraDao.themHoaDonDoiTra(hddt);
	}

	@Override
	public List<HoaDonDoiTra> getHoaDonDoiTraTheoMa(String maHDDT) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		return hoaDonDoiTraDao.getHoaDonDoiTraTheoMa(maHDDT);
	}
=======
        try {
            return hoaDonDoiTraDao.getHoaDonDoiTraTheoMa(maHDDT);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
>>>>>>> 18a2abe (Update DAO & Service)

	@Override
	public List<HoaDonDoiTra> getDSHoaDonDoiTra() throws SQLException {
		// TODO Auto-generated method stub
		return hoaDonDoiTraDao.getDSHoaDonDoiTra();
	}

	@Override
	public int editTienKhachTra(HoaDonDoiTra hddt) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		return hoaDonDoiTraDao.editTienKhachTra(hddt);
	}
=======
        try {
            return hoaDonDoiTraDao.editTienKhachTra(hddt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
>>>>>>> 18a2abe (Update DAO & Service)

	@Override
	public List<HoaDonDoiTra> getMaHoaDonDoiTraByMaHDCu(String maHDCu) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		return hoaDonDoiTraDao.getMaHoaDonDoiTraByMaHDCu(maHDCu);
	}
=======
        try {
            return hoaDonDoiTraDao.getMaHoaDonDoiTraByMaHDCu(maHDCu);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
>>>>>>> 18a2abe (Update DAO & Service)

	@Override
	public int editTienPhaiTru(HoaDonDoiTra hddt) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		return hoaDonDoiTraDao.editTienPhaiTru(hddt);
	}
=======
        try {
            return hoaDonDoiTraDao.editTienPhaiTru(hddt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
>>>>>>> 18a2abe (Update DAO & Service)

	@Override
	public List<HoaDonDoiTra> getToanBoDSHoaDonDoiTra() throws SQLException {
		// TODO Auto-generated method stub
		return hoaDonDoiTraDao.getToanBoDSHoaDonDoiTra();
	}

	@Override
	public HoaDonDoiTra timHoaDonDoiTraTheoMa(String maHoaDon) {
		// TODO Auto-generated method stub
		try {
			return hoaDonDoiTraDao.timHoaDonDoiTraTheoMa(maHoaDon);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<HoaDonDoiTra> getHoaDonDoiTraTheoTen(String tenNV) throws SQLException {
		// TODO Auto-generated method stub
		return hoaDonDoiTraDao.getHoaDonDoiTraTheoTen(tenNV);
	}

	@Override
	public List<HoaDonDoiTra> getHoaDonDoiTraTheoSDT(String sdt) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		return hoaDonDoiTraDao.getHoaDonDoiTraTheoSDT(sdt);
	}
=======
        try {
            return hoaDonDoiTraDao.getHoaDonDoiTraTheoSDT(sdt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
>>>>>>> 18a2abe (Update DAO & Service)

	@Override
	public List<HoaDonDoiTra> getHoaDonDoiTraTheoTenKH(String tenKH) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		return hoaDonDoiTraDao.getHoaDonDoiTraTheoTenKH(tenKH);
	}
=======
        try {
            return hoaDonDoiTraDao.getHoaDonDoiTraTheoTenKH(tenKH);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
>>>>>>> 18a2abe (Update DAO & Service)

}
