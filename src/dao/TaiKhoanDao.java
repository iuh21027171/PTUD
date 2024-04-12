package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;
import entity.TaiKhoan;

public class TaiKhoanDao {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public TaiKhoanDao() {
		con = DBConnection.getInstance().getConnection();
	}

	public ArrayList<TaiKhoan> getList() throws SQLException {
		ps = con.prepareStatement("SELECT * FROM TaiKhoan");
		rs = ps.executeQuery();
		NhanVienDao nhanVienDao = new NhanVienDao();
		ArrayList<TaiKhoan> listAcc = new ArrayList<>();
		while (rs.next()) {
			listAcc.add(new TaiKhoan(rs.getString(1), rs.getString(2),
					nhanVienDao.timNhanVienTheoMa(rs.getString(3)), rs.getBoolean(4)));
		}
		return listAcc;
	}

	public int insertAccount(TaiKhoan taiKhoan) throws SQLException {
		ps = con.prepareStatement("INSERT INTO TaiKhoan VALUES(?,?,?,?)");
		ps.setString(1, taiKhoan.getTenDangNhap());
		ps.setString(2, taiKhoan.getMatKhau());
		ps.setString(3, taiKhoan.getNhanVien().getMaNhanVien());
		ps.setBoolean(4, taiKhoan.isQuyen());
		return ps.executeUpdate();
	}

	public int xoaTaiKhoan(String maNhanVien) throws SQLException {
		ps = con.prepareStatement("delete from TaiKhoan where maNhanVien = ?");
		ps.setString(1, maNhanVien);
		return ps.executeUpdate();
	}

	public TaiKhoan getTaiKhoanTheoMaNV(String maNV) throws SQLException {
		NhanVienDao nhanVienDao = new NhanVienDao();
		ps = con.prepareStatement("SELECT *FROM TaiKhoan where maNhanVien =?");
		ps.setString(1, maNV);
		rs = ps.executeQuery();
		if (rs.next()) {
			return new TaiKhoan(rs.getString(1), rs.getString(2), nhanVienDao.timNhanVienTheoMa(rs.getString(3)),
					rs.getBoolean(4));
		}
		return null;
	}

	public int doiMatKhau(String passMoi, String maNV) throws SQLException {
		ps = con.prepareStatement("update TaiKhoan set matKhau =? where maNhanVien=?");
		ps.setString(1, passMoi);
		ps.setString(2, maNV);
		return ps.executeUpdate();
	}
}