package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;

import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;

public class ThongKeDao {

	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;
	private List<SanPham> dsSP;
	private List<NhanVien> dsNV;
	private List<KhachHang> dsKH;
	private ArrayList<NhanVien> dsNV1;

	public ThongKeDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public List<NhanVien> getNhanVienBanNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
		List<NhanVien> dsNV = new ArrayList<>();
		String query = "SELECT NhanVien.maNhanVien FROM ChiTietHoaDon INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN NhanVien ON HoaDon.maNhanVien = NhanVien.maNhanVien WHERE HoaDon.ngayLapHoaDon between ? and ? GROUP BY NhanVien.maNhanVien HAVING count(HoaDon.maHoaDon) >= all(SELECT count(HoaDon.maHoaDon) FROM chitiethoadon INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN NhanVien ON HoaDon.maNhanVien = NhanVien.maNhanVien WHERE HoaDon.ngayLapHoaDon between ? and ? GROUP BY NhanVien.maNhanVien)";
		ps = con.prepareStatement(query);
		ps.setString(1, ngayBatDau.toString());
		ps.setString(2, ngayKetThuc.toString());
		ps.setString(3, ngayBatDau.toString());
		ps.setString(4, ngayKetThuc.toString());
		rs = ps.executeQuery();

		while (rs.next()) {
			dsNV.add(new NhanVien(rs.getString(1)));
		}
		return dsNV;
	}

	public List<SanPham> getSanPhamBanNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
		List<SanPham> dsSP = new ArrayList<>();
		String query = "SELECT SanPham.maSanPham FROM ChiTietHoaDon INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN SanPham ON ChiTietHoaDon.maSanPham = SanPham.maSanPham WHERE HoaDon.ngayLapHoaDon BETWEEN ? and ? GROUP BY SanPham.maSanPham HAVING SUM(ChiTietHoaDon.soLuong) >= ALL(SELECT SUM(ChiTietHoaDon.soLuong) AS 'TongSoLuongDaBan' FROM ChiTietHoaDon INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN SanPham ON ChiTietHoaDon.maSanPham = SanPham.maSanPham WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ? GROUP BY SanPham.maSanPham)";
		ps = con.prepareStatement(query);
		ps.setString(1, ngayBatDau.toString());
		ps.setString(2, ngayKetThuc.toString());
		ps.setString(3, ngayBatDau.toString());
		ps.setString(4, ngayKetThuc.toString());
		rs = ps.executeQuery();

		while (rs.next()) {
			dsSP.add(new SanPham(rs.getString(1)));
		}
		return dsSP;
	}

	public int getSoLuongSachTon() throws SQLException {
		String query = "SELECT SUM(soLuongTon) FROM SanPham WHERE loaiSanPham LIKE N'Sách'";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		return rs.next() ? rs.getInt(1) : 0;
	}

	public int getSoLuongVPPTon() throws SQLException {
		String query = "SELECT SUM(soLuongTon) FROM SanPham WHERE loaiSanPham LIKE N'Văn phòng phẩm'";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		return rs.next() ? rs.getInt(1) : 0;
	}

	public int getSoLuongSachLoi() throws SQLException {
		String query = "SELECT SUM(soLuongLoi) FROM SachLoi";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		return rs.next() ? rs.getInt(1) : 0;
	}

	public int getSoLuongHoaDon(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
		String query = "SELECT COUNT(*) FROM HoaDon WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ?";
		ps = con.prepareStatement(query);
		ps.setString(1, ngayBatDau.toString());
		ps.setString(2, ngayKetThuc.toString());
		rs = ps.executeQuery();
		return rs.next() ? rs.getInt(1) : 0;
	}

	public double getDoanhThu(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
		String query = "SELECT SUM(ChiTietHoaDon.soLuong*ChiTietHoaDon.donGia) FROM ChiTietHoaDon INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ?";
		ps = con.prepareStatement(query);
		ps.setString(1, ngayBatDau.toString());
		ps.setString(2, ngayKetThuc.toString());
		rs = ps.executeQuery();
		return rs.next() ? rs.getDouble(1) : 0;
	}

	public List<KhachHang> getKhachHangMuaNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
		List<KhachHang> dsKH = new ArrayList<>();
		String query = "SELECT KhachHang.maKhachHang FROM ChiTietHoaDon INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang WHERE HoaDon.ngayLapHoaDon between ? and ? GROUP BY KhachHang.maKhachHang HAVING count(HoaDon.maHoaDon) >= all(SELECT count(HoaDon.maHoaDon) FROM chitiethoadon INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang WHERE HoaDon.ngayLapHoaDon between ? and ? GROUP BY KhachHang.maKhachHang)";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, ngayBatDau.toString());
			ps.setString(2, ngayKetThuc.toString());
			ps.setString(3, ngayBatDau.toString());
			ps.setString(4, ngayKetThuc.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				dsKH.add(new KhachHang(rs.getString(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsKH;
	}

	public int getSoLuongBanCuaSanPhamChayNhat(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
		String query = "SELECT TOP(1) SUM(ChiTietHoaDon.soLuong) FROM SanPham INNER JOIN ChiTietHoaDon ON SanPham.maSanPham = ChiTietHoaDon.maSanPham INNER JOIN HoaDon ON HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ? GROUP BY SanPham.maSanPham";
		ps = con.prepareStatement(query);
		ps.setString(1, ngayBatDau.toString());
		ps.setString(2, ngayKetThuc.toString());
		rs = ps.executeQuery();
		return rs.next() ? rs.getInt(1) : 0;
	}

	public double getTongTienCuaKhachHangTop1(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
		String query = "SELECT TOP(1) SUM(ChiTietHoaDon.soLuong*ChiTietHoaDon.donGia) FROM ChiTietHoaDon INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ? GROUP BY KhachHang.maKhachHang ORDER BY SUM(ChiTietHoaDon.soLuong*ChiTietHoaDon.donGia) DESC";
		ps = con.prepareStatement(query);
		ps.setString(1, ngayBatDau.toString());
		ps.setString(2, ngayKetThuc.toString());
		rs = ps.executeQuery();
		return rs.next() ? rs.getDouble(1) : 0;
	}

	public int getSoLuongHoaDonCuaKhachHangTheoMa(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maKH) throws SQLException {
		String query = "SELECT COUNT(HoaDon.maHoaDon) FROM HoaDon INNER JOIN KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ? AND KhachHang.maKhachHang = ? GROUP BY KhachHang.maKhachHang";
		ps = con.prepareStatement(query);
		ps.setString(1, ngayBatDau.toString());
		ps.setString(2, ngayKetThuc.toString());
		ps.setString(3, maKH);
		rs = ps.executeQuery();
		return rs.next() ? rs.getInt(1) : 0;
	}

	public double getTongTienCuaKhachHangTheoMa(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maKH) throws SQLException {
		String query = "SELECT SUM(ChiTietHoaDon.soLuong*ChiTietHoaDon.donGia) FROM ChiTietHoaDon INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ? AND KhachHang.maKhachHang = ? GROUP BY KhachHang.maKhachHang";
		ps = con.prepareStatement(query);
		ps.setString(1, ngayBatDau.toString());
		ps.setString(2, ngayKetThuc.toString());
		ps.setString(3, maKH);
		rs = ps.executeQuery();
		return rs.next() ? rs.getDouble(1) : 0;
	}

	public double getDoanhThuTheoMaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV) throws SQLException {
		String query = "SELECT SUM(ChiTietHoaDon.soLuong*ChiTietHoaDon.donGia) FROM ChiTietHoaDon INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ? AND HoaDon.maNhanVien=?";
		ps = con.prepareStatement(query);
		ps.setString(1, ngayBatDau.toString());
		ps.setString(2, ngayKetThuc.toString());
		ps.setString(3, maNV);
		rs = ps.executeQuery();
		return rs.next() ? rs.getDouble(1) : 0;
	}

	public int getSoLuongHoaDonTheoMaNV(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV) throws SQLException {
		String query = "SELECT COUNT(*) FROM ChiTietHoaDon INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ? AND HoaDon.maNhanVien=?";
		ps = con.prepareStatement(query);
		ps.setString(1, ngayBatDau.toString());
		ps.setString(2, ngayKetThuc.toString());
		ps.setString(3, maNV);
		rs = ps.executeQuery();
		return rs.next() ? rs.getInt(1) : 0;
	}

	public List<NhanVien> getDoanhThuCuaNhanVien() throws SQLException {
		List<NhanVien> dsNV = new ArrayList<>();
		String query = "SELECT NhanVien.maNhanVien FROM NhanVien JOIN HoaDon ON NhanVien.maNhanVien = HoaDon.maNhanVien";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			dsNV.add(new NhanVien(rs.getString(1)));
		}
		return dsNV;
	}

	public List<NhanVien> thongKeDoanhThu10NVBanNhieuNhat(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
		List<NhanVien> dsNV1 = new ArrayList<>();
		String query = "SELECT TOP(10) NhanVien.maNhanVien FROM NhanVien INNER JOIN HoaDon ON NhanVien.maNhanVien = HoaDon.maNhanVien INNER JOIN ChiTietHoaDon ON HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ? GROUP BY NhanVien.maNhanVien ORDER BY SUM(ChiTietHoaDon.donGia*ChiTietHoaDon.soLuong) DESC";
		ps = con.prepareStatement(query);
		ps.setString(1, ngayBatDau.toString());
		ps.setString(2, ngayKetThuc.toString());
		rs = ps.executeQuery();
		while (rs.next()) {
			dsNV1.add(new NhanVien(rs.getString(1)));
		}
		return dsNV1;
	}


	public List<KhachHang> getTop10KHThanThiet(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
		List<KhachHang> dsKH = new ArrayList<>();
		String query = "SELECT TOP(10) KhachHang.maKhachHang, SUM(ChiTietHoaDon.soLuong*ChiTietHoaDon.donGia) FROM ChiTietHoaDon INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ? GROUP BY KhachHang.maKhachHang ORDER BY SUM(ChiTietHoaDon.soLuong*ChiTietHoaDon.donGia) DESC";
		ps = con.prepareStatement(query);
		ps.setString(1, ngayBatDau.toString());
		ps.setString(2, ngayKetThuc.toString());
		rs = ps.executeQuery();
		while (rs.next()) {
			dsKH.add(new KhachHang(rs.getString(1)));
		}
		return dsKH;
	}

}
