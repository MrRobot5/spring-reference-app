package org.demo.repository;

import org.demo.domain.Actor;

public interface ActorFinder {

	public int countOfActorsByFirstName(String firstName);

	public void save(Actor actor);

	/**
	 * 14.5.4 Using SqlParameterSource to provide parameter values
	 * 
	 * 2014年9月21日 上午9:58:52
	 * @param p
	 */
	public void saveAdvance(Actor p);

}
