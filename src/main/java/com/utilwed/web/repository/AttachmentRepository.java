package com.utilwed.web.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.utilwed.web.Entity.community.Attachment;

public class AttachmentRepository extends BaseRepository{
	
	// 첨부파일 정보 삽입
	public void insertAttachment(Attachment attachment) throws SQLException{
		String sql = "INSERT INTO attachment (post_id, original_filename, unique_filename, filepath, file_size) "
				+ "VALUES (?, ?, ?, ?, ?)";
		
		try(Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, attachment.getPostId());
			pstmt.setString(2, attachment.getOriginalFilename());
			pstmt.setString(3, attachment.getUniqueFilename());
			pstmt.setString(4, attachment.getFilePath());
			pstmt.setLong(5, attachment.getFileSize());
			pstmt.executeUpdate();
			// 나중에 반환값 처리 생각
		}
	}
	
	// 특정 게시물의 첨부 파일 목록 조회
	public List<Attachment> getAttachmentsByPostId(int postId) throws SQLException{
		String sql = "SELECT id, post_id, original_filename, unique_filename, filepath, file_size, upload_date FROM attachment "
				   + "WHERE post_id = ?";
		List<Attachment> attachments = new ArrayList<Attachment>();
		
		try(Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setInt(1, postId);
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					attachments.add(new Attachment(
							rs.getInt("id"),
							rs.getInt("post_id"),
							rs.getString("original_filename"),
							rs.getString("unique_filename"),
							rs.getString("filepath"),
							rs.getLong("file_size"),
							rs.getTimestamp("upload_date")
					));
				}
			}
		}
		return attachments;
	}
	
	public Attachment getAttachmentByUniqueFilename(String uniqueFilename) throws SQLException{
		String sql = "SELECT id, post_id, original_filename, unique_filename, filepath, file_size, upload_date FROM attachment "
				   + "WHERE unique_filename = ?";
		Attachment attachment = null;
		
		try(Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, uniqueFilename);
			
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					attachment = new Attachment(
							rs.getInt("id"),
							rs.getInt("post_id"),
							rs.getString("original_filename"),
							rs.getString("unique_filename"),
							rs.getString("filepath"),
							rs.getLong("file_size"),
							rs.getTimestamp("upload_date")
					);
				}
			}
		}
		return attachment;
	}
	
	public void deleteAttachmentById(int id) throws SQLException{
		String sql = "DELETE FROM attachment WHERE id = ?";
		
		try(Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}
	}
	
}
