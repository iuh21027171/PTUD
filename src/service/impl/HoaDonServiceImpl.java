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
		return thongKeDao.getSoLuongHoaDon(ngayBatDau, ngayKetThuc);
	}

	@Override
	public double getDoanhThu(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
		return thongKeDao.getDoanhThu(ngayBatDau, ngayKetThuc);
	}

	@Override
	public double getDoanhThuTheoMaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV)
			throws SQLException {
		return thongKeDao.getDoanhThuTheoMaNhanVien(ngayBatDau, ngayKetThuc, maNV);
	}

	@Override
	public int getSoLuongHoaDonTheoMaNV(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV) throws SQLException {
		return thongKeDao.getSoLuongHoaDonTheoMaNV(ngayBatDau, ngayKetThuc, maNV);
	}

	@Override
	public int setNullChoMaNhanVienTrongHoaDon(String maNV) throws SQLException {
		return hoaDonDao.setNullChoMaNhanVienTrongHoaDon(maNV);
	}

	@Override
	public List<HoaDon> getHoaDonTheoMa(String maHD) throws SQLException {
		return hoaDonDao.getHoaDonTheoMa(maHD);
	}

	@Override
	public List<HoaDon> getDSHoaDon() throws SQLException {
		return hoaDonDao.getDSHoaDon();
	}

	@Override
	public int doiThongTinHoaDonSauKhiXoa(String tenNV) throws SQLException {
		return hoaDonDao.doiThongTinHoaDonSauKhiXoa(tenNV);
	}

	@Override
	public int themHoaDon(HoaDon hd) throws SQLException {
		return hoaDonDao.themHoaDon(hd);
	}

	@Override
	public List<HoaDon> getHoaDonThuong() throws SQLException {
		return hoaDonDao.getHoaDonThuong();
	}

	@Override
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