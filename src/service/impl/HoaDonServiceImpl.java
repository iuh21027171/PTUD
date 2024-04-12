package service.impl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import dao.HoaDonDao;
import dao.SanPhamDao;
import dao.ThongKeDao;
import entity.HoaDon;
import service.HoaDonService;

public class HoaDonServiceImpl implements HoaDonService {
	HoaDonDao hoaDonDao = new HoaDonDao();
	ThongKeDao thongKeDao = new ThongKeDao();

	@Override
	public int getSoLuongHoaDon(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
<<<<<<< HEAD
		// TODO Auto-generated method stub
=======
>>>>>>> 18a2abe (Update DAO & Service)
		return thongKeDao.getSoLuongHoaDon(ngayBatDau, ngayKetThuc);
	}

	@Override
	public double getDoanhThu(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
<<<<<<< HEAD
		// TODO Auto-generated method stub
=======
>>>>>>> 18a2abe (Update DAO & Service)
		return thongKeDao.getDoanhThu(ngayBatDau, ngayKetThuc);
	}

	@Override
	public double getDoanhThuTheoMaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV)
			throws SQLException {
<<<<<<< HEAD
		// TODO Auto-generated method stub
=======
>>>>>>> 18a2abe (Update DAO & Service)
		return thongKeDao.getDoanhThuTheoMaNhanVien(ngayBatDau, ngayKetThuc, maNV);
	}

	@Override
	public int getSoLuongHoaDonTheoMaNV(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV) throws SQLException {
<<<<<<< HEAD
		// TODO Auto-generated method stub
=======
>>>>>>> 18a2abe (Update DAO & Service)
		return thongKeDao.getSoLuongHoaDonTheoMaNV(ngayBatDau, ngayKetThuc, maNV);
	}

	@Override
<<<<<<< HEAD
	public int setNullChoMaNhanVienTrongHoaDon(String maNV) {
		// TODO Auto-generated method stub
=======
	public int setNullChoMaNhanVienTrongHoaDon(String maNV) throws SQLException {
>>>>>>> 18a2abe (Update DAO & Service)
		return hoaDonDao.setNullChoMaNhanVienTrongHoaDon(maNV);
	}

	@Override
<<<<<<< HEAD
	public List<HoaDon> getHoaDonTheoMa(String maHD) {
		// TODO Auto-generated method stub
=======
	public List<HoaDon> getHoaDonTheoMa(String maHD) throws SQLException {
>>>>>>> 18a2abe (Update DAO & Service)
		return hoaDonDao.getHoaDonTheoMa(maHD);
	}

	@Override
	public List<HoaDon> getDSHoaDon() throws SQLException {
<<<<<<< HEAD
		// TODO Auto-generated method stub
=======
>>>>>>> 18a2abe (Update DAO & Service)
		return hoaDonDao.getDSHoaDon();
	}

	@Override
<<<<<<< HEAD
	public int doiThongTinHoaDonSauKhiXoa(String tenNV) {
		// TODO Auto-generated method stub
=======
	public int doiThongTinHoaDonSauKhiXoa(String tenNV) throws SQLException {
>>>>>>> 18a2abe (Update DAO & Service)
		return hoaDonDao.doiThongTinHoaDonSauKhiXoa(tenNV);
	}

	@Override
	public int themHoaDon(HoaDon hd) throws SQLException {
<<<<<<< HEAD
		// TODO Auto-generated method stub
=======
>>>>>>> 18a2abe (Update DAO & Service)
		return hoaDonDao.themHoaDon(hd);
	}

	@Override
<<<<<<< HEAD
	public List<HoaDon> getHoaDonThuong() {
		// TODO Auto-generated method stub
=======
	public List<HoaDon> getHoaDonThuong() throws SQLException {
>>>>>>> 18a2abe (Update DAO & Service)
		return hoaDonDao.getHoaDonThuong();
	}

	@Override
<<<<<<< HEAD
	public HoaDon timHoaDonTheoMa(String maHoaDon) {
		// TODO Auto-generated method stub
		try {
			return hoaDonDao.timHoaDonTheoMa(maHoaDon);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<HoaDon> getHoaDonTheoTen(String tenNV) {
		// TODO Auto-generated method stub
		try {
			return hoaDonDao.getHoaDonTheoTen(tenNV);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<HoaDon> timHoaDonTheoSDT(String sdt) {
		// TODO Auto-generated method stub
		try {
			return hoaDonDao.timHoaDonTheoSDT(sdt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<HoaDon> timHoaDonTheoTenKH(String ten) {
		// TODO Auto-generated method stub
		try {
			return hoaDonDao.timHoaDonTheoTenKH(ten);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
=======
	public HoaDon timHoaDonTheoMa(String maHoaDon) throws SQLException {
		return hoaDonDao.timHoaDonTheoMa(maHoaDon);
	}

	@Override
	public List<HoaDon> getHoaDonTheoTen(String tenNV) throws SQLException {
		return hoaDonDao.getHoaDonTheoTen(tenNV);
	}

	@Override
	public List<HoaDon> timHoaDonTheoSDT(String sdt) throws SQLException {
		return hoaDonDao.timHoaDonTheoSDT(sdt);
	}

	@Override
	public List<HoaDon> timHoaDonTheoTenKH(String ten) throws SQLException {
		return hoaDonDao.timHoaDonTheoTenKH(ten);
	}
}
>>>>>>> 18a2abe (Update DAO & Service)
