package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< HEAD
import java.sql.Timestamp;
=======
import java.time.LocalDate;
>>>>>>> 18a2abe (Update DAO & Service)
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
<<<<<<< HEAD
import entity.KhachHang;
=======
>>>>>>> 18a2abe (Update DAO & Service)
import entity.NhanVien;

public class NhanVienDao {
	private Connection con;
<<<<<<< HEAD
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;

	public NhanVienDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
=======

	public NhanVienDao() {
		con = DBConnection.getInstance().getConnection();
>>>>>>> 18a2abe (Update DAO & Service)
	}

	public int themNhanvien(NhanVien nv) throws SQLException {
		String insert = "Insert into NhanVien values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, null, null)";
		PreparedStatement stmt = con.prepareStatement(insert);
		stmt.setString(1, nv.getMaNhanVien());
<<<<<<< HEAD

		int day = nv.getNgaySinh().getDayOfMonth();
		int month = nv.getNgaySinh().getMonthValue();
		int year = nv.getNgaySinh().getYear();

		stmt.setString(3, year + "-" + month + "-" + day);
		stmt.setString(2, nv.getHoTenNhanVien());
=======
		stmt.setString(2, nv.getHoTenNhanVien());
		stmt.setDate(3, java.sql.Date.valueOf(nv.getNgaySinh()));
>>>>>>> 18a2abe (Update DAO & Service)
		stmt.setString(4, nv.getcCCD());
		stmt.setString(5, nv.getDiaChi());
		stmt.setString(6, nv.getsDT());
		stmt.setBoolean(7, nv.isGioiTinh());
		stmt.setString(8, nv.getEmail());
		stmt.setBoolean(9, nv.isChucVu());
		stmt.setBoolean(10, nv.isCaLamViec());
		stmt.setString(11, nv.getHinhAnh());
<<<<<<< HEAD
		stmt.executeUpdate();
		return 1;
	}

