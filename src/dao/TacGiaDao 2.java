package dao;

<<<<<<< HEAD
import java.lang.reflect.Array;
=======
>>>>>>> 18a2abe (Update DAO & Service)
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.TacGia;

public class TacGiaDao {
	private Connection con;
<<<<<<< HEAD
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;

	public TacGiaDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public ArrayList<TacGia> getListTacGia() throws Exception {
		ArrayList<TacGia> list = new ArrayList<>();
		query = "SELECT maTacGia, tenTacGia\r\n" + "FROM     TacGia";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			TacGia t = new TacGia(rs.getString("maTacGia"), rs.getString("tenTacGia"));
			list.add(t);

=======
	private PreparedStatement ps;
	private ResultSet rs;

	public TacGiaDao() {
		con = DBConnection.getInstance().getConnection();
	}

	public List<TacGia> getListTacGia() throws SQLException {
		List<TacGia> list = new ArrayList<>();
		ps = con.prepareStatement("SELECT maTacGia, tenTacGia FROM TacGia");
		rs = ps.executeQuery();
		while (rs.next()) {
			list.add(new TacGia(rs.getString("maTacGia"), rs.getString("tenTacGia")));
>>>>>>> 18a2abe (Update DAO & Service)
		}
		return list;
	}

<<<<<<< HEAD
	public List<TacGia> getTacGia(String maTacGia) {
		List<TacGia> dsTG = new ArrayList<TacGia>();
		try {
			String query = "Select * from TacGia where maTacGia = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, maTacGia);
			rs = ps.executeQuery();
			while (rs.next()) {
				String matg = rs.getString("maTacGia");
				String tentg = rs.getString("tenTacGia");
				TacGia tg = new TacGia(matg, tentg);
				dsTG.add(tg);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
=======
	public List<TacGia> getTacGia(String maTacGia) throws SQLException {
		List<TacGia> dsTG = new ArrayList<>();
		ps = con.prepareStatement("Select * from TacGia where maTacGia = ?");
		ps.setString(1, maTacGia);
		rs = ps.executeQuery();
		while (rs.next()) {
			dsTG.add(new TacGia(rs.getString("maTacGia"), rs.getString("tenTacGia")));
>>>>>>> 18a2abe (Update DAO & Service)
		}
		return dsTG;
	}

<<<<<<< HEAD
	public boolean themTacGia(TacGia t) throws Exception {
		query = "INSERT [dbo].[TacGia] ([maTacGia], [tenTacGia]) VALUES ( ? , N'" + t.getTenTacGia() + "')";
		ps = con.prepareStatement(query);
		ps.setString(1, t.getMaTacGia());
		rsCheck = ps.executeUpdate();
		if (rsCheck != 0)
			return true;
		return false;
	}

	public boolean xoaTacGia(String maTacGia) {

		return false;
	}

	public TacGia timTacGia(String TacGia) throws SQLException {
		query = "select * from TacGia where tenTacGia = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, TacGia);
		rs = ps.executeQuery();
		while (rs.next()) {
			return new TacGia(rs.getString("maTacGia"), rs.getString("tenTacGia"));
		}
		return null;
	}
	public boolean kiemTraTonTaiTacGia(String ten) throws SQLException {
		query = "select * from TacGia where tenTacGia = N'"+ten+"'";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}
}
=======
	public boolean themTacGia(TacGia t) throws SQLException {
		ps = con.prepareStatement("INSERT [dbo].[TacGia] ([maTacGia], [tenTacGia]) VALUES (?, ?)");
		ps.setString(1, t.getMaTacGia());
		ps.setString(2, t.getTenTacGia());
		return ps.executeUpdate() != 0;
	}

	public TacGia timTacGia(String TacGia) throws SQLException {
		ps = con.prepareStatement("select * from TacGia where tenTacGia = ?");
		ps.setString(1, TacGia);
		rs = ps.executeQuery();
		return rs.next() ? new TacGia(rs.getString("maTacGia"), rs.getString("tenTacGia")) : null;
	}

	public boolean kiemTraTonTaiTacGia(String ten) throws SQLException {
		ps = con.prepareStatement("select * from TacGia where tenTacGia = ?");
		ps.setString(1, ten);
		rs = ps.executeQuery();
		return rs.next();
	}
}
>>>>>>> 18a2abe (Update DAO & Service)
