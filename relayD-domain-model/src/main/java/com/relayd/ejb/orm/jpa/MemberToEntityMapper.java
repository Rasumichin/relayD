package com.relayd.ejb.orm.jpa;

import com.relayd.Member;
import com.relayd.entity.MemberEntity;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   09.06.2017
 *
 */
public class MemberToEntityMapper {

	private MemberToEntityMapper() {
	}

	public static MemberToEntityMapper newInstance() {
		return new MemberToEntityMapper();
	}

	public void mapMemberToEntity(Member source, MemberEntity target) {
		if (source == null) {
			throw new IllegalArgumentException("[source] must not be 'null'!");
		}
		if (target == null) {
			throw new IllegalArgumentException("[target] must not be 'null'!");
		}

		target.setDuration(source.getDuration().toMillis());
	}
}