	public List<NhanVien> getDSNhanVien() throws SQLException {
		List<NhanVien> dsnv = new ArrayList<>();
		String query = "Select * from NhanVien";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			NhanVien nv = new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
					rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
					rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
					rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), null, null);
			dsnv.add(nv);
		}

		return dsnv;
	}

	public List<NhanVien> timDanhSachNhanVienTheoMa(String maNV) throws SQLException {
		List<NhanVien> dsnv = new ArrayList<>();
		// System.out.println(maNV);
		String query = "Select * from NhanVien where maNhanVien LIKE CONCAT('%', ?, '%')";
		ps = con.prepareStatement(query);
		ps.setString(1, maNV);
		rs = ps.executeQuery();
		while (rs.next()) {
			NhanVien nv = new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
					rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
					rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
					rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), null, null);
			dsnv.add(nv);

		}
		return dsnv;
	}

	public NhanVien timNhanVienTheoMa(String maNV) throws SQLException {
		NhanVien dsnv = new NhanVien();
		// System.out.println(maNV);
		String query = "Select * from NhanVien where maNhanVien=?";
		ps = con.prepareStatement(query);
		ps.setString(1, maNV);
		rs = ps.executeQuery();
		while (rs.next()) {
			NhanVien nv = new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
					rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
					rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
					rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), null, null);
			return nv;

		}
		return null;
	}

	public List<NhanVien> timDSNhanVienTheoTen(String tenNV) throws SQLException {
		List<NhanVien> dsnv = new ArrayList<>();
		String query = "select * from NhanVien where hoTenNhanVien LIKE CONCAT('%', ?, '%')";
		ps = con.prepareStatement(query);
		ps.setString(1, tenNV);
		rs = ps.executeQuery();
		while (rs.next()) {
			NhanVien nv = new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
					rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
					rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
					rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), null, null);
			dsnv.add(nv);
		}
		return dsnv;
	}

	public NhanVien timNhanVienTheoTen(String tenNV) throws SQLException {
		NhanVien nv = new NhanVien();
		String query = "select * from NhanVien where hoTenNhanVien LIKE CONCAT('%', ?, '%')";
		ps = con.prepareStatement(query);
		ps.setString(1, tenNV);
		rs = ps.executeQuery();
		while (rs.next()) {
			nv = new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
					rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
					rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
					rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), null, null);
			return nv;
		}
		return null;
	}

	public List<NhanVien> timNhanVienTheoSDT(String sDT) throws SQLException {
		List<NhanVien> dsnv = new ArrayList<>();
		String query = "select * from NhanVien where sdt LIKE CONCAT('%', ?, '%')";
		ps = con.prepareStatement(query);
		ps.setString(1, sDT);
		rs = ps.executeQuery();
		while (rs.next()) {
			NhanVien nv = new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
					rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
					rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
					rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), null, null);
			dsnv.add(nv);
		}
		return dsnv;
	}

	public List<NhanVien> getListNhanVienByNameAndSDT(String tenNV, String sdt) {
		List<NhanVien> dsnv = new ArrayList<>();
		try {
			String sql = "select * from NhanVien where hoTenNhanVien LIKE CONCAT('%', ?, '%') or sdt LIKE CONCAT('%', ?, '%')";
			ps = con.prepareStatement(sql);
			ps.setString(1, tenNV);
			ps.setString(2, sdt);
			rs = ps.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
						rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
						rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), null, null);
				dsnv.add(nv);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return dsnv;
	}

	public int xoaNhanVien(String maNV) {
		String query = "delete from NhanVien where maNhanVien = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, maNV);
			// rs = ps.executeQuery();
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 1;
	}

	public int suaNhanVien(NhanVien nv) {
		String sql = "UPDATE NhanVien SET hoTenNhanVien = ?, ngaySinh = ? ,cCCD = ?, diaChi =?, sdt = ?, gioiTinh =?,  email = ?, chucVu= ?, caLamViec = ?, hinhAnh=?  WHERE maNhanVien =?";
		try {

			ps = con.prepareStatement(sql);
			ps.setString(1, nv.getHoTenNhanVien());

			int day = nv.getNgaySinh().getDayOfMonth();
			int month = nv.getNgaySinh().getMonthValue();
			int year = nv.getNgaySinh().getYear();

			ps.setString(2, year + "-" + month + "-" + day);

			ps.setString(3, nv.getcCCD());
			ps.setString(4, nv.getDiaChi());
			ps.setString(5, nv.getsDT());
			ps.setBoolean(6, nv.isGioiTinh());
			ps.setString(7, nv.getEmail());
			ps.setBoolean(8, nv.isChucVu());
			ps.setBoolean(9, nv.isCaLamViec());
			ps.setString(10, nv.getHinhAnh());
			ps.setString(11, nv.getMaNhanVien());
			// rs = ps.executeQuery();

			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	public NhanVien getNhanVienByEmail(String email) {

		try {
			String query = "select *from NhanVien where email =?";

			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
						rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
						rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), rs.getString("OTP"),
						rs.getTimestamp("hetHanOTP"));
				return nv;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

	public int updateOTP(String email, String OTP, Timestamp hetHanOTP) {

		try {

			String query = "update NhanVien set OTP = ?, hetHanOTP = ? where email = ?";
			ps = con.prepareCall(query);
			ps.setString(1, OTP);
			ps.setTimestamp(2, hetHanOTP);
			ps.setString(3, email);

			return ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}
}
=======
		return stmt.executeUpdate();
	}

	public List<NhanVien> getDSNhanVien() throws SQLException {
		return executeQueryAndCollectResults("Select * from NhanVien");
	}

	public List<NhanVien> timDanhSachNhanVienTheoMa(String maNV) throws SQLException {
		return executeQueryAndCollectResults("Select * from NhanVien where maNhanVien LIKE CONCAT('%', ?, '%')", maNV);
	}

	public NhanVien timNhanVienTheoMa(String maNV) throws SQLException {
		return executeQueryAndCollectSingleResult("Select * from NhanVien where maNhanVien=?", maNV);
	}

	public List<NhanVien> timDSNhanVienTheoTen(String tenNV) throws SQLException {
		return executeQueryAndCollectResults("select * from NhanVien where hoTenNhanVien LIKE CONCAT('%', ?, '%')", tenNV);
	}

	public NhanVien timNhanVienTheoTen(String tenNV) throws SQLException {
		return executeQueryAndCollectSingleResult("select * from NhanVien where hoTenNhanVien LIKE CONCAT('%', ?, '%')", tenNV);
	}

	public List<NhanVien> timNhanVienTheoSDT(String sDT) throws SQLException {
		return executeQueryAndCollectResults("select * from NhanVien where sdt LIKE CONCAT('%', ?, '%')", sDT);
	}

	public List<NhanVien> getListNhanVienByNameAndSDT(String tenNV, String sdt) throws SQLException {
		return executeQueryAndCollectResults("select * from NhanVien where hoTenNhanVien LIKE CONCAT('%', ?, '%') or sdt LIKE CONCAT('%', ?, '%')", tenNV, sdt);
	}

	public int xoaNhanVien(String maNV) throws SQLException {
		PreparedStatement ps = con.prepareStatement("delete from NhanVien where maNhanVien = ?");
		ps.setString(1, maNV);
		return ps.executeUpdate();
	}

	public int suaNhanVien(NhanVien nv) throws SQLException {
		String sql = "UPDATE NhanVien SET hoTenNhanVien = ?, ngaySinh = ? ,cCCD = ?, diaChi =?, sdt = ?, gioiTinh =?,  email = ?, chucVu= ?, caLamViec = ?, hinhAnh=?  WHERE maNhanVien =?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, nv.getHoTenNhanVien());
		ps.setDate(2, java.sql.Date.valueOf(nv.getNgaySinh()));
		ps.setString(3, nv.getcCCD());
		ps.setString(4, nv.getDiaChi());
		ps.setString(5, nv.getsDT());
		ps.setBoolean(6, nv.isGioiTinh());
		ps.setString(7, nv.getEmail());
		ps.setBoolean(8, nv.isChucVu());
		ps.setBoolean(9, nv.isCaLamViec());
		ps.setString(10, nv.getHinhAnh());
		ps.setString(11, nv.getMaNhanVien());
		return ps.executeUpdate();
	}

	public NhanVien getNhanVienByEmail(String email) throws SQLException {
		return executeQueryAndCollectSingleResult("select *from NhanVien where email =?", email);
	}

	public int updateOTP(String email, String OTP, java.sql.Timestamp hetHanOTP) throws SQLException {
		String query = "update NhanVien set OTP = ?, hetHanOTP = ? where email = ?";
		PreparedStatement ps = con.prepareCall(query);
		ps.setString(1, OTP);
		ps.setTimestamp(2, hetHanOTP);
		ps.setString(3, email);
		return ps.executeUpdate();
	}

	private List<NhanVien> executeQueryAndCollectResults(String query, String... params) throws SQLException {
		List<NhanVien> dsnv = new ArrayList<>();
		PreparedStatement ps = con.prepareStatement(query);
		for (int i = 0; i < params.length; i++) {
			ps.setString(i + 1, params[i]);
		}
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			dsnv.add(createNhanVienFromResultSet(rs));
		}
		return dsnv;
	}

	private NhanVien executeQueryAndCollectSingleResult(String query, String... params) throws SQLException {
		PreparedStatement ps = con.prepareStatement(query);
		for (int i = 0; i < params.length; i++) {
			ps.setString(i + 1, params[i]);
		}
		ResultSet rs = ps.executeQuery();
		return rs.next() ? createNhanVienFromResultSet(rs) : null;
	}

	private NhanVien createNhanVienFromResultSet(ResultSet rs) throws SQLException {
		return new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
				rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
				rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
				rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), rs.getString("OTP"),
				rs.getTimestamp("hetHanOTP"));
	}
}
>>>>>>> 18a2abe (Update DAO & Service)
