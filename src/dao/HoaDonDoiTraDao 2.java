package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.HoaDon;
import entity.HoaDonDoiTra;
import entity.KhachHang;
import entity.NhanVien;

public class HoaDonDoiTraDao {
	private Connection con;
<<<<<<< HEAD
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;
=======
	private PreparedStatement ps;
	private ResultSet rs;
>>>>>>> 18a2abe (Update DAO & Service)
	private NhanVienDao nhanVienDao = new NhanVienDao();
	private KhachHangDao khachHangDao = new KhachHangDao();
	private HoaDonDao hoaDonDao = new HoaDonDao();

	public HoaDonDoiTraDao() {
<<<<<<< HEAD
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
=======
		con = DBConnection.getInstance().getConnection();
>>>>>>> 18a2abe (Update DAO & Service)
	}

	public int themHoaDonDoiTra(HoaDonDoiTra hddt) throws SQLException {
		String insert = "Insert into HoaDonDoiTra values (?, ?, ?, ?, ?, ?,?,?)";
<<<<<<< HEAD
		PreparedStatement stmt = con.prepareStatement(insert);
		stmt.setString(1, hddt.getMaHoaDonDoiTra());
		stmt.setString(2, hddt.getNhanVien().getMaNhanVien());
		stmt.setString(3, hddt.getKhachHang().getMaKhachHang());
		int day = hddt.getNgayLapHoaDon().getDayOfMonth();
		int month = hddt.getNgayLapHoaDon().getMonthValue();
		int year = hddt.getNgayLapHoaDon().getYear();
		stmt.setString(4, year + "-" + month + "-" + day);
		stmt.setString(5, hddt.getGhiChu());
		stmt.setDouble(6, hddt.getTienKhachDua());
		stmt.setString(7, hddt.getHoaDon().getMaHoaDon());
		stmt.setDouble(8, hddt.getTienPhaiTru());
		stmt.executeUpdate();
		return 1;
	}

