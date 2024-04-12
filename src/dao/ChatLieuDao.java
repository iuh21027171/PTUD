package dao;

import db.DBConnection;
import entity.ChatLieu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatLieuDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public ChatLieuDao() {
		con = DBConnection.getInstance().getConnection();
	}

	public List<ChatLieu> getListChatLieu() throws SQLException {
		List<ChatLieu> list = new ArrayList<>();
		ps = con.prepareStatement("SELECT maChatLieu, tenChatLieu FROM ChatLieu");
		rs = ps.executeQuery();
		while (rs.next()) {
			list.add(new ChatLieu(rs.getString("maChatLieu"), rs.getString("tenChatLieu")));
		}
		return list;
	}

	public boolean themChatLieu(ChatLieu l) throws SQLException {
		ps = con.prepareStatement("INSERT INTO ChatLieu (maChatLieu, tenChatLieu) VALUES (?, ?)");
		ps.setString(1, l.getMaChatLieu());
		ps.setString(2, l.getTenChatLieu());
		return ps.executeUpdate() > 0;
	}

	public ChatLieu timChatLieu(String tenChatLieu) throws SQLException {
		ps = con.prepareStatement("SELECT * FROM ChatLieu WHERE tenChatLieu = ?");
		ps.setString(1, tenChatLieu);
		rs = ps.executeQuery();
		return rs.next() ? new ChatLieu(rs.getString("maChatLieu"), rs.getString("tenChatLieu")) : null;
	}

	public List<ChatLieu> getChatLieu(String maChatLieu) throws SQLException {
		List<ChatLieu> dsCL = new ArrayList<>();
		ps = con.prepareStatement("SELECT * FROM ChatLieu WHERE maChatLieu = ?");
		ps.setString(1, maChatLieu);
		rs = ps.executeQuery();
		while (rs.next()) {
			dsCL.add(new ChatLieu(rs.getString("maChatLieu"), rs.getString("tenChatLieu")));
		}
		return dsCL;
	}

	public boolean kiemTraTonTaiChatLieu(String ten) throws SQLException {
		ps = con.prepareStatement("SELECT * FROM ChatLieu WHERE tenChatLieu = ?");
		ps.setString(1, ten);
		rs = ps.executeQuery();
		return rs.next();
	}
}