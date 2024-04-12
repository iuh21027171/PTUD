package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public interface HoaDonService {
	public int getSoLuongHoaDon(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException;

	public double getDoanhThu(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException;

	public double getDoanhThuTheoMaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV)
			throws SQLException;

	public int getSoLuongHoaDonTheoMaNV(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV) throws SQLException;

<<<<<<< HEAD
	public int setNullChoMaNhanVienTrongHoaDon(String maNV);

	public List<HoaDon> getHoaDonTheoMa(String maHD);

	public List<HoaDon> getDSHoaDon() throws SQLException;

	public int doiThongTinHoaDonSauKhiXoa(String tenNV);

	public int themHoaDon(HoaDon hd) throws SQLException;
	
	public List<HoaDon> getHoaDonThuong() ;
	//DSHD theo mã
	public HoaDon timHoaDonTheoMa(String maHoaDon);
	public List<HoaDon> getHoaDonTheoTen(String tenNV);
//	Tim kiem hoa don theo sdt khach hang
	
	public List<HoaDon> timHoaDonTheoSDT(String sdt);
	public List<HoaDon> timHoaDonTheoTenKH(String ten);
=======
	public int setNullChoMaNhanVienTrongHoaDon(String maNV) throws SQLException;

	public List<HoaDon> getHoaDonTheoMa(String maHD) throws SQLException;

	public List<HoaDon> getDSHoaDon() throws SQLException;

	public int doiThongTinHoaDonSauKhiXoa(String tenNV) throws SQLException;

	public int themHoaDon(HoaDon hd) throws SQLException;
	
	public List<HoaDon> getHoaDonThuong() throws SQLException;
	//DSHD theo mã
	public HoaDon timHoaDonTheoMa(String maHoaDon) throws SQLException;
	public List<HoaDon> getHoaDonTheoTen(String tenNV) throws SQLException;
//	Tim kiem hoa don theo sdt khach hang
	
	public List<HoaDon> timHoaDonTheoSDT(String sdt) throws SQLException;
	public List<HoaDon> timHoaDonTheoTenKH(String ten) throws SQLException;
>>>>>>> 18a2abe (Update DAO & Service)
}