	public List<HoaDonDoiTra> getHoaDonDoiTraTheoMa(String maHDDT) {
		List<HoaDonDoiTra> dshddt = new ArrayList<HoaDonDoiTra>();
		try {
			String query = "Select * from HoaDonDoiTra where maHoaDonDoiTra =?";
			ps = con.prepareStatement(query);
			ps.setString(1, maHDDT);
			rs = ps.executeQuery();
			while (rs.next()) {
				HoaDonDoiTra hddt = new HoaDonDoiTra(rs.getString(1), nhanVienDao.timNhanVienTheoMa(rs.getString(2)),
						khachHangDao.timKhachHangTheoMa(rs.getString(3)), rs.getDate(4).toLocalDate(), rs.getString(5),
						rs.getDouble(6), hoaDonDao.getHoaDonTheoMa(rs.getString(7)).get(0), rs.getDouble(8));
				dshddt.add(hddt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dshddt;

	}

	public List<HoaDonDoiTra> getDSHoaDonDoiTra() throws SQLException {
		List<HoaDonDoiTra> dshddt = new ArrayList<>();
		String query = "Select * from HoaDonDoiTra";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			HoaDonDoiTra hddt = new HoaDonDoiTra(rs.getString("maHoaDonDoiTra"),
					new NhanVien(rs.getString("maNhanVien")), new KhachHang(rs.getString("maKhachHang")),
					rs.getDate("ngayLapHoaDon").toLocalDate(), rs.getString("ghiChu"), rs.getFloat("tienKhachDua"),
					hoaDonDao.getHoaDonTheoMa(rs.getString(7)).get(0), rs.getDouble(8));
			dshddt.add(hddt);
		}

		return dshddt;
	}

	public int editTienKhachTra(HoaDonDoiTra hddt) {
		String query = "update HoaDonDoiTra set tienKhachDua =? where maHoaDonDoiTra=?";
		try {

			ps = con.prepareStatement(query);
			ps.setDouble(1, hddt.getTienKhachDua());

			ps.setString(2, hddt.getMaHoaDonDoiTra());

			// rs = ps.executeQuery();

			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	public int editTienPhaiTru(HoaDonDoiTra hddt) {
		String query = "update HoaDonDoiTra set tienPhaiTru =? where maHoaDonDoiTra=?";
		try {

			ps = con.prepareStatement(query);
			ps.setDouble(1, hddt.getTienPhaiTru());

			ps.setString(2, hddt.getMaHoaDonDoiTra());

			// rs = ps.executeQuery();

			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	public List<HoaDonDoiTra> getMaHoaDonDoiTraByMaHDCu(String maHDCu) {
		List<HoaDonDoiTra> dsMaHoaDonDoiTra = new ArrayList<>();
		try {

			String query = "select * from HoaDonDoiTra where maHoaDon = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, maHDCu);
			rs = ps.executeQuery();
			while (rs.next()) {
				HoaDonDoiTra hddt = new HoaDonDoiTra(rs.getString(1), nhanVienDao.timNhanVienTheoMa(rs.getString(2)),
						khachHangDao.timKhachHangTheoMa(rs.getString(3)), rs.getDate(4).toLocalDate(), rs.getString(5),
						rs.getDouble(6), hoaDonDao.getHoaDonTheoMa(rs.getString(7)).get(0), rs.getDouble(8));
				dsMaHoaDonDoiTra.add(hddt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsMaHoaDonDoiTra;
	}
	
	public List<HoaDonDoiTra> getToanBoDSHoaDonDoiTra() throws SQLException {
		List<HoaDonDoiTra> dshddt = new ArrayList<>();
		String query = "Select * from HoaDonDoiTra";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			HoaDonDoiTra hddt = new HoaDonDoiTra(rs.getString("maHoaDonDoiTra"),
					nhanVienDao.timNhanVienTheoMa(rs.getString("maNhanVien")),
					khachHangDao.timKhachHangTheoMa(rs.getString("maKhachHang")),
					rs.getDate("ngayLapHoaDon").toLocalDate(), rs.getString("ghiChu"), rs.getFloat("tienKhachDua"),
					hoaDonDao.getHoaDonTheoMa(rs.getString(7)).get(0), rs.getDouble(8));
			dshddt.add(hddt);
		}

		return dshddt;
	}

	// Tim hoa don doi tra theo ma
	public HoaDonDoiTra timHoaDonDoiTraTheoMa(String maHoaDon) throws SQLException {
		HoaDonDoiTra hoaDonDT = null;
		// System.out.println(maNV);
		String query = "Select * from HoaDonDoiTra where maHoaDonDoiTra=?";
		ps = con.prepareStatement(query);
		ps.setString(1, maHoaDon);
		rs = ps.executeQuery();
		while (rs.next()) {
			hoaDonDT = new HoaDonDoiTra(rs.getString("maHoaDonDoiTra"),
					nhanVienDao.timNhanVienTheoMa(rs.getString("maNhanVien")),
					khachHangDao.timKhachHangTheoMa(rs.getString("maKhachHang")),
					rs.getDate("ngayLapHoaDon").toLocalDate(), rs.getString("ghiChu"), rs.getFloat("tienKhachDua"),
					hoaDonDao.timHoaDonTheoMa(rs.getString("maHoaDon")), rs.getDouble("tienPhaiTru"));
		}
		return hoaDonDT;
	}

// Tim hóa đơn đổi trả theo tên nhân viên
	public List<HoaDonDoiTra> getHoaDonDoiTraTheoTen(String tenNV) throws SQLException {
		List<HoaDonDoiTra> dshddt = new ArrayList<HoaDonDoiTra>();
		// System.out.println(maNV);
		try {
			String query = "select * from HoaDonDoiTra\r\n" + "  inner join NhanVien\r\n"
					+ "  on HoaDonDoiTra.maNhanVien = NhanVien.maNhanVien\r\n" + "where NhanVien.hotenNhanVien like N'%"
					+ tenNV + "%'";

			PreparedStatement ps = con.prepareStatement(query);
//			stmt.setString(1, tenNV);
			ResultSet rs = ps.executeQuery();
			;
			while (rs.next()) {
				HoaDonDoiTra hddt = new HoaDonDoiTra(rs.getString("maHoaDonDoiTra"),
						nhanVienDao.timNhanVienTheoMa(rs.getString("maNhanVien")),
						khachHangDao.timKhachHangTheoMa(rs.getString("maKhachHang")),
						rs.getDate("ngayLapHoaDon").toLocalDate(), rs.getString("ghiChu"), rs.getFloat("tienKhachDua"),
						hoaDonDao.timHoaDonTheoMa(rs.getString("maHoaDon")), rs.getDouble("tienPhaiTru"));

				dshddt.add(hddt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dshddt;

	}

	public List<HoaDonDoiTra> getHoaDonDoiTraTheoSDT(String sdt) {
		// TODO Auto-generated method stub
		List<HoaDonDoiTra> dshddt = new ArrayList<HoaDonDoiTra>();
		// System.out.println(maNV);
		try {
			String query = " select * from HoaDonDoiTra\r\n"
					+ "	  inner join KhachHang\r\n"
					+ "	  on HoaDonDoiTra.maKhachHang = KhachHang.maKhachHang\r\n"
					+ "  where khachhang.sdt = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, sdt);
			rs = ps.executeQuery();
			while (rs.next()) {
				HoaDonDoiTra hddt = new HoaDonDoiTra(rs.getString("maHoaDonDoiTra"),
						nhanVienDao.timNhanVienTheoMa(rs.getString("maNhanVien")),
						khachHangDao.timKhachHangTheoMa(rs.getString("maKhachHang")),
						rs.getDate("ngayLapHoaDon").toLocalDate(), rs.getString("ghiChu"), rs.getFloat("tienKhachDua"),
						hoaDonDao.timHoaDonTheoMa(rs.getString("maHoaDon")), rs.getDouble("tienPhaiTru"));

				dshddt.add(hddt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dshddt;
	}

	public List<HoaDonDoiTra> getHoaDonDoiTraTheoTenKH(String tenKH) {
		List<HoaDonDoiTra> dshddt = new ArrayList<HoaDonDoiTra>();
		// System.out.println(maNV);
		try {
			String query = "select * from HoaDonDoiTra \r\n"
					+"inner join NhanVien \r\n"
					+"on HoaDonDoiTra.maNhanVien = NhanVien.maNhanVien \r\n"
					+"inner join KhachHang \r\n"
					+"on HoaDonDoiTra.maKhachHang = KhachHang.maKhachHang \r\n"
					+ "where KhachHang.hotenKhachHang like N'%"+ tenKH +"%'";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				HoaDonDoiTra hddt = new HoaDonDoiTra(rs.getString("maHoaDonDoiTra"),
						nhanVienDao.timNhanVienTheoMa(rs.getString("maNhanVien")),
						khachHangDao.timKhachHangTheoMa(rs.getString("maKhachHang")),
						rs.getDate("ngayLapHoaDon").toLocalDate(), rs.getString("ghiChu"), rs.getFloat("tienKhachDua"),
						hoaDonDao.timHoaDonTheoMa(rs.getString("maHoaDon")), rs.getDouble("tienPhaiTru"));

				dshddt.add(hddt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dshddt;
	}

}
=======
		ps = con.prepareStatement(insert);
		ps.setString(1, hddt.getMaHoaDonDoiTra());
		ps.setString(2, hddt.getNhanVien().getMaNhanVien());
		ps.setString(3, hddt.getKhachHang().getMaKhachHang());
		ps.setDate(4, java.sql.Date.valueOf(hddt.getNgayLapHoaDon()));
		ps.setString(5, hddt.getGhiChu());
		ps.setDouble(6, hddt.getTienKhachDua());
		ps.setString(7, hddt.getHoaDon().getMaHoaDon());
		ps.setDouble(8, hddt.getTienPhaiTru());
		return ps.executeUpdate();
	}

	public List<HoaDonDoiTra> getHoaDonDoiTraTheoMa(String maHDDT) throws SQLException {
		List<HoaDonDoiTra> dshddt = new ArrayList<>();
		String query = "Select * from HoaDonDoiTra where maHoaDonDoiTra =?";
		ps = con.prepareStatement(query);
		ps.setString(1, maHDDT);
		rs = ps.executeQuery();
		while (rs.next()) {
			HoaDonDoiTra hddt = new HoaDonDoiTra(rs.getString(1), nhanVienDao.timNhanVienTheoMa(rs.getString(2)),
					khachHangDao.timKhachHangTheoMa(rs.getString(3)), rs.getDate(4).toLocalDate(), rs.getString(5),
					rs.getDouble(6), hoaDonDao.timHoaDonTheoMa(rs.getString(7)), rs.getDouble(8));
			dshddt.add(hddt);
		}
		return dshddt;
	}

	public List<HoaDonDoiTra> getDSHoaDonDoiTra() throws SQLException {
		return getHoaDonDoiTraTheoMa(null);
	}

	public int editTienKhachTra(HoaDonDoiTra hddt) throws SQLException {
		return updateHoaDonDoiTra(hddt, "update HoaDonDoiTra set tienKhachDua =? where maHoaDonDoiTra=?", hddt.getTienKhachDua());
	}

	public int editTienPhaiTru(HoaDonDoiTra hddt) throws SQLException {
		return updateHoaDonDoiTra(hddt, "update HoaDonDoiTra set tienPhaiTru =? where maHoaDonDoiTra=?", hddt.getTienPhaiTru());
	}

	private int updateHoaDonDoiTra(HoaDonDoiTra hddt, String query, double value) throws SQLException {
		ps = con.prepareStatement(query);
		ps.setDouble(1, value);
		ps.setString(2, hddt.getMaHoaDonDoiTra());
		return ps.executeUpdate();
	}

	public List<HoaDonDoiTra> getMaHoaDonDoiTraByMaHDCu(String maHDCu) throws SQLException {
		return getHoaDonDoiTraTheoMa(maHDCu);
	}

	public List<HoaDonDoiTra> getToanBoDSHoaDonDoiTra() throws SQLException {
		return getHoaDonDoiTraTheoMa(null);
	}

	public HoaDonDoiTra timHoaDonDoiTraTheoMa(String maHoaDon) throws SQLException {
		List<HoaDonDoiTra> list = getHoaDonDoiTraTheoMa(maHoaDon);
		return list.isEmpty() ? null : list.get(0);
	}

	public List<HoaDonDoiTra> getHoaDonDoiTraTheoTen(String tenNV) throws SQLException {
		return getHoaDonDoiTraTheoMa(tenNV);
	}

	public List<HoaDonDoiTra> getHoaDonDoiTraTheoSDT(String sdt) throws SQLException {
		return getHoaDonDoiTraTheoMa(sdt);
	}

	public List<HoaDonDoiTra> getHoaDonDoiTraTheoTenKH(String tenKH) throws SQLException {
		return getHoaDonDoiTraTheoMa(tenKH);
	}
}
>>>>>>> 18a2abe (Update DAO & Service)
