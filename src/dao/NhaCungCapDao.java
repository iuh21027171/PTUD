package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;
import entity.NhaCungCap;

public class NhaCungCapDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public NhaCungCapDao() {
		con = DBConnection.getInstance().getConnection();
	}

	public ArrayList<NhaCungCap> getListNhaCungCap(String loaiSanPham) throws SQLException {
		ArrayList<NhaCungCap> list = new ArrayList<>();
		ps = con.prepareStatement("SELECT distinct NhaCungCap.maNCC, NhaCungCap.tenNCC FROM NhaCungCap INNER JOIN SanPham ON NhaCungCap.maNCC = SanPham.maNCC where SanPham.loaiSanPham like ?");
		ps.setString(1, loaiSanPham);
		rs = ps.executeQuery();
		while (rs.next()) {
			list.add(new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC")));
		}
		return list;
	}

	public boolean themNhaCungCap(NhaCungCap t) throws SQLException {
		ps = con.prepareStatement("INSERT [dbo].[NhaCungCap] ([maNCC], [tenNCC], [diaChi], [email], [sdt]) VALUES (?, ?, ?, ?, ?)");
		ps.setString(1, t.getMaNCC());
		ps.setString(2, t.getTenNCC());
		ps.setString(3, t.getDiaChi());
		ps.setString(4, t.getEmail());
		ps.setString(5, t.getsDT());
		return ps.executeUpdate() > 0;
	}

	public ArrayList<NhaCungCap> getAllListNhaCungCap() throws SQLException {
		ArrayList<NhaCungCap> list = new ArrayList<>();
		ps = con.prepareStatement("select maNCC, tenNCC, diaChi, email, sdt from NhaCungCap");
		rs = ps.executeQuery();
		while (rs.next()) {
			list.add(new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC"), rs.getString("diaChi"), rs.getString("email"), rs.getString("sdt")));
		}
		return list;
	}

	public NhaCungCap timNhaCungCap(String NCC) throws SQLException {
		ps = con.prepareStatement("select * from NhaCungCap where tenNCC = ?");
		ps.setString(1, NCC);
		rs = ps.executeQuery();
		return rs.next() ? new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC")) : null;
	}

	public boolean kiemTraTonTaiNCC(String ten) throws SQLException {
		ps = con.prepareStatement("select * from NhaCungCap where tenNCC = ?");
		ps.setString(1, ten);
		rs = ps.executeQuery();
		return rs.next();
	}
}