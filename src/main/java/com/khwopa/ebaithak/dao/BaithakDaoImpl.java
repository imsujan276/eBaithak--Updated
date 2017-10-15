package com.khwopa.ebaithak.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.khwopa.ebaithak.models.Baithak;

@Repository
public class BaithakDaoImpl implements BaithakDao {

	//hibernate object
	@Resource
	private SessionFactory sessionFctory;
	
	//jdbc object
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public UserDao uDao;
	
	@Override
	public void createBaithak(Baithak b) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String insertSql = "insert into `baithak`(created_by, discription, image, name) values(?,?,?,?)";
		
		jdbcTemplate.update(insertSql,new Object[]{b.getCreated_by(), b.getDiscription(), b.getImage(), b.getName()});
		
//		Notification notif = new Notification();
//		notif.setUserId(b.getCreated_by());
//		String message = "Baithak (<b>"+b.getName()+")</b> has been created.";
//		notif.setMessage(message);
//		// Mon Jul 17 16:45:16 
//		notif.setCreated_at(new Date().toString().substring(0, 20));
//		session.save(notif);
	
	}

	@Override
	public boolean deletebaithak(long id) {
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		String sql = "DELETE FROM `baithak` WHERE id = '"+id+"' ";
		System.out.println(sql);
		template.execute(sql);
		return true;
		
	}

	@Override
	public List<Baithak> getAllBaithak(long createrId) {
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		
		List<Baithak> baithakList = new ArrayList<Baithak>();
		
		String sql = "Select * from baithak where created_by = '"+createrId+"' order by created_at desc";
		//System.out.println(sql);
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (Map<?, ?> row : rows) {
			
			Baithak baithak = new Baithak();

				baithak.setId((Long)row.get("id"));
				baithak.setName((String) row.get("name"));
				baithak.setDiscription((String) row.get("discription"));
				baithak.setImage((String) row.get("image"));
				baithak.setCreated_by((Long) row.get("created_by"));

				baithakList.add(baithak);
		}
		
		return baithakList;

	}

	@Override
	public Baithak getBaithak(Long id) {
		
		Session session = sessionFctory.openSession();
		Baithak baithak= (Baithak) session.get(Baithak.class, id);
		session.close();
		return baithak;
	}

	@Override
	public long getBaithakId(String b) {
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		String sql = "SELECT id FROM baithak WHERE name='"+b+"' ";
		Long bId = template.queryForObject(sql, Long.class);
		return bId;	
	}

}
