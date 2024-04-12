package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import org.apache.tools.ant.taskdefs.Local;

import db.DBConnection;
import entity.ChiTietHoaDon;
import entity.HoaDon;
=======
import db.DBConnection;
>>>>>>> 18a2abe (Update DAO & Service)

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
<<<<<<< HEAD
	
	public List<NhanVien> getNhanVienBanNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
		dsNV = new ArrayList<NhanVien>();
		try {
			String query = "SELECT NhanVien.maNhanVien\r\n" + "	FROM     ChiTietHoaDon INNER JOIN\r\n"
					+ "	HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\r\n"
					+ "	NhanVien ON HoaDon.maNhanVien = NhanVien.maNhanVien\r\n"
					+ "			WHERE  HoaDon.ngayLapHoaDon between  ? and ?\r\n"
					+ "	GROUP BY NhanVien.maNhanVien\r\n"
					+ "	HAVING count(HoaDon.maHoaDon) >= all(SELECT count(HoaDon.maHoaDon) AS 'Tong so luong hoa don'\r\n"
					+ "	FROM     chitiethoadon INNER JOIN\r\n"
					+ "			HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\r\n"
					+ "			NhanVien ON HoaDon.maNhanVien = NhanVien.maNhanVien\r\n"
					+ "	WHERE  HoaDon.ngayLapHoaDon between  ? and ?\r\n" + "	GROUP BY NhanVien.maNhanVien)";
			ps = con.prepareStatement(query);
			int dayBD = ngayBatDau.getDayOfMonth();
			int monthBD = ngayBatDau.getMonthValue();
			int yearBD = ngayBatDau.getYear();

			ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

			int dayKT = ngayKetThuc.getDayOfMonth();
			int monthKT = ngayKetThuc.getMonthValue();
			int yearKT = ngayKetThuc.getYear();

			ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);
			ps.setString(3, yearBD + "-" + monthBD + "-" + dayBD);
			ps.setString(4, yearKT + "-" + monthKT + "-" + dayKT);
			rs = ps.executeQuery();

			while (rs.next()) {
				String maNV = rs.getString(1);
				NhanVien nhanVien = new NhanVien(maNV);
				dsNV.add(nhanVien);

			}
			return dsNV;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<SanPham> getSanPhamBanNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
		dsSP = new ArrayList<SanPham>();
		try {
			String query = "SELECT SanPham.maSanPham\r\n" + "FROM     ChiTietHoaDon INNER JOIN\r\n"
					+ "HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\r\n"
					+ "					                SanPham ON ChiTietHoaDon.maSanPham = SanPham.maSanPham\r\n"
					+ "					WHERE  HoaDon.ngayLapHoaDon BETWEEN  ? and ?\r\n"
					+ "					GROUP BY SanPham.maSanPham\r\n"
					+ "					HAVING SUM(ChiTietHoaDon.soLuong) >= ALL(SELECT  SUM(ChiTietHoaDon.soLuong) AS 'TongSoLuongDaBan'\r\n"
					+ "					FROM     ChiTietHoaDon INNER JOIN\r\n"
					+ "			        HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\r\n"
					+ "					                SanPham ON ChiTietHoaDon.maSanPham = SanPham.maSanPham\r\n"
					+ "					WHERE  HoaDon.ngayLapHoaDon BETWEEN   ? AND ?\r\n"
					+ "					GROUP BY SanPham.maSanPham)";

			ps = con.prepareStatement(query);
			int dayBD = ngayBatDau.getDayOfMonth();
			int monthBD = ngayBatDau.getMonthValue();
			int yearBD = ngayBatDau.getYear();

			ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

			int dayKT = ngayKetThuc.getDayOfMonth();
			int monthKT = ngayKetThuc.getMonthValue();
			int yearKT = ngayKetThuc.getYear();

			ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);
			ps.setString(3, yearBD + "-" + monthBD + "-" + dayBD);
			ps.setString(4, yearKT + "-" + monthKT + "-" + dayKT);

			rs = ps.executeQuery();

			while (rs.next()) {
				String maSanPham = rs.getString(1);
				SanPham sanPham = new SanPham(maSanPham);
				dsSP.add(sanPham);

			}
			return dsSP;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getSoLuongSachTon() throws SQLException {
		int soLuongTon = 0;
		String query = "SELECT SUM(soLuongTon)\r\n" + "  FROM SanPham\r\n" + "  WHERE loaiSanPham LIKE N'Sách'";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			soLuongTon = rs.getInt("");
			return soLuongTon;
		}
		return 0;
	}

	public int getSoLuongVPPTon() throws SQLException {
		int soLuongTon = 0;
		String query = "SELECT SUM(soLuongTon)\r\n" + "  FROM SanPham\r\n"
				+ "  WHERE loaiSanPham LIKE N'Văn phòng phẩm'";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			soLuongTon = rs.getInt("");
			return soLuongTon;
		}
		return 0;
	}

	public int getSoLuongSachLoi() throws SQLException {
		int soLuongTon = 0;
		String query = "SELECT SUM(soLuongLoi)\r\n" + "  FROM SachLoi\r\n";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			soLuongTon = rs.getInt("");
			return soLuongTon;
		}
		return 0;
	}

	public int getSoLuongHoaDon(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
		int soLuongHoaDon = 0;
		String query = "SELECT COUNT(*)\r\n" + "from HoaDon\r\n" + "WHERE  HoaDon.ngayLapHoaDon between ? and ?";
		ps = con.prepareStatement(query);

		int dayBD = ngayBatDau.getDayOfMonth();
		int monthBD = ngayBatDau.getMonthValue();
		int yearBD = ngayBatDau.getYear();

		ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

		int dayKT = ngayKetThuc.getDayOfMonth();
		int monthKT = ngayKetThuc.getMonthValue();
		int yearKT = ngayKetThuc.getYear();

		ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);
		rs = ps.executeQuery();
		while (rs.next()) {
			soLuongHoaDon = rs.getInt("");
			return soLuongHoaDon;
		}
		return 0;
	}

	public double getDoanhThu(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
		double doanhThu = 0;
		String query = "SELECT SUM(ChiTietHoaDon.soLuong*ChiTietHoaDon.donGia)\r\n"
				+ "	from ChiTietHoaDon   INNER JOIN\r\n" + "	HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon\r\n"
				+ "WHERE  HoaDon.ngayLapHoaDon between  ? and ?";
		ps = con.prepareStatement(query);

		int dayBD = ngayBatDau.getDayOfMonth();
		int monthBD = ngayBatDau.getMonthValue();
		int yearBD = ngayBatDau.getYear();

		ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

		int dayKT = ngayKetThuc.getDayOfMonth();
		int monthKT = ngayKetThuc.getMonthValue();
		int yearKT = ngayKetThuc.getYear();

		ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);
		rs = ps.executeQuery();
		while (rs.next()) {
			doanhThu = rs.getDouble("");
			return doanhThu;
		}
		return 0;
	}

	public List<KhachHang> getKhachHangMuaNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
		dsKH = new ArrayList<KhachHang>();
		try {
			String query = "SELECT KhachHang.maKhachHang\r\n" + "	FROM     ChiTietHoaDon INNER JOIN\r\n"
					+ "	HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\r\n"
					+ "	KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang\r\n"
					+ "			WHERE  HoaDon.ngayLapHoaDon between ? and ?\r\n"
					+ "	GROUP BY KhachHang.maKhachHang\r\n"
					+ "	HAVING count(HoaDon.maHoaDon) >= all(SELECT count(HoaDon.maHoaDon) AS 'Tong so luong hoa don'\r\n"
					+ "	FROM     chitiethoadon INNER JOIN\r\n"
					+ "			HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\r\n"
					+ "			KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang\r\n"
					+ "	WHERE  HoaDon.ngayLapHoaDon between  ? and ?\r\n" + "	GROUP BY KhachHang.maKhachHang)";

			ps = con.prepareStatement(query);
			int dayBD = ngayBatDau.getDayOfMonth();
			int monthBD = ngayBatDau.getMonthValue();
			int yearBD = ngayBatDau.getYear();

			ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

			int dayKT = ngayKetThuc.getDayOfMonth();
			int monthKT = ngayKetThuc.getMonthValue();
			int yearKT = ngayKetThuc.getYear();

			ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);
			ps.setString(3, yearBD + "-" + monthBD + "-" + dayBD);
			ps.setString(4, yearKT + "-" + monthKT + "-" + dayKT);
			rs = ps.executeQuery();

			while (rs.next()) {
				String maKH = rs.getString(1);
				KhachHang khachHang = new KhachHang(maKH);
				dsKH.add(khachHang);

			}
			return dsKH;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getSoLuongBanCuaSanPhamChayNhat(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
		int soLuongBan = 0;
		String query = "select top(1) sum(ChiTietHoaDon.soLuong)\r\n"
				+ "from SanPham inner join ChiTietHoaDon on SanPham.maSanPham = ChiTietHoaDon.maSanPham inner join HoaDon on HoaDOn.maHoaDon = ChiTietHoaDon.maHoaDon\r\n"
				+ "where HoaDon.ngayLapHoaDon between  ? and ?\r\n" + "group by SanPham.maSanPham";

		ps = con.prepareStatement(query);
		int dayBD = ngayBatDau.getDayOfMonth();
		int monthBD = ngayBatDau.getMonthValue();
		int yearBD = ngayBatDau.getYear();

		ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

		int dayKT = ngayKetThuc.getDayOfMonth();
		int monthKT = ngayKetThuc.getMonthValue();
		int yearKT = ngayKetThuc.getYear();

		ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);

		rs = ps.executeQuery();
		while (rs.next()) {
			soLuongBan = rs.getInt("");
			return soLuongBan;
		}
		return 0;

	}

	public double getTongTienCuaKhachHangTop1(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
		double tongTien = 0;
		String query = "SELECT top(1) sum(ChiTietHoaDon.soLuong*ChiTietHoaDon.donGia)\r\n"
				+ "	FROM     ChiTietHoaDon INNER JOIN\r\n"
				+ "	HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\r\n"
				+ "	KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang\r\n"
				+ "			WHERE  HoaDon.ngayLapHoaDon between  ? and ?\r\n" + "	GROUP BY KhachHang.maKhachHang \r\n"
				+ "	order by sum(ChiTietHoaDon.soLuong*ChiTietHoaDon.donGia) desc";
		ps = con.prepareStatement(query);
		int dayBD = ngayBatDau.getDayOfMonth();
		int monthBD = ngayBatDau.getMonthValue();
		int yearBD = ngayBatDau.getYear();

		ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

		int dayKT = ngayKetThuc.getDayOfMonth();
		int monthKT = ngayKetThuc.getMonthValue();
		int yearKT = ngayKetThuc.getYear();

		ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);

		rs = ps.executeQuery();
		while (rs.next()) {
			tongTien = rs.getDouble("");
			return tongTien;
		}
		return 0;
	}

	public int getSoLuongHoaDonCuaKhachHangTheoMa(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maKH)
			throws SQLException {
		int soLuong;
		String query = "SELECT  count(HoaDon.maHoaDon)\r\n" + "	FROM     HoaDon INNER JOIN\r\n"
				+ "	KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang\r\n" + "	\r\n"
				+ "			WHERE  HoaDon.ngayLapHoaDon between  ? and ? and KhachHang.maKhachHang =?\r\n"
				+ "	GROUP BY KhachHang.maKhachHang";
		ps = con.prepareStatement(query);

		int dayBD = ngayBatDau.getDayOfMonth();
		int monthBD = ngayBatDau.getMonthValue();
		int yearBD = ngayBatDau.getYear();

		ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

		int dayKT = ngayKetThuc.getDayOfMonth();
		int monthKT = ngayKetThuc.getMonthValue();
		int yearKT = ngayKetThuc.getYear();

		ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);
		ps.setString(3, maKH);
		rs = ps.executeQuery();
		while (rs.next()) {
			soLuong = rs.getInt("");
			return soLuong;
		}
		return 0;
	}

	public double getTongTienCuaKhachHangTheoMa(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maKH)
			throws SQLException {
		double tongTien = 0;
		String query = "SELECT  sum(ChiTietHoaDon.soLuong*ChiTietHoaDon.donGia)\r\n"
				+ "	FROM     ChiTietHoaDon INNER JOIN\r\n"
				+ "	HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\r\n"
				+ "	KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang\r\n"
				+ "			WHERE  HoaDon.ngayLapHoaDon between  ? and ? and KhachHang.maKhachHang =?\r\n"
				+ "	GROUP BY KhachHang.maKhachHang ";
		ps = con.prepareStatement(query);
		int dayBD = ngayBatDau.getDayOfMonth();
		int monthBD = ngayBatDau.getMonthValue();
		int yearBD = ngayBatDau.getYear();

		ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

		int dayKT = ngayKetThuc.getDayOfMonth();
		int monthKT = ngayKetThuc.getMonthValue();
		int yearKT = ngayKetThuc.getYear();

		ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);
		ps.setString(3, maKH);
		rs = ps.executeQuery();
		while (rs.next()) {
			tongTien = rs.getDouble("");
			return tongTien;
		}
		return 0;
	}



	public double getDoanhThuTheoMaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV)
			throws SQLException {
		double tongTien = 0;
		String query = "SELECT SUM(ChiTietHoaDon.soLuong*ChiTietHoaDon.donGia)\r\n"
				+ "	from ChiTietHoaDon   INNER JOIN\r\n" + "	HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon\r\n"
				+ "WHERE  HoaDon.ngayLapHoaDon between  ? and ? and HoaDon.maNhanVien=?";
		ps = con.prepareStatement(query);
		int dayBD = ngayBatDau.getDayOfMonth();
		int monthBD = ngayBatDau.getMonthValue();
		int yearBD = ngayBatDau.getYear();

		ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

		int dayKT = ngayKetThuc.getDayOfMonth();
		int monthKT = ngayKetThuc.getMonthValue();
		int yearKT = ngayKetThuc.getYear();

		ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);
		ps.setString(3, maNV);
		rs = ps.executeQuery();
		while (rs.next()) {
			tongTien = rs.getDouble("");
			return tongTien;
		}
		return 0;
	}

	public int getSoLuongHoaDonTheoMaNV(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV) throws SQLException {
		int soLuongHoaDon = 0;
		String query = "SELECT count(*)\r\n" + "	from ChiTietHoaDon   INNER JOIN\r\n"
				+ "	HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon\r\n"
				+ "WHERE  HoaDon.ngayLapHoaDon between  ? and ? and HoaDon.maNhanVien=?";
		ps = con.prepareStatement(query);
		int dayBD = ngayBatDau.getDayOfMonth();
		int monthBD = ngayBatDau.getMonthValue();
		int yearBD = ngayBatDau.getYear();

		ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

		int dayKT = ngayKetThuc.getDayOfMonth();
		int monthKT = ngayKetThuc.getMonthValue();
		int yearKT = ngayKetThuc.getYear();

		ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);
		ps.setString(3, maNV);
		rs = ps.executeQuery();
		while (rs.next()) {
			soLuongHoaDon = rs.getInt("");
			return soLuongHoaDon;
		}
		return 0;
	}

	public List<NhanVien> getDoanhThuCuaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
		dsNV = new ArrayList<NhanVien>();
		try {
			String query = "select NhanVien.maNhanVien \r\n"
					+ "from NhanVien join HoaDon on NhanVien.maNhanVien = HoaDon.maNhanVien";

			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				String maNV = rs.getString(1);
				NhanVien nhanVien = new NhanVien(maNV);
				dsNV.add(nhanVien);
				return dsNV;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<NhanVien> thongKeDoanhThu10NVBanNhieuNhat(LocalDate ngayBatDau, LocalDate ngayKetThuc){
		dsNV1 = new ArrayList<NhanVien>();
		try {
		String query= "SELECT  top(10)      NhanVien.maNhanVien, sum(ChiTietHoaDon.donGia*ChiTietHoaDon.soLuong)\r\n"
				+ "FROM            NhanVien INNER JOIN\r\n"
				+ "                         HoaDon ON NhanVien.maNhanVien = HoaDon.maNhanVien INNER JOIN\r\n"
				+ "                         ChiTietHoaDon ON HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon\r\n"
				+ "WHERE  HoaDon.ngayLapHoaDon BETWEEN ? AND ?\r\n"
				+ "group by NhanVien.maNhanVien\r\n"
				+ "order by sum(ChiTietHoaDon.donGia*ChiTietHoaDon.soLuong) desc";
		
		ps = con.prepareStatement(query);
		int dayBD = ngayBatDau.getDayOfMonth();
		int monthBD = ngayBatDau.getMonthValue();
		int yearBD = ngayBatDau.getYear();

		ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

		int dayKT = ngayKetThuc.getDayOfMonth();
		int monthKT = ngayKetThuc.getMonthValue();
		int yearKT = ngayKetThuc.getYear();

		ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);
		rs = ps.executeQuery();
		
		while (rs.next()) {
			String maNV = rs.getString(1);
			NhanVien nhanVien = new NhanVien(maNV);
			dsNV1.add(nhanVien);
		}
		return dsNV1;

	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	}
	
	
	public List<KhachHang> getTop10KHThanThiet(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
		dsKH = new ArrayList<KhachHang>();
		try {
			String query = "SELECT top(10) KhachHang.maKhachHang, sum(ChiTietHoaDon.soLuong*ChiTietHoaDon.donGia)\r\n"
					+ "	FROM     ChiTietHoaDon INNER JOIN\r\n"
					+ "	HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\r\n"
					+ "	KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang\r\n"
					+ "			WHERE  HoaDon.ngayLapHoaDon between  ? and ?\r\n"
					+ "	GROUP BY KhachHang.maKhachHang\r\n"
					+ "	order by sum(ChiTietHoaDon.soLuong*ChiTietHoaDon.donGia) desc";

			ps = con.prepareStatement(query);
			int dayBD = ngayBatDau.getDayOfMonth();
			int monthBD = ngayBatDau.getMonthValue();
			int yearBD = ngayBatDau.getYear();

			ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

			int dayKT = ngayKetThuc.getDayOfMonth();
			int monthKT = ngayKetThuc.getMonthValue();
			int yearKT = ngayKetThuc.getYear();

			ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);
			rs = ps.executeQuery();

			while (rs.next()) {
				String maKH = rs.getString(1);
				//System.out.println(maKH);
				KhachHang khachHang = new KhachHang(maKH);
				dsKH.add(khachHang);

			}
			return dsKH;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
=======

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

>>>>>>> 18a2abe (Update DAO & Service)
}
