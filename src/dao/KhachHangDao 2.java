package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.KhachHang;
<<<<<<< HEAD
import entity.NhanVien;
=======
>>>>>>> 18a2abe (Update DAO & Service)

public class KhachHangDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
<<<<<<< HEAD
	private ArrayList<KhachHang> khachhang = new ArrayList<KhachHang>();

	public KhachHangDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
=======

	public KhachHangDao() {
		con = DBConnection.getInstance().getConnection();
>>>>>>> 18a2abe (Update DAO & Service)
	}

	public int themKhachHang(KhachHang kh) throws SQLException {
		String insert = "Insert into KhachHang values (?, ?, ?, ?, ?)";
<<<<<<< HEAD
		PreparedStatement stmt = con.prepareStatement(insert);
		stmt.setString(1, kh.getMaKhachHang());
		stmt.setString(2, kh.getHoTenKhachHang());
		stmt.setBoolean(3, kh.isGioiTinh());
		stmt.setString(4, kh.getsDT());
		stmt.setString(5, kh.getDiaChi());
		stmt.executeUpdate();
		return 1;
=======
		ps = con.prepareStatement(insert);
		ps.setString(1, kh.getMaKhachHang());
		ps.setString(2, kh.getHoTenKhachHang());
		ps.setBoolean(3, kh.isGioiTinh());
		ps.setString(4, kh.getsDT());
		ps.setString(5, kh.getDiaChi());
		return ps.executeUpdate();
>>>>>>> 18a2abe (Update DAO & Service)
	}

	public List<KhachHang> getDSKhachHang() throws SQLException {
		List<KhachHang> dskh = new ArrayList<>();
		String query = "Select * from KhachHang";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
<<<<<<< HEAD
			KhachHang kh = new KhachHang(rs.getString("maKhachHang"), rs.getString("hotenKhachHang"),
					rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("diaChi"));
			dskh.add(kh);
		}

=======
			dskh.add(createKhachHangFromResultSet(rs));
		}
