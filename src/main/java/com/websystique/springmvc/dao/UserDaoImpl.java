package com.websystique.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao implements UserDao {

	@Override
	public User findById(long id) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("id", id));
		return (User) criteria.uniqueResult();
	}

	@Override
	public User findByName(String name) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", name));
		return (User) criteria.uniqueResult();
	}

	@Override
	public void saveUser(User user) {
		persist(user);

	}

	@Override
	public void updateUser(User user) {
		getSession().update(user);

	}

	@Override
	public void deleteUserById(long id) {
		Query query = getSession().createSQLQuery("delete from Users where id = :id");
		query.setLong("id", id);
		query.executeUpdate();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsers() {
		Criteria criteria = getSession().createCriteria(User.class);
		return (List<User>) criteria.list();
	}

	@Override
	public void deleteAllUsers() {
		Query query = getSession().createSQLQuery("delete from Users");
		query.executeUpdate();
	}

	@Override
	public boolean isUserExist(User user) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", user.getUsername()));
		User result = (User) criteria.uniqueResult();
		if (result != null) {
			return true;
		}
		return false;
	}

}