>>>>>>> 18a2abe (Update DAO & Service)
		return dskh;
	}

	public KhachHang timKhachHangTheoMa(String maKH) throws SQLException {
<<<<<<< HEAD
		KhachHang kh = new KhachHang();
=======
>>>>>>> 18a2abe (Update DAO & Service)
		String query = "Select * from KhachHang where maKhachHang=?";
		ps = con.prepareStatement(query);
		ps.setString(1, maKH);
		rs = ps.executeQuery();
<<<<<<< HEAD
		while (rs.next()) {
			kh = new KhachHang(rs.getString("maKhachHang"), rs.getString("hotenKhachHang"), rs.getString("sdt"),
					rs.getBoolean("gioiTinh"), rs.getString("diaChi"));
			return kh;
		}
		return null;
	}

	public ArrayList<KhachHang> timKhachHangTheoTen(String tenKH) throws SQLException {
		KhachHang kh = new KhachHang();
		khachhang = new ArrayList<KhachHang>();
		System.out.println(tenKH);
		String query = "Select * from KhachHang where hotenKhachHang LIKE CONCAT('%', ?, '%')";
		ps = con.prepareStatement(query);
		ps.setString(1, tenKH);
		rs = ps.executeQuery();
		while (rs.next()) {
			kh = new KhachHang(rs.getString("maKhachHang"), rs.getString("hotenKhachHang"), rs.getString("sdt"),
					rs.getBoolean("gioiTinh"), rs.getString("diaChi"));
			System.out.println(kh);
			khachhang.add(kh);
			// return khachhang;
		}
		return khachhang;
	}

	public int capNhatKhachHang(KhachHang kh) {
		String sql = "UPDATE KhachHang SET hotenKhachHang = ?, gioiTinh = ?, sdt = ?, diaChi = ? WHERE maKhachHang = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, kh.getHoTenKhachHang());
			ps.setBoolean(2, kh.isGioiTinh());
			ps.setString(3, kh.getsDT());
			ps.setString(4, kh.getDiaChi());
			ps.setString(5, kh.getMaKhachHang());

			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	

	public ArrayList<KhachHang> timKhachHangTheoSDT(String sDT) throws SQLException {
		KhachHang kh = new KhachHang();
		String query = "Select * from KhachHang where sdt LIKE CONCAT('%', ?, '%')";
		PreparedStatement stmt = con.prepareCall(query);
		stmt.setString(1, sDT);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			kh = new KhachHang(rs.getString("maKhachHang"), rs.getString("hotenKhachHang"), rs.getString("sdt"),
					rs.getBoolean("gioiTinh"), rs.getString("diaChi"));
			khachhang.add(kh);
		}
		return khachhang;
	}

	public ArrayList<KhachHang> getListKhachHangByNameAndSDT(String tenKH, String sdt) {
		KhachHang kh = new KhachHang();
		try {
			String sql = "select * from KhachHang where hotenKhachHang LIKE CONCAT('%', ?, '%') or sdt LIKE CONCAT('%', ?, '%')";
			PreparedStatement stmt = con.prepareCall(sql);
			stmt.setString(1, tenKH);
			stmt.setString(2, sdt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				kh = new KhachHang(rs.getString("maKhachHang"), rs.getString("hotenKhachHang"), rs.getString("sdt"),
						rs.getBoolean("gioiTinh"), rs.getString("diaChi"));
				khachhang.add(kh);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return khachhang;
	}

	public KhachHang timKhachHangBangSDT(String sdt) throws SQLException {
		KhachHang kh = new KhachHang();
=======
		return rs.next() ? createKhachHangFromResultSet(rs) : null;
	}

	public List<KhachHang> timKhachHangTheoTen(String tenKH) throws SQLException {
		return executeQueryAndCollectResults("Select * from KhachHang where hotenKhachHang LIKE CONCAT('%', ?, '%')", tenKH);
	}

	public int capNhatKhachHang(KhachHang kh) throws SQLException {
		String sql = "UPDATE KhachHang SET hotenKhachHang = ?, gioiTinh = ?, sdt = ?, diaChi = ? WHERE maKhachHang = ?";
		ps = con.prepareStatement(sql);
		ps.setString(1, kh.getHoTenKhachHang());
		ps.setBoolean(2, kh.isGioiTinh());
		ps.setString(3, kh.getsDT());
		ps.setString(4, kh.getDiaChi());
		ps.setString(5, kh.getMaKhachHang());
		return ps.executeUpdate();
	}

	public List<KhachHang> timKhachHangTheoSDT(String sDT) throws SQLException {
		return executeQueryAndCollectResults("Select * from KhachHang where sdt LIKE CONCAT('%', ?, '%')", sDT);
	}

	public List<KhachHang> getListKhachHangByNameAndSDT(String tenKH, String sdt) throws SQLException {
		return executeQueryAndCollectResults("select * from KhachHang where hotenKhachHang LIKE CONCAT('%', ?, '%') or sdt LIKE CONCAT('%', ?, '%')", tenKH, sdt);
	}

	public KhachHang timKhachHangBangSDT(String sdt) throws SQLException {
>>>>>>> 18a2abe (Update DAO & Service)
		String query = "Select * from KhachHang where sdt=?";
		ps = con.prepareStatement(query);
		ps.setString(1, sdt);
		rs = ps.executeQuery();
<<<<<<< HEAD
		while (rs.next()) {
			kh = new KhachHang(rs.getString("maKhachHang"), rs.getString("hotenKhachHang"), rs.getString("sdt"),
					rs.getBoolean("gioiTinh"), rs.getString("diaChi"));
			return kh;
		}
		return null;
	}

}
=======
		return rs.next() ? createKhachHangFromResultSet(rs) : null;
	}

	private KhachHang createKhachHangFromResultSet(ResultSet rs) throws SQLException {
		return new KhachHang(rs.getString("maKhachHang"), rs.getString("hotenKhachHang"), rs.getString("sdt"),
				rs.getBoolean("gioiTinh"), rs.getString("diaChi"));
	}

	private List<KhachHang> executeQueryAndCollectResults(String query, String... params) throws SQLException {
		List<KhachHang> khachhang = new ArrayList<>();
		ps = con.prepareStatement(query);
		for (int i = 0; i < params.length; i++) {
			ps.setString(i + 1, params[i]);
		}
		rs = ps.executeQuery();
		while (rs.next()) {
			khachhang.add(createKhachHangFromResultSet(rs));
		}
		return khachhang;
	}
}
>>>>>>> 18a2abe (Update DAO & Service)
